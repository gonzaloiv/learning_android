package com.example.a14gonzaloiv.ud_a1a_a14gonzaloiv;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class ActivityLista extends AppCompatActivity {

    private Persoa persoaSeleccionada=null;

    // vista
    Spinner spinnerNomes;
    TextView textDescricions;
    Button buttonGardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_lista);

        // view
        spinnerNomes = (Spinner) findViewById(R.id.spinnerNomes);
        textDescricions = (TextView) findViewById(R.id.textDescricions);
        buttonGardar = (Button) findViewById(R.id.buttonGardar);

        // base de datos
        Base baseDatos = new Base(this);
        baseDatos.sqlLiteDB = baseDatos.getReadableDatabase();

        // adapter
        ArrayList<Persoa> persoas = baseDatos.listaPersoas();
        ArrayList<String> nomes = new ArrayList<String>();
        for(Persoa persoa: persoas){
            nomes.add(persoa.getNome());
        }

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1,nomes);
        spinnerNomes.setAdapter(adaptador);
        spinnerNomes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // cambio na base de datos
                ArrayAdapter<String> adaptador = (ArrayAdapter<String>) adapterView.getAdapter();
                String nome = adaptador.getItem(i);
                Base baseDatos = new Base(ActivityLista.this);
                baseDatos.sqlLiteDB = baseDatos.getReadableDatabase();
                textDescricions.setText((baseDatos.consultaPersoa(nome).getDescricion()));
                // gardado do item seleccionado para logo gardalo
                persoaSeleccionada=baseDatos.consultaPersoa(nome);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        buttonGardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String datosPersoa = "Persoa: " +
                        persoaSeleccionada.getNome() +
                        ", " +
                        persoaSeleccionada.getDescricion();
                File ruta = getFilesDir();
                File arquivo = new File(ruta, persoaSeleccionada.getNome() + ".txt");
                try {
                    FileOutputStream fos = new FileOutputStream(arquivo);
                    OutputStreamWriter osr = new OutputStreamWriter(fos);
                    try{
                        osr.write(datosPersoa);
                        Toast.makeText(ActivityLista.this, persoaSeleccionada.getNome() + " convertido en texto." , Toast.LENGTH_LONG).show();
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
    }

}
