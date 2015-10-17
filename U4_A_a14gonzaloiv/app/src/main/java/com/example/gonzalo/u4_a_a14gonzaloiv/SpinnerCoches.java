package com.example.gonzalo.u4_a_a14gonzaloiv;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static android.widget.Toast.LENGTH_SHORT;

public class SpinnerCoches extends ActionBarActivity {

    Spinner spinnerCoches;
    ArrayList<String> datos;
    // Bandeira para a primeira selección do Spinner
    Boolean comezo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_coches);
        // Inicialización bandeira
        comezo=true;

        datos = new ArrayList<String>();
        spinnerCoches = (Spinner) findViewById(R.id.spinnerCoches);

        amosarDatos(getApplicationContext(),spinnerCoches, datos);

        spinnerCoches.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(
                    getBaseContext(),
                    "Posición: " +
                    (position + 1) +
                    " - " +
                    ((TextView) view).getText(),
                    LENGTH_SHORT
                ).show();
            }
            // Método non necesario para as funcións da aplicación
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}

        });
    }

    private void amosarDatos(Context context, Spinner spinnerCoches, ArrayList<String> datos){

        // Obtemos os datos co método das UtilidadesSD
        datos = UtilidadesSD.obterDatos(datos, SpinnerCoches.this);
        // Enchemos o adaptador cos datos e co layout adecuado
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, datos);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Conectamos adaptador e Spinner
        spinnerCoches.setAdapter(adaptador);

    }
}
