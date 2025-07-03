package com.example.gestorestacionamientofx.DAO;

import com.example.gestorestacionamientofx.Model.*;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CocheraDAO extends BaseDAO<Cochera> {

    TipoContratoDAO contratoDAO = new TipoContratoDAO();
    ServicioDAO servicioDAO = new ServicioDAO();


    @Override
    public Response<List<Cochera>> readAll() {
        List<Cochera> cocheras = new ArrayList<>();
        String sql = "SELECT * FROM cochera";

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
                String estadoStr = rs.getString("estado").toUpperCase();
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

//metodo para cargar el estado de cada cochera - se utiliza para pintar las cocheras de verde o rojo
    public Response<List<Cochera>> readEstado() {
        List<Cochera> cocheras = new ArrayList<>();
        String sql = "SELECT codigo_cochera, estado FROM cochera";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cochera cochera = new Cochera();
                cochera.setCodigoCochera(rs.getInt("codigo_cochera"));

                String estadoStr = rs.getString("estado");
                if (estadoStr != null) {
                    EstadoCochera estado = EstadoCochera.valueOf(estadoStr.toUpperCase());
                    cochera.setEstadoCochera(estado);
                } else {
                    cochera.setEstadoCochera(null);
                }

                cocheras.add(cochera);
            }

            return new Response<List<Cochera>>(true, "Estados obtenidos correctamente", cocheras);
        } catch (SQLException e) {
            return new Response<>(false, "Error al obtener estados: " + e.getMessage(), null);
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

    //    actualizar base de datos con cochera reservada
//    cuando se ingresa un vehiculo, se autocompleta la fecha y hora,
//    se elije la cochera, el tipo de contrato, si contrato algun servicio,. estado, patente descripcion (suv, pickup sedan)
//    y el recargo correspondiente
    public Response<Cochera> ingresarVehiculo(Cochera cochera) {
        String sql = "UPDATE cochera SET fecha_entrada = ?, id_tipo_contrato = ?, id_servicio = ?, estado = ?, patente = ?, descripcion = ? WHERE codigo_cochera = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, cochera.getFechaIngreso()); // LocalDateTime
            stmt.setInt(2, cochera.getContrato().getId());
            stmt.setInt(3, cochera.getServicio().getId_servicio());
            stmt.setString(4, cochera.getEstadoCochera().name());
            stmt.setString(5, cochera.getVehiculo().getPatente());
            stmt.setString(6, cochera.getVehiculo().getDescripcion()); // tipo de vehiculo
            stmt.setInt(7, cochera.getCodigoCochera());

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

//    actualizo el estado de la cochera. eliminando los datos
    public Response<Cochera> liberarCochera(int codigoCochera) {
        String sql = "UPDATE cochera SET fecha_entrada = NULL, id_servicio = NULL, id_tipo_contrato = NULL, " +
                "estado = ?, patente = NULL, descripcion = NULL WHERE codigo_cochera = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, EstadoCochera.DISPONIBLE.name());
            stmt.setInt(2, codigoCochera);

            int filas = stmt.executeUpdate();
            if (filas > 0) {
                return new Response<>(true, "Cochera liberada con éxito", null);
            } else {
                return new Response<>(false, "No se encontró la cochera con ese código", null);
            }
        } catch (SQLException e) {
            return new Response<>(false, "Error al liberar cochera: " + e.getMessage(), null);
        }

    }



//obtengo id por patente
public Response<Integer> obtenerIdPorPatente(String patente) {
    String sql = "SELECT codigo_cochera FROM cochera WHERE patente = ? AND estado = 'OCUPADO'";

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, patente);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            int id = rs.getInt("codigo_cochera");
            System.out.println("Cochera obtenida: " + id);
            return new Response<>(true, "Cochera encontrada", id);
        } else {
            return new Response<>(false, "No se encontró una cochera ocupada con esa patente", null);
        }
    } catch (SQLException e) {
        return new Response<>(false, "Error al buscar cochera por patente: " + e.getMessage(), null);
    }
}


    @Override
    public Response<Cochera> read(int id) {
        String sql = "SELECT * FROM cochera WHERE codigo_cochera = ?";

        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Cochera cochera = new Cochera();
                cochera.setCodigoCochera(id);
                cochera.setFechaIngreso(rs.getObject("fecha_entrada", LocalDateTime.class));
                cochera.setEstadoCochera(EstadoCochera.valueOf(rs.getString("estado")));

//                obtengo la patente y la descripcion del vehiculo
                String descripcion = rs.getString("descripcion");
                String patente = rs.getString("patente");

//                reconstruyo el vehiculo
                Vehiculo vehiculo = switch (descripcion) {
                    case "Sedan" -> new Sedan(patente);
                    case "SUV" -> new Suv(patente);
                    case "Pickup" -> new Pickup(patente);
                    default -> null; // o podrías lanzar una excepción si querés evitar nulos
                };

                cochera.setVehiculo(vehiculo); // asigno vehiculo
                int idContrato = rs.getInt("id_tipo_contrato");
                if (!rs.wasNull()) {
                    Response<TipoContrato> contratoResp = new TipoContratoDAO().read(idContrato);
                    if (contratoResp.isSuccess()) {
                        cochera.setContrato(contratoResp.getEntity());
                    }
                }
//                cochera.setContrato(new TipoContratoDAO().read(rs.getInt("id_tipo_contrato")).getEntity());
                int idServicio = rs.getInt("id_servicio");
                if (!rs.wasNull()) {
                    Response<Servicio> servicioResp = new ServicioDAO().read(idServicio);
                    if (servicioResp.isSuccess()) {
                        cochera.setServicio(servicioResp.getEntity());
                    }
                }
//                cochera.setServicio(new ServicioDAO().read(rs.getInt("id_servicio")).getEntity());
//                cochera.setVehiculo(vehiculo); // primero le pasás el vehículo como objeto

                return new Response<>(true, "Cochera encontrada", cochera);
            }else{
                return new Response<>(false, "No se encontró la cochera", null);
            }
        }catch (SQLException e){
            return new Response<>(false, "Error al obtener cochera: " + e.getMessage(), null);
        }
    }

    @Override
    public Response<Cochera> create(Cochera entity) {
        return null;
    }
}
