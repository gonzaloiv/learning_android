package com.example.gonzalo.u3_a14gonzaloiv;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class U3_a14gonzaloiv extends ActionBarActivity {

    Button button1;
    Button button2;

    AlertDialog.Builder venta;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u3_a14gonzaloiv);

        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);

        // Listener para el button1 en click corto
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Abrir segunda activity
                Intent secondary = new Intent(U3_a14gonzaloiv.this, U3_a14gonzaloiv_B.class);
                startActivity(secondary);

            }
        });
        // Listener para el button1 en click largo
        button1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                // AlertDialog: Chamar ao número ou buscar en google
                createDialogWindow();
                return true;

            }
        });

        // Listener para el button2
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Toast para amosar o nome e o teléfono da outra activity
                // Débese conservar o contido desta aínda en horizontal

            }
        });
    }

    // Función para o AlertDialog
    public void createDialogWindow(){

        // Condicións: casa como palabra clave + sen teléfono -> Toast
        // necesidade de permisos no manifest: teléfono e internet

        venta = new AlertDialog.Builder(U3_a14gonzaloiv.this);
        venta.setTitle(getResources().getString(R.string.dialog_title));
        venta.setMessage(getResources().getString(R.string.dialog_message));
        venta.setCancelable(true);

        // Caso de busca
        venta.setPositiveButton(getResources().getString(R.string.dialog_option1), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        // Caso de chamada
        venta.setNegativeButton(getResources().getString(R.string.dialog_option2), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        // Mostra o AlertDialog
        venta.create().show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_u3_a14gonzaloiv, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
