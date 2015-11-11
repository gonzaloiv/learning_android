package com.example.a14gonzaloiv.ud_a1a_a14gonzaloiv;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main extends AppCompatActivity {

    private Base baseDatos;

    // View
    Button buttonAlta;
    Button buttonLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicio base de datos
        baseDatos = new Base(getApplicationContext());
        baseDatos.getWritableDatabase();

        // Listeners para as activities secundarias
        buttonAlta = (Button) findViewById(R.id.buttonAlta);
        buttonAlta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this, ActivityAlta.class);
                startActivity(intent);
            }
        });
        buttonLista = (Button) findViewById(R.id.buttonLista);
        buttonLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this, ActivityLista.class);
                startActivity(intent);
            }
        });

    }
}
