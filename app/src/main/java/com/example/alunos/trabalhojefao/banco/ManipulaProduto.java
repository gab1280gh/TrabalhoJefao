package com.example.alunos.trabalhojefao.banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class ManipulaProduto {

    private Cursor buscador;
    private ContentValues pacoteDeInsercao;
    private CriaBanco retornaInstanciaDoBanco;
    private Context contexto;
    private SQLiteDatabase manipulaBancoDeDados;
    public ManipulaProduto(Context c) { contexto = c; }
    public void abrir() throws SQLException
    {
        retornaInstanciaDoBanco = new CriaBanco(contexto);
        manipulaBancoDeDados = retornaInstanciaDoBanco.getWritableDatabase();
    }
    public void fechar()
    {
        retornaInstanciaDoBanco.close();
    }
    public long inserirProduto(Produto produto) {
        long posicaoBanco;
        pacoteDeInsercao = new ContentValues();
        pacoteDeInsercao.put("descricao", produto.getDescricao());
        pacoteDeInsercao.put("valor", produto.getValor());
        pacoteDeInsercao.put("foto", produto.getFoto());
        posicaoBanco = manipulaBancoDeDados.insert("produto", null, pacoteDeInsercao);
        return (posicaoBanco);
    }

    public long alterarProduto(Produto produto) {
        long posicaoBanco;
        pacoteDeInsercao = new ContentValues();
        pacoteDeInsercao.put("descricao", produto.getDescricao());
        pacoteDeInsercao.put("valor", produto.getValor());
        pacoteDeInsercao.put("foto", produto.getFoto());
        posicaoBanco = manipulaBancoDeDados.update("produto", pacoteDeInsercao, "id_produto" + " = " + produto.getId(), null);
        return posicaoBanco;
    }

    public void deletarProduto(Produto produto) {
        manipulaBancoDeDados.delete("produto", "id_produto" + " = " + produto.getId(), null);
    }

    public ArrayList<Produto> retornaProduto() {
        Produto produto;
        ArrayList<Produto> listaProduto = new ArrayList<>();
        String sql = "select * from produto;";
        buscador = manipulaBancoDeDados.rawQuery(sql, null);
        if (buscador.getCount() > 0) {
            buscador.moveToFirst();
            do {
                produto = new Produto(buscador.getInt(0), buscador.getString(1), buscador.getDouble(2), buscador.getString(3));
                listaProduto.add(produto);
            } while (buscador.moveToNext());
        } else {
            listaProduto = null;
        }
        return listaProduto;
    }

    public Produto buscaProdutoPorId(int s) {

        Produto produto;
        String sql = "select * from produto where id = " + s;
        buscador = manipulaBancoDeDados.rawQuery(sql, null);
        if(buscador.getCount()>0)
        {
            buscador.moveToFirst();
            do{
                produto = new Produto(buscador.getInt(0), buscador.getString(2), buscador.getDouble(3));
            }while(buscador.moveToNext());
        }else{
            produto = null;
        }
        return(produto);
    }


    public ArrayList<Produto> retornaProdutos_Pedido(int id) {

        Produto produto;
        ArrayList<Produto> listaPP = new ArrayList<>();
        String sql = "select produto.id, produto.descricao, produto.valor from produto inner join produto_pedido on produto.id = produto_pedido.fk_id_produto where produto_pedido.fk_id_pedido =" +id;
        buscador = manipulaBancoDeDados.rawQuery(sql, null);
        if (buscador.getCount() > 0) {
            buscador.moveToFirst();
            do {
                produto = new Produto();
                int idProd = buscador.getInt(0);
                String desc = buscador.getString(1);
                Double valor = buscador.getDouble(2);
                produto.setValor(valor);
                produto.setId(idProd);
                produto.setDescricao(desc);
                listaPP.add(produto);
            } while (buscador.moveToNext());
        } else {
            listaPP = null;
        }
        return listaPP;

    }

    public void atualizar(Produto produto) {
        try{
            pacoteDeInsercao = new ContentValues();
            pacoteDeInsercao.put("descricao", produto.getDescricao());
            pacoteDeInsercao.put("valor", produto.getValor());
            manipulaBancoDeDados.update("produto", pacoteDeInsercao,  "id = " + produto.getId(), null);
            //update(String table, ContentValues values, String whereClause, String[] whereArgs)
        }catch(Exception e){
            Log.i("Erro","Erro ao alterar");
        }
    }
}