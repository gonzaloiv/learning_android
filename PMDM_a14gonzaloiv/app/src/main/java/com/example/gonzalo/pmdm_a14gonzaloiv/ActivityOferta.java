package com.example.gonzalo.pmdm_a14gonzaloiv;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class ActivityOferta extends AppCompatActivity {

    // VIEW
    Spinner spinnerCiclos;
    TextView txtCiclo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_oferta);

        //SETUP DOR THE TEXTVIEWS
        txtCiclo = (TextView) findViewById(R.id.txt_ciclo);

        // ADAPTER FOR THE SPINNER
        final String[] arrayCiclos = {"ASIR","DAM","SRM"};
        final ArrayAdapter adapterCiclos = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, arrayCiclos);
        // SETUP FOR THE SPINNER
        spinnerCiclos = (Spinner) findViewById(R.id.spinner_ciclos);
        spinnerCiclos.setAdapter(adapterCiclos);
        spinnerCiclos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (arrayCiclos[i]){
                    case "ASIR":
                        txtCiclo.setText("ASIR");
                        return;
                    case "DAM":
                        txtCiclo.setText("DAM");
                        return;
                    case "SRM":
                        txtCiclo.setText("SRM");
                        return;
                    default:
                        return;
                }
            }
        });
    }

}
