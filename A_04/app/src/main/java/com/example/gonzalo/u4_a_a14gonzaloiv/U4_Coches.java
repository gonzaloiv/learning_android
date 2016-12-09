package com.example.gonzalo.u4_a_a14gonzaloiv;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class U4_Coches extends ActionBarActivity {

    // Variables da View
    Button buttonDatos;
    Button buttonActivities;

    EditText textCoches;

    RadioButton radioEngadir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u4_coches);

        buttonActivities = (Button) findViewById(R.id.buttonActivities);
        buttonDatos = (Button) findViewById(R.id.buttonDatos);
        radioEngadir = (RadioButton) findViewById(R.id.radioEngadir);
        textCoches = (EditText) findViewById(R.id.textCoches);

        // Escoitador para o botón de engadido/sobrescritura de datos
        buttonDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(radioEngadir.isChecked()){
                    UtilidadesSD.engadirDatos(true, textCoches, (Activity) U4_Coches.this);
                }else {
                    UtilidadesSD.engadirDatos(false, textCoches, (Activity) U4_Coches.this);
                }

            }
        });
        // Escoitador para o botón de lectura de datos
        buttonActivities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearDialog();
            }
        });

    }


    // Método para o diálogo de selección de vista para os datos do ficheiro
    private void crearDialog(){
        AlertDialog.Builder dialogOpcion = new AlertDialog.Builder(this);
        dialogOpcion.setTitle("Escolle unha opción");
        dialogOpcion.setItems(new String[]{"Lista", "Spinner"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent secundaria;
                if (which == 0) {
                    secundaria = new Intent(getApplication(), ListCoches.class);
                } else {
                    secundaria = new Intent(getApplication(), SpinnerCoches.class);
                }
                startActivity(secundaria);
            }
        });
        dialogOpcion.create().show();
    }


}
