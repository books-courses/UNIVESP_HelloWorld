package com.example.helloworld;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


class BancoDeDados extends SQLiteOpenHelper {


    // 1 Definido as constantes (static final) que irão compor o "STATEMENT" para criação da TABELA "Mensagem"
    public static final String NOME_BANCO = "MeuBancodeDados.db";
    public static final int VERSAO_BANCO = 1;
    public static final String TABLE_NAME = "Mensagem";
    public static final String COLUNA0 = "_id";
    public static final String COLUNA1 = "mensagemrecebida";


    // 2 Criando o  "STATEMENT" da TABELA "Mensagem"
    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUNA0 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUNA1 + " TEXT)";


    // 3 Declarando o construtor do banco de dados
    public BancoDeDados(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    // Criando a tabela utilizando o método execSQL
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }


    // 4 Declarando o método onUpgrade
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
    }


    // 5 Criando o método salvamensagem
    public long salvaMensagem(String mensagem) {
        // Variável que irá receber o retorno do método insert (-1 falha, 0 bem sucedido)
        long id;
        // abrindo a conexão com o banco de dados
        SQLiteDatabase db = getWritableDatabase();
        try {
            // colocando o valor a ser inserido na variável ContentValues (chave,valor)
            ContentValues valores = new ContentValues();
            valores.put(COLUNA1, mensagem);

            // evocando o método insert para inserção da mensagem na tabela e receber o retorno do processamento.
            id = db.insert(TABLE_NAME, null, valores);
            return id;

        } finally {
            // fechando a conexão com banco de dados
            db.close();
        }
    }


    // 6 Criando o método apagaMensagem
    public int apagaMensagem() {
        // variável que irá receber o retorno do método delete (0 se a tabela estiver vazia)
        int count;
        // abrindo a conexão com o banco de dados
        SQLiteDatabase db = getWritableDatabase();
        try {
            // evocando o método delete para apagar o conteudo da tabela,e receber o retorno do processamento.
            count = db.delete(TABLE_NAME, null, null);
            return count;

        } finally {
            // fechando a conexão com banco de dados
            db.close();
        }
    }


    @SuppressLint("Range")
    public String findAll() {
        // abrindo a conexão com o banco de dados
        SQLiteDatabase db = getWritableDatabase();

        // variável que irá receber a mensagem contida no banco de dados
        String mensagem = "";

        try {
            // uso do método query para retornar o conteúdo da tabela, que é a propria mensagem
            Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);

            //posiciona o curso no primeiro elemento retornado ( no nosso caso só há um)
            c.moveToFirst();

            // Se a tabela não estiver vazia (c.moveToFirst()=True)
            if (c.moveToFirst()) {
                // captura a String na única coluna da tabela que é a coluna "mensagemrecebida"
                mensagem = c.getString(c.getColumnIndex("mensagemrecebida"));
            }
            return mensagem;

        } finally {
            // fechando a conexão com banco de dados
            db.close();
        }
    }


}
