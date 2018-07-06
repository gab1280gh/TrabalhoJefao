package com.example.alunos.trabalhojefao;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.alunos.trabalhojefao.adaptadores.AdaptadorCliente;
import com.example.alunos.trabalhojefao.banco.Cliente;
import com.example.alunos.trabalhojefao.banco.ManipulaCliente;

import java.util.ArrayList;

public class TelaClientes extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    EditText et_nomeCliente, et_telefoneCliente, et_enderecoCliente, et_cpf;
    Button bt_inserirCliente, bt_atualizarCliente, bt_removerCliente, bt_voltar;
    ListView lv_clientes;
    ManipulaCliente manipulador;
    ArrayList<Cliente> al_cliente;
    AlertDialog alerta;
    Cliente cliente;
    MediaPlayer mediaPlayer1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_clientes);

        mediaPlayer1 = MediaPlayer.create(this, R.raw.beep03);

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
        lv_clientes.setOnItemClickListener(this);

        manipulador = new ManipulaCliente(this);

        montarListView();

    }

    private void montarListView() {

        manipulador = new ManipulaCliente(this);
        manipulador.abrir();
        al_cliente = manipulador.retornarClientes();
        manipulador.fechar();
        if (al_cliente != null){
            lv_clientes.setAdapter(new AdaptadorCliente(this, al_cliente));
            Log.i("Teste", "Existem " + al_cliente.size() + " caminhoes registrados");
            //adaptadorSimples = new ArrayAdapter(this, android.R.layout.simple_list_item_1, al_caminhoes);
            //lv_caminhoes.setAdapter(adaptadorSimples);
        }
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
            alertar(this);
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

        if (v.getId() == bt_removerCliente.getId()){

            String cpf = et_cpf.getText().toString();
            String nome = et_nomeCliente.getText().toString();
            String telefone = et_telefoneCliente.getText().toString();
            String endereco = et_enderecoCliente.getText().toString();

            cliente = new Cliente(cpf, nome, endereco, telefone);
            manipulador.abrir();
            manipulador.deletar(cliente);
            manipulador.fechar();
            tocarLocal();
            alertar(this);
        }

    }

    public void tocarLocal()
    {
        mediaPlayer1.start();
    }

    public void alertar(Context context)
    {
        AlertDialog.Builder construtor = new AlertDialog.Builder(context);
        construtor.setTitle("Banco");
        construtor.setMessage("Sucesso!");
        alerta = construtor.create();
        alerta.show();
    }

    public void mensagem(String msg)
    {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        cliente = (Cliente)parent.getAdapter().getItem(position);
        et_nomeCliente.setText(cliente.getNome());
        et_telefoneCliente.setText(cliente.getTelefone());
        et_cpf.setText(String.valueOf(cliente.getCpf()));
        et_enderecoCliente.setText(String.valueOf(cliente.getEndereco()));
    }
}
