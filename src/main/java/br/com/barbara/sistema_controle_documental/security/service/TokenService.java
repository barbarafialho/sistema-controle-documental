package br.com.barbara.sistema_controle_documental.security.service;

import br.com.barbara.sistema_controle_documental.model.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;

// Classe responsável por criar o token JWT quando o usuário fizer login
// e validar o token nas próximas chamadas.
@Service
public class TokenService {

    @Value("${security.jwt.secret}")
    private String secret;

    @Value("${security.jwt.expiration}")
    private Long expiration;

    public String gerarToken(Usuario usuario){
        // Cria uma assinatura com HMAC
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
                .withIssuer("sistema-controle-ambiental")
                .withSubject(usuario.getEmail())
                .withExpiresAt(Instant.now().plusMillis(expiration))
                .sign(algorithm);
    }

    public String verificaToken(String token){
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.require(algorithm)
                .withIssuer("sistema-controle-ambiental")
                .build()
                .verify(token)
                .getSubject();
    }
    // Retorna o subject (e-mail do usuário)
}
