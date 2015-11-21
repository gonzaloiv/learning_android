package com.example.gonzalo.ud_a2a_a14gonzaloiv;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class Main extends AppCompatActivity {

    // AUDIO & FILES
    MediaPlayer mediaplayer = new MediaPlayer();
    MediaRecorder mediaRecorder;
    String pathAudio = Environment.getExternalStorageDirectory().getAbsolutePath() + "/UD_A2A/MUSICA/";
    String pathFoto = Environment.getExternalStorageDirectory().getAbsolutePath() + "/UD_A2A/FOTO/";
    String nameArquivoAudio = "";

    // VIEW
    Button buttonReproducir;
    Button buttonGravar;
    Button buttonFoto;
    Spinner spinnerAudio;
    ImageView viewFoto;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // audio files startup
        copyAssets();

        // spinner startup
        spinnerAudio = (Spinner) findViewById(R.id.spinnerAudio);
        spinnerAudio.setAdapter(startAdapter());
        spinnerAudio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ArrayAdapter<String> adapter = (ArrayAdapter<String>) adapterView.getAdapter();
                nameArquivoAudio = adapter.getItem(i);
                Toast.makeText(Main.this, "Escollido: " + nameArquivoAudio, Toast.LENGTH_SHORT);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        buttonReproducir = (Button) findViewById(R.id.buttonReproducir);
        buttonReproducir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pathArquivo = pathAudio + nameArquivoAudio;
                try{
                    Toast.makeText(view.getContext(), "Soa: " + nameArquivoAudio, Toast.LENGTH_SHORT).show();
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
                mediaRecorder = new MediaRecorder();
                // path settings
                File folder = new File(pathAudio);
                nameArquivoAudio = pathAudio + "audio" +folder.list().length+".3gp";
                // recorder settings
                mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                mediaRecorder.setMaxDuration(10000);
                mediaRecorder.setAudioEncodingBitRate(32768);
                mediaRecorder.setAudioSamplingRate(8000); // No emulador só 8000 coma
                mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
                mediaRecorder.setOutputFile(nameArquivoAudio);
                try {
                    mediaRecorder.prepare();
                } catch (Exception e) {
                    mediaRecorder.reset();
                }
                mediaRecorder.start();
                // interface dialog
                openDialog();
            }
        });

        buttonFoto = (Button) findViewById(R.id.buttonFoto);
        buttonFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                File arquivoFoto = new File(pathFoto, "thisFoto.jpg");
                viewFoto = (ImageView) findViewById(R.id.viewFoto);
                viewFoto.setImageBitmap((Bitmap) data.getExtras().get("data"));
            }
        }
    }

    private void copyAssets() {
        AssetManager assetManager = getAssets();
        String[] files = null;
        try {
            files = assetManager.list("");
        } catch (IOException e) {
            Log.e("Import assets: ", e.getMessage());
        }
        for(String filename : files) {
            InputStream in = null;
            OutputStream out = null;
            try {
                in = assetManager.open(filename);
                out = new FileOutputStream(pathAudio + filename);
                copyFile(in, out);
                in.close();
                in = null;
                out.flush();
                out.close();
                out = null;
            } catch(Exception e) {
                Log.e("Import assets: ", e.getMessage());
            }
        }
    }

    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1){
            out.write(buffer, 0, read);
        }
    }

    private void openDialog(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage("Gravando...");
        dialog.setPositiveButton("Stop", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mediaRecorder.stop();
                mediaRecorder.release();
                mediaRecorder = null;
                spinnerAudio.setAdapter(startAdapter());
            }
        });
        dialog.show();

    }

    private ArrayAdapter<String> startAdapter() {
        ArrayList<String> audio = new ArrayList<String>();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, audio);
        File fileDirectorio = new File(pathAudio);
        for(File f : fileDirectorio.listFiles()){
            if(f.isFile()){
                adapter.add(f.getName());
            }
        }
        return adapter;
    }

}
