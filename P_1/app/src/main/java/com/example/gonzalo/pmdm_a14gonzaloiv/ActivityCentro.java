package com.example.gonzalo.pmdm_a14gonzaloiv;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class ActivityCentro extends AppCompatActivity {

    // VIEW
    TextView textHistoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_centro);

        // SET UP FOR THE TEXT VIEW
        textHistoria= (TextView) findViewById(R.id.text_historia);
        textHistoria.setMovementMethod(new ScrollingMovementMethod());
    }
}
