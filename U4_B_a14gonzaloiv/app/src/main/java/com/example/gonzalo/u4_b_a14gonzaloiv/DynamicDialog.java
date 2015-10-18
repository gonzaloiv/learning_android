package com.example.gonzalo.u4_b_a14gonzaloiv;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class DynamicDialog  extends DialogFragment {

    ArrayList<String> listSeleccion = new ArrayList<String>();
    // Selección inicial para o FragmentDialog
    boolean[] listCheckedInicio = {true, false, true, false, false, false, true};


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder;

        final String[] listFrameworks = getResources().getStringArray(R.array.opcions_list);

        // Lista de opción seleccionadas enchida coa selección de inicio
        listSeleccion.add(listFrameworks[0]);
        listSeleccion.add(listFrameworks[2]);
        listSeleccion.add(listFrameworks[6]);

        builder = new AlertDialog.Builder(getActivity())
                .setTitle("Escolle algúns frameworks")
                .setMultiChoiceItems(
                        listFrameworks,
                        listCheckedInicio,
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
                .setPositiveButton("Aceptar", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i("Seleccionados:", String.valueOf(listSeleccion.size()));
                        Main.cambiarTexto(getListSeleccion(listSeleccion));
                        // Reseteamos a lista de selección para a seguinte vez
                        listSeleccion.clear();
                    }
                })
                .setNegativeButton("Cancelar", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Reseteamos a lista de selección para a seguinte vez
                        listSeleccion.clear();
                    }
                });
        return builder.create();
    }

    // Método para formatar o ArrayList a String
    public String getListSeleccion(ArrayList<String> listSeleccion){
        String textElementos = "Seleccionaches: ";
        for(int i = 0; i < listSeleccion.size(); i ++){
            textElementos = textElementos + listSeleccion.get(i) + " ";
            Log.i("Seleccionados:",listSeleccion.get(i));
        }
        return textElementos;
    }

}
