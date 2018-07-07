package com.example.alunos.trabalhojefao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.alunos.trabalhojefao.banco.Pedido;

public class TelaVisuPedido extends AppCompatActivity {

    TextView tv_idPed, tv_idCli, tv_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_visu_pedido);

        tv_data = findViewById(R.id.tv_visu_data);
        tv_idCli = findViewById(R.id.tv_visu_idCli);
        tv_idPed = findViewById(R.id.tv_visu_id);

        Pedido pedido = (Pedido) getIntent().getSerializableExtra("pedido");

        tv_idPed.setText(String.valueOf(pedido.getId()));
        tv_idCli.setText(String.valueOf(pedido.getFk_cliente_id()));
        tv_data.setText(pedido.getData().toString());

    }
}
