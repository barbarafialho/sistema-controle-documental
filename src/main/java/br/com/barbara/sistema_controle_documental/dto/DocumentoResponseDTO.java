package br.com.barbara.sistema_controle_documental.dto;

import br.com.barbara.sistema_controle_documental.model.enuns.StatusDocumento;
import br.com.barbara.sistema_controle_documental.model.enuns.TipoDocumento;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DocumentoResponseDTO(
        Long id,
        TipoDocumento tipoDocumento,
        String numeroProcesso,
        String descricao,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dataEmissao,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dataVencimento,
        StatusDocumento status,
        String caminhoArquivo,
        Long propriedadeId,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime criadoEm,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime atualizadoEm
) {
}
