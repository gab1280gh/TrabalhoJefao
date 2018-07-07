package com.example.alunos.trabalhojefao.banco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CriaBanco extends SQLiteOpenHelper {


    public CriaBanco(Context context) {
        super(context, "trabalho_final", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE pedido(id integer PRIMARY KEY AUTOINCREMENT NOT NULL, data text NOT NULL,fk_id_cliente, FOREIGN KEY(fk_id_cliente) REFERENCES cliente(id));");
        db.execSQL("CREATE TABLE produto_pedido(id_produto_pedido integer PRIMARY KEY AUTOINCREMENT NOT NULL,quantidade integer NOT NULL, fk_id_produto,fk_id_pedido, FOREIGN KEY(fk_id_produto) REFERENCES produto(id), FOREIGN KEY(fk_id_pedido) REFERENCES pedido(id) );");
        db.execSQL("CREATE TABLE produto(id integer PRIMARY KEY AUTOINCREMENT NOT NULL, descricao text NOT NULL, valor real NOT NULL, foto text);");
        db.execSQL("CREATE TABLE cliente(id integer PRIMARY KEY AUTOINCREMENT NOT NULL, cpf text NOT NULL, nome text NOT NULL, telefone text NOT NULL, endereco text NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS produto_pedido");
        db.execSQL("DROP TABLE IF EXISTS produto");
        db.execSQL("DROP TABLE IF EXISTS cliente");
        db.execSQL("DROP TABLE IF EXISTS pedido");
        this.onCreate(db);
    }
}
