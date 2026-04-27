package br.com.barbara.sistema_controle_documental.model.enuns;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoDocumento {

    CAR("Cadastro Ambiental Rural"),
    PRA("Programa de Regularização Ambiental"),
    PRADA("Projeto de Recomposição de Áreas Degradadas e Alteradas"),
    LP("Licença Prévia"),
    LI("Licença de Instalação"),
    LO("Licença de Operação"),
    AUTORIZACAO_SUPRESSAO("Autorização para Supressão de Vegetação"),
    AUTORIZACAO_QUEIMA("Autorização de Queima Controlada"),
    OUTORGA("Outorga de Uso de Água"),
    CERTIFICADO_DISPENSA("Certificado de Dispensa de Licença"),

    OUTROS("Outros Documentos Ambientais");

    private String descricao;

    TipoDocumento(String descricao) {
        this.descricao = descricao;
    }

    @JsonValue
    public String getDescricao() {
        return descricao;
    }
}