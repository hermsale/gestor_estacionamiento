package com.example.gestorestacionamientofx.Model;

public class Pickup extends Vehiculo{

    public Pickup(String patente) {
        super(patente);
    }

//    aplico un recargo del 20%
    @Override
    public double obtenerRecargo() {
        return 0.20;
    }
}
