package com.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BD extends SQLiteOpenHelper {

    public SQLiteDatabase sqlLiteDB;

    private String CREAR_TABOA_SALARIOS = "CREATE TABLE salary( " +
            " month VARCHAR(10) PRIMARY KEY," +
            "total_salary REAL" +
            ")";
    private String DROP_TABOA_SALARIOS = "DROP TABLE IF EXISTS salary";

    public BD(Context context) {
        super(context, "salaries.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(CREAR_TABOA_SALARIOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_TABOA_SALARIOS);
        onCreate(sqLiteDatabase);
    }

    public long insertSalary(Salary salary){
        ContentValues valores = new ContentValues();
        valores.put("month", salary.getMonth());
        valores.put("total_salary", salary.getTotalSalary());
        long id = sqlLiteDB.insert("salary",null,valores);

        return id;

    }
}
