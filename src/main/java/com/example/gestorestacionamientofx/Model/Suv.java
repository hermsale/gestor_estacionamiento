package com.example.gestorestacionamientofx.Model;

public class Suv extends Vehiculo{



    public Suv(String patente) {
        super(patente, "SUV");
    }

    //    aplico un recargo del 10%
    @Override
    public double obtenerRecargo() {
        return 0.10;
    }
}
