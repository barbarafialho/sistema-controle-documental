package br.com.barbara.sistema_controle_documental.mapper;

import br.com.barbara.sistema_controle_documental.dto.CondicionanteRequestDTO;
import br.com.barbara.sistema_controle_documental.dto.CondicionanteResponseDTO;
import br.com.barbara.sistema_controle_documental.model.Condicionante;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CondicionanteMapper {
    @Mapping(source = "documentoId", target = "documento.id")
    Condicionante toEntity(CondicionanteRequestDTO dto);

    @Mapping(source = "documento.id", target = "documentoId")
    CondicionanteResponseDTO toResponseDTO(Condicionante condicionante);

    @Mapping(target = "id", ignore = true)
    Condicionante updateEntityFromDto(CondicionanteRequestDTO dto, @MappingTarget Condicionante entity);

    List<CondicionanteResponseDTO> toResponseDTOList(List<Condicionante> condicionantes);
}