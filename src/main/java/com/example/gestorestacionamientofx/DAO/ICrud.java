package com.example.gestorestacionamientofx.DAO;

import com.example.gestorestacionamientofx.Model.Response;

// Todo DAO<T> que implemente esto, debe tener los métodos create, read, update, delete, readAll
public interface ICrud<T> {
    Response<T> create(T entity);

    Response<T> read(int id);

    Response<T> update(T entity);

    Response<T> delete(int id);

    Response<T> readAll();
}
