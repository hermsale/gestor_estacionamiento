package com.example.gestorestacionamientofx.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuPrincipalController {

    @FXML
    private Button btnIngresar, btnRetirar, btnReportes, btnCerrarSesion;

    @FXML
    public void initialize() {
        btnIngresar.setOnAction(this::abrirVistaIngresoVehiculo);
        btnCerrarSesion.setOnAction(e -> cerrarVentanaActual());
        // btnRetirar y btnReportes los agregás después
        btnRetirar.setOnAction(this::abrirVistaRetiroVehiculo);
    }

    private void abrirVistaIngresoVehiculo(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestorestacionamientofx/vehiculo_form.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Ingreso de Vehículo");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("❌ No se pudo cargar la vista de cochera: " + e.getMessage());
        }
    }

    private void abrirVistaRetiroVehiculo(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestorestacionamientofx/retirar_vehiculo.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Retiro de Vehículo");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("❌ No se pudo cargar la vista de cochera: " + e.getMessage());
        }
    }
    private void cerrarVentanaActual() {
        Stage stage = (Stage) btnCerrarSesion.getScene().getWindow();
        stage.close();
    }
}
