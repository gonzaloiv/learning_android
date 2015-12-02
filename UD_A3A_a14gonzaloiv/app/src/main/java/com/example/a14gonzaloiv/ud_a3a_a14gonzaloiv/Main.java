package com.example.a14gonzaloiv.ud_a3a_a14gonzaloiv;

import java.lang.ref.WeakReference;
import java.util.Random;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


public class Main extends AppCompatActivity {

    // DATA
    static final int TEMPO_CRONO=20;
    private Random r;
    static boolean fin = false;
    static int nRandom;

    // ASYNCTASK
    private MiñaTarefa miñaTarefa;

    // THREAD
    private Thread meuFio;
    private ClassPonte ponte = new ClassPonte(this);


    // VIEW
    Button btnTEmpezar;
    Button btnTParar;
    Button btnAEmpezar;
    Button btnAParar;
    TextView viewConta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // INICIO
        viewConta = (TextView) findViewById(R.id.viewConta);
        r = new Random();

        btnTEmpezar = (Button) findViewById(R.id.btnTEmpezar);
        btnTEmpezar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((meuFio==null) || (!meuFio.isAlive())){
                    meuFio = new MeuFio();
                    meuFio.start();
                    nRandom=r.nextInt(6)+5;
                    viewConta.setText(""+nRandom);
                }
                else {
                    Toast.makeText(getApplicationContext(), "O fío aínda non acabou.", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnTParar = (Button) findViewById(R.id.btnTParar);
        btnTParar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fin=true;
            }
        });

        btnAEmpezar = (Button) findViewById(R.id.btnAEmpezar);
        btnAEmpezar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((miñaTarefa==null) || (miñaTarefa.getStatus()==AsyncTask.Status.FINISHED)){
                    miñaTarefa = new MiñaTarefa();
                    miñaTarefa.execute();
                    nRandom=r.nextInt(6)+5;
                    viewConta.setText("" + nRandom);
                }
                else {
                    Toast.makeText(getApplicationContext(), "A tarefa non acabou!!!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnAParar = (Button) findViewById(R.id.btnAParar);
        btnAParar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (miñaTarefa.getStatus()==AsyncTask.Status.RUNNING){
                    miñaTarefa.cancel(true);
                }
            }
        });
    }

    // INICIO DA CLASE HANDLER
    private static class ClassPonte extends Handler {
        private WeakReference<Main> mTarget = null;
        ClassPonte(Main target) {
            mTarget = new WeakReference<Main>(target);
        }
        @Override
        public void handleMessage(Message msg) {
            Main target = mTarget.get();
            if (msg.arg2==1){
                fin = false;
                if(nRandom == msg.arg1) {
                    Toast.makeText(target.getApplicationContext(), "Coinciden.", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(target.getApplicationContext(), "Non coinciden.", Toast.LENGTH_LONG).show();
                }
            }else {
                Toast.makeText(target.getApplicationContext(), "" + msg.arg1, Toast.LENGTH_LONG).show();
            }
        }
    }; // Fin do Handler

    // INICIO DA CLASE FIO
    private class MeuFio extends Thread{
        public void run(){
            for (int a = TEMPO_CRONO; a >= 0; a--){
                if(fin){
                    break;
                }
                try {
                    Thread.sleep(1000);
                    Message msg = new Message();
                    msg.arg1 = a;
                    ponte.sendMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Message msgFin = new Message();
            msgFin.arg2=1;
            ponte.sendMessage(msgFin);
        }
    };


    // INICIO DA ASYNCTASK
    private class MiñaTarefa extends AsyncTask<Void, Integer, Boolean> {
        int progreso;
        @Override
        protected Boolean doInBackground(Void... params) {
            for (int i = TEMPO_CRONO; i >= 0; i--) {
                try {
                    progreso=i;
                    publishProgress(i);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (isCancelled())
                    break;
            }
            return true;
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            Toast.makeText(getApplicationContext(), "" + progreso, Toast.LENGTH_LONG).show();

        }
        @Override
        protected void onPreExecute() {}

        @Override
        protected void onPostExecute(Boolean result) {}

        @Override
        protected void onCancelled() {
            if(nRandom == progreso) {
                Toast.makeText(getApplicationContext(), "Coinciden.", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(getApplicationContext(), "Non coinciden.", Toast.LENGTH_LONG).show();
            }
        }
    };

}
