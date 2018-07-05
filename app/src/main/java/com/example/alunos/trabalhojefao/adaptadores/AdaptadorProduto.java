package com.example.alunos.trabalhojefao.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.alunos.trabalhojefao.R;
import com.example.alunos.trabalhojefao.banco.Produto;

import java.util.ArrayList;

public class AdaptadorProduto extends BaseAdapter {

    private Context context;
    private ArrayList<Produto> al_lista;

    public AdaptadorProduto(Context context, ArrayList<Produto> lista) {
        this.context = context;
        this.al_lista = lista;
    }

    @Override
    public int getCount()
    {
        return al_lista.size();
    }
    @Override
    public Object getItem(int position)
    {
        return al_lista.get(position);
    }
    @Override
    public long getItemId(int position)
    {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Produto produto = al_lista.get(position);
        LayoutInflater inflador = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View subTela = inflador.inflate(R.layout.itemproduto, null);

        TextView tv_sub_id_caminhao = subTela.findViewById(R.id.tv_sub_id_caminhao);
        TextView tv_sub_produtoDescricao = subTela.findViewById(R.id.tv_sub_produtoDescricao);
        TextView tv_sub_produtoValor = subTela.findViewById(R.id.tv_sub_produtoValor);
        TextView tv_sub_produtoFoto = subTela.findViewById(R.id.tv_sub_produtoFoto);

        tv_sub_id_caminhao.setText(String.valueOf(produto.getId()));
        tv_sub_produtoDescricao.setText(produto.getDescricao());
        tv_sub_produtoValor.setText(String.valueOf(produto.getValor()));
        tv_sub_produtoFoto.setText(produto.getFoto());

        return subTela;
    }

}