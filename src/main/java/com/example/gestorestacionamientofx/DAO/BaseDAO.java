package com.example.gestorestacionamientofx.DAO;



import com.example.gestorestacionamientofx.DB.ConexionDB;
import com.example.gestorestacionamientofx.Model.Response;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDAO<T> implements ICrud<T> {
//    El BaseDAO centraliza la conexión

    protected Connection conn;

//    creo una instancia de conexion con la BD
    public BaseDAO() {
        try {
            this.conn = ConexionDB.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    @Override
    public abstract Response<T> create(T entity);

    @Override
    public abstract Response<T> read(int id);

    @Override
    public abstract Response<T> update(T entity);

    @Override
    public abstract Response<T> delete(int id);

    @Override
//    public abstract Response<T> readAll();

    public abstract Response<List<T>> readAll();
}