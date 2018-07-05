package com.example.alunos.trabalhojefao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class TelaClientes extends AppCompatActivity implements View.OnClickListener {

    EditText et_nomeCliente, et_telefoneCliente, et_enderecoCliente;
    Button bt_inserirCliente, bt_atualizarCliente, bt_removerCliente;
    ListView lv_clientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_clientes);

       bt_inserirCliente = findViewById(R.id.BT_InsCliente);
       bt_atualizarCliente = findViewById(R.id.BT_attCliente);
       bt_removerCliente = findViewById(R.id.BT_delCliente);

       et_nomeCliente = findViewById(R.id.ET_nomeCli);
       et_enderecoCliente = findViewById(R.id.ET_endeCli);
       et_telefoneCliente = findViewById(R.id.ET_teleCli);

       lv_clientes = findViewById(R.id.LV_cli);

       bt_removerCliente.setOnClickListener(this);
       bt_atualizarCliente.setOnClickListener(this);
       bt_inserirCliente.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

    }
}
