package br.com.barbara.sistema_controle_documental.service;

import br.com.barbara.sistema_controle_documental.dto.PerfilDTO;
import br.com.barbara.sistema_controle_documental.dto.UsuarioRequestDTO;
import br.com.barbara.sistema_controle_documental.dto.UsuarioResponseDTO;
import br.com.barbara.sistema_controle_documental.exceptions.ResourceNotFoundException;
import br.com.barbara.sistema_controle_documental.mapper.UsuarioMapper;
import br.com.barbara.sistema_controle_documental.model.Usuario;
import br.com.barbara.sistema_controle_documental.repository.UsuarioRepository;
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
        Usuario entidade = usuarioMapper.toEntity(dto);

        entidade.setSenha(passwordEncoder.encode(entidade.getSenha()));
        entidade.setAtivo(true);

        var entidadeSalva = usuarioRepository.save(entidade);
        return usuarioMapper.toResponseDTO(entidadeSalva);
    }

    @Transactional
    public UsuarioResponseDTO atualizarPerfil(PerfilDTO dto, Usuario usuarioLogado) {
        // usuarioLogado que o SecurityFilter recuperou do banco
        usuarioLogado.setNome(dto.nome());
        usuarioLogado.setEmail(dto.email());
        usuarioLogado.setCpfCnpj(dto.cpfCnpj());

        return usuarioMapper.toResponseDTO(usuarioRepository.save(usuarioLogado));
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