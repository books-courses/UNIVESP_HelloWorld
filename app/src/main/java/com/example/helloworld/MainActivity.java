package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    // Variavel EditText n Java para captura do conteudo inserido pelo usuario (associado ao componente EditTextXML no Layout)
    private EditText mensagemInserida;

    // String contendo msg a ser enviada
    private String mensagem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mensagemInserida = findViewById(R.id.editTextXML);
    }

    public void MeuClique(View e) {
        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://michelmetran.com.br"));
        startActivity(myIntent);

        //

    }
//    // Testes
//    public void MeuTel(View e)
//    {
//        Intent myIntentTel = new Intent(Intent.ACTION_CALL, Uri.parse("tel:19999468284"));
//        startActivity(myIntentTel);
//    }
//    // Testes
//    public void MeuTel(View e)
//    {
//        Intent myIntentMedia = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        startActivity(myIntentMedia);
//    }
    public void disparoNovaTela(View e)
    {
        //Atribuiçao do valor digitado pelo usuario no campo EditTextXML para a variavel mensagem
        mensagem = mensagemInserida.getText().toString();

        // Criaçao de Intent
        Intent myIntent = new Intent(this, Tela2.class);

        // Put Extra para enviar msg
        myIntent.putExtra("mensagemEnviada", mensagem);

        startActivity(myIntent);

    }
}

