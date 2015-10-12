package com.example.gonzalo.u3_b_a14gonzaloiv;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class dialogo_entrada extends DialogFragment{

    public String valorTexto;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.dialogo_entrada, container, false);
        getDialog().setTitle(getTag());         // O Tag se envía dende a activiy có método show.

        Button btn = (Button) rootView.findViewById(R.id.buttonPecharDialogo);
        btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                EditText edit = (EditText)rootView.findViewById(R.id.editTexto);
                valorTexto = edit.getText().toString();
                ((U3_B_a14gonzaloiv)dialogo_entrada.this.getActivity()).pecharDialogo();
                dismiss();
            }
        });

        // Do something else
        return rootView;
    }
}