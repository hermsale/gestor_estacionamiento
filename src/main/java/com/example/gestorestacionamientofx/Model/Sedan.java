package com.example.gestorestacionamientofx.Model;

public class Sedan extends Vehiculo{
    public Sedan(String patente) {
        super(patente,"Sedan");
    }

//    implemento el metodo abstracto
    @Override
    public double obtenerRecargo() {
        return 0;
    }
}
