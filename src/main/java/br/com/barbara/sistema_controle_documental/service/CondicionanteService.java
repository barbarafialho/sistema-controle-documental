package br.com.barbara.sistema_controle_documental.service;

import br.com.barbara.sistema_controle_documental.dto.CondicionanteRequestDTO;
import br.com.barbara.sistema_controle_documental.dto.CondicionanteResponseDTO;
import br.com.barbara.sistema_controle_documental.exceptions.ResourceNotFoundException;
import br.com.barbara.sistema_controle_documental.mapper.CondicionanteMapper;
import br.com.barbara.sistema_controle_documental.model.Condicionante;
import br.com.barbara.sistema_controle_documental.model.Documento;
import br.com.barbara.sistema_controle_documental.repository.CondicionanteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CondicionanteService {
    private final CondicionanteRepository condicionanteRepository;
    private final CondicionanteMapper condicionanteMapper;

    public CondicionanteService(CondicionanteRepository condicionanteRepository, CondicionanteMapper condicionanteMapper) {
        this.condicionanteRepository = condicionanteRepository;
        this.condicionanteMapper = condicionanteMapper;
    }

    public List<CondicionanteResponseDTO> findByDocumento(Long id) {
        var listaEntidades = condicionanteRepository.findByDocumentoId(id);
        return condicionanteMapper.toResponseDTOList(listaEntidades);
    }

    public CondicionanteResponseDTO findById(Long id) {
        var entidade = condicionanteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Condicionante não encontrada"));
        return condicionanteMapper.toResponseDTO(entidade);
    }

    @Transactional
    public CondicionanteResponseDTO save(CondicionanteRequestDTO dto) {
        var entidade = condicionanteRepository.save(condicionanteMapper.toEntity(dto));
        return condicionanteMapper.toResponseDTO(entidade);
    }

    @Transactional
    public CondicionanteResponseDTO update(Long id, CondicionanteRequestDTO dto) {
        Condicionante condicionanteBD = condicionanteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Condicionante não encontrada"));

        var entidade = condicionanteRepository.save(condicionanteMapper.updateEntityFromDto(dto, condicionanteBD));
        return condicionanteMapper.toResponseDTO(entidade);
    }

    @Transactional
    public void delete(Long id) {
        var entidade = condicionanteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Condicionante não encontrada"));
        condicionanteRepository.delete(entidade);
    }
}