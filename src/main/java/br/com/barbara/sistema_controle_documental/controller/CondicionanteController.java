package br.com.barbara.sistema_controle_documental.controller;

import br.com.barbara.sistema_controle_documental.dto.CondicionanteRequestDTO;
import br.com.barbara.sistema_controle_documental.dto.CondicionanteResponseDTO;
import br.com.barbara.sistema_controle_documental.service.CondicionanteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/condicionantes")
@Tag(name = "Condicionantes", description = "Endpoints para gerenciamento das condicionantes")
public class CondicionanteController {
    private final CondicionanteService condicionanteService;

    public CondicionanteController(CondicionanteService condicionanteService) {
        this.condicionanteService = condicionanteService;
    }

    @GetMapping("/documento/{documentoId}")
    public ResponseEntity<List<CondicionanteResponseDTO>> listarPorDocumento(@PathVariable Long documentoId) {
        return ResponseEntity.ok(condicionanteService.findByDocumento(documentoId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CondicionanteResponseDTO> listarPorId(@PathVariable("id") Long id){
        return ResponseEntity.ok(condicionanteService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CondicionanteResponseDTO> criar(@RequestBody CondicionanteRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(condicionanteService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CondicionanteResponseDTO> atualizar(@PathVariable Long id, @RequestBody CondicionanteRequestDTO dto) {
        return ResponseEntity.ok(condicionanteService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        condicionanteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}