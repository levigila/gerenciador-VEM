package com.javangular.urbana.dto;
import com.javangular.urbana.model.TipoCartao;

public class CartaoDTO {

    private Long id;
    private String numeroCartao;
    private String nomeCartao;
    private Boolean statusCartao;
    private TipoCartao tipoCartao;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getNomeCartao() {
        return nomeCartao;
    }

    public void setNomeCartao(String nomeCartao) {
        this.nomeCartao = nomeCartao;
    }

    public boolean isStatusCartao() {
        return statusCartao;
    }

    public void setStatusCartao(boolean statusCartao) {
        this.statusCartao = statusCartao;
    }

    public TipoCartao getTipoCartao() {
        return tipoCartao;
    }

    public void setTipoCartao(TipoCartao tipoCartao) {
        this.tipoCartao = tipoCartao;
    }

    public Boolean getStatusCartao() {
        return statusCartao; // Retorna o valor do atributo statusCartao
    }
}
