package com.example.gonzalo.ud_a2a_a14gonzaloiv;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main extends AppCompatActivity {

    // AUDIO & FILES
    MediaPlayer mediaplayer = new MediaPlayer();
    String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/UD_A2A/MUSICA/";
    String nameArquivo = "";

    // VIEW
    Button buttonReproducir;
    Button buttonGravar;
    Button buttonFoto;
    Spinner spinnerAudio;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // adapter startup
        ArrayList<String> audio = new ArrayList<String>();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, audio);
        File fileDirectorio = new File(path);
        for(File f : fileDirectorio.listFiles()){
            if(f.isFile()){
                adapter.add(f.getName());
            }
        }

        // spinner startup
        spinnerAudio = (Spinner) findViewById(R.id.spinnerAudio);
        spinnerAudio.setAdapter(adapter);
        spinnerAudio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ArrayAdapter<String> adapter = (ArrayAdapter<String>) adapterView.getAdapter();
                nameArquivo = adapter.getItem(i);
                Toast.makeText(Main.this, "Escollido: " + nameArquivo, Toast.LENGTH_SHORT);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        buttonReproducir = (Button) findViewById(R.id.buttonReproducir);
        buttonReproducir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pathArquivo = path + nameArquivo;
                try{
                    Toast.makeText(view.getContext(), "Soa: " + nameArquivo, Toast.LENGTH_SHORT).show();
                    mediaplayer.reset();
                    mediaplayer.setDataSource(pathArquivo);
                    mediaplayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaplayer.prepare();
                    mediaplayer.start();
                }catch(Exception ex){
                    Log.e("MediaPlayer: ", ex.getMessage());
                }
            }
        });

        buttonGravar = (Button) findViewById(R.id.buttonGravar);
        buttonGravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Main.this, "A gravar!", Toast.LENGTH_SHORT).show();
            }
        });

        buttonFoto = (Button) findViewById(R.id.buttonFoto);
        buttonFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Main.this, "A fotear!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
