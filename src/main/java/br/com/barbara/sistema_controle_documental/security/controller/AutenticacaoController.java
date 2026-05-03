package br.com.barbara.sistema_controle_documental.security.controller;

import br.com.barbara.sistema_controle_documental.model.Usuario;
import br.com.barbara.sistema_controle_documental.security.controller.records.DadosAutenticacao;
import br.com.barbara.sistema_controle_documental.security.controller.records.DadosTokenJWT;
import br.com.barbara.sistema_controle_documental.security.service.TokenService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
@Tag(name = "Login", description = "Endpoints para se autenticar no sistema")
public class AutenticacaoController {

    private final AuthenticationManager manager;
    private final TokenService tokenService;

    public AutenticacaoController(AuthenticationManager manager, TokenService tokenService) {
        this.manager = manager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<DadosTokenJWT> login(@RequestBody @Valid DadosAutenticacao dados) {
        var auth = manager.authenticate(
                new UsernamePasswordAuthenticationToken(dados.email(), dados.senha())
        );

        return ResponseEntity.ok(new DadosTokenJWT(
                tokenService.gerarToken((Usuario) auth.getPrincipal())));
    }
}
