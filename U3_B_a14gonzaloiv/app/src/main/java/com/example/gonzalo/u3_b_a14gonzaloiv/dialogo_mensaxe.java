package com.example.gonzalo.u3_b_a14gonzaloiv;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

public class dialogo_mensaxe extends DialogFragment{


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle("Atención")
                .setMessage("Nova mensaxe. Preme o botón 'Back' para volver á pantalla principal")
                .setIcon(android.R.drawable.ic_dialog_email);
        return builder.create();

    }


}