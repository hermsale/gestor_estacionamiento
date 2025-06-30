package com.example.gestorestacionamientofx.Model;

import java.time.LocalDateTime;

public class Cochera {

    private int id_cochera;
    private int codigo_Cochera;
//    guarda el estado de ocupacion de la cochera: disponible/ocupado
    private EstadoCochera estadoCochera;
    private Vehiculo vehiculo;
//    guarda el tipo de contrato: hora/dia/mes
    private TipoContrato contrato;
//    guarda el tipo de servicio contratado si lo hubiese: lavado/lavado y aspirado/...
    private Servicio servicio;
    private LocalDateTime fechaIngreso;
    private LocalDateTime fechaSalida;

    public Cochera(int id_cochera, int codigo_Cochera, EstadoCochera estadoCochera, Vehiculo vehiculo, TipoContrato contrato, Servicio servicio, LocalDateTime fechaIngreso, LocalDateTime fechaSalida) {
        this.id_cochera = id_cochera;
        this.codigo_Cochera = codigo_Cochera;
        this.estadoCochera = estadoCochera;
        this.vehiculo = vehiculo;
        this.contrato = contrato;
        this.servicio = servicio;
        this.fechaIngreso = fechaIngreso;
        this.fechaSalida = fechaSalida;
    }

    public Cochera(){}

    public int getId_cochera() {
        return id_cochera;
    }

    public void setId_cochera(int id_cochera) {
        this.id_cochera = id_cochera;
    }

    public int getCodigo_Cochera() {
        return codigo_Cochera;
    }

    public void setCodigo_Cochera(int codigo_Cochera) {
        this.codigo_Cochera = codigo_Cochera;
    }

    public EstadoCochera getEstadoCochera() {
        return estadoCochera;
    }

    public void setEstadoCochera(EstadoCochera estadoCochera) {
        this.estadoCochera = estadoCochera;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public TipoContrato getContrato() {
        return contrato;
    }

    public void setContrato(TipoContrato contrato) {
        this.contrato = contrato;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public LocalDateTime getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDateTime fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public LocalDateTime getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDateTime fechaSalida) {
        this.fechaSalida = fechaSalida;
    }
}
