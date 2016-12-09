package com.example.gonzalo.pmdm_a14gonzaloiv;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class ActivityOferta extends AppCompatActivity {

    // VIEW
    Spinner spinnerCiclos;
    TextView txtCiclo;
    ImageView imgModulos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_oferta);

        //SETUP FOR THE VIEW
        txtCiclo = (TextView) findViewById(R.id.txt_ciclo);
        imgModulos = (ImageView) findViewById(R.id.img_modulos);

        // ADAPTER FOR THE SPINNER
        final String[] arrayCiclos = {"ASIR","DAM","SRM"};
        final ArrayAdapter<String> adapterCiclos = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayCiclos);
        // SETUP FOR THE SPINNER
        spinnerCiclos = (Spinner) findViewById(R.id.spinner_ciclos);
        spinnerCiclos.setAdapter(adapterCiclos);
        spinnerCiclos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (arrayCiclos[i]) {
                    case "ASIR":
                        txtCiclo.setText(arrayCiclos[i]);
                        imgModulos.setBackgroundResource(R.mipmap.horario_asir);
                        break;
                    case "DAM":
                        txtCiclo.setText(arrayCiclos[i]);
                        imgModulos.setBackgroundResource(R.mipmap.horario_dam);
                        break;
                    case "SRM":
                        txtCiclo.setText(arrayCiclos[i]);
                        imgModulos.setBackgroundResource(R.mipmap.horario_smr);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

}
