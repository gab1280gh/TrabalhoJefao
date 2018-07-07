package com.example.alunos.trabalhojefao;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.alunos.trabalhojefao.adaptadores.AdaptadorCliente;
import com.example.alunos.trabalhojefao.adaptadores.AdaptadorPedidos;
import com.example.alunos.trabalhojefao.banco.Cliente;
import com.example.alunos.trabalhojefao.banco.ManipulaPedidos;
import com.example.alunos.trabalhojefao.banco.Pedido;

import java.util.ArrayList;

public class TelaPedidos extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    EditText et_cpf;
    Button bt_buscar, bt_novoPedido, bt_voltar;
    ListView lv_pedidos;
    ManipulaPedidos manipulaPedidos;
    ArrayList<Pedido> al_pedido;
    Pedido pedido;
    AlertDialog alerta;


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
        lv_pedidos.setOnItemClickListener(this);

        manipulaPedidos = new ManipulaPedidos(this);
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == bt_buscar.getId()){
            if (et_cpf.getText().toString().matches("")){
                alertar(this);
            }else{
                manipulaPedidos.abrir();
                al_pedido = manipulaPedidos.retornarPedidos(et_cpf.getText().toString());
                manipulaPedidos.fechar();
                if (al_pedido != null){
                    lv_pedidos.setAdapter(new AdaptadorPedidos(this, al_pedido));
                }
            }
        }
        if (v.getId() == bt_novoPedido.getId()){

            Intent it = new Intent (TelaPedidos.this, TelaFazPedido.class);
            startActivity(it);

        }
        if (v.getId() == bt_voltar.getId()){

            Intent it = new Intent (TelaPedidos.this, TelaPrincipal.class);
            startActivity(it);

        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        pedido = (Pedido)parent.getAdapter().getItem(position);
        Intent it = new Intent (TelaPedidos.this, TelaVisuPedido.class);
        it.putExtra("pedido", pedido);
        startActivity(it);
    }

    public void alertar(Context context)
    {
        AlertDialog.Builder construtor = new AlertDialog.Builder(context);
        construtor.setTitle("Banco");
        construtor.setMessage("Digite um CPF!");
        alerta = construtor.create();
        alerta.show();
    }
}
