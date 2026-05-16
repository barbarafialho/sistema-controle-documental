package br.com.barbara.sistema_controle_documental.controller;

import br.com.barbara.sistema_controle_documental.dto.UsuarioRequestDTO;
import br.com.barbara.sistema_controle_documental.dto.UsuarioResponseDTO;
import br.com.barbara.sistema_controle_documental.service.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/usuarios")
@Tag(name = "Admin - Usuários", description = "Endpoints administrativos para gerenciamento de usuários")
public class AdminUsuarioController {

    private final UsuarioService usuarioService;

    public AdminUsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarTodos() {
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> listarPorId(@PathVariable("id") Long id){
        return ResponseEntity.ok(usuarioService.listarPorId(id));
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> criar(@RequestBody UsuarioRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.salvar(dto));
    }

    @PutMapping("/{id}/desativar")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        usuarioService.desativar(id);
        return ResponseEntity.noContent().build();
    }
}