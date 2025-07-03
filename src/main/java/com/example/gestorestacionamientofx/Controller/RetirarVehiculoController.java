package com.example.gestorestacionamientofx.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class RetirarVehiculoController {

    @FXML
    private TextField patenteField;

    @FXML
    private Button btnRetirar;

    @FXML
    private VBox detalleBox;

    @FXML
    private Label lblEspacioOcupado, lblTipoContrato, lblHoraIngreso, lblHoraRetiro, lblDuracion, lblServicio, lblDescuento, lblPrecioFinal;

    @FXML
    public void initialize() {
        btnRetirar.setOnAction(e -> retirarVehiculo());
    }

    private void retirarVehiculo() {
        String patente = patenteField.getText().trim();
        if (patente.isEmpty()) {
            // Podés agregar un aviso al usuario, por ejemplo un diálogo
            return;
        }

        // Simulamos la carga de datos (en tu app vas a buscar en la base o lógica real)
        lblEspacioOcupado.setText("Espacio ocupado: N°15");
        lblTipoContrato.setText("Tipo de contrato: Por hora");
        lblHoraIngreso.setText("Hora de ingreso: 10:30");
        lblHoraRetiro.setText("Hura de retiro: 14:30");
        lblDuracion.setText("Duración: 4 horas");
        lblServicio.setText("Servicio solicitado: Lavado");
        lblDescuento.setText("Descuento aplicado: 0%");
        lblPrecioFinal.setText("Precio final: $20.000,00");

        detalleBox.setVisible(true);
    }
}
