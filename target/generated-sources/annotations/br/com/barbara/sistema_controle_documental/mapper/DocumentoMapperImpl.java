package br.com.barbara.sistema_controle_documental.mapper;

import br.com.barbara.sistema_controle_documental.dto.DocumentoRequestDTO;
import br.com.barbara.sistema_controle_documental.dto.DocumentoResponseDTO;
import br.com.barbara.sistema_controle_documental.model.Documento;
import br.com.barbara.sistema_controle_documental.model.Propriedade;
import br.com.barbara.sistema_controle_documental.model.enuns.StatusDocumento;
import br.com.barbara.sistema_controle_documental.model.enuns.TipoDocumento;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
public class DocumentoMapperImpl implements DocumentoMapper {

    @Override
    public Documento toEntity(DocumentoRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Documento documento = new Documento();

        documento.setPropriedade( documentoRequestDTOToPropriedade( dto ) );
        documento.setTipoDocumento( dto.tipoDocumento() );
        documento.setNumeroProcesso( dto.numeroProcesso() );
        documento.setDescricao( dto.descricao() );
        documento.setDataEmissao( dto.dataEmissao() );
        documento.setDataVencimento( dto.dataVencimento() );

        return documento;
    }

    @Override
    public DocumentoResponseDTO toResponseDTO(Documento documento) {
        if ( documento == null ) {
            return null;
        }

        Long propriedadeId = null;
        Long id = null;
        TipoDocumento tipoDocumento = null;
        String numeroProcesso = null;
        String descricao = null;
        LocalDate dataEmissao = null;
        LocalDate dataVencimento = null;
        StatusDocumento status = null;
        String caminhoArquivo = null;
        LocalDateTime criadoEm = null;
        LocalDateTime atualizadoEm = null;

        propriedadeId = documentoPropriedadeId( documento );
        id = documento.getId();
        tipoDocumento = documento.getTipoDocumento();
        numeroProcesso = documento.getNumeroProcesso();
        descricao = documento.getDescricao();
        dataEmissao = documento.getDataEmissao();
        dataVencimento = documento.getDataVencimento();
        status = documento.getStatus();
        caminhoArquivo = documento.getCaminhoArquivo();
        criadoEm = documento.getCriadoEm();
        atualizadoEm = documento.getAtualizadoEm();

        DocumentoResponseDTO documentoResponseDTO = new DocumentoResponseDTO( id, tipoDocumento, numeroProcesso, descricao, dataEmissao, dataVencimento, status, caminhoArquivo, propriedadeId, criadoEm, atualizadoEm );

        return documentoResponseDTO;
    }

    @Override
    public Documento updateEntityFromDto(DocumentoRequestDTO dto, Documento entity) {
        if ( dto == null ) {
            return entity;
        }

        entity.setTipoDocumento( dto.tipoDocumento() );
        entity.setNumeroProcesso( dto.numeroProcesso() );
        entity.setDescricao( dto.descricao() );
        entity.setDataEmissao( dto.dataEmissao() );
        entity.setDataVencimento( dto.dataVencimento() );

        return entity;
    }

    @Override
    public List<DocumentoResponseDTO> toResponseDTOList(List<Documento> documentos) {
        if ( documentos == null ) {
            return null;
        }

        List<DocumentoResponseDTO> list = new ArrayList<DocumentoResponseDTO>( documentos.size() );
        for ( Documento documento : documentos ) {
            list.add( toResponseDTO( documento ) );
        }

        return list;
    }

    protected Propriedade documentoRequestDTOToPropriedade(DocumentoRequestDTO documentoRequestDTO) {
        if ( documentoRequestDTO == null ) {
            return null;
        }

        Propriedade propriedade = new Propriedade();

        propriedade.setId( documentoRequestDTO.propriedadeId() );

        return propriedade;
    }

    private Long documentoPropriedadeId(Documento documento) {
        Propriedade propriedade = documento.getPropriedade();
        if ( propriedade == null ) {
            return null;
        }
        return propriedade.getId();
    }
}
