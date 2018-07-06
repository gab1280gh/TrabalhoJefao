package com.example.alunos.trabalhojefao;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.alunos.trabalhojefao.adaptadores.AdaptadorProduto;
import com.example.alunos.trabalhojefao.banco.Cliente;
import com.example.alunos.trabalhojefao.banco.ManipulaProduto;
import com.example.alunos.trabalhojefao.banco.Produto;

import java.util.ArrayList;

public class TelaProdutos extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    Button bt_inserirProduto, bt_alterarProduto, bt_deletarProduto, bt_voltar;
    EditText et_descricaoProduto, et_valorProduto, et_id;
    ManipulaProduto manipulaProduto;
    ArrayList<Produto> al_produto;
    ListView lv_produto;
    Produto produtoSelecionado;
    AlertDialog alerta;
    AlertDialog alertinho;
    Produto produto;

    MediaPlayer mediaPlayer1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_produtos);

        mediaPlayer1 = MediaPlayer.create(this, R.raw.beep03);

        bt_inserirProduto = findViewById(R.id.bt_inserirProduto);
        bt_alterarProduto = findViewById(R.id.bt_alterarProduto);
        bt_deletarProduto = findViewById(R.id.bt_deletarProduto);
        bt_voltar = findViewById(R.id.BT_prodVoltar);

        et_descricaoProduto = findViewById(R.id.et_descricaoProduto);
        et_valorProduto = findViewById(R.id.et_valorProduto);
        et_id = findViewById(R.id.et_idProd);
        lv_produto = findViewById(R.id.lv_produto);

        bt_inserirProduto.setOnClickListener(this);
        bt_voltar.setOnClickListener(this);
        lv_produto.setOnItemClickListener(this);

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
            alertar(this);
            manipulaProduto.fechar();
            et_descricaoProduto.setText("");
            et_valorProduto.setText("");

        }
        if (view.getId() == bt_alterarProduto.getId()) {
            //Produto produto =
        }
        if (view.getId() == bt_deletarProduto.getId()) {
            String desc = et_descricaoProduto.getText().toString();
            Double valor = Double.parseDouble(et_valorProduto.getText().toString());
            int id = Integer.parseInt(et_id.getText().toString());

            if (id != 0){
                produto = new Produto(desc, valor);

                manipulaProduto.abrir();
                manipulaProduto.deletarProduto(produto);
                manipulaProduto.fechar();
                tocarLocal();
                alertar(this);
            }else{
                alertinho(this);
            }

        }
        if (view.getId() == bt_voltar.getId()){

            Intent it = new Intent(TelaProdutos.this, TelaPrincipal.class);
            startActivity(it);

        }
    }

    public void tocarLocal()
    {
        mediaPlayer1.start();
    }

    public void alertinho(Context context)
    {
        AlertDialog.Builder construtor = new AlertDialog.Builder(context);
        construtor.setTitle("Erro");
        construtor.setMessage("Nenhum produto selecionado");
        alertinho = construtor.create();
        alertinho.show();
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
        produto = (Produto)parent.getAdapter().getItem(position);
        et_descricaoProduto.setText(produto.getDescricao());
        et_valorProduto.setText(Double.toString(produto.getValor()));
        et_id.setText(produto.getId());
    }
}
