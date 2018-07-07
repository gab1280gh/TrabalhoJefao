package com.example.alunos.trabalhojefao.banco;

import java.io.Serializable;
import java.util.Date;

public class Pedido implements Serializable {

    private int id;

    private String data;

    private int fk_cliente_id;

    public Pedido(int id, String data, int fk_cliente_id) {
        this.id = id;
        this.data = data;
        this.fk_cliente_id = fk_cliente_id;
    }

    public Pedido(int id, int i, int fk_cliente_id) {
        this.id = id;
        this.data = data;
        this.fk_cliente_id = fk_cliente_id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getFk_cliente_id() {
        return fk_cliente_id;
    }

    public void setFk_cliente_id(int fk_cliente_id) {
        this.fk_cliente_id = fk_cliente_id;
    }



}
