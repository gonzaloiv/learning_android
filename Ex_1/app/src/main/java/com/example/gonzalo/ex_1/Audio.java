package com.example.gonzalo.ex_1;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

public class Audio extends AppCompatActivity {

    File file;
    String[] files;
    MediaPlayer mediaplayer;
    MediaRecorder mediaRecorder;
    int fileSelected;
    //VIEW
    Spinner spinnerAudio;
    Button buttonReproducir;
    Button buttonParar;
    Button buttonGravar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);
        //STARTUP
        createFolder();
        setUpAdapter();
        mediaplayer = new MediaPlayer();
        //VIEW
        buttonReproducir = (Button) findViewById(R.id.buttonReproducir);
        buttonParar = (Button) findViewById(R.id.buttonParar);
        buttonGravar = (Button) findViewById(R.id.buttonGravar);
        //LISTENERS
        buttonReproducir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    mediaplayer.reset();
                    mediaplayer.setDataSource(file.getAbsolutePath()+"/"+files[fileSelected]);
                    mediaplayer.prepare();
                    mediaplayer.start();
                }catch(IOException ex){
                    ex.printStackTrace();
                }
            }
        });
        buttonParar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaplayer.stop();
            }
        });
        buttonGravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String timeStamp = DateFormat.getDateTimeInstance().format(
                        new Date()).replaceAll(":", "").replaceAll("/", "_")
                        .replaceAll(" ", "_");

                mediaRecorder = new MediaRecorder();
                mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                mediaRecorder.setMaxDuration(15000);
                mediaRecorder.setAudioEncodingBitRate(32768);
                mediaRecorder.setAudioSamplingRate(8000); // No emulador só 8000 coma
                mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
                mediaRecorder.setOutputFile(file.getAbsolutePath() + "/" + timeStamp + ".3gp");
                try {
                    mediaRecorder.prepare();
                } catch (Exception e) {
                    mediaRecorder.reset();
                }
                mediaRecorder.start();
                openDialog();
            }
        });
    }

    private void createFolder(){
        Intent intent = getIntent();
        String userName = intent.getStringExtra("name");
        file=new File(Environment.getExternalStorageDirectory()+"/AUDIO/" + userName +"/");
        if(!file.exists()) file.mkdirs();
    }

    private void setUpAdapter(){
        spinnerAudio = (Spinner) findViewById(R.id.spinnerAudio);
        files = file.list();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, files);
        spinnerAudio.setAdapter(adapter);
        spinnerAudio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                fileSelected = i;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
    }
    private void openDialog(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage("Recording");
        dialog.setPositiveButton("Stop", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    mediaRecorder.stop();
                    mediaRecorder.release();
                    mediaRecorder=null;
                }
            });
        dialog.show();
    }

}
