package com.example.alunos.trabalhojefao;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.alunos.trabalhojefao.banco.Cliente;
import com.example.alunos.trabalhojefao.banco.ManipulaCliente;
import com.example.alunos.trabalhojefao.banco.ManipulaPedidos;
import com.example.alunos.trabalhojefao.banco.ManipulaProduto;
import com.example.alunos.trabalhojefao.banco.Produto;

import java.util.List;

public class TelaFazPedido extends AppCompatActivity implements View.OnClickListener {

    EditText et_cpf;
    Button bt_comprar, bt_cancelar;
    EditText et_codProd;
    Cliente cliente;
    Produto produto;
    ManipulaCliente manipulaCliente;
    ManipulaPedidos manipulaPedidos;
    ManipulaProduto manipulaProduto;
    AlertDialog alerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_faz_pedido);

        bt_comprar = findViewById(R.id.BT_comprar);
        bt_cancelar = findViewById(R.id.BT_cancelaPedido);
        et_cpf = findViewById(R.id.ET_cpfFazPedido);
        et_codProd = findViewById(R.id.ET_idProdFazPed);

        bt_cancelar.setOnClickListener(this);
        bt_comprar.setOnClickListener(this);
        manipulaCliente = new ManipulaCliente(this);
        manipulaPedidos = new ManipulaPedidos(this);
        manipulaProduto = new ManipulaProduto(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == bt_comprar.getId()){

            if (et_cpf.getText().toString().matches("") && et_codProd.getText().toString().matches("")){
                alertar(this);
            }else{
                manipulaProduto.abrir();
                produto = manipulaProduto.buscaProdutoPorId(Integer.parseInt(et_codProd.getText().toString()));
                manipulaProduto.fechar();
                manipulaCliente.abrir();
                cliente = manipulaCliente.buscaPorCpf(et_cpf.getText().toString());
                manipulaCliente.fechar();

                if (produto != null && cliente != null){
                    manipulaPedidos.abrir();
                    manipulaPedidos.registrarCompra(et_codProd.getText().toString(), cliente);
                    manipulaCliente.fechar();
                }else{
                    alertar2(this);
                }

            }

        }

        if (v.getId() == bt_cancelar.getId()){
            Intent it = new Intent (TelaFazPedido.this, TelaPedidos.class);
            startActivity(it);
        }
    }


    public void alertar(Context context)
    {
        AlertDialog.Builder construtor = new AlertDialog.Builder(context);
        construtor.setTitle("Banco");
        construtor.setMessage("Digite um ID e CPF!");
        alerta = construtor.create();
        alerta.show();
    }
    public void alertar2(Context context)
    {
        AlertDialog.Builder construtor = new AlertDialog.Builder(context);
        construtor.setTitle("Banco");
        construtor.setMessage("Id produto ou CPF não encontrado!");
        alerta = construtor.create();
        alerta.show();
    }

}
