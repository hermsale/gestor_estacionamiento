package com.example.gestorestacionamientofx.DAO;

import com.example.gestorestacionamientofx.Model.Response;
import com.example.gestorestacionamientofx.Model.Servicio;
import com.example.gestorestacionamientofx.Model.TipoContrato;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicioDAO extends BaseDAO<Servicio> {

    //   esta clase solo se encarga de leer todos los servicios almacenados en la BD

    //    metodo para leer todos los servicios
    @Override
    public Response<Servicio> readAll() {
        List<Servicio> lista = new ArrayList<>();
        String sql = "SELECT * FROM servicio";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Servicio servicio = new Servicio();
                servicio.setId_servicio(rs.getInt("id_servicio"));
                servicio.setDescripcionServicio(rs.getString("nombre_servicio"));
                servicio.setCostoServicio(rs.getBigDecimal("precio_base"));
                lista.add(servicio);
            }

            return new Response<>(true, "Tipos de servicios obtenidos", lista);

        } catch (SQLException e) {
            return new Response<>(false, "Error al obtener tipos de servicios: " + e.getMessage(), null);
        }
    }

    //    metodos que no se implementan
    @Override
    public Response<Servicio> read(int id) {
        return null;
    }


    @Override
    public Response<Servicio> create(Servicio entity) {
        return null;
    }


    @Override
    public Response<Servicio> update(Servicio entity) {
        return null;
    }

    @Override
    public Response<Servicio> delete(int id) {
        return null;
    }
}
