package br.com.barbara.sistema_controle_documental.dto;

public record PropriedadeRequestDTO(
        String nome,
        String cnpj,
        Double areaTotal,
        String cidade
) {
}
