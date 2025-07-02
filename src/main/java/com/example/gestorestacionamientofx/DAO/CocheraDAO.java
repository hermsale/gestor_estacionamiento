package com.example.gestorestacionamientofx.DAO;

import com.example.gestorestacionamientofx.Model.*;

import java.math.BigDecimal;
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
    public Response<List<Cochera>> readAll() {
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


                // Vehículo
                String patente = rs.getString("patente");
                String tipo = rs.getString("descripcion");

                Vehiculo vehiculo = switch (tipo.toUpperCase()) {
                    case "SEDAN" -> new Sedan(patente);
                    case "SUV" -> new Suv(patente);
                    case "PICKUP" -> new Pickup(patente);
                    default -> null;
                };

                cochera.setVehiculo(vehiculo);


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

//                agrego todo a cocheras
                cocheras.add(cochera);
            }

//            envio mensaje por las cocheras recibidas
            return new Response<List<Cochera>>(true,"Las cocheras se obtuvieron correctamente ",cocheras );
        }catch(SQLException e){
//            en caso que falle, envio mensaje
            return new Response<>(false,"Error al obtener las cocheras: "+e.getMessage(),null);
        }
    }

    @Override
    public Response<Cochera> delete(int id) {
        return null;
    }

//    actualizar base de datos con cochera reservada
    @Override
    public Response<Cochera> update(Cochera cochera) {
        String sql = "UPDATE cochera SET fecha_entrada = ?, id_tipo_contrato = ?, id_servicio = ?, estado = ?, patente = ?, descripcion = ?, recargo = ? WHERE codigo_cochera = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, cochera.getFechaIngreso()); // LocalDateTime
            stmt.setInt(2, cochera.getContrato().getId());
            stmt.setInt(3, cochera.getServicio().getId_servicio());
            stmt.setString(4, cochera.getEstadoCochera().name());
            stmt.setString(5, cochera.getVehiculo().getPatente());
            stmt.setString(6, cochera.getVehiculo().getDescripcion()); // tipo de vehiculo
            stmt.setBigDecimal(7, BigDecimal.valueOf(cochera.getVehiculo().obtenerRecargo())); // recargo
            stmt.setInt(8, cochera.getcodigoCochera());

            int filas = stmt.executeUpdate();
            if (filas > 0) {
                return new Response<>(true, "Cochera actualizada con éxito", cochera);
            } else {
                return new Response<>(false, "No se encontró la cochera con ese código", null);
            }
        } catch (SQLException e) {
            return new Response<>(false, "Error al actualizar cochera: " + e.getMessage(), null);
        }
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
