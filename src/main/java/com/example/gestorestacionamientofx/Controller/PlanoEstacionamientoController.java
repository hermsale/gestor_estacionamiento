package com.example.gestorestacionamientofx.Controller;

import com.example.gestorestacionamientofx.Model.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.List;

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

//    vamos a guardar y listar las cocheras,
    private List<Cochera> listaCocheras;

    public void setListaCocheras(List<Cochera> listaCocheras) {
        this.listaCocheras = listaCocheras;
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

    }

//    selecciono la cochera y cierro la ventana
    public void seleccionarCochera(int numero) {
        this.cocheraSeleccionada = numero;
        Stage stage = (Stage) ingresoVehiculo.getScene().getWindow();
        stage.close();
    }
//    metodo para pintar las cocheras de colores - se carga al principio desde la ventana de ingresoController
    public void pintarCocherasPorEstado() {
        if (listaCocheras == null) return;

        for (Cochera c : listaCocheras) {
            Button btn = botonesCocheras.get(c.getCodigoCochera());
            if (btn != null) {
//            si el estado de la cochera es OCUPADO, lo pinta en rojo y lo deshabilita
                if (c.getEstadoCochera() == EstadoCochera.OCUPADO) {
                    btn.setStyle("-fx-background-color: red;");
                    btn.setDisable(true); // ❌ No se puede seleccionar
                } else {
//                    si el estado de la cochera es DISPONIBLE lo pinta de verde y lo habilita
                    btn.setStyle("-fx-background-color: green;");
                    btn.setDisable(false); // ✅ Disponible y seleccionable
                }
            }
        }
    }

}
