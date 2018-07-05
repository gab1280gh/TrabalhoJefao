package com.example.alunos.trabalhojefao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class TelaClientes extends AppCompatActivity implements View.OnClickListener {

    EditText et_nomeCliente, et_telefoneCliente, et_enderecoCliente;
    Button bt_inserirCliente, bt_atualizarCliente, bt_removerCliente, bt_voltar;
    ListView lv_clientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_clientes);

        bt_inserirCliente = findViewById(R.id.BT_InsCliente);
        bt_atualizarCliente = findViewById(R.id.BT_attCliente);
        bt_removerCliente = findViewById(R.id.BT_delCliente);
        bt_voltar = findViewById(R.id.BT_cliVoltar);

        et_nomeCliente = findViewById(R.id.ET_nomeCli);
        et_enderecoCliente = findViewById(R.id.ET_endeCli);
        et_telefoneCliente = findViewById(R.id.ET_teleCli);

        lv_clientes = findViewById(R.id.LV_cli);

        bt_removerCliente.setOnClickListener(this);
        bt_atualizarCliente.setOnClickListener(this);
        bt_inserirCliente.setOnClickListener(this);
        bt_voltar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (bt_voltar.getId() == v.getId()){

            Intent it = new Intent(TelaClientes.this, TelaPrincipal.class);
            startActivity(it);

        }

    }
}
