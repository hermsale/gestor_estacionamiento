package com.example.gestorestacionamientofx.Model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Facturacion {
    private int id_facturacion;
    private LocalDateTime fecha_factura;
    private String patente;
    private String tipo_vehiculo;
    private int codigo_cochera;
    private String tipo_contrato;
    private LocalDateTime fecha_ingreso;
    private LocalDateTime fecha_egreso;
    private String duracion;
    private String servicio;
    private int descuento;
    private BigDecimal monto_total;

    public Facturacion() {}


    public Facturacion(int id_facturacion, LocalDateTime fecha_factura, String patente, String tipo_vehiculo, int codigo_cochera, String tipo_contrato,
                       LocalDateTime fecha_ingreso, LocalDateTime fecha_egreso, String duracion, String servicio, int descuento, BigDecimal monto_total) {
        this.id_facturacion = id_facturacion;
        this.fecha_factura = fecha_factura;
        this.patente = patente;
        this.tipo_vehiculo = tipo_vehiculo;
        this.codigo_cochera = codigo_cochera;
        this.tipo_contrato = tipo_contrato;
        this.fecha_ingreso = fecha_ingreso;
        this.fecha_egreso = fecha_egreso;
        this.duracion = duracion;
        this.servicio = servicio;
        this.descuento = descuento;
        this.monto_total = monto_total;
    }

    public int getId_facturacion() {
        return id_facturacion;
    }

    public void setId_facturacion(int id_facturacion) {
        this.id_facturacion = id_facturacion;
    }

    public LocalDateTime getFecha_factura() {
        return fecha_factura;
    }

    public void setFecha_factura(LocalDateTime fecha_factura) {
        this.fecha_factura = fecha_factura;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getTipo_vehiculo() {
        return tipo_vehiculo;
    }

    public void setTipo_vehiculo(String tipo_vehiculo) {
        this.tipo_vehiculo = tipo_vehiculo;
    }

    public int getCodigo_cochera() {
        return codigo_cochera;
    }

    public void setCodigo_cochera(int codigo_cochera) {
        this.codigo_cochera = codigo_cochera;
    }

    public String getTipo_contrato() {
        return tipo_contrato;
    }

    public void setTipo_contrato(String tipo_contrato) {
        this.tipo_contrato = tipo_contrato;
    }

    public LocalDateTime getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(LocalDateTime fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public LocalDateTime getFecha_egreso() {
        return fecha_egreso;
    }

    public void setFecha_egreso(LocalDateTime fecha_egreso) {
        this.fecha_egreso = fecha_egreso;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public BigDecimal getMonto_total() {
        return monto_total;
    }

    public void setMonto_total(BigDecimal monto_total) {
        this.monto_total = monto_total;
    }
}
