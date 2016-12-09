package com.example.gonzalo.u3_b_a14gonzaloiv;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

public class dialogo_tres extends DialogFragment{


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle("Enquisa")
                .setMessage("Compras sempre en grandes superficies?")
                .setPositiveButton("Si", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "Premeches 'Si'", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("Non", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(),"Premeches 'Non'", Toast.LENGTH_SHORT).show();
                    }
                }).setNeutralButton("Ás veces", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(),"Premeches 'Ás veces'",Toast.LENGTH_SHORT).show();
                    }
                });
        return builder.create();


    }


}