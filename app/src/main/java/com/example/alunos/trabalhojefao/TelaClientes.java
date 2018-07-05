package com.example.alunos.trabalhojefao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.alunos.trabalhojefao.banco.Cliente;
import com.example.alunos.trabalhojefao.banco.ManipulaCliente;

public class TelaClientes extends AppCompatActivity implements View.OnClickListener {

    EditText et_nomeCliente, et_telefoneCliente, et_enderecoCliente, et_cpf;
    Button bt_inserirCliente, bt_atualizarCliente, bt_removerCliente, bt_voltar;
    ListView lv_clientes;
    ManipulaCliente manipulador;

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
        et_cpf = findViewById(R.id.et_cpf);

        lv_clientes = findViewById(R.id.LV_cli);

        bt_removerCliente.setOnClickListener(this);
        bt_atualizarCliente.setOnClickListener(this);
        bt_inserirCliente.setOnClickListener(this);
        bt_voltar.setOnClickListener(this);

        manipulador = new ManipulaCliente(this);

    }

    @Override
    public void onClick(View v) {

        if (bt_inserirCliente.getId() == v.getId()){
            Cliente cliente = new Cliente();
            cliente.setCpf(et_cpf.getText().toString());
            cliente.setEndereco(et_enderecoCliente.getText().toString());
            cliente.setNome(et_nomeCliente.getText().toString());
            cliente.setTelefone(et_telefoneCliente.getText().toString());
            manipulador.abrir();
            long verificaInsercao = manipulador.inserir(cliente);
            mensagem("Inseriu cliente: " + verificaInsercao);
            manipulador.fechar();
            et_nomeCliente.setText("");
            et_cpf.setText("");
            et_enderecoCliente.setText("");
            et_telefoneCliente.setText("");
        }

        if (bt_voltar.getId() == v.getId()){

            Intent it = new Intent(TelaClientes.this, TelaPrincipal.class);
            startActivity(it);

        }

    }

    public void mensagem(String msg)
    {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}
