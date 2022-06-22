package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Tela2 extends AppCompatActivity {


    // 1- String para armazenar o conteúdo da mensagem recebida da primeira tela
    String mensagemRecebida;

    // 2- variável TextView no código Java associada ao TextView "mensagemRecebidaXML" no Layout)
    TextView exibeMensagem;


    // 6 criação das variáveis para os botões salvar, apagar e visualizar
    private Button salvar;
    private Button apagar;
    private Button visualizar;


    // 7 Criação da variavel do Banco de dados
    private BancoDeDados db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela2);


        // 3 Associando a variável mensagemInserida do Java com o TextView "mensagemRecebidaXML" do arquivoXML
        exibeMensagem = findViewById(R.id.mensagemRecebidaXML);


        // 8 Associação das variáveis ao seus componentes XML.
        salvar = findViewById(R.id.salvarButton);
        visualizar = findViewById(R.id.visualizarButton);
        apagar = findViewById(R.id.apagarButton);


        // 9 criação de uma instância do bando de dados
        db = new BancoDeDados(this);


        // 4 Utilizando o método getExtras() para recuperar a mensagem enviada.
        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        String mensagemRecebida = (String) bd.get("mensagemEnviada");


        // 5 Exibindo a mensagem na Tela
        exibeMensagem.setText(mensagemRecebida);


        // 10 Associação do botão "salvar" ao método "salvaMensagem" para inserir a mensagem no  banco de dados
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long id = db.salvaMensagem(mensagemRecebida);
                if (id != -1)
                    Toast.makeText(Tela2.this, "Mensagem Salva!", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(Tela2.this, "Ops! ocorreu algum problema...", Toast.LENGTH_LONG).show();
            }
        });


        // 11 Associação do botão "visualizar" ao metodo "findAll" para visualização da mensagem guardada no banco de dados
        visualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exibeMensagem.setText(db.findAll());
            }
        });


        // 12 Associação do botão "apagar" ao método "delete" para apagar a mensagem do banco de dados.
        // Toast trabalha com
        apagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = db.apagaMensagem();
                if (count == 0) {
                    Toast.makeText(Tela2.this, "Não há mensagens para apagar!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Tela2.this, "mensagem apagada!", Toast.LENGTH_SHORT).show();
                }
                exibeMensagem.setText("");
            }
        });
    }
}
