package br.com.barbara.sistema_controle_documental.controller;

import br.com.barbara.sistema_controle_documental.dto.DocumentoRequestDTO;
import br.com.barbara.sistema_controle_documental.dto.DocumentoResponseDTO;
import br.com.barbara.sistema_controle_documental.model.Usuario;
import br.com.barbara.sistema_controle_documental.service.DocumentoService;
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

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/api/documentos")
@Tag(name = "Documento", description = "Endpoint para gerenciamento de documentos")
public class DocumentoController {
    private final DocumentoService documentoService;

    public DocumentoController(DocumentoService documentoService) {
        this.documentoService = documentoService;
    }

    private Usuario getUsuarioLogado() {
        return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @GetMapping
    public ResponseEntity<List<DocumentoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(documentoService.listarTodos(getUsuarioLogado()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentoResponseDTO> listarPorId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(documentoService.listarPorId(id, getUsuarioLogado()));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<DocumentoResponseDTO> criar(
            @RequestPart("dados") @Valid DocumentoRequestDTO dto,
            @RequestPart("arquivo") MultipartFile arquivo) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(documentoService.salvar(dto, arquivo, getUsuarioLogado()));
    }

    // O required = false no arquivo permite atualizar apenas os dados de texto.
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<DocumentoResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestPart("dados") @Valid DocumentoRequestDTO dto,
            @RequestPart(value = "arquivo", required = false) MultipartFile arquivo) {

        return ResponseEntity.ok(documentoService.atualizar(id, dto, arquivo, getUsuarioLogado()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        documentoService.excluir(id, getUsuarioLogado());
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Resource> download(@PathVariable Long id) {
        Usuario logado = getUsuarioLogado();
        Resource arquivo = documentoService.baixarArquivo(id, logado);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + arquivo.getFilename() + "\"")
                .body(arquivo);
    }
}