package br.com.barbara.sistema_controle_documental.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record CondicionanteRequestDTO(
        String descricao,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dataPrazo,
        Long documentoId
) {
}
