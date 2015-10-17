package com.example.gonzalo.u4_a_a14gonzaloiv;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.widget.Toast.LENGTH_SHORT;

public class ListCoches extends ActionBarActivity {

    ListView listCoches;
    ArrayList<String> datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_coches);

        datos = new ArrayList<String>();
        listCoches = (ListView) findViewById(R.id.listCoches);

        amosarDatos(getApplicationContext(),listCoches, datos);

    }

    private void amosarDatos(Context context, ListView listCoches, ArrayList<String> datos){

        // Obtemos os datos co método das UtilidadesSD
        datos = UtilidadesSD.obterDatos(datos, ListCoches.this);
        // Enchemos o adaptador cos datos e co layout adecuado
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, datos);
        // Conectamos adaptador e Spinner
        listCoches.setAdapter(adaptador);

        listCoches.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(
                    getBaseContext(),
                    "Posición: " +
                    (position + 1) +
                    " - " +
                    ((TextView) view).getText(),
                    LENGTH_SHORT
                ).show();
            }
        });

    }
}
