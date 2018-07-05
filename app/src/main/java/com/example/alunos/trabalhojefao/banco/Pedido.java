package com.example.alunos.trabalhojefao.banco;

import java.util.Date;

public class Pedido {

    private int id;

    private Date data;

    private int fk_cliente_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getFk_cliente_id() {
        return fk_cliente_id;
    }

    public void setFk_cliente_id(int fk_cliente_id) {
        this.fk_cliente_id = fk_cliente_id;
    }
}