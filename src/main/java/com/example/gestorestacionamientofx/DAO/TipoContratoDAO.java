package com.example.gestorestacionamientofx.DAO;

import com.example.gestorestacionamientofx.Model.Response;
import com.example.gestorestacionamientofx.Model.TipoContrato;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoContratoDAO extends BaseDAO<TipoContrato> {

//   esta clase solo se encarga de leer todos los contratos almacenados en la BD

//    metodo para leer todos los contratos
    @Override
    public Response<TipoContrato> readAll() {
        List<TipoContrato> lista = new ArrayList<>();
        String sql = "SELECT * FROM tipo_contrato";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                TipoContrato tipo = new TipoContrato();
                tipo.setId(rs.getInt("id_tipo_contrato"));
                tipo.setNombreContrato(rs.getString("nombre_contrato"));
                tipo.setDescuentoServicio(rs.getInt("descuento_servicio"));
                tipo.setPrecioBaseCochera(rs.getBigDecimal("precio_base_cochera"));
                lista.add(tipo);
            }

            return new Response<>(true, "Tipos de contrato obtenidos", lista);

        } catch (SQLException e) {
            return new Response<>(false, "Error al obtener tipos de contrato: " + e.getMessage(), null);
        }
    }

//    metodos que no se implementan
    @Override
    public Response<TipoContrato> read(int id) {
        return null;
    }


    @Override
    public Response<TipoContrato> create(TipoContrato entity) {
        return null;
    }


    @Override
    public Response<TipoContrato> update(TipoContrato entity) {
        return null;
    }

    @Override
    public Response<TipoContrato> delete(int id) {
        return null;
    }

}
