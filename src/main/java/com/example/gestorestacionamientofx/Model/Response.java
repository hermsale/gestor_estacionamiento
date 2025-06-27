package com.example.gestorestacionamientofx.Model;

import java.util.List;

public class Response<T>{
    private boolean success;
    private String message;
    private T entity;
    private List<T> list; // opcional para readAll()

    public Response(boolean success, String message, T entity) {
        this.success = success;
        this.message = message;
        this.entity = entity;
    }

    public Response(boolean success, String message, List<T> list) {
        this.success = success;
        this.message = message;
        this.list = list;
    }

    // Getters
    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public T getEntity() {
        return entity;
    }

    public List<T> getList() {
        return list;
    }
}
