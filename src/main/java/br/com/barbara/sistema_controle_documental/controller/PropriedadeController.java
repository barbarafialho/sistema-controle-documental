package br.com.barbara.sistema_controle_documental.controller;

import br.com.barbara.sistema_controle_documental.dto.PropriedadeRequestDTO;
import br.com.barbara.sistema_controle_documental.dto.PropriedadeResponseDTO;
import br.com.barbara.sistema_controle_documental.model.Usuario;
import br.com.barbara.sistema_controle_documental.service.PropriedadeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/api/propriedades")
@Tag(name = "Propriedades", description = "Endpoint para gerenciamento de propriedades")
public class PropriedadeController {
    private final PropriedadeService propriedadeService;

    public PropriedadeController(PropriedadeService propriedadeService) {
        this.propriedadeService = propriedadeService;
    }

    // Método auxiliar para obter o usuário autenticado do contexto de segurança
    private Usuario getUsuarioLogado() {
        return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @GetMapping
    public ResponseEntity<List<PropriedadeResponseDTO>> listarTodos() {
        return ResponseEntity.ok(propriedadeService.listarTodos(getUsuarioLogado()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropriedadeResponseDTO> listarPorId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(propriedadeService.listarPorId(id, getUsuarioLogado()));
    }

    @PostMapping
    public ResponseEntity<PropriedadeResponseDTO> criar(@RequestBody @Valid PropriedadeRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(propriedadeService.salvar(dto, getUsuarioLogado()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PropriedadeResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid PropriedadeRequestDTO dto) {
        return ResponseEntity.ok(propriedadeService.atualizar(id, dto, getUsuarioLogado()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        propriedadeService.excluir(id, getUsuarioLogado());
        return ResponseEntity.noContent().build();
    }
}