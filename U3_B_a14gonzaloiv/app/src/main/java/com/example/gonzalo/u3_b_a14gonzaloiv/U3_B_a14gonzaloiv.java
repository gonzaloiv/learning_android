package com.example.gonzalo.u3_b_a14gonzaloiv;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class U3_B_a14gonzaloiv extends FragmentActivity {

    private dialogo_mensaxe dialogo_mensaxe;
    private dialogo_tres dialogo_tres;
    private dialogo_list dialogo_list;
    private dialogo_radio dialogo_radio;
    private dialogo_checkbox dialogo_checkbox;
    private dialogo_entrada dialogo_entrada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u3__b_a14gonzaloiv);

        dialogo_mensaxe = new dialogo_mensaxe();
        dialogo_tres = new dialogo_tres();
        dialogo_list = new dialogo_list();
        dialogo_radio = new dialogo_radio();
        dialogo_checkbox = new dialogo_checkbox();
        dialogo_entrada = new dialogo_entrada();

    }

    // Botón para pechar os diálogos
    public void pecharDialogo(){
        Toast.makeText(this, dialogo_entrada.valorTexto, Toast.LENGTH_LONG).show();
    }

    public void onBotonClick(View view) {

        FragmentManager fm = getSupportFragmentManager();

        switch (view.getId()) {
            case R.id.btn_dialogo:
                dialogo_mensaxe.show(fm, "Diálogo con mensaxe");
                break;

            case R.id.btn_diag_tres_botons:
                dialogo_tres.show(fm, "Diálogo con tres botóns");
                break;

            case R.id.btn_diag_list_selecc:
                dialogo_list.show(fm, "Dialogo con selección");
                break;

            case R.id.btn_diag_radio_button:
                dialogo_radio.show(fm, "Diálogo con radio button");
                break;

            case R.id.btn_diag_checkbox:
                dialogo_checkbox.show(fm, "Diálogo con checkbox");
                break;

            case R.id.btn_diag_entrada_texto:
                dialogo_entrada.show(fm, "Diálogo con entrada de texto");
                break;

            default:
                break;
        }
    }

}