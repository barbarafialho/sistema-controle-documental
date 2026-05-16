package br.com.barbara.sistema_controle_documental.service;

import br.com.barbara.sistema_controle_documental.dto.PerfilResponseDTO;
import br.com.barbara.sistema_controle_documental.dto.UsuarioRequestDTO;
import br.com.barbara.sistema_controle_documental.dto.UsuarioResponseDTO;
import br.com.barbara.sistema_controle_documental.exceptions.ResourceNotFoundException;
import br.com.barbara.sistema_controle_documental.mapper.UsuarioMapper;
import br.com.barbara.sistema_controle_documental.model.Usuario;
import br.com.barbara.sistema_controle_documental.repository.UsuarioRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository,
                          UsuarioMapper usuarioMapper,
                          PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UsuarioResponseDTO> listarTodos() {
        var listaEntidades = usuarioRepository.findAll();
        return usuarioMapper.toResponseDTOList(listaEntidades);
    }

    public UsuarioResponseDTO listarPorId(Long id) {
        var entidade = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        return usuarioMapper.toResponseDTO(entidade);
    }

    @Transactional
    public UsuarioResponseDTO salvar(UsuarioRequestDTO dto) {
        if (usuarioRepository.existsByEmail(dto.email())) {
            throw new DataIntegrityViolationException("Este e-mail já está cadastrado.");
        }
        if (usuarioRepository.existsByCpfCnpj(dto.cpfCnpj())) {
            throw new DataIntegrityViolationException("Este CPF/CNPJ já está cadastrado.");
        }

        var entidade = usuarioMapper.toEntity(dto);
        entidade.setSenha(passwordEncoder.encode(entidade.getSenha()));
        entidade.setAtivo(true);

        return usuarioMapper.toResponseDTO(usuarioRepository.save(entidade));
    }

    @Transactional
    public PerfilResponseDTO atualizarPerfil(PerfilResponseDTO dto, Usuario usuarioLogado) {
        // usuarioLogado que o SecurityFilter recuperou do banco
        var usuarioBD = usuarioRepository.findById(usuarioLogado.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        // Validação de Email Duplicado
        if (!dto.email().equals(usuarioBD.getEmail()) && usuarioRepository.existsByEmail(dto.email())) {
            throw new DataIntegrityViolationException("Este e-mail já está sendo usado por outro usuário.");
        }

        // Validação de CPF/CNPJ Duplicado
        if (!dto.cpfCnpj().equals(usuarioBD.getCpfCnpj()) && usuarioRepository.existsByCpfCnpj(dto.cpfCnpj())) {
            throw new DataIntegrityViolationException("Este CPF/CNPJ já está cadastrado.");
        }

        var entidade = usuarioMapper.updateEntityFromDto(dto, usuarioBD);

        entidade = usuarioRepository.save(entidade);
        return usuarioMapper.toPerfilResponseDTO(entidade);
    }

    @Transactional
    // Método de Soft Delete
    public void desativar(Long id) {
        var entidade = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        entidade.setAtivo(false);
        usuarioRepository.save(entidade);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o email: " + username));
    }
}