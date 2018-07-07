package com.example.alunos.trabalhojefao.banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ManipulaPedidos {

    private Cursor buscador;
    private ContentValues pacoteDeInsercao;
    private CriaBanco retornaInstanciaDoBanco;
    private Context contexto;
    private SQLiteDatabase manipulaBancoDeDados;
    private Produto produto;

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
          String sql = "select * from pedido inner join cliente on pedido.fk_id_cliente = cliente.id where cliente.cpf = " + cpf + ";";
          buscador = manipulaBancoDeDados.rawQuery(sql, null);
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
          return listaPedidos;
    }

    public void deletar(Pedido pedido) {

//        try{
//            manipulaBancoDeDados.delete("cliente", " cpf = "+cliente.getCpf(), null);
//            //db.delete("tablename","id=? and name=?",new String[]{"1","jack"});
//        }catch(Exception e){
//            Log.i("Erro","Erro ao deletar");
//        }

    }

    public void registrarCompra(ArrayList<Integer> listaProds, Cliente cliente) {

        pacoteDeInsercao = new ContentValues();
        Date data = Calendar.getInstance().getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String hoje = sdf.format(data);

        pacoteDeInsercao.put("data", hoje);
        pacoteDeInsercao.put("fk_id_cliente", cliente.getId());
        manipulaBancoDeDados.insert("pedido", null,pacoteDeInsercao);

        String sql = "select id from pedido ORDER BY id DESC LIMIT 1 ";
        buscador = manipulaBancoDeDados.rawQuery(sql, null);

        buscador.moveToFirst();

        adicionarProdAoPed(buscador.getInt(0), listaProds);

    }

    public void adicionarProdAoPed(Integer id, ArrayList<Integer> listaProds) {

        for(int i = 0; i < listaProds.size(); i++){
            pacoteDeInsercao = new ContentValues();
            int idProd = listaProds.get(i);
            pacoteDeInsercao.put("fk_id_pedido", id);
            pacoteDeInsercao.put("fk_id_produto", idProd);
            manipulaBancoDeDados.insert("produto_pedido", null,pacoteDeInsercao);
        }
    }

    public ArrayList<Produto> retornaProdutos_Pedido(int id) {
        Produto produto;
        ArrayList<Produto> listaPP = new ArrayList<>();
        String sql = "select produto.id, produto.descricao, produto.valor from produto inner join produto_pedido on produto.id = produto_pedido.fk_id_produto where produto_pedido.fk_id_pedido = " + id;
        buscador = manipulaBancoDeDados.rawQuery(sql, null);
        if(buscador.getCount()>0)
        {
            buscador.moveToFirst();
            do{
                produto = new Produto (buscador.getInt(0), buscador.getString(1), buscador.getInt(2));
                listaPP.add(produto);
            }while(buscador.moveToNext());
        }else{
            listaPP = null;
        }
        return listaPP;
    }
}


