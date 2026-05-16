package br.com.barbara.sistema_controle_documental.model;

import br.com.barbara.sistema_controle_documental.model.validate.UsuarioInterface;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "condicionante")
public class Condicionante implements UsuarioInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descricao;

    @Column(name = "data_prazo", nullable = false)
    private LocalDate dataPrazo;

    @Column(nullable = false)
    private boolean concluida;

    @Column(name = "caminho_arquivo", nullable = false)
    private String caminhoArquivo;

    @ManyToOne
    @JoinColumn(name = "documento_id")
    private Documento documento;

    @Column(name = "criado_em", nullable = false)
    private LocalDateTime criadoEm;

    @Column(name = "atualizado_em", nullable = false)
    private LocalDateTime atualizadoEm;

    @Override
    public Long getUsuarioID() {
        return this.documento.getPropriedade().getUsuario().getId();
    }

    //Antes de inserir ou atualizar, insere a data atual
    @PrePersist
    protected void aoCriar() {
        criadoEm = LocalDateTime.now();
        atualizadoEm = LocalDateTime.now();
    }

    @PreUpdate
    protected void aoAtualizar() {
        atualizadoEm = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataPrazo() {
        return dataPrazo;
    }

    public void setDataPrazo(LocalDate dataPrazo) {
        this.dataPrazo = dataPrazo;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void setConcluida(boolean concluido) {
        this.concluida = concluido;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }

    public LocalDateTime getAtualizadoEm() {
        return atualizadoEm;
    }

    public void setAtualizadoEm(LocalDateTime atualizadoEm) {
        this.atualizadoEm = atualizadoEm;
    }

    public String getCaminhoArquivo() {
        return caminhoArquivo;
    }

    public void setCaminhoArquivo(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

}
