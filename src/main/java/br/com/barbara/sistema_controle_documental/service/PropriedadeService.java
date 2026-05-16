package br.com.barbara.sistema_controle_documental.service;

import br.com.barbara.sistema_controle_documental.dto.PropriedadeRequestDTO;
import br.com.barbara.sistema_controle_documental.dto.PropriedadeResponseDTO;
import br.com.barbara.sistema_controle_documental.exceptions.ResourceNotFoundException;
import br.com.barbara.sistema_controle_documental.mapper.PropriedadeMapper;
import br.com.barbara.sistema_controle_documental.model.Propriedade;
import br.com.barbara.sistema_controle_documental.model.Usuario;
import br.com.barbara.sistema_controle_documental.model.enuns.Role;
import br.com.barbara.sistema_controle_documental.repository.PropriedadeRepository;
import br.com.barbara.sistema_controle_documental.service.component.ValidarPermissao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Service
public class PropriedadeService {
    private final PropriedadeRepository propriedadeRepository;
    private final PropriedadeMapper propriedadeMapper;
    private final ValidarPermissao validarPermissao;

    public PropriedadeService(PropriedadeRepository propriedadeRepository, PropriedadeMapper propriedadeMapper, ValidarPermissao validarPermissao) {
        this.propriedadeRepository = propriedadeRepository;
        this.propriedadeMapper = propriedadeMapper;
        this.validarPermissao = validarPermissao;
    }

    public List<PropriedadeResponseDTO> listarTodos(Usuario usuarioLogado) {
        List<Propriedade> listaEntidades;
        if (usuarioLogado.getRole() == Role.ADMIN) {
            return propriedadeMapper.toResponseDTOList(propriedadeRepository.findAll());
        }
        listaEntidades = propriedadeRepository.findUsuarioLogado(usuarioLogado.getId());
        return propriedadeMapper.toResponseDTOList(listaEntidades);
    }

    public PropriedadeResponseDTO listarPorId(Long id, Usuario usuarioLogado) {
        var entidade = propriedadeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para esse ID."));

        validarPermissao.validar(entidade, usuarioLogado);

        return propriedadeMapper.toResponseDTO(entidade);
    }

    @Transactional
    public PropriedadeResponseDTO salvar(PropriedadeRequestDTO dto, Usuario usuarioLogado) {
        var entidade = propriedadeMapper.toEntity(dto);

        // Define o usuário logado como dono da propriedade automaticamente
        entidade.setUsuario(usuarioLogado);

        entidade = propriedadeRepository.save(entidade);
        return propriedadeMapper.toResponseDTO(entidade);
    }

    @Transactional
    public PropriedadeResponseDTO atualizar(Long id, PropriedadeRequestDTO dto, Usuario usuarioLogado) {
        var propriedadeBD = propriedadeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para esse ID."));

        validarPermissao.validar(propriedadeBD, usuarioLogado);

        var entidade = propriedadeMapper.updateEntityFromDto(dto, propriedadeBD);

        entidade = propriedadeRepository.save(entidade);
        return propriedadeMapper.toResponseDTO(entidade);
    }

    @Transactional
    public void excluir(Long id, Usuario usuarioLogado) {
        var entidade = propriedadeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para esse ID."));

        validarPermissao.validar(entidade, usuarioLogado);

        propriedadeRepository.delete(entidade);
    }
}