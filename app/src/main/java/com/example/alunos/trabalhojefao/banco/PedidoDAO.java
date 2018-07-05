package com.example.alunos.trabalhojefao.banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;

public class PedidoDAO {

    private Cursor buscador;
    private ContentValues pacoteInsercao;
    private CriaBanco dbContext;
    private SQLiteDatabase dbSet;
    private Context _context;

    public PedidoDAO(Context context) {
        _context = context;
    }

    public void abrir() throws SQLException {

        dbContext = new CriaBanco(_context);
        dbSet = dbContext.getWritableDatabase();

    }

    public void fechar() {
        dbContext.close();
    }

    public long inserir(Pedido pedido, ArrayList<Produto> produtos, int quantidade) {
        long posicaoBanco;
        pacoteInsercao = new ContentValues();
        pacoteInsercao.put("data", pedido.getData());
        pacoteInsercao.put("fk_id_cliente", pedido.getFk_cliente_id());
        // pacoteInsercao.put("cpf", pedido.fk_cliente_id());

        posicaoBanco = dbSet.insert("pedido", null, pacoteInsercao);
        for (Produto produto : produtos) {
            ContentValues pacoteInsercaoTemp = new ContentValues();
            pacoteInsercaoTemp.put("quantidade", quantidade);
            pacoteInsercaoTemp.put("fk_id_produto", produto.getId());
            pacoteInsercaoTemp.put("fk_id_pedido", posicaoBanco);
            dbSet.insert("produto_pedido", null, pacoteInsercaoTemp);
        }
        return (posicaoBanco);
    }

    public long alterar(Pedido pedido) {
        long posicaoBanco;
        pacoteInsercao = new ContentValues();
        pacoteInsercao.put("data", pedido.getData());
        pacoteInsercao.put("fk_id_cliente", pedido.getFk_cliente_id());
        //pacoteInsercao.put("cpf", cliente.getCpf());
        posicaoBanco = dbSet.update("pedido", pacoteInsercao, "id = " + pedido.getId(), null);
        return (posicaoBanco);
    }

    public void deletar(Pedido pedido) {
        dbSet.delete("pedido", " id = " + pedido.getId(), null);
    }

    public ArrayList<Pedido> listarPedidos() {
        Pedido pedido;
        ArrayList<Pedido> listaPedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedido";
        buscador = dbSet.rawQuery(sql, null);
        if (buscador.getCount() > 0) {
            buscador.moveToFirst();
            do {
                pedido = new Pedido(
                        buscador.getInt(buscador.getColumnIndex("id")),
                        buscador.getString(buscador.getColumnIndex("data")),
                        buscador.getInt(buscador.getColumnIndex("fk_id_cliente"))
                );
                listaPedidos.add(pedido);
            } while (buscador.moveToNext());
        }
        return (listaPedidos);
    }

    public ArrayList<Pedido> listarPedidosProCliente(int cliente_id) {
        Pedido pedido;
        ArrayList<Pedido> listaPedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedido where fk_id_cliente = " + cliente_id;
        buscador = dbSet.rawQuery(sql, null);
        if (buscador.getCount() > 0) {
            buscador.moveToFirst();
            do {
                pedido = new Pedido(
                        buscador.getInt(buscador.getColumnIndex("id")),
                        buscador.getString(buscador.getColumnIndex("data")),
                        buscador.getInt(buscador.getColumnIndex("fk_id_cliente"))
                );
                listaPedidos.add(pedido);
            } while (buscador.moveToNext());
        }
        return (listaPedidos);
    }

}
