package br.com.barbara.sistema_controle_documental.mapper;

import br.com.barbara.sistema_controle_documental.dto.ConfigEmailRequestDTO;
import br.com.barbara.sistema_controle_documental.dto.ConfigEmailResponseDTO;
import br.com.barbara.sistema_controle_documental.model.ConfigServidorEmail;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-26T11:32:04-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class ConfigEmailMapperImpl implements ConfigEmailMapper {

    @Override
    public ConfigServidorEmail toEntity(ConfigEmailRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ConfigServidorEmail configServidorEmail = new ConfigServidorEmail();

        configServidorEmail.setHost( dto.host() );
        configServidorEmail.setPorta( dto.porta() );
        configServidorEmail.setNomeUsuario( dto.nomeUsuario() );
        configServidorEmail.setSenha( dto.senha() );
        configServidorEmail.setProtocolo( dto.protocolo() );

        return configServidorEmail;
    }

    @Override
    public ConfigEmailResponseDTO toResponseDTO(ConfigServidorEmail entity) {
        if ( entity == null ) {
            return null;
        }

        Long id = null;
        String host = null;
        String porta = null;
        String nomeUsuario = null;
        String protocolo = null;
        LocalDateTime criadoEm = null;
        LocalDateTime atualizadoEm = null;

        id = entity.getId();
        host = entity.getHost();
        porta = entity.getPorta();
        nomeUsuario = entity.getNomeUsuario();
        protocolo = entity.getProtocolo();
        criadoEm = entity.getCriadoEm();
        atualizadoEm = entity.getAtualizadoEm();

        ConfigEmailResponseDTO configEmailResponseDTO = new ConfigEmailResponseDTO( id, host, porta, nomeUsuario, protocolo, criadoEm, atualizadoEm );

        return configEmailResponseDTO;
    }

    @Override
    public List<ConfigEmailResponseDTO> toResponseDTOList(List<ConfigServidorEmail> entidades) {
        if ( entidades == null ) {
            return null;
        }

        List<ConfigEmailResponseDTO> list = new ArrayList<ConfigEmailResponseDTO>( entidades.size() );
        for ( ConfigServidorEmail configServidorEmail : entidades ) {
            list.add( toResponseDTO( configServidorEmail ) );
        }

        return list;
    }

    @Override
    public ConfigServidorEmail updateEntityFromDto(ConfigEmailRequestDTO dto, ConfigServidorEmail entity) {
        if ( dto == null ) {
            return entity;
        }

        entity.setHost( dto.host() );
        entity.setPorta( dto.porta() );
        entity.setNomeUsuario( dto.nomeUsuario() );
        entity.setSenha( dto.senha() );
        entity.setProtocolo( dto.protocolo() );

        return entity;
    }
}
