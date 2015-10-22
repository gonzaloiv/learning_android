package com.example.gonzalo.u4_b_a14gonzaloiv;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class Main extends FragmentActivity{

    // Variables selección
    public static ArrayList<String> listSeleccion = new ArrayList<String>();
    public static String[] listFrameworks;
    public static boolean[] listCheckInicio;

    public static String[] lol = new String[4];

    // Diálogo
    private DynamicDialog dynamicDialog;
    FragmentManager fm;
    AlertDialog alertDialog;

    // Vista
    Button buttonDialogoFragment;
    Button buttonDialogoShow;
    static TextView textElementos;

    // Introducción de elementos
    Button buttonEngadir;
    EditText textEngadir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textElementos=(TextView) findViewById(R.id.textElementos);

        // Configuración listas de elementos
        listFrameworks = getResources().getStringArray(R.array.opcions_list);
        listCheckInicio= new boolean[]{true, false, true, false, false, true};

        // Configuración FragmentDialog
        dynamicDialog = new DynamicDialog();
        fm = getSupportFragmentManager();

        // Chamada ao Dialog desde FragmentDialog
        buttonDialogoFragment = (Button) findViewById(R.id.buttonDialogoFragment);
        buttonDialogoFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dynamicDialog.show(fm, "Cadro de diálogo de selección múltiple");
            }
        });

        // Chamada ao Dialog desde showDialog
        buttonDialogoShow = (Button) findViewById(R.id.buttonDialogoShow);
        buttonDialogoShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(1);
            }
        });

        // Botón para engadir elementos
        textEngadir = (EditText) findViewById(R.id.textEngadir);
        buttonEngadir = (Button) findViewById(R.id.buttonEngadir);
        buttonEngadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addElementoList(textEngadir.getText().toString(), false);
                textEngadir.setText("");
            }
        });
    }

    // Método static chamado desde o Dialog cambiar o texto
    public static void cambiarTexto(String elSeleccion) {
        textElementos.setText(elSeleccion);
    }

    @Override
    protected Dialog onCreateDialog(int id) {

        AlertDialog.Builder builder;
        // Inicio selección
        inicioListaSeleccion();


        builder = new AlertDialog.Builder(this)
                .setTitle("Escolle algúns frameworks")
                .setMultiChoiceItems(
                        listFrameworks,
                        listCheckInicio,
                        new DialogInterface.OnMultiChoiceClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                if(isChecked){
                                    listSeleccion.add(listFrameworks[which]);
                                } else if(Main.listSeleccion.contains(listFrameworks[which])){
                                    listSeleccion.remove(listFrameworks[which]);
                                }
                            }
                        }
                )
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i("Seleccionados:", String.valueOf(listSeleccion.size()));
                        Main.cambiarTexto(getListSeleccion(listSeleccion));
                        // Reseteamos a lista de selección para a seguinte vez
                        listSeleccion.clear();
                        removeDialog(1);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Reseteamos a lista de selección para a seguinte vez
                        listSeleccion.clear();
                        removeDialog(1);
                    }
                });
        return builder.show();

    }

    // Método para formatar o ArrayList a String
    public static String getListSeleccion(ArrayList<String> listSeleccion){
        String textElementos = "Seleccionaches: ";
        for(int i = 0; i < listSeleccion.size(); i ++){
            textElementos = textElementos + listSeleccion.get(i) + " ";
            Log.i("Seleccionados:",listSeleccion.get(i));
        }
        return textElementos;
    }

    // Método que engade elementos á lista de selección
    public static void addElementoList(String elemento, Boolean estado) {
        String[] extraString = listFrameworks;
        listFrameworks=new String[extraString.length+1];
        listFrameworks[listFrameworks.length-1] = elemento;
        for(int i = 0; i < extraString.length; i++){
            listFrameworks[i] = extraString[i];
        }
        boolean[] extraBoolean = listCheckInicio;
        listCheckInicio=new boolean[extraBoolean.length+1];
        listCheckInicio[listCheckInicio.length-1] = estado;
        for(int i = 0; i < extraString.length; i++){
            listCheckInicio[i] = extraBoolean[i];
        }

    }
    // Método que inicia a lista de selección ambos Dialog
    public static void inicioListaSeleccion(){
        for(int i = 0; i< listFrameworks.length; i++){
            if(listCheckInicio[i]==true){
                listSeleccion.add(listFrameworks[i]);
            }
        }
    }

}
