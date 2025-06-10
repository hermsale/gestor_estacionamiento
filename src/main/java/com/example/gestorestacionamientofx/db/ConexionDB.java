package com.example.gestorestacionamientofx.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//creo un singletone
public final class ConexionDB {
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = "root";
    private static final String PASSWORD = "";

//    pongo privado el constructor para no poder ser instanciado ni heredado
    private ConexionDB() {};

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }


}
