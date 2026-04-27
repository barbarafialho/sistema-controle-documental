package br.com.barbara.sistema_controle_documental.service;

import br.com.barbara.sistema_controle_documental.exceptions.ResourceNotFoundException;
import br.com.barbara.sistema_controle_documental.model.ConfigServidorEmail;
import br.com.barbara.sistema_controle_documental.repository.ConfigEmailRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class EmailService {
    private final ConfigEmailRepository emailRepository;

    public EmailService(ConfigEmailRepository emailRepository){
        this.emailRepository = emailRepository;
    }

    public void enviarEmailService(String destinatario, String assunto, String mensagem){
            ConfigServidorEmail config = emailRepository.findFirstByOrderByAtualizadoEmDesc()
                    .orElseThrow(()-> new ResourceNotFoundException("Nenhuma configuração encontrada no banco"));

            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
            mailSender.setHost(config.getHost());
            mailSender.setPort(Integer.parseInt(config.getPorta()));
            mailSender.setUsername(config.getNomeUsuario());
            mailSender.setPassword(config.getSenha());
            mailSender.setProtocol(config.getProtocolo());

            Properties props = mailSender.getJavaMailProperties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");

            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(config.getNomeUsuario());
            simpleMailMessage.setTo(destinatario);
            simpleMailMessage.setSubject(assunto);
            simpleMailMessage.setText(mensagem);
            mailSender.send(simpleMailMessage);
    }
}
