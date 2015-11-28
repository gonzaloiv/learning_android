package com.example.gonzalo.ud_a45a_a14gonzaloiv;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class Main extends AppCompatActivity {

    // STATIC
    public static enum TIPOREDE{MOBIL, ETHERNET, WIFI, SENREDE};
    private TIPOREDE conexion;
    private final String URL_DESCARGA="http://manuais.iessanclemente.net/images/2/20/Platega_pdm_rutas.xml";
    private File rutaArquivo;
    private Thread thread;

    // VIEW
    Button buttonDescargar;
    TextView tvArquivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonDescargar = (Button) findViewById(R.id.buttonDescargar);
        buttonDescargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread thread = new Thread() {
                    @Override
                    public void run() {
                            descargarArquivo();
                    }
                };
                thread.start();
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    lecturaArquivo();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    private void lecturaArquivo() throws IOException, XmlPullParserException {
        tvArquivo = (TextView) findViewById(R.id.tvArquivo);
        InputStream is = new FileInputStream(rutaArquivo);
        XmlPullParser parser = Xml.newPullParser();
        parser.setInput(is, "UTF-8");
        int evento = parser.next();
        while (evento != XmlPullParser.END_DOCUMENT) {
            if (evento == XmlPullParser.START_TAG) {
                if (parser.getName().equals("ruta")) {
                    parser.nextTag();
                    tvArquivo.append(parser.nextText() + ": ");
                    parser.nextTag();
                    tvArquivo.append(parser.nextText() + "\n");
                }
            }
            evento = parser.next();
        }
        is.close();
    }

    private TIPOREDE comprobarRede(){
        NetworkInfo networkInfo=null;
        ConnectivityManager connMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        networkInfo = connMgr.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()){
            switch(networkInfo.getType()){
                case ConnectivityManager.TYPE_MOBILE:
                    return TIPOREDE.MOBIL;
                case ConnectivityManager.TYPE_ETHERNET:
                    return TIPOREDE.ETHERNET;
                case ConnectivityManager.TYPE_WIFI:
                    return TIPOREDE.WIFI;
            }
        }
        return TIPOREDE.SENREDE;
    }

    private void descargarArquivo(){
        URL url=null;
        try{
            url = new URL(URL_DESCARGA);
        }catch(MalformedURLException ex){
            ex.printStackTrace();
            return;
        }

        HttpURLConnection conn = null;
        String nomeArquivo = Uri.parse(URL_DESCARGA).getLastPathSegment();
        rutaArquivo = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/RUTAS/" + nomeArquivo);
        if(rutaArquivo.exists()) rutaArquivo.delete();

        try{
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.connect();
            int response = conn.getResponseCode();
            if(response != HttpURLConnection.HTTP_OK){
                return;
            }
            OutputStream os = new FileOutputStream(rutaArquivo);
            InputStream in = conn.getInputStream();
            byte data[] = new byte[1024];
            int count;
            while((count=in.read(data)) != -1){
                os.write(data, 0, count);
            }
            os.flush();
            os.close();
            in.close();
            conn.disconnect();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
