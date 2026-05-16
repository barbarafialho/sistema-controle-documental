package br.com.barbara.sistema_controle_documental.mapper;

import br.com.barbara.sistema_controle_documental.dto.PropriedadeRequestDTO;
import br.com.barbara.sistema_controle_documental.dto.PropriedadeResponseDTO;
import br.com.barbara.sistema_controle_documental.model.Propriedade;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PropriedadeMapper {
    Propriedade toEntity(PropriedadeRequestDTO dto);

    @Mapping(source = "usuario.id", target = "usuarioId")
    PropriedadeResponseDTO toResponseDTO(Propriedade propriedade);

    @Mapping(target = "id", ignore = true)
    Propriedade updateEntityFromDto(PropriedadeRequestDTO dto, @MappingTarget Propriedade entity);

    List<PropriedadeResponseDTO> toResponseDTOList(List<Propriedade> propriedades);
}