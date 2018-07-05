package com.example.alunos.trabalhojefao.banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutoDAO {


    private Cursor buscador;
    private ContentValues pacoteInsercao;
    private CriaBanco dbContext;
    private SQLiteDatabase dbSet;
    private Context _context;

    public ProdutoDAO(Context context){
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

    public long inserir(Produto produto)
    {
        long posicaoBanco;
        pacoteInsercao = new ContentValues();
        pacoteInsercao.put("descricao", produto.getDescricao());
        pacoteInsercao.put("valor", produto.getValor());
        pacoteInsercao.put("foto", produto.getFoto());
        posicaoBanco = dbSet.insert("produto", null,pacoteInsercao);
        return(posicaoBanco);
    }

    public long alterar(Produto produto)
    {
        long posicaoBanco;
        pacoteInsercao = new ContentValues();
        pacoteInsercao.put("descricao", produto.getDescricao());
        pacoteInsercao.put("valor", produto.getValor());
        pacoteInsercao.put("foto", produto.getFoto());
        posicaoBanco = dbSet.update("cliente",pacoteInsercao, "id = " + produto.getId() , null);
        return(posicaoBanco);
    }

    public void deletar(Produto produto){
        dbSet.delete("produto"," id = " + produto.getId(),null);
    }

    public ArrayList<Produto> listarProdutos(){
        Produto produto;
        ArrayList<Produto> listaProdutos = new ArrayList<>();
        String sql = "SELECT * FROM produto";
        buscador = dbSet.rawQuery(sql,null);
        if(buscador.getCount() > 0){
            buscador.moveToFirst();
            do{
                produto = new Produto(
                        buscador.getInt(buscador.getColumnIndex("id")),
                        buscador.getString(buscador.getColumnIndex("descricao")),
                        buscador.getDouble(buscador.getColumnIndex("valor")),
                        buscador.getString(buscador.getColumnIndex("foto"))
                );
                listaProdutos.add(produto);
            }while(buscador.moveToNext());
        }
        return (listaProdutos);
    }

}
