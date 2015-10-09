package com.example.gonzalo.u3_a14gonzaloiv;

// Singleton para gardar os datos entre activities
// sen asolagar o espazo de nomes global
public class Data {

    private static final Data INSTANCE = new Data();

    // Datos a gardar
    public String termo="";
    public String numero="";

    private Data(){};

    // Chamada á instancia
    public static Data getInstance(){
        return INSTANCE;
    }

}
