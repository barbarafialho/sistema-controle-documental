package br.com.barbara.sistema_controle_documental.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CondicionanteResponseDTO(
        Long id,
        String descricao,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dataPrazo,
        boolean concluida,
        Long documentoId,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime criadoEm,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime atualizadoEm
) {
}
