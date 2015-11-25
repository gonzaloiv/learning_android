package com.example.a14gonzaloiv.ud_a3a_a14gonzaloiv;

import java.lang.ref.WeakReference;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class Main extends AppCompatActivity {

    private final int TEMPO_CRONO=20;

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

        viewConta = (TextView) findViewById(R.id.viewConta);

        btnTEmpezar = (Button) findViewById(R.id.btnTEmpezar);
        btnTEmpezar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((meuFio==null) || (!meuFio.isAlive())){
                    meuFio = new MeuFio();
                    meuFio.start();
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

            }
        });
    }

    // INICO DA CLASE HANDLER
    private static class ClassPonte extends Handler {
        private WeakReference<Main> mTarget = null;
        ClassPonte(Main target) {
            mTarget = new WeakReference<Main>(target);
        }
        @Override
        public void handleMessage(Message msg) {
            Main target = mTarget.get();
            if (msg.arg2==1){
                Toast.makeText(target.getApplicationContext(), "ACABOUSE O CRONO", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(target.getApplicationContext(), msg.arg1, Toast.LENGTH_LONG).show();
            }
        }
    }; // Fin do Handler
    private ClassPonte ponte = new ClassPonte(this);

    // INICIO DA CLASE FIO
    private class MeuFio extends Thread{
        public void run(){
            for (int a=TEMPO_CRONO;a<=0;a--){
                try {
                    Thread.sleep(1000);
                    Message msg = new Message();
                    msg.arg1=a;
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
    private Thread meuFio;
}
