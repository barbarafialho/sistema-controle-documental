package br.com.barbara.sistema_controle_documental.dto;

public record PropriedadeResponseDTO(
        Long id,
        String nome,
        String cnpj,
        Double areaTotal,
        String cidade,
        Long usuarioId
) {
}
