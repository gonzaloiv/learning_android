package com.example.gonzalo.u5_a14gonzaloiv;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class U5_a14gonzaloiv extends ActionBarActivity {

    // Configuración cámara
    private static final int REQUEST_CODE_CAMARA = 7;
    Intent intentCamara= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

    // Vista
    ImageView viewImaxe;
    TextView textCamara;
    Button limparImaxe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u5_a14gonzaloiv);

        // Inicio elementos vista
        textCamara = (TextView) findViewById(R.id.textCamara);
        viewImaxe = (ImageView) findViewById(R.id.viewImaxe);
        limparImaxe = (Button) findViewById(R.id.limparImaxe);

        registerForContextMenu(textCamara);

        limparImaxe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewImaxe.setImageBitmap(null);
            }
        });
    }

    // Método para recibir a imaxe
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode == RESULT_OK) {
            if (data == null) {
                Toast.makeText(this, "Non hai imaxe", Toast.LENGTH_LONG).show();
                return;
            }
            Bitmap bitMap = (Bitmap) data.getExtras().get("data");
            viewImaxe.setImageBitmap(bitMap);
        }else if(resultCode == RESULT_CANCELED){
            Toast.makeText(this, "Foto cancelada", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Fallo na captura da imaxe", Toast.LENGTH_LONG).show();
        }
    }

    // Inflado do menú
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_camara, menu);
        return true;
    }

    // Listener para o menú
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.itemCamara:
                startActivityForResult(intentCamara, REQUEST_CODE_CAMARA);
                return true;
            case R.id.itemSair:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }

    public void finish(){
        super.finish();
    }

    // Inicio menú contextual
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();

        menu.setHeaderTitle("Cámara");
        inflater.inflate(R.menu.menu_contextual, menu);

    }

    // Accións que realiza ao seleccionar o menú
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.contextCamara:
                startActivityForResult(intentCamara, REQUEST_CODE_CAMARA);
                return true;
            case R.id.contextCancel:
                return true;
            default:
                return super.onContextItemSelected(item);
        }

    }
}
