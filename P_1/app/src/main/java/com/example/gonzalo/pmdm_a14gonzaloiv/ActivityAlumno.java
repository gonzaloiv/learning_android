package com.example.gonzalo.pmdm_a14gonzaloiv;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class ActivityAlumno extends AppCompatActivity {

    // LOGIN DETAILS
    static String USUARIO="a14gonzaloiv";
    static String CONTRASINAL="abc123.";

    // VIEW
    Button btnLogin;
    EditText etUsuario;
    EditText etContrasinal;
    ImageView imgHorario;

    // VIEW TYPE: False for login...
    boolean tipoVista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_alumno);

        //SETUP FOR THE VIEWS
        etUsuario = (EditText) findViewById(R.id.et_usuario);
        etContrasinal = (EditText) findViewById(R.id.et_contrasinal);
        imgHorario = (ImageView) findViewById(R.id.img_horario);
        // LOGIN VIEW
        setLoginView();
        // METHOD FOR THE BUTTON
        button();

    }

    private void button(){
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!tipoVista) {
                    if (etUsuario.getText().toString().equals(USUARIO) & etContrasinal.getText().toString().equals(CONTRASINAL)) {
                        setAdminView();
                    } else {
                        Toast.makeText(getApplicationContext(), "Algún dato é incorrecto...", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    if(etUsuario.getText().toString().equals(CONTRASINAL)
                            & !etContrasinal.getText().toString().equals("")){
                        CONTRASINAL = etContrasinal.getText().toString();
                        Toast.makeText(getApplicationContext(), "Contrasinal modificado", Toast.LENGTH_SHORT).show();
                    } else{
                        Toast.makeText(getApplicationContext(), "Algún dato é incorrecto...", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    // VIEW  TO LOGIN
    private void setLoginView(){
        etContrasinal.setText("");
        etUsuario.setText("");
        etUsuario.setHint("Nome de usuario");
        etUsuario.setInputType(InputType.TYPE_CLASS_TEXT);
        etContrasinal.setHint("Contrasinal");
        etUsuario.setInputType(InputType.TYPE_TEXT_VARIATION_WEB_PASSWORD);
        imgHorario.setVisibility(View.GONE);
        tipoVista = false;
    }

    // VIEW TO ADMIN
    private void setAdminView(){
        etContrasinal.setText("");
        etUsuario.setText("");
        etUsuario.setHint("Antigo contrasinal");
        etUsuario.setInputType(InputType.TYPE_TEXT_VARIATION_WEB_PASSWORD);
        etContrasinal.setHint("Novo contrasinal");
        etUsuario.setInputType(InputType.TYPE_TEXT_VARIATION_WEB_PASSWORD);
        imgHorario.setVisibility(View.VISIBLE);
        tipoVista = true;
    }

}
