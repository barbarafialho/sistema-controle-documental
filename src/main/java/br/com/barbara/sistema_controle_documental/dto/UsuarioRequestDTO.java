package br.com.barbara.sistema_controle_documental.dto;

import br.com.barbara.sistema_controle_documental.model.enuns.Role;

public record UsuarioRequestDTO(
        String nome,
        String email,
        String senha,
        String cpfCnpj,
        Role role
) {
}
