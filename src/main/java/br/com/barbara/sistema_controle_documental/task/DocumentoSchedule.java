package br.com.barbara.sistema_controle_documental.task;

import br.com.barbara.sistema_controle_documental.model.Documento;
import br.com.barbara.sistema_controle_documental.model.enuns.StatusDocumento;
import br.com.barbara.sistema_controle_documental.model.records.DadosEmail;
import br.com.barbara.sistema_controle_documental.repository.DocumentoRepository;
import br.com.barbara.sistema_controle_documental.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Component
public class DocumentoSchedule {
    private final DocumentoRepository documentoRepository;
    private final EmailService emailService;

    public DocumentoSchedule(DocumentoRepository documentoRepository, EmailService emailService){
        this.documentoRepository = documentoRepository;
        this.emailService = emailService;
    }

    private final Logger logger = LoggerFactory.getLogger(DocumentoSchedule.class.getName());

    @Scheduled(cron = "0 0 1 * * *")
    @Transactional
    public void checkDocumentos(){
        List<Integer> datasAviso = List.of(30, 15);
        LocalDate dataHoje = LocalDate.now();

        logger.info("Iniciando processamento do Schedule ...");

        logger.info("Verificando documentos vencidos ...");
        List<Documento> vencidos = documentoRepository
                .findVencidosComUsuario(dataHoje, StatusDocumento.VALIDO);
        vencidos.forEach(
                documento -> {
                    documento.setStatus(StatusDocumento.EXPIRADO);
                    enviarEmail(documento,"Aviso: Documento Expirado", true);
                });

        for (Integer dias : datasAviso) {
            LocalDate dataAlvo = dataHoje.plusDays(dias);
            logger.info("Verificando documentos que vencem em {} dias ...", dias);

            List<Documento> pertoVencimento = documentoRepository
                    .findByDataVencimentoAndStatus(dataAlvo, StatusDocumento.VALIDO);

            pertoVencimento.forEach(documento -> {
                String assunto = "Aviso: Vencimento em " + dias + " dias";
                enviarEmail(documento, assunto, false);
            });
        }

        logger.info("Tarefa finalizada.");
    }

    private void enviarEmail(Documento documento, String assunto, boolean isVencido){
        DadosEmail dados = documento.getDados();

        if (dados == null) {
            logger.warn("Documento {} falta informações sobre propriedade ou responsável.", documento.getId());
            return;
        }

        StringBuilder mensagem = new StringBuilder();
        mensagem.append("Olá ").append(dados.nome()).append(",\n\n");

        if (isVencido) {
            mensagem.append("O prazo de validade do documento ").append(dados.doc())
                    .append(" da propriedade ").append(dados.propriedade()).append(" VENCEU.\n")
                    .append("Providencie a regularização para evitar multas.");
        } else {
            mensagem.append("Lembramos que o documento ").append(dados.doc())
                    .append(" da propriedade ").append(dados.propriedade())
                    .append(" vencerá em breve.\n")
                    .append("Data de vencimento: ").append(documento.getDataVencimento())
                    .append("Regularize com antecedência para evitar multas.");
        }

        mensagem.append("\n\nEste é um e-mail automático, por favor não responda.");

        try {
            emailService.enviarEmailService(dados.email(), assunto, mensagem.toString());
            logger.info("E-mail enviado ({}) para: {}", assunto, dados.email());
        } catch (Exception e) {
            logger.error("Falha ao enviar e-mail do documento {}", documento.getId(), e);
        }
    }
}
