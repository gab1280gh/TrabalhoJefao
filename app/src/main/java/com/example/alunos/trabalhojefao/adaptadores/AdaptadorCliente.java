package com.example.alunos.trabalhojefao.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.alunos.trabalhojefao.R;
import com.example.alunos.trabalhojefao.banco.Cliente;

import java.util.ArrayList;

public class AdaptadorCliente extends BaseAdapter {

    private Context context;
    private ArrayList<Cliente> al_lista;

    public AdaptadorCliente(Context context, ArrayList<Cliente> lista) {
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
        Cliente cliente = al_lista.get(position);
        LayoutInflater inflador = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View subTela = inflador.inflate(R.layout.itempessoa, null);

        TextView tv_nome = subTela.findViewById(R.id.tv_nome);
        TextView tv_telefone = subTela.findViewById(R.id.tv_telefone);
        TextView tv_id = subTela.findViewById(R.id.tv_id);

        tv_nome.setText(cliente.getNome());
        tv_telefone.setText(cliente.getTelefone());
        tv_id.setText((Long.toString(cliente.getId())));

        return (subTela);
    }
}
