package br.com.barbara.sistema_controle_documental.repository;

import br.com.barbara.sistema_controle_documental.model.ConfigServidorEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfigEmailRepository extends JpaRepository<ConfigServidorEmail, Long> {
    //busca a última configuração de servidor de email
    Optional<ConfigServidorEmail> findFirstByOrderByAtualizadoEmDesc();
}
