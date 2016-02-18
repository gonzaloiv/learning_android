package com.example.gonzalo.pmdm_a14gonzaloiv;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Main extends AppCompatActivity {

    // VIEW
    Button btnCentro;
    Button btnOferta;
    Button btnAlumno;
    Button btnContacto;
    // ACTIVITIES
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // SETUP FOR THE BUTTONS (VIEW AND LISTENERS)
        buttons();

    }

    // METHOD FOR THE BUTTONS
    private void buttons(){
        btnCentro = (Button) findViewById(R.id.btn_centro);
        btnCentro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), ActivityCentro.class);
                startActivity(intent);
            }
        });
        btnOferta = (Button) findViewById(R.id.btn_oferta);
        btnOferta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), ActivityOferta.class);
                startActivity(intent);
            }
        });
        btnAlumno = (Button) findViewById(R.id.btn_alumno);
        btnAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), ActivityAlumno.class);
                startActivity(intent);
            }
        });
        btnContacto = (Button) findViewById(R.id.btn_contacto);
        btnContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), ActivityContacto.class);
                startActivity(intent);
            }
        });
    }

    // LISTENER FOR THE MENU
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_youtube:
                intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/user/sanclementetv"));
                startActivity(intent);
                return true;
            case R.id.action_facebook:
                intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/IES-San-Clemente-372154394748/"));
                startActivity(intent);
                return true;
            case R.id.action_sair:
                System.exit(1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // SETUP FOR THE MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
