package com.example.gestorestacionamientofx.Model;

public abstract class  Vehiculo {
    private String patente;

//    defino la clase constructor
    public Vehiculo(String patente) {
        this.patente = patente;
    }

//    genero los getter y setter
    public String getPatente() {
        return patente;
    }

    private void setPatente(String patente) {
        this.patente = patente;
    }

    public abstract double obtenerRecargo();
}
