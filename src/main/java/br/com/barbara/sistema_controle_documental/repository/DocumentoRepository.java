package br.com.barbara.sistema_controle_documental.repository;

import br.com.barbara.sistema_controle_documental.model.Documento;
import br.com.barbara.sistema_controle_documental.model.enuns.StatusDocumento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Long> {
    // Usando o padrão Derived Query Method para criar as funções de busca
    List<Documento> findByDataVencimentoAndStatus(LocalDate data, StatusDocumento status);

    @Query("SELECT d FROM Documento d " +
            "JOIN FETCH d.propriedade p " +
            "JOIN FETCH p.usuario u " +
            "WHERE d.dataVencimento < :hoje AND d.status = :status")
    List<Documento> findVencidosComUsuario(@Param("hoje") LocalDate hoje, @Param("status") StatusDocumento status);

    @Query("SELECT d FROM Documento d WHERE d.propriedade.usuario.id = :usuarioId")
    List<Documento> findUsuarioLogado(@Param("usuarioId") Long usuarioId);
}