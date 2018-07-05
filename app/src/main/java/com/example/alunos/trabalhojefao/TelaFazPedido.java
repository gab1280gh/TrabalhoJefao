package com.example.alunos.trabalhojefao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class TelaFazPedido extends AppCompatActivity implements View.OnClickListener {

    EditText et_cpf;
    Button bt_comprar, bt_cancelar;
    ListView lv_produtosPraCompra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_faz_pedido);

        bt_comprar = findViewById(R.id.BT_comprar);
        bt_cancelar = findViewById(R.id.BT_cancelaPedido);
        et_cpf = findViewById(R.id.ET_cpfFazPedido);
        lv_produtosPraCompra = findViewById(R.id.LV_pedidosCompra);

        bt_cancelar.setOnClickListener(this);
        bt_comprar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == bt_cancelar.getId()){
            Intent it = new Intent (TelaFazPedido.this, TelaPedidos.class);
            startActivity(it);
        }
    }
}
