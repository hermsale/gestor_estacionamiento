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
    public Response<List<TipoContrato>> readAll() {
        List<TipoContrato> lista = new ArrayList<>();
        String sql = "SELECT * FROM tipo_contrato";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                TipoContrato tipo = new TipoContrato();
                tipo.setId(rs.getInt("id_tipo_contrato"));
                tipo.setNombreContrato(rs.getString("descripcion_contrato"));
                tipo.setDescuentoServicio(rs.getBigDecimal("descuento_servicio"));
                tipo.setPrecioBaseCochera(rs.getBigDecimal("precio_base"));
                lista.add(tipo);
            }

            return new Response<List<TipoContrato>>(true, "Tipos de contrato obtenidos", lista);

        } catch (SQLException e) {
            return new Response<>(false, "Error al obtener tipos de contrato: " + e.getMessage(), null);
        }
    }

//    metodo para cargar en cocheraDAO
    @Override
    public Response<TipoContrato> read(int id) {
        String sql = "SELECT * FROM tipo_contrato WHERE id_tipo_contrato = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    TipoContrato tipoContrato = new TipoContrato();
                    tipoContrato.setId(rs.getInt("id_tipo_contrato"));
                    tipoContrato.setNombreContrato(rs.getString("descripcion_contrato"));
                    tipoContrato.setDescuentoServicio(rs.getBigDecimal("descuento_servicio"));
                    tipoContrato.setPrecioBaseCochera(rs.getBigDecimal("precio_base"));

                    return new Response<>(true, "Tipo de contrato obtenido", tipoContrato);
                } else {
                    return new Response<>(false, "No se encontró el tipo de contrato", null);
                }
            }
        } catch (SQLException e) {
            return new Response<>(false, "Error al obtener tipo de contrato: " + e.getMessage(), null);
        }
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
