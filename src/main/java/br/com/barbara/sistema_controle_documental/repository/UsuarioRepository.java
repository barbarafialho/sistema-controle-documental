package br.com.barbara.sistema_controle_documental.repository;

import br.com.barbara.sistema_controle_documental.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);

    boolean existsByCpfCnpj(String s);

    boolean existsByEmail(String email);

    //UserDetails findByEmail(String email);
}