package com.example.gonzalo.u3_a14gonzaloiv;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class U3_a14gonzaloiv extends ActionBarActivity {

    private static final int CODE = 33;

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
                startActivityForResult(secondary, 33);

            }
        });
        // Listener para el button1 en click largo
        button1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                // Chamada ao método de AlertDialog
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
                Toast.makeText(
                        v.getContext(),
                        "Aquí saírían os datos da Activity 2",
                        Toast.LENGTH_SHORT).
                        show();

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
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY, "LoL");
                U3_a14gonzaloiv.this.startActivity(intent);
            }
        });

        // Caso de chamada
        venta.setNegativeButton(getResources().getString(R.string.dialog_option2), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:(+34)981588512"));
                U3_a14gonzaloiv.this.startActivity(intent);
            }
        });

        // Mostra o AlertDialog
        venta.create().show();

    }

    // Función para llamar a la activity secundaria esperando datos
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == CODE) {
            if (resultCode == RESULT_OK) {
                    Toast.makeText(
                            this,
                            data.getExtras().getString("BUSCA") +
                            data.getExtras().getString("TELEFONO"),
                            Toast.LENGTH_SHORT).
                            show();

                // Para saídas incorrectas da activity secundaria
            } else {
                Toast.makeText(
                        this,
                        "Saíches da actividade secundaria sen premer o botón Pechar",
                        Toast.LENGTH_SHORT).
                        show();
            }
        }
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
