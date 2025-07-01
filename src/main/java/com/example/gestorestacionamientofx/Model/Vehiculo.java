package com.example.gestorestacionamientofx.Model;

public abstract class  Vehiculo {
//    defino como final la patente y la descripcion, ya que una vez que se crean, no se deben poder modificar
    private final String patente;
    private final String descripcion;

//    defino la clase constructor
    public Vehiculo(String patente, String descripcion) {
        this.patente = patente;
        this.descripcion = descripcion;
    }

    //    genero los getter
    public String getDescripcion() {
        return descripcion;
    }

    public String getPatente() {
        return patente;
    }

    public abstract double obtenerRecargo();
}
