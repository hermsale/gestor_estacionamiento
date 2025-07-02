package com.example.gestorestacionamientofx.Controller;

import com.example.gestorestacionamientofx.Model.Pickup;
import com.example.gestorestacionamientofx.Model.Sedan;
import com.example.gestorestacionamientofx.Model.Suv;
import com.example.gestorestacionamientofx.Model.Vehiculo;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.HashMap;

public class PlanoEstacionamientoController {

    @FXML
    private GridPane cocheraGrid;

    private HashMap<Integer, Button> botonesCocheras = new HashMap<>();

    @FXML
    private AnchorPane ingresoVehiculo;

    private int cocheraSeleccionada = -1;

//    este metodo sera usado desde el CocheraController
    public int getCocheraSeleccionada() {
        return cocheraSeleccionada;
    }

    @FXML
    public void initialize() {
        int num = 1;
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 5; col++) {
                Button btn = new Button(String.valueOf(num));
                btn.setPrefSize(60, 60);
                btn.setStyle("-fx-background-color: green;"); // por defecto disponible

                // boton de cada cochera
                btn.setOnAction(e -> {
                    System.out.println("Cochera seleccionada: " + btn.getText());
//                    capturo el numero  y lo parseo
                    int codigo_cochera = Integer.parseInt(btn.getText());
//                    se lo envio a la variable para pasarlo a la ventana anterior
                    seleccionarCochera(codigo_cochera);
                });

                botonesCocheras.put(num, btn);
                cocheraGrid.add(btn, col, row);
                num++;
            }
        }

        // Acá podrías cargar estado desde BD y pintar rojos los ocupados
    }

    public void seleccionarCochera(int numero) {
        this.cocheraSeleccionada = numero;

        // Cerrar ventana
        Stage stage = (Stage) ingresoVehiculo.getScene().getWindow();
        stage.close();
    }

}
