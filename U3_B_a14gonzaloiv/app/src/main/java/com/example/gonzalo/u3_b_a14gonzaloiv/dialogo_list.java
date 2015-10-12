package com.example.gonzalo.u3_b_a14gonzaloiv;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

public class dialogo_list extends DialogFragment{


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle("Escolle unha opción")
                .setItems(R.array.elementos_dialog_seleccion, new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String[] opcions = getResources().getStringArray(R.array.elementos_dialog_seleccion);
                        Toast.makeText(getActivity(), "Seleccionaches: '" + opcions[which] + "'", 1).show();
                    }
                });
        return builder.create();


    }


}