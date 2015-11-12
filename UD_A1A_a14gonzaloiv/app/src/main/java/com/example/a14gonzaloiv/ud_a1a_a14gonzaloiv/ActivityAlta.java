package com.example.a14gonzaloiv.ud_a1a_a14gonzaloiv;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivityAlta extends AppCompatActivity {

    EditText editNome;
    EditText editDescricion;

    Button buttonAlta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_alta);

        // view
        editNome = (EditText) findViewById(R.id.editNome);
        editDescricion = (EditText) findViewById(R.id.editDescricion);

        buttonAlta = (Button) findViewById(R.id.buttonAlta);
        buttonAlta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Persoa persoa = new Persoa(
                        editNome.getText().toString(),
                        editDescricion.getText().toString()
                );
                Base baseDatos = new Base(ActivityAlta.this);
                baseDatos.sqlLiteDB = baseDatos.getWritableDatabase();
                baseDatos.engadirPersoa(persoa);
                editNome.setText("");
                editDescricion.setText("");
            }
        });
    }
}
