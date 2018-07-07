package com.example.alunos.trabalhojefao;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.alunos.trabalhojefao.adaptadores.AdaptadorPP;
import com.example.alunos.trabalhojefao.adaptadores.AdaptadorProduto;
import com.example.alunos.trabalhojefao.banco.ManipulaPedidos;
import com.example.alunos.trabalhojefao.banco.ManipulaProduto;
import com.example.alunos.trabalhojefao.banco.Pedido;
import com.example.alunos.trabalhojefao.banco.Produto;
import com.example.alunos.trabalhojefao.banco.Produtos_Pedido;

import java.util.ArrayList;

public class TelaVisuPedido extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    TextView tv_idPed, tv_idCli, tv_data;
    ListView lv_visu;
    Button bt_voltar, bt_remover;
    CheckBox cb_remover;
    ManipulaPedidos manipulaPedidos;
    ArrayList<Produto> al_produtos_pedido;
    Pedido pedido;
    ManipulaProduto manipulaProdutos;
    AlertDialog alerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_visu_pedido);

        tv_data = findViewById(R.id.tv_visu_data);
        tv_idCli = findViewById(R.id.tv_visu_idCli);
        tv_idPed = findViewById(R.id.tv_visu_id);

        lv_visu = findViewById(R.id.LV_listavisu);

        bt_voltar = findViewById(R.id.BT_visuVoltar);
        bt_remover = findViewById(R.id.BT_visuDeletar);
        cb_remover = findViewById(R.id.CB_certeza);

        bt_remover.setOnClickListener(this);
        bt_voltar.setOnClickListener(this);
        cb_remover.setOnCheckedChangeListener(this);

        pedido = (Pedido) getIntent().getParcelableExtra("pedido");

        tv_idPed.setText(String.valueOf(pedido.getId()));
        tv_idCli.setText(String.valueOf(pedido.getFk_cliente_id()));
        tv_data.setText(pedido.getData().toString());

        montarListView();

    }

    public void montarListView() {
        manipulaProdutos = new ManipulaProduto(this);
        manipulaProdutos.abrir();
        al_produtos_pedido = manipulaProdutos.retornaProdutos_Pedido(pedido.getId());
        manipulaProdutos.fechar();
        if (al_produtos_pedido != null && !al_produtos_pedido.isEmpty()) {
            lv_visu.setAdapter(new AdaptadorProduto(this, al_produtos_pedido));
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (cb_remover.isChecked()){
            bt_remover.setVisibility(View.VISIBLE);
        }else{
            bt_remover.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == bt_voltar.getId()){
            Intent it = new Intent (TelaVisuPedido.this, TelaPedidos.class);
            startActivity(it);
        }
        if (v.getId() == bt_remover.getId()){
            alertar(this);
//            manipulaPedidos = new ManipulaPedidos(this);
//            manipulaPedidos.abrir();
//            manipulaPedidos.remover(pedido);
//            manipulaPedidos.removeConexao(pedido);
//            manipulaPedidos.fechar();
        }
    }

    private void alertar(Context context) {
        AlertDialog.Builder construtor = new AlertDialog.Builder(context);
        construtor.setTitle("Falha");
        construtor.setMessage("Eu não consegui implementar esse método, não consegui entender como fazer foreign key funcionar, mas eu tentei :(");
        alerta = construtor.create();
        alerta.show();
    }
}
