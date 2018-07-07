package com.example.alunos.trabalhojefao.banco;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;

public class Pedido implements Parcelable {

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


    protected Pedido(Parcel in) {
        id = in.readInt();
        data = in.readString();
        fk_cliente_id = in.readInt();
    }

    public static final Creator<Pedido> CREATOR = new Creator<Pedido>() {
        @Override
        public Pedido createFromParcel(Parcel in) {
            return new Pedido(in);
        }

        @Override
        public Pedido[] newArray(int size) {
            return new Pedido[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(data);
        dest.writeInt(fk_cliente_id);
    }
}
