package br.com.barbara.sistema_controle_documental.dto;

import br.com.barbara.sistema_controle_documental.model.enuns.TipoDocumento;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record DocumentoRequestDTO(
        TipoDocumento tipoDocumento,
        String numeroProcesso,
        String descricao,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dataEmissao,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dataVencimento,
        String caminhoArquivo,
        Long propriedadeId
) {
}
