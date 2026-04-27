package br.com.barbara.sistema_controle_documental.controller;

import br.com.barbara.sistema_controle_documental.dto.PropriedadeRequestDTO;
import br.com.barbara.sistema_controle_documental.dto.PropriedadeResponseDTO;
import br.com.barbara.sistema_controle_documental.dto.UsuarioResponseDTO;
import br.com.barbara.sistema_controle_documental.service.PropriedadeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/propriedades")
@Tag(name = "Propriedades", description = "Endpoint para gerenciamento de propriedades")
public class PropriedadeController {
    private final PropriedadeService propriedadeService;

    public PropriedadeController(PropriedadeService propriedadesService) {
        this.propriedadeService = propriedadesService;
    }

    @GetMapping
    public ResponseEntity<List<PropriedadeResponseDTO>> listarTodos() {
        return ResponseEntity.ok(propriedadeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropriedadeResponseDTO> listarPorId(@PathVariable("id") Long id){
        return ResponseEntity.ok(propriedadeService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PropriedadeResponseDTO> criar(@RequestBody PropriedadeRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(propriedadeService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PropriedadeResponseDTO> atualizar(@PathVariable Long id, @RequestBody PropriedadeRequestDTO dto) {
        return ResponseEntity.ok(propriedadeService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        propriedadeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}