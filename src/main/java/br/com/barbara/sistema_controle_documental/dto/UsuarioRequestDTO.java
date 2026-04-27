package br.com.barbara.sistema_controle_documental.dto;

public record UsuarioRequestDTO(
        String nome,
        String email,
        String senha,
        String cpfCnpj
) {
}
