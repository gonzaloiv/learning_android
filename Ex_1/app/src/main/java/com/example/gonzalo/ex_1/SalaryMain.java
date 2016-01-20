package com.example.gonzalo.ex_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SalaryMain extends AppCompatActivity {

    //VIEW
    Button buttonXML;
    Button buttonShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salary);
        //VIEW
        buttonShow = (Button) findViewById(R.id.buttonShow);
        buttonXML = (Button) findViewById(R.id.buttonXML);
        //LISTENERS
        buttonXML.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downloadFile();
                processXML();
                insertSalaries();
            }
        });
        buttonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSalaries();
            }
        });

    }
    private void getConnection(){

    }
    private void downloadFile(){
        getConnection();
    }
    private void processXML(){

    }
    private void insertSalaries(){

    }
    private void showSalaries(){

    }
}
