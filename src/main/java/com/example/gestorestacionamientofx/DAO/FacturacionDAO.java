package com.example.gestorestacionamientofx.DAO;

import com.example.gestorestacionamientofx.Model.Cochera;
import com.example.gestorestacionamientofx.Model.Facturacion;
import com.example.gestorestacionamientofx.Model.Response;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacturacionDAO extends BaseDAO<Facturacion> {

//metodo para guardar facturas en la bd
@Override
public Response<Facturacion> create(Facturacion entity) {
    String sql = "INSERT INTO facturacion (fecha_factura, patente, tipo_vehiculo, codigo_cochera, tipo_contrato, fecha_ingreso, fecha_egreso, duracion, servicio, descuento, monto_total) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        stmt.setTimestamp(1, Timestamp.valueOf(entity.getFecha_factura()));
        stmt.setString(2, entity.getPatente());
        stmt.setString(3, entity.getTipo_vehiculo());
        stmt.setInt(4, entity.getCodigo_cochera());
        stmt.setString(5, entity.getTipo_contrato());
        stmt.setTimestamp(6, Timestamp.valueOf(entity.getFecha_ingreso()));
        stmt.setTimestamp(7, Timestamp.valueOf(entity.getFecha_egreso()));
        stmt.setString(8, entity.getDuracion());
        stmt.setString(9, entity.getServicio());
        stmt.setInt(10, entity.getDescuento());
        stmt.setBigDecimal(11, entity.getMonto_total());

        int rows = stmt.executeUpdate();

        if (rows > 0) {
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    entity.setId_facturacion(generatedKeys.getInt(1));
                }
            }
            return new Response<>(true, "Factura creada correctamente", entity);
        } else {
            return new Response<>(false, "No se pudo crear la factura", null);
        }

    } catch (SQLException e) {
        return new Response<>(false, "Error al crear factura: " + e.getMessage(), null);
    }
}


    //    metodo para obtener todas las facturas emitidas
    @Override
    public Response<List<Facturacion>> readAll() {
        System.out.println("Se llama al readAll()");
        List<Facturacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM facturacion";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Facturacion factura = new Facturacion();
                factura.setId_facturacion(rs.getInt("id_facturacion"));
                factura.setFecha_factura(rs.getTimestamp("fecha_factura").toLocalDateTime());
                factura.setPatente(rs.getString("patente"));
                factura.setTipo_vehiculo(rs.getString("tipo_vehiculo"));
                factura.setCodigo_cochera(rs.getInt("codigo_cochera"));
                factura.setTipo_contrato(rs.getString("tipo_contrato"));
                factura.setFecha_ingreso(rs.getTimestamp("fecha_ingreso").toLocalDateTime());
                factura.setFecha_egreso(rs.getTimestamp("fecha_egreso").toLocalDateTime());
                factura.setDuracion(rs.getString("duracion"));
                factura.setServicio(rs.getString("servicio"));
                factura.setDescuento(rs.getInt("descuento"));
                factura.setMonto_total(rs.getBigDecimal("monto_total"));

                lista.add(factura);
            }

            return new Response<List<Facturacion>>(true, "Facturas obtenidas", lista);

        } catch (SQLException e) {
            return new Response<>(false, "Error al obtener facturas: " + e.getMessage(), null);
        }
    }


    @Override
    public Response<Facturacion> read(int id) {
        return null;
    }

    @Override
    public Response<Facturacion> update(Facturacion entity) {
        return null;
    }

    @Override
    public Response<Facturacion> delete(int id) {
        return null;
    }


}
