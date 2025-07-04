package com.example.gestorestacionamientofx.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class LoginController {

    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Label lblMensaje;

    @FXML
    private void handleLogin() {
//        se genero un login provisorio, en donde las credenciales estan hardcodiadas. Y no hace uso de la BD.
        String usuario = txtUsuario.getText();
        String password = txtPassword.getText();

        // Hardcodeamos usuario y password
        if ("admin".equals(usuario) && "admin".equals(password)) {
            try {
                // Cargo la vista del menú principal
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestorestacionamientofx/menu_principal.fxml"));
                Parent root = loader.load();

                Stage stage = (Stage) txtUsuario.getScene().getWindow();  // Tomo la ventana actual
                stage.setScene(new Scene(root));
                stage.setTitle("Menú Principal");
            } catch (Exception e) {
                lblMensaje.setText("Error al cargar el menú: " + e.getMessage());
            }
        } else {
            lblMensaje.setText("Usuario o contraseña incorrectos");
        }
    }
}
