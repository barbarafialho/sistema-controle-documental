package br.com.barbara.sistema_controle_documental.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record ConfigEmailResponseDTO(
        Long id,
        String host,
        String porta,
        String nomeUsuario,
        String protocolo,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime criadoEm,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime atualizadoEm
) {}