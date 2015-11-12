package com.example.a14gonzaloiv.ud_a1a_a14gonzaloiv;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main extends AppCompatActivity {

    // View
    Button buttonAlta;
    Button buttonLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Base baseDatos = new Base(this);
        baseDatos.sqlLiteDB = baseDatos.getWritableDatabase();
        long way = baseDatos.engadirPersoa(new Persoa("John Wayne", "Actor"));
        way = baseDatos.engadirPersoa(new Persoa("John Ford", "Director"));


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
