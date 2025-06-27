package com.example.gestorestacionamientofx.DAO;



import com.example.gestorestacionamientofx.DB.ConexionDB;
import com.example.gestorestacionamientofx.Model.Response;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class BaseDAO<T> implements ICrud<T> {

    protected Connection conn;

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
    public abstract Response<T> readAll();
}