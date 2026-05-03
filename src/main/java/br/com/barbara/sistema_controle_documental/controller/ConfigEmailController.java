package br.com.barbara.sistema_controle_documental.controller;
import br.com.barbara.sistema_controle_documental.dto.ConfigEmailRequestDTO;
import br.com.barbara.sistema_controle_documental.dto.ConfigEmailResponseDTO;
import br.com.barbara.sistema_controle_documental.service.ConfigEmailService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/config-email")
@Tag(name = "Configuração de E-mail", description = "Gerenciamento do servidor SMTP de disparo")
public class ConfigEmailController {

    private final ConfigEmailService configService;

    public ConfigEmailController(ConfigEmailService configService) {
        this.configService = configService;
    }

    @GetMapping
    public ResponseEntity<List<ConfigEmailResponseDTO>> listarTodos() {
        return ResponseEntity.ok(configService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConfigEmailResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(configService.listarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ConfigEmailResponseDTO> criarNova(@RequestBody ConfigEmailRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(configService.salvar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConfigEmailResponseDTO> atualizar(@PathVariable Long id, @RequestBody ConfigEmailRequestDTO dto) {
        return ResponseEntity.ok(configService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        configService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}