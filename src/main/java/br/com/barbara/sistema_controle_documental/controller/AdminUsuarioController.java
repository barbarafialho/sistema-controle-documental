package br.com.barbara.sistema_controle_documental.controller;

import br.com.barbara.sistema_controle_documental.dto.PerfilDTO;
import br.com.barbara.sistema_controle_documental.dto.UsuarioRequestDTO;
import br.com.barbara.sistema_controle_documental.dto.UsuarioResponseDTO;
import br.com.barbara.sistema_controle_documental.model.Usuario;
import br.com.barbara.sistema_controle_documental.service.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuário", description = "Endpoint para gerenciamento de usuários")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
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

    @PutMapping("/perfil")
    public ResponseEntity<UsuarioResponseDTO> atualizarMeusDados(@RequestBody PerfilDTO dto) {
        Usuario logado = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(usuarioService.atualizarPerfil(dto, logado));
    }

    @PutMapping("/{id}/desativar")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        usuarioService.desativar(id);
        return ResponseEntity.noContent().build();
    }
}