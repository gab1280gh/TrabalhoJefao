package com.example.alunos.trabalhojefao.banco;

public class Produto {

    private int id;

    private String descricao;

    private Double valor;

    private String foto;

    public Produto(int id, String descricao, Double valor, String foto) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.foto = foto;
    }

    public Produto(String descricao, Double valor, String foto) {
        this.descricao = descricao;
        this.valor = valor;
        this.foto = foto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }


}