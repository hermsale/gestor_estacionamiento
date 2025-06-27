package com.example.gestorestacionamientofx.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//creo un singletone
public final class ConexionDB {
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private Connection conn;

    private static ConexionDB instance;

    private ConexionDB() throws SQLException {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            this.conn = DriverManager.getConnection(URL, USER, PASSWORD);

        }catch(ClassNotFoundException e){
            System.out.println("Error al cargar el driver de MYSQL: " + e.getMessage());
        }catch(SQLException e){
            System.out.println("Error al intentar generar la conexion: " + e.getMessage());
        }catch(Exception e){
            System.out.println("Error : " + e.getMessage());
        }

    }

    public Connection getConnection(){
        return this.conn;
    }


    public static ConexionDB getInstance() throws SQLException {
        if(instance == null){
            instance = new ConexionDB();
        }else if(instance.getConnection().isClosed()){
            instance = new ConexionDB();
        }
        return instance;
    }


}
