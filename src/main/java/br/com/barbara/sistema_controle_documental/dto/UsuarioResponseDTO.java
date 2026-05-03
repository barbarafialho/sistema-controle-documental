package br.com.barbara.sistema_controle_documental.dto;

import br.com.barbara.sistema_controle_documental.model.enuns.Role;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String email,
        String cpfCnpj,
        Role role,
        boolean ativo
) {
}
