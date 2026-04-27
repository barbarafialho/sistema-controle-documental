package br.com.barbara.sistema_controle_documental.mapper;

import br.com.barbara.sistema_controle_documental.dto.ConfigEmailRequestDTO;
import br.com.barbara.sistema_controle_documental.dto.ConfigEmailResponseDTO;
import br.com.barbara.sistema_controle_documental.model.ConfigServidorEmail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ConfigEmailMapper {

    ConfigServidorEmail toEntity(ConfigEmailRequestDTO dto);

    ConfigEmailResponseDTO toResponseDTO(ConfigServidorEmail entity);

    List<ConfigEmailResponseDTO> toResponseDTOList(List<ConfigServidorEmail> entidades);

    @Mapping(target = "id", ignore = true)
    ConfigServidorEmail updateEntityFromDto(ConfigEmailRequestDTO dto, @MappingTarget ConfigServidorEmail entity);
}