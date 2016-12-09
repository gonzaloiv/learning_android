package com.example.gonzalo.ex_1;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main extends AppCompatActivity {

    //VIEW
    EditText editName;
    EditText editPhone;
    Button buttonCall;
    Button buttonAudio;
    Button buttonSalary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //VIEW
        editName = (EditText) findViewById(R.id.editName);
        editPhone = (EditText) findViewById(R.id.editPhone);
        buttonCall = (Button) findViewById(R.id.buttonCall);
        buttonAudio = (Button) findViewById(R.id.buttonAudio);
        buttonSalary = (Button) findViewById(R.id.buttonSalary);
        //LISTENERS
        buttonCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((editPhone.getText()).toString().equals("")){
                    Toast.makeText(getApplicationContext(), "You must introduce a phone number...", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + editPhone.getText()));
                    startActivity(intent);
                }
            }
        });
        buttonAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((editName.getText()).toString().equals("")){
                    Toast.makeText(getApplicationContext(), "You must introduce a username...", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(getApplicationContext(), Audio.class);
                    intent.putExtra("name", editName.getText().toString());
                    startActivity(intent);
                }
            }
        });
        buttonSalary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((editName.getText()).toString().equals("")){
                    Toast.makeText(getApplicationContext(), "You must introduce a username...", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(getApplicationContext(), SalaryMain.class);
                    intent.putExtra("name", editName.getText().toString());
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.itemAzul) {
            editName.setTextColor(getResources().getColor(R.color.blue));
            item.setChecked(true);
        }
        if(id == R.id.itemVerde) {
            editName.setTextColor(getResources().getColor(R.color.green));
            item.setChecked(true);
        }
        return true;
    }
}
