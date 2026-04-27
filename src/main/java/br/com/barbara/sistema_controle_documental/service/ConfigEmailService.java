package br.com.barbara.sistema_controle_documental.service;

import br.com.barbara.sistema_controle_documental.dto.ConfigEmailRequestDTO;
import br.com.barbara.sistema_controle_documental.dto.ConfigEmailResponseDTO;
import br.com.barbara.sistema_controle_documental.exceptions.ResourceNotFoundException;
import br.com.barbara.sistema_controle_documental.mapper.ConfigEmailMapper;
import br.com.barbara.sistema_controle_documental.repository.ConfigEmailRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ConfigEmailService {
    private final ConfigEmailRepository repository;
    private final ConfigEmailMapper mapper;

    public ConfigEmailService(ConfigEmailRepository repository, ConfigEmailMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<ConfigEmailResponseDTO> findAll() {
        return mapper.toResponseDTOList(repository.findAll());
    }

    public ConfigEmailResponseDTO findById(Long id) {
        var entidade = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Configuração não encontrada"));
        return mapper.toResponseDTO(entidade);
    }

    @Transactional
    public ConfigEmailResponseDTO save(ConfigEmailRequestDTO dto) {
        var entidade = mapper.toEntity(dto);
        entidade.setAtualizadoEm(LocalDateTime.now()); // Seta a data na criação
        return mapper.toResponseDTO(repository.save(entidade));
    }

    @Transactional
    public ConfigEmailResponseDTO update(Long id, ConfigEmailRequestDTO dto) {
        var configBD = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Configuração não encontrada"));

        var entidade = mapper.updateEntityFromDto(dto, configBD);
        entidade.setAtualizadoEm(LocalDateTime.now()); // Atualiza a data na edição

        return mapper.toResponseDTO(repository.save(entidade));
    }

    @Transactional
    public void delete(Long id) {
        var entidade = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Configuração não encontrada"));
        repository.delete(entidade);
    }
}
