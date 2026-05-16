package br.com.barbara.sistema_controle_documental.mapper;

import br.com.barbara.sistema_controle_documental.dto.PerfilResponseDTO;
import br.com.barbara.sistema_controle_documental.dto.UsuarioRequestDTO;
import br.com.barbara.sistema_controle_documental.dto.UsuarioResponseDTO;
import br.com.barbara.sistema_controle_documental.model.Usuario;
import br.com.barbara.sistema_controle_documental.model.enuns.Role;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-15T21:24:12-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class UsuarioMapperImpl implements UsuarioMapper {

    @Override
    public Usuario toEntity(UsuarioRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setNome( dto.nome() );
        usuario.setEmail( dto.email() );
        usuario.setSenha( dto.senha() );
        usuario.setCpfCnpj( dto.cpfCnpj() );
        usuario.setRole( dto.role() );

        return usuario;
    }

    @Override
    public UsuarioResponseDTO toResponseDTO(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }

        Long id = null;
        String nome = null;
        String email = null;
        String cpfCnpj = null;
        Role role = null;
        boolean ativo = false;

        id = usuario.getId();
        nome = usuario.getNome();
        email = usuario.getEmail();
        cpfCnpj = usuario.getCpfCnpj();
        role = usuario.getRole();
        ativo = usuario.isAtivo();

        UsuarioResponseDTO usuarioResponseDTO = new UsuarioResponseDTO( id, nome, email, cpfCnpj, role, ativo );

        return usuarioResponseDTO;
    }

    @Override
    public Usuario updateEntityFromDto(PerfilResponseDTO dto, Usuario entity) {
        if ( dto == null ) {
            return entity;
        }

        entity.setNome( dto.nome() );
        entity.setEmail( dto.email() );
        entity.setCpfCnpj( dto.cpfCnpj() );

        return entity;
    }

    @Override
    public List<UsuarioResponseDTO> toResponseDTOList(List<Usuario> usuarios) {
        if ( usuarios == null ) {
            return null;
        }

        List<UsuarioResponseDTO> list = new ArrayList<UsuarioResponseDTO>( usuarios.size() );
        for ( Usuario usuario : usuarios ) {
            list.add( toResponseDTO( usuario ) );
        }

        return list;
    }

    @Override
    public PerfilResponseDTO toPerfilResponseDTO(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }

        String nome = null;
        String email = null;
        String cpfCnpj = null;

        nome = usuario.getNome();
        email = usuario.getEmail();
        cpfCnpj = usuario.getCpfCnpj();

        PerfilResponseDTO perfilResponseDTO = new PerfilResponseDTO( nome, email, cpfCnpj );

        return perfilResponseDTO;
    }
}
