package br.com.barbara.sistema_controle_documental.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(name = "cpf_cnpj", nullable = false, unique = true)
    private String cpfCnpj; // cpf ou cnpj

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Propriedade> propriedadeList;

    public List<Propriedade> getPropriedadeList() {
        return propriedadeList;
    }

    public void setPropriedadeList(List<Propriedade> propriedadeList) {
        this.propriedadeList = propriedadeList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String identificacao) {
        this.cpfCnpj = identificacao;
    }
}