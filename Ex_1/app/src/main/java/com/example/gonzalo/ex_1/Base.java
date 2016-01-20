package com.example.gonzalo.ex_1;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Base extends SQLiteOpenHelper{
    public SQLiteDatabase sqlLiteDB;
    public Base(Context context){
        super(context, "salaries", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqlLiteDB.execSQL("CREATE TABLE salary(month VARCHAR(20) PRIMARY KEY, total_salary REAL NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long insert(Salary salary){
        ContentValues contentValues = new ContentValues();
        contentValues.put("month", salary.getMonth());
        contentValues.put("total_salary", salary.getTotal_salary());
        long id = sqlLiteDB.insert("salary", null, contentValues);
        return id;
    }
}
