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
    public Response<List<Servicio>> readAll() {
        List<Servicio> lista = new ArrayList<>();
        String sql = "SELECT * FROM servicio";

        System.out.println("Consultando todos los servicios ");
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {


            while (rs.next()) {
                Servicio servicio = new Servicio();
                servicio.setId_servicio(rs.getInt("id_servicio"));
                servicio.setDescripcionServicio(rs.getString("descripcion_servicio"));
                servicio.setCostoServicio(rs.getBigDecimal("precio_base"));

                System.out.println("Servicio leído: id=" + servicio.getId_servicio()
                        + ", desc=" + servicio.getDescripcionServicio()
                        + ", costo=" + servicio.getCostoServicio());
                lista.add(servicio);
            }

            System.out.println("List " +lista.size() + " servicios encontrados" );
            return new Response<List<Servicio>>(true, "Tipos de servicios obtenidos", lista);

        } catch (SQLException e) {
            return new Response<>(false, "Error al obtener tipos de servicios: " + e.getMessage(), null);
        }
    }

//    metodo implementado para que lo obtenga el CocheraDAO
    @Override
    public Response<Servicio> read(int id) {
        Servicio  servicio = new Servicio();
        String sql = "SELECT * FROM servicio WHERE id_servicio = ?";

        try(PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery())
        {
            stmt.setInt(1, id);
            if (rs.next()) {
                servicio.setId_servicio(rs.getInt("id_servicio"));
                servicio.setDescripcionServicio(rs.getString("descripcion_servicio"));
                servicio.setCostoServicio(rs.getBigDecimal("precio_base"));

            return new Response<>(true, "Tipo de servicio obtenido", servicio);
            }else{

                return new Response<>(false, "No se encontro el servicio", null);
            }
        }catch(SQLException e){
            return new Response<>(false, "Error al obtener tipos de servicios: " + e.getMessage(), null);
        }

    }


    //    metodos que no se implementan
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
