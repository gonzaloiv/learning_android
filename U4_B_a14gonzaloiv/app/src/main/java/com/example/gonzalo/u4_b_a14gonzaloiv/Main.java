package com.example.gonzalo.u4_b_a14gonzaloiv;

import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main extends FragmentActivity{

    // Diálogo
    private DynamicDialog dynamicDialog;
    FragmentManager fm;

    // Vista
    Button buttonDialogo;
    static TextView textElementos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textElementos=(TextView) findViewById(R.id.textElementos);

        // Configuración Dialog
        dynamicDialog = new DynamicDialog();
        fm = getSupportFragmentManager();

        // Chamada ao Dialog
        buttonDialogo = (Button) findViewById(R.id.buttonDialogo);
        buttonDialogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dynamicDialog.show(fm, "Cadro de diálogo de selección múltiple");
            }
        });

    }

    // Método static chamado desde o Dialog cambiar o texto
    public static void cambiarTexto(String elSeleccion) {
        textElementos.setText(elSeleccion);
    }
}
