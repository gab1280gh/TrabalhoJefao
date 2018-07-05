package com.example.alunos.trabalhojefao.banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ManipulaCliente {

    private Cursor buscador;
    private ContentValues pacoteDeInsercao;
    private CriaBanco retornaInstanciaDoBanco;
    private Context contexto;
    private SQLiteDatabase manipulaBancoDeDados;

    public ManipulaCliente(Context c)
    {
        contexto = c;
    }

    public void abrir() throws SQLException
    {
        retornaInstanciaDoBanco = new CriaBanco(contexto);
        manipulaBancoDeDados = retornaInstanciaDoBanco.getWritableDatabase();
    }
    public void fechar()
    {
        retornaInstanciaDoBanco.close();
    }

    public long inserir(Cliente cliente)
    {
        long posicaoBanco;
        pacoteDeInsercao = new ContentValues();
        pacoteDeInsercao.put("cpf", cliente.getCpf());
        pacoteDeInsercao.put("nome", cliente.getNome());
        pacoteDeInsercao.put("telefone", cliente.getTelefone());
        pacoteDeInsercao.put("endereco", cliente.getEndereco());
        posicaoBanco = manipulaBancoDeDados.insert("cliente", null,pacoteDeInsercao);
        return(posicaoBanco);
    }

}
