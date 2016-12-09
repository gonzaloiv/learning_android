package com.example.gonzalo.u3_a14gonzaloiv;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class U3_a14gonzaloiv extends ActionBarActivity {

    private static final int CODE = 33;
    private static final String NOTEXT = "";

    Button button_varios;
    Button button_datos;

    AlertDialog.Builder venta;

    // Cadrod de texto ocultos para gardar datos que se modifican no cambio de estado
    TextView text_termo;
    TextView text_numero;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u3_a14gonzaloiv);

        button_varios = (Button) findViewById(R.id.button_varios);
        button_datos = (Button) findViewById(R.id.button_datos);

        // Datos segunda activity: Termo de busca e número de teléfono
        text_termo = (TextView) findViewById(R.id.text_termo);
        text_numero = (TextView) findViewById(R.id.text_numero);

        // Instancia da clase cos Datos da activity secundaria
        final Data dataClass = Data.getInstance();

        // Listener para el button1 en click corto
        button_varios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Abrir segunda activity
                Intent secondary = new Intent(U3_a14gonzaloiv.this, U3_a14gonzaloiv_B.class);
                startActivityForResult(secondary, CODE);

            }
        });
        // Listener para el button1 en click largo
        button_varios.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                // Chamada á función do AlertDialog
                createDialogWindow();
                return true;

            }
        });

        // Listener para el button2
        button_datos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (text_numero.getText().toString().equals(NOTEXT) || text_termo.getText().toString().equals(NOTEXT)){
                    // Toast en caso de non ter introducido datos
                    Toast.makeText(
                            v.getContext(),
                            "Non se introduciron datos...",
                            Toast.LENGTH_SHORT).
                            show();
                } else {
                    // Toast para amosar o nome e o teléfono da outra activity
                    // a partir dos TextView ocultos
                    Toast.makeText(
                            v.getContext(),
                            "Datos introducidos. \n" +
                                    "Termo de busca: " + text_termo.getText() +
                                    "\nTeléfono: " + text_numero.getText(),
                            Toast.LENGTH_SHORT).
                            show();
                }
            }
        });

    } // onCreate

    // Función para o AlertDialog feita usando unha clase para os datos
    public void createDialogWindow(){

        // Instancia da clase de datos para chamada e busca
        final Data dataClass = Data.getInstance();

        venta = new AlertDialog.Builder(U3_a14gonzaloiv.this);
        venta.setTitle(getResources().getString(R.string.dialog_title));
        venta.setCancelable(true);

        // Caso de busca
        venta.setPositiveButton(getResources().getString(R.string.dialog_option1), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Intent para actividade secundaria
                Intent intent;

                //Sen texto
                if (dataClass.termo == NOTEXT) {
                    intent = new Intent(Intent.ACTION_WEB_SEARCH).putExtra(SearchManager.QUERY, "casa");

                    // Con texto
                } else {
                    intent = new Intent(Intent.ACTION_WEB_SEARCH);
                    intent.putExtra(SearchManager.QUERY, dataClass.termo);
                }
                U3_a14gonzaloiv.this.startActivity(intent);
            }
        });

        // Caso de chamada
        venta.setNegativeButton(getResources().getString(R.string.dialog_option2), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Intent para actividade secundaria
                Intent intent;

                // Sen número
                if (dataClass.numero == NOTEXT) {
                    Toast.makeText(
                            U3_a14gonzaloiv.this,
                            "Ningún número introducido...",
                            Toast.LENGTH_SHORT).
                            show();

                    // Con número
                } else {
                    intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + dataClass.numero));
                    U3_a14gonzaloiv.this.startActivity(intent);
                }
            }
        });

        // Mostra o AlertDialog
        venta.create().show();

    } // AlertDialog

    // Función para llamar a la activity secundaria esperando datos
    public void onActivityResult(int requestCode, int resultCode, Intent data){

        text_termo = (TextView) findViewById(R.id.text_termo);
        text_numero = (TextView) findViewById(R.id.text_numero);

        if (requestCode == CODE) {
            if (resultCode == RESULT_OK) {

                // Gárdanse os datos na clase Data para a chamada e a busca
                Data dataClass = Data.getInstance();
                dataClass.numero=data.getExtras().getString("NUMERO");
                dataClass.termo=data.getExtras().getString("TERMO");

                // Gárdanse os datos nos TextView ocultos para o Toast
                text_numero.setText(data.getExtras().getString("NUMERO"));
                text_termo.setText(data.getExtras().getString("TERMO"));

            } else {
                // Aviso ao usuario en caso de saídas imprevistas
                Toast.makeText(
                        this,
                        "Saíches da actividade secundaria sen premer o botón Pechar",
                        Toast.LENGTH_SHORT).
                        show();
            }
        }
    } // onActivityResult

    // Garda os datos aínda despois dun cambio de estado
    @Override
    protected void onSaveInstanceState(Bundle estado) {
        estado.putString("TERMO", text_termo.getText().toString());
        estado.putString("NUMERO", text_numero.getText().toString());
        super.onSaveInstanceState(estado);
    }

    // Recupera os datos
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        text_termo.setText(savedInstanceState.getString("TERMO"));
        text_numero.setText(savedInstanceState.getString("NUMERO"));
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
