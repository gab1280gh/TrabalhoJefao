package com.example.alunos.trabalhojefao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class TelaPedidos extends AppCompatActivity implements View.OnClickListener {

    EditText et_cpf;
    Button bt_buscar, bt_novoPedido, bt_voltar;
    ListView lv_pedidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_pedidos);

        bt_buscar = findViewById(R.id.BT_buscarPed);
        bt_novoPedido = findViewById(R.id.BT_fazerPed);
        bt_voltar = findViewById(R.id.BT_pedVoltar);

        et_cpf = findViewById(R.id.ET_buscaPed);

        lv_pedidos = findViewById(R.id.LV_pedidos);

        bt_novoPedido.setOnClickListener(this);
        bt_buscar.setOnClickListener(this);
        bt_voltar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == bt_novoPedido.getId()){

            Intent it = new Intent (TelaPedidos.this, TelaFazPedido.class);
            startActivity(it);

        }
        if (v.getId() == bt_voltar.getId()){

            Intent it = new Intent (TelaPedidos.this, TelaPrincipal.class);
            startActivity(it);

        }

    }
}
