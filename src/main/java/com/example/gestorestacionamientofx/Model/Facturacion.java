package com.example.gestorestacionamientofx.Model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Facturacion {
    private int id_facturacion;
    private LocalDateTime fecha_factura;
    private BigDecimal montoTotal;
    private Cochera cochera;


    public Facturacion() {}

    public Facturacion(Cochera cochera, BigDecimal montoTotal, LocalDateTime fecha_factura, int id_facturacion) {
        this.cochera = cochera;
        this.montoTotal = montoTotal;
        this.fecha_factura = fecha_factura;
        this.id_facturacion = id_facturacion;
    }

    public int getId_facturacion() {
        return id_facturacion;
    }

    public void setId_facturacion(int id_facturacion) {
        this.id_facturacion = id_facturacion;
    }

    public Cochera getCochera() {
        return cochera;
    }

    public void setCochera(Cochera cochera) {
        this.cochera = cochera;
    }

    public BigDecimal getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(BigDecimal montoTotal) {
        this.montoTotal = montoTotal;
    }

    public LocalDateTime getFecha_factura() {
        return fecha_factura;
    }

    public void setFecha_factura(LocalDateTime fecha_factura) {
        this.fecha_factura = fecha_factura;
    }

}
