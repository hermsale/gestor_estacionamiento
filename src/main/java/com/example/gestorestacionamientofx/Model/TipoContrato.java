package com.example.gestorestacionamientofx.Model;

import java.math.BigDecimal;

public class TipoContrato {
    private int id_contrato;
    private String nombreContrato;
    private BigDecimal precioBaseCochera;
    private BigDecimal descuentoServicio;

//    Tipos de contrato que se puede seleccionar para el ingreso de un vehiculo
public TipoContrato(int id_contrato, String nombreContrato, BigDecimal precioBaseCochera, BigDecimal descuentoServicio) {
        this.id_contrato = id_contrato;
        this.nombreContrato = nombreContrato;
        this.precioBaseCochera = precioBaseCochera;
        this.descuentoServicio = descuentoServicio;
    }

//    creo el contructor vacio que requiere javaFx
    public TipoContrato() {

    }

//creo los setter que requiere javaFX
    public void setNombreContrato(String nombreContrato) {
        this.nombreContrato = nombreContrato;
    }

    public void setId(int id_contrato) {
        this.id_contrato = id_contrato;
    }

    public void setPrecioBaseCochera(BigDecimal precioBaseCochera) {
        this.precioBaseCochera = precioBaseCochera;
    }

    public void setDescuentoServicio(BigDecimal descuentoServicio) {
        this.descuentoServicio = descuentoServicio;
    }

    public int getId() {
        return id_contrato;
    }

    public BigDecimal getDescuentoServicio() {
        return descuentoServicio;
    }

    public String getNombreContrato() {
        return nombreContrato;
    }

    public BigDecimal getPrecioBaseCochera() {
        return precioBaseCochera;
    }

//    se reflejan los tipos de contrato disponibles, su precio y dependiendo el tipo de contrato, se aplica o no un descuento
    @Override
    public String toString() {
        return nombreContrato;
    }
}


