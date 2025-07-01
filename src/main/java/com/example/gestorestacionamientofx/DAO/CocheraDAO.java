package com.example.gestorestacionamientofx.DAO;

import com.example.gestorestacionamientofx.Model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CocheraDAO extends BaseDAO<Cochera> {

    TipoContratoDAO contratoDAO = new TipoContratoDAO();
    ServicioDAO servicioDAO = new ServicioDAO();

//    me falta instanciar esto . crearlo.
//    VehiculoDAO vehiculoDAO = new VehiculoDAO();
    @Override
    public Response<Cochera> readAll() {
        List<Cochera> cocheras = new ArrayList<>();
        String sql = "SELECT * FROM cocheras";

        try(PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery()){

//            asigno lo que viene de la tabla a cada variable
            while (rs.next()) {
//                creo la instancia de cochera
                Cochera cochera = new Cochera();

//                asigno el id
                cochera.setId_cochera(rs.getInt("id_cochera"));
                cochera.setCodigoCochera(rs.getInt("codigo_cochera"));

//                traigo lo que este en el enum de estado
                String estadoStr = rs.getString("estado");
                EstadoCochera estado = EstadoCochera.valueOf(estadoStr); // convierto String a Enum
                cochera.setEstadoCochera(estado); // lo setea en el objeto

                // Fechas (pueden ser null)
                Timestamp tsEntrada = rs.getTimestamp("fecha_entrada");
                cochera.setFechaIngreso(tsEntrada != null ? tsEntrada.toLocalDateTime() : null);

                Timestamp tsSalida = rs.getTimestamp("fecha_salida");
                cochera.setFechaSalida(tsSalida != null ? tsSalida.toLocalDateTime() : null);


                cochera.setVehiculo(null); // podés usar VehiculoDAO.read(id_vehiculo) si querés cargarlo


                // Tabla Contrato - metodo Read
                int idContrato = rs.getInt("id_tipo_contrato");
                if (!rs.wasNull()) {
                    Response<TipoContrato> contratoResp = contratoDAO.read(idContrato);
                    if (contratoResp.isSuccess()) {
                        cochera.setContrato(contratoResp.getEntity());
                    }
                }

                // Tabla Servicio - metodo Read
                int idServicio = rs.getInt("id_servicio");
                if (!rs.wasNull()) {
                    Response<Servicio> servicioResp = servicioDAO.read(idServicio);
                    if (servicioResp.isSuccess()) {
                        cochera.setServicio(servicioResp.getEntity());
                    }
                }
                cocheras.add(cochera);
            }

//            envio mensaje por las cocheras recibidas
            return new Response<>(true,"Las cocheras se obtuvieron correctamente ",cocheras );
        }catch(SQLException e){
//            en caso que falle, envio mensaje
            return new Response<>(false,"Error al obtener las cocheras: "+e.getMessage(),null);
        }
    }

    @Override
    public Response<Cochera> delete(int id) {
        return null;
    }

    @Override
    public Response<Cochera> update(Cochera entity) {
        return null;
    }

    @Override
    public Response<Cochera> read(int id) {
        return null;
    }

    @Override
    public Response<Cochera> create(Cochera entity) {
        return null;
    }
}
