package com.example.a14gonzaloiv.ud_a1a_a14gonzaloiv;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class Base extends SQLiteOpenHelper{

    public SQLiteDatabase sqlLiteDB;

    public final static String NOME_BD = "Persoas.db";
    public final static int VERSION_BD = 1;

    private String CREAR_TABOA_PERSOAS =
            "CREATE TABLE persoas ( " +
            "nome VARCHAR(20) PRIMARY KEY, " +
            "descricion VARCHAR(100)" +
            " )";

    public Base(Context context) {
        super(context, NOME_BD, null, VERSION_BD);
    }

    public long engadirPersoa(Persoa persoa){
        ContentValues valores = new ContentValues();
        valores.put("nome", persoa.getNome());
        valores.put("descricion", persoa.getDescricion());
        long id = sqlLiteDB.insert("persoas", null, valores);
        return id;
    }

    public Persoa consultaPersoa(String nome){
        Cursor datosConsulta = sqlLiteDB.rawQuery(
                "SELECT * FROM persoas WHERE nome='" + nome + "'",
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

    public ArrayList<Persoa> listaPersoas(){
        ArrayList<Persoa> persoas = new ArrayList<Persoa>();
        Cursor datosConsulta = sqlLiteDB.rawQuery(
                "SELECT * FROM persoas ORDER BY nome", null);
        if (datosConsulta.moveToFirst()) {
            Persoa persoa;
            while (!datosConsulta.isAfterLast()) {
                persoa = new Persoa(datosConsulta.getString(0),
                        datosConsulta.getString(1));
                persoas.add(persoa);
                datosConsulta.moveToNext();
            }
        }
        return persoas;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAR_TABOA_PERSOAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS PERSOAS");

        // Create tables again
        onCreate(db);
    }
}
