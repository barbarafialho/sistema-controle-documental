package br.com.barbara.sistema_controle_documental.mapper;

import br.com.barbara.sistema_controle_documental.dto.DocumentoRequestDTO;
import br.com.barbara.sistema_controle_documental.dto.DocumentoResponseDTO;
import br.com.barbara.sistema_controle_documental.model.Documento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DocumentoMapper {

    // DTO para Entity
    @Mapping(source = "propriedadeId", target = "propriedade.id")
    Documento toEntity(DocumentoRequestDTO dto);

    // Entity para DTO
    @Mapping(source = "propriedade.id", target = "propriedadeId")
    DocumentoResponseDTO toResponseDTO(Documento documento);

    @Mapping(target = "id", ignore = true) // Não mapeia o id para não alterar
    Documento updateEntityFromDto(DocumentoRequestDTO dto, @MappingTarget Documento entity);

    // Converte uma lista de Documentos
    List<DocumentoResponseDTO> toResponseDTOList(List<Documento> documentos);
}