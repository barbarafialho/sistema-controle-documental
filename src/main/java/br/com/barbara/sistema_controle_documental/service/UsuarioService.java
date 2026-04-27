package br.com.barbara.sistema_controle_documental.service;

import br.com.barbara.sistema_controle_documental.dto.UsuarioRequestDTO;
import br.com.barbara.sistema_controle_documental.dto.UsuarioResponseDTO;
import br.com.barbara.sistema_controle_documental.exceptions.ResourceNotFoundException;
import br.com.barbara.sistema_controle_documental.mapper.UsuarioMapper;
import br.com.barbara.sistema_controle_documental.model.Usuario;
import br.com.barbara.sistema_controle_documental.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    public List<UsuarioResponseDTO> findAll() {
        var listaEntidades = usuarioRepository.findAll();
        return usuarioMapper.toResponseDTOList(listaEntidades);
    }

    public UsuarioResponseDTO findById(Long id) {
        var entidade = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        return usuarioMapper.toResponseDTO(entidade);
    }

    @Transactional
    public UsuarioResponseDTO save(UsuarioRequestDTO dto) {
        var entidade = usuarioRepository.save(usuarioMapper.toEntity(dto));
        return usuarioMapper.toResponseDTO(entidade);
    }

    @Transactional
    public UsuarioResponseDTO update(Long id, UsuarioRequestDTO dto) {
        Usuario usuarioBD = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        var entidade = usuarioRepository.save(usuarioMapper.updateEntityFromDto(dto, usuarioBD));
        return usuarioMapper.toResponseDTO(entidade);
    }

    @Transactional
    public void delete(Long id) {
        var entidade = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        usuarioRepository.delete(entidade);
    }
}