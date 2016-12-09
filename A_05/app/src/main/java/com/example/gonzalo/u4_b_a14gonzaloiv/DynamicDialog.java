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

        // Inicio selección
        Main.inicioListaSeleccion();

        builder = new AlertDialog.Builder(getActivity())
                .setTitle("Escolle algúns frameworks")
                .setMultiChoiceItems(
                        Main.listFrameworks,
                        Main.listCheckInicio,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                if (isChecked) {
                                    Main.listSeleccion.add(Main.listFrameworks[which]);
                                } else if (Main.listSeleccion.contains(Main.listFrameworks[which])) {
                                    Main.listSeleccion.remove(Main.listFrameworks[which]);
                                }
                            }
                        }
                )
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i("Seleccionados:", String.valueOf(Main.listSeleccion.size()));
                        Main.cambiarTexto(Main.getListSeleccion(Main.listSeleccion));
                        // Reseteamos a lista de selección para a seguinte vez
                        Main.listSeleccion.clear();

                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Reseteamos a lista de selección para a seguinte vez
                        Main.listSeleccion.clear();

                    }
                });
        return builder.create();
    }
}
