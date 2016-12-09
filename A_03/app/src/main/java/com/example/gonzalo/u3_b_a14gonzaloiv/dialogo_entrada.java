package com.example.gonzalo.u3_b_a14gonzaloiv;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class dialogo_entrada extends DialogFragment{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Primeiro preparamos o interior da ventá de diálogo inflando o seu
        // fichero XML
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getActivity().getSystemService(infService);
        // Inflamos o compoñente composto definido no XML
        View inflador = li.inflate(R.layout.dialogo_entrada, null);
        // Buscamos os compoñentes dentro do Diálogo
        final TextView etNome = (TextView) inflador.findViewById(R.id.et_nome);
        final TextView etContrasinal = (TextView) inflador.findViewById(R.id.et_contrasinal);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle("Indica usuario e contrasinal")
                .setView(inflador)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int boton) {
                        Toast.makeText(getActivity(),
                                "Escribiches nome: '" +
                                        etNome.getText().toString() +
                                        "'. Contrasinal: '" +
                                        etContrasinal.getText().toString() +
                                        "' e premeches 'Aceptar'",
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int boton) {
                        Toast.makeText(getActivity(), "Premeches en 'Cancelar'", Toast.LENGTH_SHORT).show();
                    }
                });
        return builder.create();

    }
}