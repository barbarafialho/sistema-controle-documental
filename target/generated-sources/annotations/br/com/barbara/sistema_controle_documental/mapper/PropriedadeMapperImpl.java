package br.com.barbara.sistema_controle_documental.mapper;

import br.com.barbara.sistema_controle_documental.dto.PropriedadeRequestDTO;
import br.com.barbara.sistema_controle_documental.dto.PropriedadeResponseDTO;
import br.com.barbara.sistema_controle_documental.model.Propriedade;
import br.com.barbara.sistema_controle_documental.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-01T20:20:11-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class PropriedadeMapperImpl implements PropriedadeMapper {

    @Override
    public Propriedade toEntity(PropriedadeRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Propriedade propriedade = new Propriedade();

        propriedade.setUsuario( propriedadeRequestDTOToUsuario( dto ) );
        propriedade.setNome( dto.nome() );
        propriedade.setCnpj( dto.cnpj() );
        propriedade.setAreaTotal( dto.areaTotal() );
        propriedade.setCidade( dto.cidade() );

        return propriedade;
    }

    @Override
    public PropriedadeResponseDTO toResponseDTO(Propriedade propriedade) {
        if ( propriedade == null ) {
            return null;
        }

        Long usuarioId = null;
        Long id = null;
        String nome = null;
        String cnpj = null;
        Double areaTotal = null;
        String cidade = null;

        usuarioId = propriedadeUsuarioId( propriedade );
        id = propriedade.getId();
        nome = propriedade.getNome();
        cnpj = propriedade.getCnpj();
        areaTotal = propriedade.getAreaTotal();
        cidade = propriedade.getCidade();

        PropriedadeResponseDTO propriedadeResponseDTO = new PropriedadeResponseDTO( id, nome, cnpj, areaTotal, cidade, usuarioId );

        return propriedadeResponseDTO;
    }

    @Override
    public Propriedade updateEntityFromDto(PropriedadeRequestDTO dto, Propriedade entity) {
        if ( dto == null ) {
            return entity;
        }

        entity.setNome( dto.nome() );
        entity.setCnpj( dto.cnpj() );
        entity.setAreaTotal( dto.areaTotal() );
        entity.setCidade( dto.cidade() );

        return entity;
    }

    @Override
    public List<PropriedadeResponseDTO> toResponseDTOList(List<Propriedade> propriedades) {
        if ( propriedades == null ) {
            return null;
        }

        List<PropriedadeResponseDTO> list = new ArrayList<PropriedadeResponseDTO>( propriedades.size() );
        for ( Propriedade propriedade : propriedades ) {
            list.add( toResponseDTO( propriedade ) );
        }

        return list;
    }

    protected Usuario propriedadeRequestDTOToUsuario(PropriedadeRequestDTO propriedadeRequestDTO) {
        if ( propriedadeRequestDTO == null ) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setId( propriedadeRequestDTO.usuarioId() );

        return usuario;
    }

    private Long propriedadeUsuarioId(Propriedade propriedade) {
        Usuario usuario = propriedade.getUsuario();
        if ( usuario == null ) {
            return null;
        }
        return usuario.getId();
    }
}
