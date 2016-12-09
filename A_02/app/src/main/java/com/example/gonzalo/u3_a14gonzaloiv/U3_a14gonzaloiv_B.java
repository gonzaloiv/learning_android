package com.example.gonzalo.u3_a14gonzaloiv;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class U3_a14gonzaloiv_B extends ActionBarActivity {

    EditText text1;
    EditText text2;
    Button button3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u3_a14gonzaloiv__b);

        button3 = (Button) findViewById(R.id.button3);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                text1 = (EditText) findViewById(R.id.text_edit1);
                text2 = (EditText) findViewById(R.id.text_edit2);

                // Garda os datos para a activity principal
                Intent data = new Intent();
                data.putExtra("TERMO", text1.getText().toString());
                data.putExtra("NUMERO", text2.getText().toString());

                // Condicións
                setResult(RESULT_OK, data);

                // Chamada ao método para pechar
                finish();

            }
        });
    } // onCreate

    // Función que pecha a actividade
    public void finish() {
        // Chamada á activity para pechar
        super.finish();
    }

}
