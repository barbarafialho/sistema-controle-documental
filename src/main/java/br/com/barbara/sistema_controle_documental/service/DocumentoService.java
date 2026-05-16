package br.com.barbara.sistema_controle_documental.service;

import br.com.barbara.sistema_controle_documental.dto.DocumentoRequestDTO;
import br.com.barbara.sistema_controle_documental.dto.DocumentoResponseDTO;
import br.com.barbara.sistema_controle_documental.exceptions.FileStorageException;
import br.com.barbara.sistema_controle_documental.exceptions.ResourceNotFoundException;
import br.com.barbara.sistema_controle_documental.mapper.DocumentoMapper;
import br.com.barbara.sistema_controle_documental.model.Documento;
import br.com.barbara.sistema_controle_documental.model.Propriedade;
import br.com.barbara.sistema_controle_documental.model.Usuario;
import br.com.barbara.sistema_controle_documental.model.enuns.Role;
import br.com.barbara.sistema_controle_documental.model.enuns.StatusDocumento;
import br.com.barbara.sistema_controle_documental.repository.DocumentoRepository;
import br.com.barbara.sistema_controle_documental.repository.PropriedadeRepository;
import br.com.barbara.sistema_controle_documental.service.component.ValidarPermissao;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.util.List;

@Service
public class DocumentoService {

    private final DocumentoRepository documentoRepository;
    private final DocumentoMapper documentoMapper;
    private final ArquivoService arquivoService;
    private final PropriedadeRepository propriedadeRepository;
    private final ValidarPermissao validarPermissao;

    public DocumentoService(DocumentoRepository documentoRepository, DocumentoMapper documentoMapper, ArquivoService arquivoService, PropriedadeRepository propriedadeRepository, ValidarPermissao validarPermissao) {
        this.documentoRepository = documentoRepository;
        this.documentoMapper = documentoMapper;
        this.arquivoService = arquivoService;
        this.propriedadeRepository = propriedadeRepository;
        this.validarPermissao = validarPermissao;
    }

    public List<DocumentoResponseDTO> listarTodos(Usuario usuarioLogado) {
        List<Documento> listaEntidades;
        if (usuarioLogado.getRole() == Role.ADMIN) {
            return documentoMapper.toResponseDTOList(documentoRepository.findAll());
        }
        listaEntidades = documentoRepository.findUsuarioLogado(usuarioLogado.getId());
        return documentoMapper.toResponseDTOList(listaEntidades);
    }

    public DocumentoResponseDTO listarPorId(Long id, Usuario usuarioLogado) {
        var entidade = documentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para esse ID."));

        validarPermissao.validar(entidade, usuarioLogado);

        return documentoMapper.toResponseDTO(entidade);
    }

    @Transactional
    public DocumentoResponseDTO salvar(DocumentoRequestDTO dto, MultipartFile arquivo, Usuario usuarioLogado) {
        // Busca a propriedade para validar o dono
        var propriedade = propriedadeRepository.findById(dto.propriedadeId())
                .orElseThrow(() -> new ResourceNotFoundException("Nenhuma propriedade foi encontrada."));

        validarPermissao.validar(propriedade, usuarioLogado);

        var entidade = documentoMapper.toEntity(dto);
        entidade.setPropriedade(propriedade);

        if (arquivo == null || arquivo.isEmpty()) {
            throw new FileStorageException("É obrigatório a inclusão de um arquivo para o documento.");
        }
        // Salva o arquivo e seta o caminho
        String caminhoArquivo = arquivoService.salvarArquivo(arquivo);
        entidade.setCaminhoArquivo(caminhoArquivo);

        // Status inicial baseado no vencimento
        entidade.setStatus(calculaStatus(entidade.getDataVencimento()));

        entidade = documentoRepository.save(entidade);
        return documentoMapper.toResponseDTO(entidade);
    }

    @Transactional
    public DocumentoResponseDTO atualizar(Long id, DocumentoRequestDTO dto, MultipartFile arquivo, Usuario usuarioLogado) {
        var documentoBD = documentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para esse ID."));

        validarPermissao.validar(documentoBD, usuarioLogado);

        var entidade = documentoMapper.updateEntityFromDto(dto, documentoBD);

        // Se enviou arquivo novo, apaga o antigo e salva o novo
        if (arquivo != null && !arquivo.isEmpty()) {
            if (documentoBD.getCaminhoArquivo() != null) {
                arquivoService.excluirArquivo(documentoBD.getCaminhoArquivo());
            }
            String novoCaminho = arquivoService.salvarArquivo(arquivo);
            entidade.setCaminhoArquivo(novoCaminho);
        }

        // Recalcula o status caso a data de vencimento tenha mudado
        entidade.setStatus(calculaStatus(entidade.getDataVencimento()));

        entidade = documentoRepository.save(entidade);
        return documentoMapper.toResponseDTO(entidade);
    }

    @Transactional
    public void excluir(Long id, Usuario usuarioLogado) {
        var entidade = documentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para esse ID."));

        validarPermissao.validar(entidade, usuarioLogado);

        // Apaga o arquivo físico primeiro
        if (entidade.getCaminhoArquivo() != null) {
            arquivoService.excluirArquivo(entidade.getCaminhoArquivo());
        }

        documentoRepository.delete(entidade);
    }

    public Resource baixarArquivo(Long id, Usuario usuarioLogado) {
        var entidade = documentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para esse ID."));

        validarPermissao.validar(entidade, usuarioLogado);

        if (entidade.getCaminhoArquivo() == null) {
            throw new ResourceNotFoundException("Este documento não possui arquivo anexado.");
        }

        return arquivoService.carregarArquivo(entidade.getCaminhoArquivo());
    }

    private StatusDocumento calculaStatus(LocalDate dataVencimento) {
        return dataVencimento.isBefore(LocalDate.now()) ?
                StatusDocumento.EXPIRADO : StatusDocumento.VALIDO;
    }
}
