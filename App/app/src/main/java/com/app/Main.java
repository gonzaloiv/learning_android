package com.app;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main extends AppCompatActivity {

    // VIEW
    EditText etName;
    EditText etPhone;
    Button btnCall;
    Button btnVideo;

    // ACTIVITIES
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gestionVista();

    }

    private void gestionVista(){

        etName = (EditText) findViewById(R.id.etName);
        etPhone = (EditText) findViewById(R.id.etPhone);

        btnCall = (Button) findViewById(R.id.btnCall);
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etPhone.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "You must introduce a number", Toast.LENGTH_SHORT).show();
                }else{
                    intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + etPhone.getText().toString()));
                    startActivity(intent);
                }
            }
        });

        btnVideo = (Button) findViewById(R.id.btnVideo);
        btnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), Video.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.action_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id == R.id.opAzul) etName.setTextColor(getResources().getColor(R.color.blue));
        if(id == R.id.opVerde) etName.setTextColor(getResources().getColor(R.color.green));
        return super.onOptionsItemSelected(item);

    }

}
