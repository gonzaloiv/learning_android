package com.example.gonzalo.u4_a_a14gonzaloiv;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SpinnerCoches extends ActionBarActivity {

    Spinner spinnerCoches;
    ArrayList<String> datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_coches);

        datos = new ArrayList<String>();
        spinnerCoches = (Spinner) findViewById(R.id.spinnerCoches);

        amosarDatos(getApplicationContext(),spinnerCoches, datos);

    }



    private void amosarDatos(Context context, Spinner spinnerCoches, ArrayList<String> datos){

        datos = UtilidadesSD.obterDatos(datos, SpinnerCoches.this);

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, datos);

        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerCoches.setAdapter(adaptador);

    }

}
