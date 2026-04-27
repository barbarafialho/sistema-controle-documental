package br.com.barbara.sistema_controle_documental.dto;

public record ConfigEmailRequestDTO(
        String host,
        String porta,
        String nomeUsuario,
        String senha,
        String protocolo
) {}