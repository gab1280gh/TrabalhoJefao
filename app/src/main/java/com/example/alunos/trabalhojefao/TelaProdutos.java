package com.example.alunos.trabalhojefao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.alunos.trabalhojefao.adaptadores.AdaptadorProduto;
import com.example.alunos.trabalhojefao.banco.ManipulaProduto;
import com.example.alunos.trabalhojefao.banco.Produto;

import java.util.ArrayList;

public class TelaProdutos extends AppCompatActivity implements View.OnClickListener {

    Button bt_inserirProduto, bt_alterarProduto, bt_deletarProduto, bt_voltar;
    EditText et_descricaoProduto, et_valorProduto;
    ManipulaProduto manipulaProduto;
    ArrayList<Produto> al_produto;
    ListView lv_produto;
    Produto produtoSelecionado;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_produtos);

        bt_inserirProduto = findViewById(R.id.bt_inserirProduto);
        bt_alterarProduto = findViewById(R.id.bt_alterarProduto);
        bt_deletarProduto = findViewById(R.id.bt_deletarProduto);
        bt_voltar = findViewById(R.id.BT_prodVoltar);

        et_descricaoProduto = findViewById(R.id.et_descricaoProduto);
        et_valorProduto = findViewById(R.id.et_valorProduto);
        lv_produto = findViewById(R.id.lv_produto);

        bt_inserirProduto.setOnClickListener(this);
        bt_voltar.setOnClickListener(this);

        lv_produto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                produtoSelecionado = (Produto) lv_produto.getItemAtPosition(position);
                et_descricaoProduto.setText(produtoSelecionado.getDescricao());
                et_valorProduto.setText(String.valueOf(produtoSelecionado.getValor()));
            }
        });
        montarListView();
    }

    public void montarListView() {
        manipulaProduto = new ManipulaProduto(this);
        manipulaProduto.abrir();
        al_produto = manipulaProduto.retornaProduto();
        manipulaProduto.fechar();
        if (al_produto != null && !al_produto.isEmpty()) {
            lv_produto.setAdapter(new AdaptadorProduto(this, al_produto));
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == bt_inserirProduto.getId()) {
            Produto produto = new Produto();
            produto.setDescricao(et_descricaoProduto.getText().toString());
            produto.setValor(Double.valueOf(et_valorProduto.getText().toString()));
            manipulaProduto.abrir();
            long verificaInsercao = manipulaProduto.inserirProduto(produto);
            mensagem("Inseriu produto: " + verificaInsercao);
            manipulaProduto.fechar();
            et_descricaoProduto.setText("");
            et_valorProduto.setText("");

        }
        if (view.getId() == bt_alterarProduto.getId()) {
            //Produto produto =
        }
        if (view.getId() == bt_deletarProduto.getId()) {

        }
        if (view.getId() == bt_voltar.getId()){

            Intent it = new Intent(TelaProdutos.this, TelaPrincipal.class);
            startActivity(it);

        }
    }

    public void mensagem(String msg)
    {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}
