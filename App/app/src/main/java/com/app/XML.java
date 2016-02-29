package com.app;

import android.media.audiofx.EnvironmentalReverb;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class XML extends AppCompatActivity {

    // VIEW
    Button btnXML;
    // DB
    private BD bd;
    // Model
    Salary salary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salary);

        bd = new BD(getApplicationContext());
        bd.getWritableDatabase();

        btnXML = (Button) findViewById(R.id.btnXML);
        btnXML.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread fio = new Thread() {
                    @Override
                    public void run() {
                        descargarXML();
                    }
                };
                fio.start();

            }
        });
    }

    private void descargarXML() {
        String URL_SALARY="http://manuais.iessanclemente.net/images/5/53/Salaries.xml";
        URL url=null;
        try {
            url = new URL(URL_SALARY);
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
            return;
        }
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            int response = connection.getResponseCode();
            OutputStream os;
            InputStream in;
            String nomeArquivo = Uri.parse(URL_SALARY).getLastPathSegment();
            File arquivo = new File(Environment.getExternalStorageDirectory(), "/SALARIO/" + nomeArquivo);
            if(!arquivo.exists())arquivo.mkdirs();
            os = new FileOutputStream(arquivo);
            in = connection.getInputStream();
            byte data[] = new byte[1024];       // Buffer a utilizar
            int count;
            while ((count = in.read(data)) != -1) {
                os.write(data, 0, count);
            }
            os.flush();
            os.close();
            in.close();
            connection.disconnect();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}

