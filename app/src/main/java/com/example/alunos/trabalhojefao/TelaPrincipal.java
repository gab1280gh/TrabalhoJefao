package com.example.alunos.trabalhojefao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TelaPrincipal extends AppCompatActivity implements View.OnClickListener {

    Button bt_clientes, bt_produtos, bt_pedidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);

        bt_clientes = findViewById(R.id.BT_clientes);
        bt_produtos = findViewById(R.id.BT_produtos);
        bt_pedidos = findViewById(R.id.BT_pedidos);

        bt_clientes.setOnClickListener(this);
        bt_produtos.setOnClickListener(this);
        bt_pedidos.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (bt_clientes.getId() == v.getId()){
            Intent it = new Intent (TelaPrincipal.this, TelaClientes.class);
            startActivity(it);
        }
        if (bt_produtos.getId() == v.getId()){
            Intent it = new Intent (TelaPrincipal.this, TelaProdutos.class);
            startActivity(it);
        }
        if (bt_pedidos.getId() == v.getId()){
            Intent it = new Intent (TelaPrincipal.this, TelaPedidos.class);
            startActivity(it);
        }
    }
}
