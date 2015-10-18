package com.example.gonzalo.u4_b_a14gonzaloiv;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class DynamicDialog  extends DialogFragment{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder;
        final ArrayList<String> listSeleccion = new ArrayList();
        boolean[] listCheckedInicio = {true, false, true, false, false, false, true};
        final String[] listFrameworks = getResources().getStringArray(R.array.opcions_list);

        builder = new AlertDialog.Builder(getActivity())
                .setTitle("Escolle uns frameworks")
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
                        String textoToast = "";
                        for(int i = 0; i < listSeleccion.size(); i ++){
                            textoToast = textoToast + listSeleccion.get(i) + " ";
                        }
                        Toast.makeText(
                                getActivity().getApplicationContext(),
                                "Seleccionaches: " + textoToast,
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                }).setNegativeButton("Cancelar", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        return builder.create();
    }
}
