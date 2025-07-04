package com.example.gestorestacionamientofx.DB;

import java.io.*;
import java.sql.Connection;
import java.sql.Statement;

public class DBInitializer {

    public static void initializeDatabase() {
        try {
            Connection conn = ConexionDB.getInstance().getConnection();
            Statement stmt = conn.createStatement();

            // Ruta relativa desde donde se ejecuta la app
            InputStream input = DBInitializer.class
                    .getClassLoader()
                    .getResourceAsStream("com/example/gestorestacionamientofx/insertGestor.sql");

            if (input == null) {
                System.out.println("No se encontró el archivo insertGestor.sql");
                return;
            }


            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            StringBuilder sqlBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sqlBuilder.append(line).append("\n");
            }

            // Separar sentencias por ;
            String[] sqlStatements = sqlBuilder.toString().split(";");

            for (String sql : sqlStatements) {
                sql = sql.trim();
                if (!sql.isEmpty()) {
                    try {
                        stmt.execute(sql);
                    } catch (Exception e) {
                        System.out.println("Error al ejecutar sentencia: " + sql);
                        System.out.println("Motivo: " + e.getMessage());
                    }
                }
            }

            System.out.println("Base de datos inicializada correctamente.");
        } catch (Exception e) {
            System.out.println("Error al inicializar la base de datos: " + e.getMessage());
        }
    }
}
