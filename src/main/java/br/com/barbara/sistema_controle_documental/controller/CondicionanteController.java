package br.com.barbara.sistema_controle_documental.controller;

import br.com.barbara.sistema_controle_documental.dto.CondicionanteRequestDTO;
import br.com.barbara.sistema_controle_documental.dto.CondicionanteResponseDTO;
import br.com.barbara.sistema_controle_documental.model.Usuario;
import br.com.barbara.sistema_controle_documental.service.CondicionanteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/condicionantes")
@Tag(name = "Condicionantes", description = "Endpoints para gerenciamento das condicionantes")
public class CondicionanteController {
    private final CondicionanteService condicionanteService;

    public CondicionanteController(CondicionanteService condicionanteService) {
        this.condicionanteService = condicionanteService;
    }

    private Usuario getUsuarioLogado() {
        return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @GetMapping("/documento/{documentoId}")
    public ResponseEntity<List<CondicionanteResponseDTO>> listarPorDocumento(@PathVariable Long documentoId) {
        return ResponseEntity.ok(condicionanteService.listarPorDocumento(documentoId, getUsuarioLogado()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CondicionanteResponseDTO> listarPorId(@PathVariable("id") Long id){
        return ResponseEntity.ok(condicionanteService.listarPorId(id, getUsuarioLogado()));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CondicionanteResponseDTO> criar(
            @RequestPart("dados") @Valid CondicionanteRequestDTO dto,
            @RequestPart("arquivo") MultipartFile arquivo) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(condicionanteService.salvar(dto, arquivo, getUsuarioLogado()));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CondicionanteResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestPart("dados") @Valid CondicionanteRequestDTO dto,
            @RequestPart(value = "arquivo", required = false) MultipartFile arquivo) {

        return ResponseEntity.ok(condicionanteService.atualizar(id, dto, arquivo, getUsuarioLogado()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        condicionanteService.excluir(id, getUsuarioLogado());
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Resource> download(@PathVariable Long id) {
        Resource arquivo = condicionanteService.baixarArquivo(id, getUsuarioLogado());

        String nomeArquivo = arquivo.getFilename() != null ? arquivo.getFilename() : "condicionante_anexo";

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + nomeArquivo + "\"")
                .body(arquivo);
    }
}