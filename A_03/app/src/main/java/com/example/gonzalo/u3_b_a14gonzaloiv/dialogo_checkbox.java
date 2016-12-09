package com.example.gonzalo.u3_b_a14gonzaloiv;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

public class dialogo_checkbox extends DialogFragment{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // lista para a selección
        final String[] matriz = getResources().getStringArray(R.array.elementos_dialog_seleccion2);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle("Selecciona modos de transporte")
                .setMultiChoiceItems(matriz,
                new boolean[] {false, true, false, true, false, false, false},
                new DialogInterface.OnMultiChoiceClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked)
                            Toast.makeText(getActivity(), "Seleccionaches " + matriz[which], Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(getActivity(), "Deseleccionaches " + matriz[which], Toast.LENGTH_SHORT).show();
                    }
                });

        return builder.create();
    }
}