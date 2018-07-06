package com.example.alunos.trabalhojefao.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.alunos.trabalhojefao.R;
import com.example.alunos.trabalhojefao.banco.Cliente;
import com.example.alunos.trabalhojefao.banco.Pedido;

import java.util.ArrayList;

public class AdaptadorPedidos extends BaseAdapter {

    private Context context;
    private ArrayList<Pedido> al_lista;

    public AdaptadorPedidos(Context context, ArrayList<Pedido> lista) {
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
        Pedido pedido = al_lista.get(position);
        LayoutInflater inflador = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View subTela = inflador.inflate(R.layout.itempedido, null);

        TextView tv_data = subTela.findViewById(R.id.tv_data);
        TextView tv_id = subTela.findViewById(R.id.tv_idCli);

        tv_data.setText(pedido.getData());
        tv_id.setText((Long.toString(pedido.getFk_cliente_id())));

        return (subTela);
    }
}
