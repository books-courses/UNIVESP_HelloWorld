package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Tela2 extends AppCompatActivity {
    // String para armazenar o conteudo da mensagem recebida da primeira tela
    String mensagemRecebida;

    // Variavel TextView no codigo Java associado ao TextView "mensagemRecebidaXML" no layout
    TextView exibeMensagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela2);

        //
        exibeMensagem = findViewById(R.id.mensagemRecebidaXML);

        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        String mensagemRecebida = (String) bd.get("mensagemEnviada");


        exibeMensagem.setText(mensagemRecebida);
    }
}




