package br.com.barbara.sistema_controle_documental.service;

import br.com.barbara.sistema_controle_documental.dto.CondicionanteRequestDTO;
import br.com.barbara.sistema_controle_documental.dto.CondicionanteResponseDTO;
import br.com.barbara.sistema_controle_documental.exceptions.FileStorageException;
import br.com.barbara.sistema_controle_documental.exceptions.ResourceNotFoundException;
import br.com.barbara.sistema_controle_documental.mapper.CondicionanteMapper;
import br.com.barbara.sistema_controle_documental.model.Condicionante;
import br.com.barbara.sistema_controle_documental.model.Documento;
import br.com.barbara.sistema_controle_documental.model.enuns.Role;
import br.com.barbara.sistema_controle_documental.model.Usuario;
import br.com.barbara.sistema_controle_documental.repository.CondicionanteRepository;
import br.com.barbara.sistema_controle_documental.repository.DocumentoRepository;
import br.com.barbara.sistema_controle_documental.service.component.ValidarPermissao;
import org.springframework.core.io.Resource;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class CondicionanteService {
    private final CondicionanteRepository condicionanteRepository;
    private final DocumentoRepository documentoRepository;
    private final CondicionanteMapper condicionanteMapper;
    private final ArquivoService arquivoService;
    private final ValidarPermissao validarPermissao;

    public CondicionanteService(CondicionanteRepository condicionanteRepository,
                                DocumentoRepository documentoRepository,
                                CondicionanteMapper condicionanteMapper,
                                ArquivoService arquivoService, ValidarPermissao validarPermissao) {
        this.condicionanteRepository = condicionanteRepository;
        this.documentoRepository = documentoRepository;
        this.condicionanteMapper = condicionanteMapper;
        this.arquivoService = arquivoService;
        this.validarPermissao = validarPermissao;
    }

    public List<CondicionanteResponseDTO> listarPorDocumento(Long documentoId, Usuario usuarioLogado) {
        var documento = documentoRepository.findById(documentoId)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para esse ID."));

        validarPermissao.validar(documento, usuarioLogado);

        List<Condicionante> listaEntidades = condicionanteRepository.findByDocumentoId(documentoId);
        return condicionanteMapper.toResponseDTOList(listaEntidades);
    }

    public CondicionanteResponseDTO listarPorId(Long id, Usuario usuarioLogado) {
        var entidade = condicionanteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para esse ID."));

        validarPermissao.validar(entidade, usuarioLogado);

        return condicionanteMapper.toResponseDTO(entidade);
    }

    @Transactional
    public CondicionanteResponseDTO salvar(CondicionanteRequestDTO dto, MultipartFile arquivo, Usuario usuarioLogado) {
        // Busca o documento para validar o dono
        var documento = documentoRepository.findById(dto.documentoId())
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum documento foi encontrado."));

        validarPermissao.validar(documento, usuarioLogado);

        var entidade = condicionanteMapper.toEntity(dto);
        entidade.setDocumento(documento);

        if (arquivo == null || arquivo.isEmpty()) {
            throw new FileStorageException("É obrigatório a inclusão de um arquivo para o documento.");
        }
        // Salva o arquivo e seta o caminho
        String caminhoArquivo = arquivoService.salvarArquivo(arquivo);
        entidade.setCaminhoArquivo(caminhoArquivo);

        entidade = condicionanteRepository.save(entidade);
        return condicionanteMapper.toResponseDTO(entidade);
    }

    @Transactional
    public CondicionanteResponseDTO atualizar(Long id, CondicionanteRequestDTO dto, MultipartFile arquivo, Usuario usuarioLogado) {
        var condicionanteBD = condicionanteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para esse ID."));

        validarPermissao.validar(condicionanteBD, usuarioLogado);

        var entidade = condicionanteMapper.updateEntityFromDto(dto, condicionanteBD);

        // Se enviou arquivo novo, apaga o antigo e salva o novo
        if (arquivo != null && !arquivo.isEmpty()) {
            if (condicionanteBD.getCaminhoArquivo() != null) {
                arquivoService.excluirArquivo(condicionanteBD.getCaminhoArquivo());
            }
            String novoCaminho = arquivoService.salvarArquivo(arquivo);
            entidade.setCaminhoArquivo(novoCaminho);
        }

        entidade = condicionanteRepository.save(entidade);
        return condicionanteMapper.toResponseDTO(entidade);
    }

    @Transactional
    public void excluir(Long id, Usuario usuarioLogado) {
        var entidade = condicionanteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para esse ID."));

        validarPermissao.validar(entidade, usuarioLogado);

        // Apaga o arquivo físico primeiro
        if (entidade.getCaminhoArquivo() != null) {
            arquivoService.excluirArquivo(entidade.getCaminhoArquivo());
        }

        condicionanteRepository.delete(entidade);
    }

    public Resource baixarArquivo(Long id, Usuario usuarioLogado) {
        var entidade = condicionanteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para esse ID."));

        validarPermissao.validar(entidade, usuarioLogado);

        if (entidade.getCaminhoArquivo() == null) {
            throw new ResourceNotFoundException("Esta condicionante não possui arquivo anexado.");
        }

        return arquivoService.carregarArquivo(entidade.getCaminhoArquivo());
    }
}