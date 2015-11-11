package com.example.a14gonzaloiv.ud_a1a_a14gonzaloiv;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Base extends SQLiteOpenHelper{

    public SQLiteDatabase sqlLiteDB;
    public final static String NOME_BD = "Base_Nomes";
    public final static int VERSION_BD = 1;

    private String CREAR_TABOA_AULAS = "CREATE TABLE PERSOAS (" +
            "nome VARCHAR(20) PRIMARY KEY, " +
            "descricion VARCHAR(100) " +
            ")";

    public Base(Context context) {
        super(context, NOME_BD, null, VERSION_BD);
    }

    public void engadirPersoa(Persoa persoa){
        ContentValues valores = new ContentValues();
        valores.put("nome", persoa.getNome());
        valores.put("descricion", persoa.getDescricion());
    }

    public Persoa consultaPersoa(String nome){
        Cursor datosConsulta = sqlLiteDB.rawQuery(
                "SELECT * FROM PERSOAS WHERE NOME=" + nome,
                null
        );
        Persoa persoa = null;
        if(datosConsulta.moveToFirst()) {
            persoa = new Persoa(
                    datosConsulta.getString(0),
                    datosConsulta.getString(1)
            );
        }
        return persoa;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
