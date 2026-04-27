package br.com.barbara.sistema_controle_documental.dto;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String email,
        String cpfCnpj
) {
}
