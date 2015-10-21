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
    static ArrayList<String> listSeleccion = new ArrayList<String>();
    static  ArrayList<Boolean> listSeleccionados = new ArrayList<Boolean>();
    String[] listFrameworks;

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

        // Configuración inicial para o FragmentDialog
        String[] listFrameworks = new String[Main.listSeleccion.size()];
        Boolean[] listCheckInicio = new Boolean[Main.listSeleccionados.size()];

        listFrameworks=getResources().getStringArray(R.array.opcions_list);

        inicioListas(listFrameworks);
        convertirBoolean(listSeleccionados, listCheckInicio);
        convertirString(listSeleccion, listFrameworks);


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
                                } else if(listSeleccion.contains(listFrameworks[which])){
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
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Reseteamos a lista de selección para a seguinte vez
                        listSeleccion.clear();
                    }
                });
        return builder.create();

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
        listSeleccion.add(elemento);
        listSeleccionados.add(estado);
    }

    // Método para convertir unha lista de String a array
    public static String[] convertirString(ArrayList<String> lista, String[] array){

        for(int i = 0; i < lista.size(); i++){
            array[i] = lista.get(i);
        }

        return array;
    }

    // Método para convertir unha lista de String a array
    public static Boolean[] convertirBoolean(ArrayList<Boolean> lista, Boolean[] array){

        for(int i = 0; i < lista.size(); i++){
            array[i] = lista.get(i);
        }

        return array;
    }

    public static void inicioListas(String[] listFrameworks){

        // Inicio lista de seleccionados
        Main.listSeleccionados.add(true);
        Main.listSeleccionados.add(false);
        Main.listSeleccionados.add(true);
        Main.listSeleccionados.add(false);
        Main.listSeleccionados.add(false);
        Main.listSeleccionados.add(true);

        // Lista de opción seleccionadas enchida coa selección de inicio
        Main.listSeleccion.add(listFrameworks[0]);
        Main.listSeleccion.add(listFrameworks[1]);
        Main.listSeleccion.add(listFrameworks[5]);


    }

}
