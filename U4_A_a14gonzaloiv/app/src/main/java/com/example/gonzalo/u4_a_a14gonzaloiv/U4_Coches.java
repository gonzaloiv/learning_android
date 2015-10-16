package com.example.gonzalo.u4_a_a14gonzaloiv;

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

    // Variables da SD
    boolean sdDisponible = false;
    boolean sdAccesoEscritura = false;
    File dirFicheiroSD;
    File rutaCompleta;
    public static String nomeFicheiro= "coches.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u4_coches);

        buttonActivities = (Button) findViewById(R.id.buttonActivities);
        buttonDatos = (Button) findViewById(R.id.buttonDatos);
        radioEngadir = (RadioButton) findViewById(R.id.radioEngadir);

        // Escoitador para o botón de engadido/sobrescritura de datos
        buttonDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(radioEngadir.isChecked()){
                    engadirDatos(false);
                }else {
                    engadirDatos(true);
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

    // Método para engadir datos ao ficheiro
    private void engadirDatos(Boolean sobrescribir){

        textCoches = (EditText) findViewById(R.id.textCoches);
        comprobarEstadoSD();
        establecerDirectorioFicheiro();

        if (sdAccesoEscritura) {
            try {
                OutputStreamWriter osw = new OutputStreamWriter(
                        new FileOutputStream(rutaCompleta, sobrescribir));
                osw.write(
                        textCoches.getText() +
                        " - " +
                        obterDataActual() +
                        "\n"
                );
                osw.close();
                // Limpamos o EditText
                textCoches.setText("");
            } catch (Exception ex) {Log.e("SD", "Error escribindo no ficheiro");}
        }
    }

    // Método para obter a data e darlle o formato correcto
    private String obterDataActual(){
        String dataActual= "";
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("EEEE, MMMM d, yyyy 'at' h:mm a");
        dataActual=format.format(cal.getTime());

        return dataActual;
    }

    // Métodos de comprobación da SD
    private void comprobarEstadoSD(){
        String estado = Environment.getExternalStorageState();
        Log.e("SD", estado);

        if(estado.equals(Environment.MEDIA_MOUNTED)){
            sdDisponible = true;
            sdAccesoEscritura = true;
        }else if(estado.equals(Environment.MEDIA_MOUNTED_READ_ONLY)){
            sdDisponible = true;
        }else{
            Toast.makeText(
                    this,
                    "A tarxeta SD non está dispoñible",
                    Toast.LENGTH_SHORT
            ).show();
            finish();
        }
    }

    public void establecerDirectorioFicheiro() {
        if (sdDisponible) {
            // Se quixeremos utilizar o directorio xeral da SD
            // dirFicheiroSD = Environment.getExternalStorageDirectory();
            dirFicheiroSD = getExternalFilesDir(null);
            rutaCompleta = new File(dirFicheiroSD.getAbsolutePath(), nomeFicheiro);
        }
    }


    // Método para o diálogo de selección de vista para os datos
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
