package br.com.barbara.sistema_controle_documental.service;

import br.com.barbara.sistema_controle_documental.dto.PropriedadeRequestDTO;
import br.com.barbara.sistema_controle_documental.dto.PropriedadeResponseDTO;
import br.com.barbara.sistema_controle_documental.exceptions.ResourceNotFoundException;
import br.com.barbara.sistema_controle_documental.mapper.PropriedadeMapper;
import br.com.barbara.sistema_controle_documental.model.Propriedade;
import br.com.barbara.sistema_controle_documental.repository.PropriedadeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PropriedadeService {
    private final PropriedadeRepository propriedadeRepository;
    private final PropriedadeMapper propriedadeMapper;

    public PropriedadeService(PropriedadeRepository propriedadeRepository, PropriedadeMapper propriedadeMapper) {
        this.propriedadeRepository = propriedadeRepository;
        this.propriedadeMapper = propriedadeMapper;
    }

    public List<PropriedadeResponseDTO> findAll() {
        var listaEntidades = propriedadeRepository.findAll();
        return propriedadeMapper.toResponseDTOList(listaEntidades);
    }

    public PropriedadeResponseDTO findById(Long id) {
        var entidade = propriedadeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Propriedade não encontrada"));
        return propriedadeMapper.toResponseDTO(entidade);
    }

    @Transactional
    public PropriedadeResponseDTO save(PropriedadeRequestDTO dto) {
        var entidade = propriedadeRepository.save(propriedadeMapper.toEntity(dto));
        return propriedadeMapper.toResponseDTO(entidade);
    }

    @Transactional
    public PropriedadeResponseDTO update(Long id, PropriedadeRequestDTO dto) {
        Propriedade propriedadeBD = propriedadeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Propriedade não encontrada"));

        var entidade = propriedadeRepository.save(propriedadeMapper.updateEntityFromDto(dto, propriedadeBD));
        return propriedadeMapper.toResponseDTO(entidade);
    }

    @Transactional
    public void delete(Long id) {
        var entidade = propriedadeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Propriedade não encontrada"));
        propriedadeRepository.delete(entidade);
    }
}