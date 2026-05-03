package br.com.barbara.sistema_controle_documental.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public record CondicionanteRequestDTO(
        String descricao,
        @JsonFormat(pattern = "dd/MM/yyyy")
        @Schema(example = "01/05/2026", type = "string", format = "date")
        LocalDate dataPrazo,
        Long documentoId
) {
}
