package com.app;

import android.media.audiofx.EnvironmentalReverb;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class XML extends AppCompatActivity {

    // VIEW
    Button btnXML;
    Button btnShowSalaries;
    Button btnXmlToFile;
    TextView tvSalaries;
    // DB
    private BD bd;
    // Model
    Salary salary;
    ArrayList<Salary> salaries;
    // FILES
    File arquivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salary);

        bd = new BD(getApplicationContext());
        bd.sqlLiteDB = bd.getWritableDatabase();

        salaries = bd.getSalaries();

        tvSalaries = (TextView) findViewById(R.id.tvSalaries);

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
                try {
                    fio.join();
                }catch(Exception ex){
                    ex.printStackTrace();
                }
                procesarXML();
            }
        });

        btnShowSalaries = (Button) findViewById(R.id.btnShowSalaries);
        btnShowSalaries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salaries = bd.getSalaries();
                showSalaries();
            }
        });

        btnXmlToFile = (Button) findViewById(R.id.btnSalariesToFile);
        btnXmlToFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    fromDBToFile();
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        });
    }

    private void fromDBToFile() throws IOException {
        salaries = bd.getSalaries();
        File fsaida = new File(Environment.getExternalStorageDirectory(),"salaries.txt" );
        if(fsaida.exists()) fsaida.delete();
        FileOutputStream fos = new FileOutputStream(fsaida);
        OutputStreamWriter osw = new OutputStreamWriter(fos);
        int tamread;
        char[] buffer = new char[2048];
        String cadena;
        for(Salary s : salaries){
            osw.write(s.toString()+"\n");
        }
        osw.close();
    }

    private void showSalaries(){
        tvSalaries.setText("");
        tvSalaries.append("Total Salary\tMonth\n");
        for (Salary s : salaries) {
            tvSalaries.append("" + s.getTotalSalary() + "\t\t" + s.getMonth() + "\n");
        }
    }

    private void procesarXML(){
        try {
            Boolean xaIncluido=false;
            Salary salary=null;
            InputStream is = new FileInputStream(arquivo);
            XmlPullParser parser = Xml.newPullParser();
            parser.setInput(is, "UTF-8");
            while(parser.nextTag() != XmlPullParser.END_DOCUMENT){
                if(parser.nextTag() == XmlPullParser.START_TAG) {
                    if (parser.getName().equals("salary")) {
                        salary = new Salary();
                        parser.nextTag();
                        salary.setMonth(parser.nextText());
                        parser.nextTag();
                        salary.setTotalSalary(Float.parseFloat(parser.nextText()));
                        parser.nextTag();
                        salary.setTotalSalary(salary.getTotalSalary() + Float.parseFloat(parser.nextText()));
                        parser.nextTag();
                        salary.setTotalSalary(salary.getTotalSalary() + Float.parseFloat(parser.nextText()));
                    }
                }
                if(parser.nextTag()==XmlPullParser.END_TAG){
                    if(parser.getName().equals("salary")){
                        for(Salary s : salaries){
                            if (salary.getMonth().equals(s.getMonth())){
                                xaIncluido=true;
                            }
                        }
                        if(!xaIncluido)
                            bd.insertSalary(salary);
                    }
                }
                parser.next();
            }
            is.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
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

            // CONEXIÓN
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            int response = connection.getResponseCode();

            // CONFIGURACIÓN DE ARCHIVOS Y STREAMS
            OutputStream os;
            InputStream in;
            String nomeArquivo = Uri.parse(URL_SALARY).getLastPathSegment();
            File ruta = new File(Environment.getExternalStorageDirectory(), "/SALARY");
            if(!ruta.exists())ruta.mkdirs();
            arquivo = new File(ruta.getAbsolutePath() + File.separator + nomeArquivo);

            // LECTURA Y GUARDADO
            os = new FileOutputStream(arquivo);
            in = connection.getInputStream();
            byte data[] = new byte[1024];
            int count;
            while ((count = in.read(data)) != -1) {
                os.write(data, 0, count);
            }
            // FIN DE LA LECTURA Y DE LA CONEXIÓN
            os.flush();
            os.close();
            in.close();
            connection.disconnect();

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}

