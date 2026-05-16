package br.com.barbara.sistema_controle_documental.repository;

import br.com.barbara.sistema_controle_documental.model.Propriedade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropriedadeRepository extends JpaRepository<Propriedade, Long> {

    @Query("SELECT p FROM Propriedade p WHERE p.usuario.id = :usuarioId")
    List<Propriedade> findUsuarioLogado(@Param("usuarioId") Long usuarioId);

}