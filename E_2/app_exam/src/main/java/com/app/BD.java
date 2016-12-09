package com.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class BD extends SQLiteOpenHelper {

    public SQLiteDatabase sqlLiteDB;

    private String CREAR_TABOA_SALARIOS = "CREATE TABLE salary( " +
            "month VARCHAR(10) PRIMARY KEY," +
            "total_salary REAL" +
            ")";
    private String DROP_TABOA_SALARIOS = "DROP TABLE IF EXISTS salary";

    public BD(Context context) {
        super(context, "salaries.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DROP_TABOA_SALARIOS);
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

    public ArrayList<Salary> getSalaries(){
        ArrayList<Salary> salaries= new ArrayList<Salary>();
        Salary salary = null;
        Cursor cursor = sqlLiteDB.rawQuery("select month, total_salary from salary",null);
        if(cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                salary = new Salary();
                salary.setMonth(cursor.getString(0));
                salary.setTotalSalary(cursor.getFloat(1));
                salaries.add(salary);
                cursor.moveToNext();
            }
        }
        return salaries;
    }
}
