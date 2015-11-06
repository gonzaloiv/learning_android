package com.example.a14gonzaloiv.ex_3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityImaxe extends AppCompatActivity {

    Button buttonPechar;
    TextView textSNome;
    TextView textSIdade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_imaxe);

        Intent intent = getIntent();

        textSNome = (TextView) findViewById(R.id.secondTNome);
        textSNome.setText(intent.getExtras().getString(MainActivity.NOME));
        textSIdade = (TextView) findViewById(R.id.secondTIdade);
        textSIdade.setText(intent.getExtras().getString(MainActivity.IDADE));


        buttonPechar = (Button) findViewById(R.id.buttonPechar);
        buttonPechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityImaxe.super.finish();
            }
        });
    }

}
