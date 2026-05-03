package br.com.barbara.sistema_controle_documental.dto;

import br.com.barbara.sistema_controle_documental.model.enuns.TipoDocumento;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public record DocumentoRequestDTO(
        TipoDocumento tipoDocumento,
        String numeroProcesso,
        String descricao,
        @JsonFormat(pattern = "dd/MM/yyyy")
        @Schema(example = "01/05/2026", type = "string", format = "date")
        LocalDate dataEmissao,
        @JsonFormat(pattern = "dd/MM/yyyy")
        @Schema(example = "01/05/2027", type = "string", format = "date")
        LocalDate dataVencimento,
        String caminhoArquivo,
        Long propriedadeId
) {
}
