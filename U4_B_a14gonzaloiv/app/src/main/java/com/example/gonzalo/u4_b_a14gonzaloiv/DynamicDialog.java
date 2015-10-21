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
import java.util.Arrays;
import java.util.List;

public class DynamicDialog  extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder;

        // Configuración inicial para o FragmentDialog
        String[] listFrameworks = new String[Main.listSeleccion.size()];
        Boolean[] listCheckInicio = new Boolean[Main.listSeleccionados.size()];

        listFrameworks=getResources().getStringArray(R.array.opcions_list);

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

        builder = new AlertDialog.Builder(getActivity())
                .setTitle("Escolle algúns frameworks")
                .setMultiChoiceItems(
                        listFrameworks,
                        listCheckInicio,
                        new DialogInterface.OnMultiChoiceClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                if(isChecked){
                                    Main.listSeleccion.add(listFrameworks[which]);
                                } else if(Main.listSeleccion.contains(listFrameworks[which])){
                                    Main.listSeleccion.remove(listFrameworks[which]);
                                }
                            }
                        }
                )
                .setPositiveButton("Aceptar", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i("Seleccionados:", String.valueOf(Main.listSeleccion.size()));
                        Main.cambiarTexto(Main.getListSeleccion(Main.listSeleccion));
                        // Reseteamos a lista de selección para a seguinte vez
                        Main.listSeleccion.clear();
                    }
                })
                .setNegativeButton("Cancelar", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Reseteamos a lista de selección para a seguinte vez
                        Main.listSeleccion.clear();
                    }
                });
        return builder.create();
    }

}
