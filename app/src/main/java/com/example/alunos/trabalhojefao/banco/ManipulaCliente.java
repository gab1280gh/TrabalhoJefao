package com.example.alunos.trabalhojefao.banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.EditText;

import java.util.ArrayList;

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
    public ArrayList<Cliente> retornarClientes()
    {
        Cliente cliente;
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        String sql = "select * from cliente;";
        buscador = manipulaBancoDeDados.rawQuery(sql, null);
        if(buscador.getCount()>0)
        {
            buscador.moveToFirst();
            do{
                cliente = new Cliente(buscador.getString(1), buscador.getString(2), buscador.getString(3), buscador.getString(4));
                cliente.setId((int) buscador.getLong(0));
                listaClientes.add(cliente);
            }while(buscador.moveToNext());
        }else{
            listaClientes = null;
        }
        return(listaClientes);
    }

    public void deletar(Cliente cliente) {

        try{
            manipulaBancoDeDados.delete("cliente", " cpf = "+cliente.getCpf(), null);
            //db.delete("tablename","id=? and name=?",new String[]{"1","jack"});
        }catch(Exception e){
            Log.i("Erro","Erro ao deletar");
        }

    }

    public Cliente buscaPorCpf(String cpf) {
        Cliente cliente;
        String sql = "select * from cliente where cpf = " + cpf;
        buscador = manipulaBancoDeDados.rawQuery(sql, null);
        if(buscador.getCount()>0)
        {
            buscador.moveToFirst();
            do{
                cliente = new Cliente(buscador.getString(1), buscador.getString(2), buscador.getString(3), buscador.getString(4));
                cliente.setId((int) buscador.getLong(0));
            }while(buscador.moveToNext());
        }else{
            cliente = null;
        }
        return(cliente);
    }
}
