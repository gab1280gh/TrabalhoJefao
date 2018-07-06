package com.example.alunos.trabalhojefao.banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class ManipulaPedidos {

    private Cursor buscador;
    private ContentValues pacoteDeInsercao;
    private CriaBanco retornaInstanciaDoBanco;
    private Context contexto;
    private SQLiteDatabase manipulaBancoDeDados;

    public ManipulaPedidos(Context c)
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

    public long inserir(Pedido pedido){

//        long posicaoBanco;
//        pacoteDeInsercao = new ContentValues();
//        pacoteDeInsercao.put("cpf", cliente.getCpf());
//        pacoteDeInsercao.put("nome", cliente.getNome());
//        pacoteDeInsercao.put("telefone", cliente.getTelefone());
//        pacoteDeInsercao.put("endereco", cliente.getEndereco());
//        posicaoBanco = manipulaBancoDeDados.insert("cliente", null,pacoteDeInsercao);
//        return(posicaoBanco);

        return 0;
    }

    public ArrayList<Pedido> retornarPedidos(String cpf){
          Pedido pedido;
          int idBusca;
          ArrayList<Pedido> listaPedidos = new ArrayList<>();
          String sqlCpf = "select id from cliente where cpf = " + cpf +";";
          buscador = manipulaBancoDeDados.rawQuery(sqlCpf, null);
          if(buscador.getCount()>0)
          {
              buscador.moveToFirst();
              do{
                  idBusca = buscador.getInt(0);
              }while(buscador.moveToNext());
          }else{
              idBusca = 0;
          }
          if (idBusca != 0){
              String sql = "select * from pedidos where fk_id_cliente = " + idBusca;
              buscador = manipulaBancoDeDados.rawQuery(sqlCpf, null);
              if(buscador.getCount()>0)
              {
                  buscador.moveToFirst();
                  do{
                      pedido = new Pedido(buscador.getInt(0), buscador.getString(1), buscador.getInt(2));
                      listaPedidos.add(pedido);
                  }while(buscador.moveToNext());
              }else{
                  listaPedidos = null;
              }
          }

          return(listaPedidos);
    }

    public void deletar(Pedido pedido) {

//        try{
//            manipulaBancoDeDados.delete("cliente", " cpf = "+cliente.getCpf(), null);
//            //db.delete("tablename","id=? and name=?",new String[]{"1","jack"});
//        }catch(Exception e){
//            Log.i("Erro","Erro ao deletar");
//        }

    }

}


