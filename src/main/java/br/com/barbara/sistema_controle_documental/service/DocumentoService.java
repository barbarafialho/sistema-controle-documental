package br.com.barbara.sistema_controle_documental.service;

import br.com.barbara.sistema_controle_documental.dto.DocumentoRequestDTO;
import br.com.barbara.sistema_controle_documental.dto.DocumentoResponseDTO;
import br.com.barbara.sistema_controle_documental.exceptions.ResourceNotFoundException;
import br.com.barbara.sistema_controle_documental.mapper.DocumentoMapper;
import br.com.barbara.sistema_controle_documental.model.Documento;
import br.com.barbara.sistema_controle_documental.model.enuns.StatusDocumento;
import br.com.barbara.sistema_controle_documental.repository.DocumentoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class DocumentoService {

    private final DocumentoRepository documentoRepository;
    private final DocumentoMapper documentoMapper;

    public DocumentoService(DocumentoRepository documentoRepository, DocumentoMapper documentoMapper) {
        this.documentoRepository = documentoRepository;
        this.documentoMapper = documentoMapper;
    }

    public List<DocumentoResponseDTO> findAll(){
        var listaEntidades = documentoRepository.findAll();
        return documentoMapper.toResponseDTOList(listaEntidades);
    }

    public DocumentoResponseDTO findById(Long id){
        var entidade = documentoRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para esse ID"));

        return documentoMapper.toResponseDTO(entidade);
    }

    @Transactional
    public DocumentoResponseDTO save(DocumentoRequestDTO documentoRequestDTO){
        var entidade = documentoMapper.toEntity(documentoRequestDTO);

        // Checa a data de validade do documento
        if (entidade.getDataVencimento().isBefore(LocalDate.now())) {
            entidade.setStatus(StatusDocumento.EXPIRADO);
        }
        else {
            entidade.setStatus(StatusDocumento.VALIDO);
        }

        entidade = documentoRepository.save(entidade);
        return documentoMapper.toResponseDTO(entidade);
    }

    @Transactional
    public DocumentoResponseDTO update(Long id, DocumentoRequestDTO documentoRequestDTO){
        Documento documentoBD = documentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para esse ID"));

        var entidade = documentoRepository.save(documentoMapper.updateEntityFromDto
                (documentoRequestDTO, documentoBD));
        return documentoMapper.toResponseDTO(entidade);
    }

    @Transactional
    public void delete(Long id){
        documentoRepository.delete(documentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para esse ID")));
    }
}
