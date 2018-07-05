package com.example.alunos.trabalhojefao.banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteDAO {

    private Cursor buscador;
    private ContentValues pacoteInsercao;
    private CriaBanco dbContext;
    private SQLiteDatabase dbSet;
    private Context _context;

    public ClienteDAO(Context context){
        _context = context;
    }

    public void abrir() throws SQLException {

        dbContext = new CriaBanco(_context);
        dbSet = dbContext.getWritableDatabase();

    }

    public void fechar()
    {
        dbContext.close();
    }

    public long inserir(Cliente cliente)
    {
        long posicaoBanco;
        pacoteInsercao = new ContentValues();
        pacoteInsercao.put("nome", cliente.getNome());
        pacoteInsercao.put("telefone", cliente.getTelefone());
        pacoteInsercao.put("endereco", cliente.getEndereco());
        pacoteInsercao.put("cpf", cliente.getCpf());

        posicaoBanco = dbSet.insert("cliente", null,pacoteInsercao);
        return(posicaoBanco);
    }

    public long alterar(Cliente cliente)
    {
        long posicaoBanco;
        pacoteInsercao = new ContentValues();
        pacoteInsercao.put("nome", cliente.getNome());
        pacoteInsercao.put("telefone", cliente.getTelefone());
        pacoteInsercao.put("endereco", cliente.getEndereco());
        pacoteInsercao.put("cpf", cliente.getCpf());
        posicaoBanco = dbSet.update("cliente",pacoteInsercao, "id = " + cliente.getId() , null);
        return(posicaoBanco);
    }

    public void deletar(Cliente cliente){
        dbSet.delete("cliente"," id = " + cliente.getId(),null);
    }

    public ArrayList<Cliente> listarClientes(){
        Cliente cliente;
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente";
        buscador = dbSet.rawQuery(sql,null);
        if(buscador.getCount() > 0){
            buscador.moveToFirst();
            do{
                cliente = new Cliente(
                        buscador.getInt(buscador.getColumnIndex("id")),
                        buscador.getString(buscador.getColumnIndex("cpf")),
                        buscador.getString(buscador.getColumnIndex("telefone")),
                        buscador.getString(buscador.getColumnIndex("endereco")),
                        buscador.getString(buscador.getColumnIndex("nome"))
                );
                listaClientes.add(cliente);
            }while(buscador.moveToNext());
        }
        return (listaClientes);
    }

}
