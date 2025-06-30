package com.example.gestorestacionamientofx.Model;

import java.math.BigDecimal;

public class Servicio {

    private int id_servicio;
    private String descripcionServicio;
    private BigDecimal costoServicio;

    public Servicio(int id_servicio, String descripcionServicio, BigDecimal costoServicio) {
        this.id_servicio = id_servicio;
        this.descripcionServicio = descripcionServicio;
        this.costoServicio = costoServicio;
    }

//    creo el constructor vacio que necesita JavaFX
    public Servicio(){
    }

    public int getId_servicio() {
        return id_servicio;
    }

    public void setId_servicio(int id_servicio) {
        this.id_servicio = id_servicio;
    }

    public void setDescripcionServicio(String descripcionServicio) {
        this.descripcionServicio = descripcionServicio;
    }

    public void setCostoServicio(BigDecimal costoServicio) {
        this.costoServicio = costoServicio;
    }

    public String getDescripcionServicio() {
        return descripcionServicio;
    }

    public BigDecimal getCostoServicio() {
        return costoServicio;
    }

    @Override
    public String toString() {
        return "Servicio [" +
                "Id: " + id_servicio + '\''+
                "Descripcion: " + descripcionServicio + '\'' +
                ", Precio: " + costoServicio +
                ']';
    }
}
