package br.com.barbara.sistema_controle_documental.model;

import br.com.barbara.sistema_controle_documental.model.enuns.StatusDocumento;
import br.com.barbara.sistema_controle_documental.model.enuns.TipoDocumento;
import br.com.barbara.sistema_controle_documental.model.records.DadosEmail;
import br.com.barbara.sistema_controle_documental.model.validate.UsuarioInterface;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "documento")
public class Documento implements UsuarioInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_documento", nullable = false)
    private TipoDocumento tipoDocumento;

    @Column(name = "numero_processo")
    private String numeroProcesso;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "data_emissao", nullable = false)
    private LocalDate dataEmissao;

    @Column(name = "data_vencimento", nullable = false)
    private LocalDate dataVencimento;

    @Column(name = "caminho_arquivo", nullable = false)
    private String caminhoArquivo;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusDocumento status;

    @ManyToOne
    @JoinColumn(name = "propriedade_id") //foreign key no banco de dados
    private Propriedade propriedade;

    @OneToMany(mappedBy = "documento", cascade = CascadeType.ALL)
    private List<Condicionante> condicionanteList;

    @Column(name = "criado_em", nullable = false)
    private LocalDateTime criadoEm;

    @Column(name = "atualizado_em", nullable = false)
    private LocalDateTime atualizadoEm;

    @Override
    public Long getUsuarioID() {
        return this.propriedade.getUsuario().getId();
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

    public DadosEmail getDados(){
        if (this.propriedade == null) {
            return null;
        }

        return new DadosEmail(
                this.propriedade.getUsuario().getNome(),
                this.propriedade.getUsuario().getEmail(),
                this.propriedade.getNome(),
                this.tipoDocumento.getDescricao()
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroProcesso() {
        return numeroProcesso;
    }

    public void setNumeroProcesso(String numeroProcesso) {
        this.numeroProcesso = numeroProcesso;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public String getCaminhoArquivo() {
        return caminhoArquivo;
    }

    public void setCaminhoArquivo(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

    public StatusDocumento getStatus() {
        return status;
    }

    public void setStatus(StatusDocumento status) {
        this.status = status;
    }

    public Propriedade getPropriedade() {
        return propriedade;
    }

    public void setPropriedade(Propriedade propriedade) {
        this.propriedade = propriedade;
    }

    public List<Condicionante> getCondicionantesList() {
        return condicionanteList;
    }

    public void setCondicionantesList(List<Condicionante> condicionantesList) {
        this.condicionanteList = condicionantesList;
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
}