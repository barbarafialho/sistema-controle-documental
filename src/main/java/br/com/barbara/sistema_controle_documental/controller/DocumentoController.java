package br.com.barbara.sistema_controle_documental.controller;

import br.com.barbara.sistema_controle_documental.dto.DocumentoRequestDTO;
import br.com.barbara.sistema_controle_documental.dto.DocumentoResponseDTO;
import br.com.barbara.sistema_controle_documental.service.DocumentoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/documentos")
@Tag(name = "Documento", description = "Endpoint para gerenciamento de documentos")
public class DocumentoController {
    private final DocumentoService documentoService;

    public DocumentoController(DocumentoService documentoService) {
        this.documentoService = documentoService;
    }

    @GetMapping
    public ResponseEntity<List<DocumentoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(documentoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentoResponseDTO> listarPorId(@PathVariable("id") Long id){
        return ResponseEntity.ok(documentoService.listarPorId(id));
    }

    @PostMapping
    public ResponseEntity<DocumentoResponseDTO> criar(@RequestBody DocumentoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(documentoService.salvar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocumentoResponseDTO> atualizar(@PathVariable Long id, @RequestBody DocumentoRequestDTO dto) {
        return ResponseEntity.ok(documentoService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        documentoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}