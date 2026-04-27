package br.com.barbara.sistema_controle_documental.mapper;

import br.com.barbara.sistema_controle_documental.dto.CondicionanteRequestDTO;
import br.com.barbara.sistema_controle_documental.dto.CondicionanteResponseDTO;
import br.com.barbara.sistema_controle_documental.model.Condicionante;
import br.com.barbara.sistema_controle_documental.model.Documento;
import java.time.LocalDate;
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
public class CondicionanteMapperImpl implements CondicionanteMapper {

    @Override
    public Condicionante toEntity(CondicionanteRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Condicionante condicionante = new Condicionante();

        condicionante.setDocumento( condicionanteRequestDTOToDocumento( dto ) );
        condicionante.setDescricao( dto.descricao() );
        condicionante.setDataPrazo( dto.dataPrazo() );

        return condicionante;
    }

    @Override
    public CondicionanteResponseDTO toResponseDTO(Condicionante condicionante) {
        if ( condicionante == null ) {
            return null;
        }

        Long documentoId = null;
        Long id = null;
        String descricao = null;
        LocalDate dataPrazo = null;
        boolean concluida = false;
        LocalDateTime criadoEm = null;
        LocalDateTime atualizadoEm = null;

        documentoId = condicionanteDocumentoId( condicionante );
        id = condicionante.getId();
        descricao = condicionante.getDescricao();
        dataPrazo = condicionante.getDataPrazo();
        concluida = condicionante.isConcluida();
        criadoEm = condicionante.getCriadoEm();
        atualizadoEm = condicionante.getAtualizadoEm();

        CondicionanteResponseDTO condicionanteResponseDTO = new CondicionanteResponseDTO( id, descricao, dataPrazo, concluida, documentoId, criadoEm, atualizadoEm );

        return condicionanteResponseDTO;
    }

    @Override
    public Condicionante updateEntityFromDto(CondicionanteRequestDTO dto, Condicionante entity) {
        if ( dto == null ) {
            return entity;
        }

        entity.setDescricao( dto.descricao() );
        entity.setDataPrazo( dto.dataPrazo() );

        return entity;
    }

    @Override
    public List<CondicionanteResponseDTO> toResponseDTOList(List<Condicionante> condicionantes) {
        if ( condicionantes == null ) {
            return null;
        }

        List<CondicionanteResponseDTO> list = new ArrayList<CondicionanteResponseDTO>( condicionantes.size() );
        for ( Condicionante condicionante : condicionantes ) {
            list.add( toResponseDTO( condicionante ) );
        }

        return list;
    }

    protected Documento condicionanteRequestDTOToDocumento(CondicionanteRequestDTO condicionanteRequestDTO) {
        if ( condicionanteRequestDTO == null ) {
            return null;
        }

        Documento documento = new Documento();

        documento.setId( condicionanteRequestDTO.documentoId() );

        return documento;
    }

    private Long condicionanteDocumentoId(Condicionante condicionante) {
        Documento documento = condicionante.getDocumento();
        if ( documento == null ) {
            return null;
        }
        return documento.getId();
    }
}
