package com.example.alunos.trabalhojefao.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.alunos.trabalhojefao.R;
import com.example.alunos.trabalhojefao.TelaVisuPedido;
import com.example.alunos.trabalhojefao.banco.Pedido;
import com.example.alunos.trabalhojefao.banco.Produto;
import com.example.alunos.trabalhojefao.banco.Produtos_Pedido;

import java.util.ArrayList;

public class AdaptadorPP extends BaseAdapter {

    private Context context;
    private ArrayList<Produto> al_lista;

    public AdaptadorPP(Context context, ArrayList<Produto> lista) {
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
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Produto produto = al_lista.get(position);
        LayoutInflater inflador = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View subTela = inflador.inflate(R.layout.itemvisupedido, null);

        TextView tv_data = subTela.findViewById(R.id.tv_data);
        TextView tv_idPed = subTela.findViewById(R.id.tv_idPed);
        TextView tv_idCli = subTela.findViewById(R.id.tv_idCli);

//        tv_data.setText(pedido.getData());
//        tv_idPed.setText((Long.toString(pedido.getId())));
//        tv_idCli.setText((Long.toString(pedido.getFk_cliente_id())));

        return (subTela);
    }
}
