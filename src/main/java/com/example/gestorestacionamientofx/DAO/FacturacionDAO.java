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
        String sql = "INSERT INTO facturacion (fecha_factura, monto_total, id_cochera) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setTimestamp(1, Timestamp.valueOf(entity.getFecha_factura()));
            stmt.setBigDecimal(2, entity.getMontoTotal());
            stmt.setInt(3, entity.getCochera().getId_cochera());

            int filas = stmt.executeUpdate();

            if (filas > 0) {
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
    public Response<Facturacion> readAll() {
        List<Facturacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM facturacion";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Facturacion factura = new Facturacion();
                factura.setId_facturacion(rs.getInt("id_facturacion"));
                factura.setFecha_factura(rs.getTimestamp("fecha_factura").toLocalDateTime());
                factura.setMontoTotal(rs.getBigDecimal("monto_total"));

                // Para simplificar, solo cargamos el ID de cochera, no el objeto completo
                Cochera cochera = new Cochera();
                cochera.setId_cochera(rs.getInt("id_cochera"));
                factura.setCochera(cochera);

                lista.add(factura);
            }

            return new Response<>(true, "Facturas obtenidas", lista);

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
