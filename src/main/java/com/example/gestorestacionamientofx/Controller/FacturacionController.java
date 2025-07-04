package com.example.gestorestacionamientofx.Controller;

import com.example.gestorestacionamientofx.DAO.FacturacionDAO;
import com.example.gestorestacionamientofx.Model.Facturacion;
import com.example.gestorestacionamientofx.Model.Response;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class FacturacionController {

    @FXML
    private TableView<Facturacion> tablaFacturas;

    @FXML
    private TableColumn<Facturacion, String> colFechaEmision;

    @FXML
    private TableColumn<Facturacion, String> colPatente;

    @FXML
    private TableColumn<Facturacion, String> colTipoVehiculo;

    @FXML
    private TableColumn<Facturacion, Integer> colCochera;

    @FXML
    private TableColumn<Facturacion, String> colContrato;

    @FXML
    private TableColumn<Facturacion, String> colIngreso;

    @FXML
    private TableColumn<Facturacion, String> colEgreso;

    @FXML
    private TableColumn<Facturacion, String> colDuracion;

    @FXML
    private TableColumn<Facturacion, String> colServicio;

    @FXML
    private TableColumn<Facturacion, Integer> colDescuento;

    @FXML
    private TableColumn<Facturacion, String> colMonto;

    private final ObservableList<Facturacion> datos = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        cargarColumnas();
        cargarDatos();
    }

    private void cargarColumnas() {
        colFechaEmision.setCellValueFactory(new PropertyValueFactory<>("fecha_factura"));
        colPatente.setCellValueFactory(new PropertyValueFactory<>("patente"));
        colTipoVehiculo.setCellValueFactory(new PropertyValueFactory<>("tipo_vehiculo"));
        colCochera.setCellValueFactory(new PropertyValueFactory<>("codigo_cochera"));
        colContrato.setCellValueFactory(new PropertyValueFactory<>("tipo_contrato"));
        colIngreso.setCellValueFactory(new PropertyValueFactory<>("fecha_ingreso"));
        colEgreso.setCellValueFactory(new PropertyValueFactory<>("fecha_egreso"));
        colDuracion.setCellValueFactory(new PropertyValueFactory<>("duracion"));
        colServicio.setCellValueFactory(new PropertyValueFactory<>("servicio"));
        colDescuento.setCellValueFactory(new PropertyValueFactory<>("descuento"));
        colMonto.setCellValueFactory(new PropertyValueFactory<>("monto_total"));
    }

//    metodo para cargar todas las facturas almacenadas en la BD
    private void cargarDatos() {
        FacturacionDAO facturacionDAO = new FacturacionDAO();
        Response<List<Facturacion>> resp = facturacionDAO.readAll();

        if (resp.isSuccess()) {
            datos.setAll(resp.getEntity());
            tablaFacturas.setItems(datos);
        } else {
            System.out.println("❌ Error al cargar facturas: " + resp.getMessage());
        }
    }
}
