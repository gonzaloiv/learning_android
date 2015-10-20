package com.example.gonzalo.u4_b_a14gonzaloiv;

import android.app.Dialog;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main extends FragmentActivity{

    // Diálogo
    private DynamicDialog dynamicDialog;
    FragmentManager fm;

    // Vista
    Button buttonDialogoFragment;
    Button buttonDialogoShow;
    static TextView textElementos;

    // Introducción de elementos
    Button buttonEngadir;
    EditText textEngadir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textElementos=(TextView) findViewById(R.id.textElementos);

        // Configuración Dialog
        dynamicDialog = new DynamicDialog();
        fm = getSupportFragmentManager();

        // Chamada ao Dialog desde FragmentDialog
        buttonDialogoFragment = (Button) findViewById(R.id.buttonDialogoFragment);
        buttonDialogoFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dynamicDialog.show(fm, "Cadro de diálogo de selección múltiple");
            }
        });

        // Chamada ao Dialog desde showDialog
        buttonDialogoShow = (Button) findViewById(R.id.buttonDialogoShow);
        buttonDialogoShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dynamicDialog.show(fm, "Cadro de diálogo de selección múltiple");
            }
        });

        // Botón para engadir elementos
        textEngadir = (EditText) findViewById(R.id.textEngadir);
        buttonEngadir = (Button) findViewById(R.id.buttonEngadir);
        buttonEngadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DynamicDialog.addElementoList(textEngadir.getText().toString());
            }
        });
    }

    // Método static chamado desde o Dialog cambiar o texto
    public static void cambiarTexto(String elSeleccion) {
        textElementos.setText(elSeleccion);
    }
}
