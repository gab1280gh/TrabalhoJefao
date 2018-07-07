package com.example.alunos.trabalhojefao.banco;

public class Produtos_Pedido {

    private int id_produto_pedido;
    private int fk_id_pedido;
    private int fk_id_produto;

    public Produtos_Pedido() {
    }

    public Produtos_Pedido(int a, int b, int c) {

        this.id_produto_pedido = a;
        this.fk_id_produto = b;
        this.fk_id_pedido = c;

    }

    public int getId_produto_pedido() {
        return id_produto_pedido;
    }

    public void setId_produto_pedido(int id_produto_pedido) {
        this.id_produto_pedido = id_produto_pedido;
    }

    public int getFk_id_pedido() {
        return fk_id_pedido;
    }

    public void setFk_id_pedido(int fk_id_pedido) {
        this.fk_id_pedido = fk_id_pedido;
    }

    public int getFk_id_produto() {
        return fk_id_produto;
    }

    public void setFk_id_produto(int fk_id_produto) {
        this.fk_id_produto = fk_id_produto;
    }

}

