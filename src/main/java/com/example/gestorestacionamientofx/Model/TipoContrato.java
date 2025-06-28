package com.example.gestorestacionamientofx.Model;

public class TipoContrato {
    private int id;
    private String nombreContrato;
    private double precioBaseCochera;
    private double descuentoServicio;

//    Tipos de contrato que se puede seleccionar para el ingreso de un vehiculo
    private TipoContrato(int id, String nombreContrato, double precioBaseCochera, double descuentoServicio) {
        this.id = id;
        this.nombreContrato = nombreContrato;
        this.precioBaseCochera = precioBaseCochera;
        this.descuentoServicio = descuentoServicio;
    }

    public int getId() {
        return id;
    }

    public double getDescuentoServicio() {
        return descuentoServicio;
    }

    public String getNombreContrato() {
        return nombreContrato;
    }

    public double getPrecioBaseCochera() {
        return precioBaseCochera;
    }

//    se reflejan los tipos de contrato disponibles, su precio y dependiendo el tipo de contrato, se aplica o no un descuento
    @Override
    public String toString() {
        return "[Tipo de Contrato: " +
                "id=" + id +
                ", Tipo de contrato='" + nombreContrato + '\'' +
                ", precio base= " + precioBaseCochera +
                ", Descuento aplicado a servicio= " + (descuentoServicio*100)+"%" +
                ']';
    }
}


