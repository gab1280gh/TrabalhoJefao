package com.example.alunos.trabalhojefao.banco;

public class Cliente {

    private int id;

    private String cpf;

    private String nome;

    private String telefone;

<<<<<<< HEAD
    private String endereco;
=======
>>>>>>> 5a5eed6dd11b50b9f2626444de20fd30446cb6a4

    private String endereco;

    public Cliente(int id, String cpf, String telefone, String endereco, String nome) {
        this.id = id;
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;
        this.nome = nome;
    }

<<<<<<< HEAD
=======

>>>>>>> 5a5eed6dd11b50b9f2626444de20fd30446cb6a4
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}