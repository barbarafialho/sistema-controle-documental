package br.com.barbara.sistema_controle_documental.repository;

import br.com.barbara.sistema_controle_documental.model.Condicionante;
import br.com.barbara.sistema_controle_documental.model.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CondicionanteRepository extends JpaRepository<Condicionante, Long> {
    List<Condicionante> findByDocumentoId(Long id);

    @Query("SELECT c FROM Condicionante c WHERE c.documento.propriedade.usuario.id = :usuarioId")
    List<Condicionante> findUsuarioLogado(@Param("usuarioId") Long usuarioId);
}
