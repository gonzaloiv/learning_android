package com.example.a14gonzaloiv.ex_3;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    // Statics
    public final static String NOME="nome";
    public final static String IDADE="idade";


    ToggleButton toggleTexto;
    EditText editNome;
    EditText editIdade;
    ImageView imageProvincias;

    // radioGroup
    RadioGroup groupProvincias;
    RadioButton radioCoruna;
    RadioButton radioLugo;
    RadioButton radioPontevedra;
    RadioButton radioOurense;
    RadioButton radioExtra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editNome = (EditText) findViewById(R.id.editNome);
        editIdade = (EditText) findViewById(R.id.editIdade);
        toggleTexto = (ToggleButton) findViewById(R.id.toggleTexto);
        toggleTexto.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    editNome.setEnabled(true);
                    editIdade.setEnabled(true);
                } else {
                    editNome.setEnabled(false);
                    editIdade.setEnabled(false);
                }

            }
        });
        radioPontevedra = (RadioButton) findViewById(R.id.pontevedra);
        radioOurense = (RadioButton) findViewById(R.id.ourense);
        radioLugo = (RadioButton) findViewById(R.id.lugo);
        radioCoruna = (RadioButton) findViewById(R.id.coruna);
        radioCoruna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioOurense.setChecked(false);
                radioPontevedra.setChecked(false);
                radioLugo.setChecked(false);
                radioCoruna.setChecked(true);
            }
        });
        radioPontevedra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioOurense.setChecked(false);
                radioPontevedra.setChecked(true);
                radioLugo.setChecked(false);
                radioCoruna.setChecked(false);
            }
        });
        radioLugo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioOurense.setChecked(false);
                radioPontevedra.setChecked(false);
                radioLugo.setChecked(true);
                radioCoruna.setChecked(false);
            }
        });
        radioOurense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioCoruna.setChecked(false);
                radioPontevedra.setChecked(false);
                radioLugo.setChecked(false);
                radioOurense.setChecked(true);
            }
        });

        imageProvincias = (ImageView) findViewById(R.id.imageProvincias);
        imageProvincias.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                Intent intent= new Intent(MainActivity.this, ActivityImaxe.class);
                intent.putExtra(NOME, editNome.getText().toString());
                intent.putExtra(IDADE, editIdade.getText().toString());
                startActivity(intent);

                return true;
            }
        });
        imageProvincias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClassName("com.android.calculator2", "com.android.calculator2.Calculator");

                startActivity(intent);

            }
        });


    }


}
