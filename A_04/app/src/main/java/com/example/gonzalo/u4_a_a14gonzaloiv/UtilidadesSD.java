package com.example.gonzalo.u4_a_a14gonzaloiv;

import android.app.Activity;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

// Clase para poder chamar ás ferramentas de acceso á SD de modo estático
public class UtilidadesSD {

    // Variables da SD utilizadas nas tres Activities
    public static boolean sdDisponible = false;
    public static boolean sdAccesoEscritura = false;
    public static File dirFicheiroSD;
    public static File rutaCompleta;
    public static String nomeFicheiro= "coches.txt";

    // Método para engadir datos ao ficheiro
    public static void engadirDatos(Boolean sobrescribir, TextView textCoches, Activity main){

        String novoTexto="";
        comprobarEstadoSD(main);
        establecerDirectorioFicheiro(main);

        if (sdAccesoEscritura && comprobarTexto(textCoches, main)){
            try {
                // Proceso de escritura atendendo a se sobrescribe ou non
                OutputStreamWriter osw = new OutputStreamWriter(
                        new FileOutputStream(rutaCompleta, sobrescribir));

                novoTexto = textCoches.getText() + " - " + obterDataActual() + "\n";
                osw.write(novoTexto);
                osw.close();

                // Mensaxe de LogCat: Ruta completa do ficheiro
                Log.i("DEPURACIÓN SD", rutaCompleta.getPath());

                // Mensaxe de LogCat: Contido a engadir ao ficheiro
                Log.i("DEPURACIÓN SD", novoTexto);

                // Limpamos o EditText
                textCoches.setText("");

            } catch (Exception ex) {Log.e("SD", "Error escribindo no ficheiro");}
        }
    }

    // Método para obter a data e darlle o formato correcto
    private static String obterDataActual(){
        String dataActual= "";
        Calendar cal = Calendar.getInstance();
        // Formato con SimpleDateFormat
        SimpleDateFormat format = new SimpleDateFormat("EEEE, MMMM d, yyyy 'at' h:mm a");
        dataActual=format.format(cal.getTime());

        return dataActual;
    }

    // Métodos de comprobación da SD
    public static void comprobarEstadoSD(Activity main){
        String estado = Environment.getExternalStorageState();
        Log.e("SD", estado);

        if(estado.equals(Environment.MEDIA_MOUNTED)){
            sdDisponible = true;
            sdAccesoEscritura = true;
        }else if(estado.equals(Environment.MEDIA_MOUNTED_READ_ONLY)){
            sdDisponible = true;
        }else{
            Toast.makeText(
                    main.getApplicationContext(),
                    "A tarxeta SD non está dispoñible",
                    Toast.LENGTH_SHORT
            ).show();
            main.finish();
        }
    }

    public static void establecerDirectorioFicheiro(Activity main) {
        if (sdDisponible) {
            // Se quixeremos utilizar o directorio xeral da SD
            // dirFicheiroSD = Environment.getExternalStorageDirectory();
            // Usando getExternalFiles(null), úsase a ruta:
            // /storage/sdcard/Android/data/paquete_java/files/nome_do_ficheiro
            // e así, os datos serán borrados unha vez eliminada a aplicación
            dirFicheiroSD = main.getApplicationContext().getExternalFilesDir(null);
            rutaCompleta = new File(dirFicheiroSD.getAbsolutePath(), nomeFicheiro);
        }
    }

    // Exemplo de posible mellora á hora de introducir datos.
    // Neste caso sño comproba que hay alg introducido
   public static Boolean comprobarTexto(TextView textCoches, Activity main){

       Boolean textoCorrecto = true;
       if(textCoches.getText().toString().equals("")){
           Toast.makeText(main.getApplicationContext(), "Texto non introducido", Toast.LENGTH_SHORT).show();
           textoCorrecto = false;
       }
       return textoCorrecto;
   }

   public static ArrayList<String> obterDatos(ArrayList<String> datos, Activity main){

        String lTexto = "";
        // Mantemos a comprobación da SD para a lectura
        comprobarEstadoSD(main);
        establecerDirectorioFicheiro(main);

        if (sdDisponible) {
            try {

                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(rutaCompleta)));

                while ((lTexto = br.readLine()) != null) {
                    datos.add(lTexto);
                    Log.i("Depuración obterDatos", lTexto);
                }
                br.close();

            } catch (Exception ex) {
                Toast.makeText(main.getApplicationContext(), "Problemas lendo o ficheiro", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(main.getApplicationContext(), "A tarxeta SD non está dispoñible", Toast.LENGTH_SHORT).show();
        }
        return datos;
    }

}
