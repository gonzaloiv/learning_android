package com.example.gonzalo.u3_b_a14gonzaloiv;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

public class dialogo_radio extends DialogFragment{


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Lista de smartphones
        final CharSequence[] smartphones = getResources().getStringArray(R.array.elementos_dialog_seleccion3);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle("Selecciona un smartphone")
                .setSingleChoiceItems(smartphones, 0, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int opcion) {
                        // Evento que ocorre cando o usuario selecciona una opción
                        Toast.makeText(getActivity(), "Seleccionaches: " + smartphones[opcion], Toast.LENGTH_SHORT).show();
                    }
                }).setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int boton) {
                        Toast.makeText(getActivity(), "Premeches 'Aceptar'", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int boton) {
                        Toast.makeText(getActivity(), "Premeches 'Cancelar'", Toast.LENGTH_SHORT).show();
                    }
                });

        return builder.create();

    }

}