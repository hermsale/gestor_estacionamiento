package com.example.gestorestacionamientofx.Controller;

import com.example.gestorestacionamientofx.DAO.CocheraDAO;
import com.example.gestorestacionamientofx.DAO.FacturacionDAO;
import com.example.gestorestacionamientofx.Model.Cochera;
import com.example.gestorestacionamientofx.Model.Facturacion;
import com.example.gestorestacionamientofx.Model.Response;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RetirarVehiculoController {

    @FXML
    private TextField patenteField;

    @FXML
    private Button btnRetirar;


    @FXML
    private Label lblEspacioOcupado, lblTipoContrato, lblHoraIngreso, lblHoraRetiro, lblDuracion, lblServicio, lblDescuento, lblPrecioFinal;

    @FXML
    public void initialize() {
        btnRetirar.setOnAction(e -> retirarVehiculo());
    }

    private void retirarVehiculo() {
//        traigo los metodos de cocheraDAO
        CocheraDAO cocheraDAO = new CocheraDAO();

        String patente = patenteField.getText().trim();
        if (patente.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Por favor ingrese una patente");

            alert.showAndWait();
            return;
        }

// Buscar código cochera por patente
        Response<Integer> respId = cocheraDAO.obtenerIdPorPatente(patente);
        if (!respId.isSuccess()) {
            System.out.println("❌ " + respId.getMessage());
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("La patente '" + patente + "' no esta registrada");
            alert.showAndWait();
            return;
        }else{
            System.out.println(respId.getMessage()+"ID "+respId.getEntity());
        }

//        obtengo el id de la cochera
        int codigo_cochera =  respId.getEntity();
//        guardo en respCochera todo lo referido a la cochera
        Response<Cochera> respCochera =cocheraDAO.read(codigo_cochera);
        if (!respCochera.isSuccess()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No se pudo obtener la información de la cochera");
            alert.showAndWait();
            return;
        }

//        guardo en cochera lo que viene del entity de respCochera
        Cochera cochera = respCochera.getEntity();


//        Fecha ingreso, egreso y duracion
        LocalDateTime fecha_ingreso = cochera.getFechaIngreso();
        LocalDateTime fecha_egreso = LocalDateTime.now();

//        formato para mostrar
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
//        System.out.println("Fecha y hora: " + cochera.getFechaIngreso().format(formatter));
//        se calcula la duracion entre el ingreso y el egreso
        Duration duracion = Duration.between(fecha_ingreso, fecha_egreso);

//        el precio final, representa el precio base con el calculo respectivo del tipo de contrato
//        en esta primera instancia, verifico que tipo de contrato tiene, se calcula las politicas minimas de uso de la cochera
//        ejemplo: por hora, minimo 2 horas se cobra.
//        por dia, se cobra minimo 1 dia.
//        se multiplica en cada caso la cantidad de horas y dias que, por el precio base. y se guarda
        double precioFinal = 0;
//        datos de la cochera - obtengo el nombre del contrato
        String tipoContrato = cochera.getContrato().getNombreContrato();
        double precioBaseContrato = cochera.getContrato().getPrecioBaseCochera().doubleValue(); // precio base del contrato
        String textoDuracion;
//        tomo el tipo de contrato y lo paso a minuscula
        switch (tipoContrato.toLowerCase()) {
            case "por hora" -> {
                // redondeo hacia arriba y minimo 2 horas  se le va a cobrar
                long horas = Math.max(2, (duracion.toMinutes() + 59) / 60);
                textoDuracion = "Duración: " + horas + " hora/s";
                lblDuracion.setText(textoDuracion);
                precioFinal = precioBaseContrato * horas;
            }

            case "por día" -> {
                long dias = Math.max(1, (duracion.toHours() + 23) / 24); // minimo se cobra 1 dia
                textoDuracion = ("Duración: " + dias + " día/s");
                lblDuracion.setText(textoDuracion);
                precioFinal = precioBaseContrato * dias;

            }

            case "por mes" -> {
                textoDuracion = ("Duración: Contrato mensual");
                lblDuracion.setText(textoDuracion);
                precioFinal = precioBaseContrato;
            }

            default -> {
                textoDuracion = ("Duración: No disponible");
                lblDuracion.setText(textoDuracion);
            }
        }

//        una vez guardado el monto a pagar, se le aplica los descuentos si los hubiese,. y se suma los recargos
//        los recargos pueden ser, según el tipo de vehiculo: SUV tiene recargo del 10% PICKUP del 20%
//        los descuentos es según el tipo de contrato.

        String servicioDescripcion = cochera.getServicio().getDescripcionServicio(); // nombre del servicio
        double costoServicio = cochera.getServicio().getCostoServicio().doubleValue(); // guardo el costo del servicio
        int descuentoServicio = cochera.getContrato().getDescuentoServicio().intValue(); // descuento al servicio
        double recargoCochera = cochera.getVehiculo().obtenerRecargo(); // obtengo el recargo segun SUV, Sedan o Pickup

//        aplico el recargo a la cochera, segun el tipo de vehiculo ingresado
        double precioConRecargo = precioFinal+(precioFinal * recargoCochera);
//        aplico el descuento al servicio
        double precioServicioConDescuento = costoServicio * (1 - descuentoServicio / 100.0);

        double precioTotal = precioConRecargo + precioServicioConDescuento;

        // Aplico el descuento del tipo de contrato al servicio
//        double descuentoAplicado = precioFinal * (descuentoServicio / 100.0);
//        double precioConDescuento = precioFinal - descuentoAplicado;

// Sumo el recargo por tipo de vehículo
//        double precioTotal = precioConDescuento + recargoCochera+costoServicio;

        lblEspacioOcupado.setText("Espacio ocupado: "+cochera.getCodigoCochera());
        lblTipoContrato.setText("Tipo de contrato: "+tipoContrato);
        lblHoraIngreso.setText("Fecha y Hora de ingreso: "+fecha_ingreso.format(formatter));
        lblHoraRetiro.setText("Fecha y Hura de retiro: "+fecha_egreso.format(formatter));

//        si no hay servicios contratados muestro un mensaje, de lo contrario especifico servicio y descuento si lo hubiese
        if (servicioDescripcion.equals("Ninguno")){
            lblServicio.setText("Sin servicio contratado");
            lblDescuento.setText("No se aplica descuento");
        } else {
            lblServicio.setText("Servicio solicitado: " + servicioDescripcion);
            lblDescuento.setText("Precio con descuento (" + descuentoServicio + "%): $" + String.format("%.2f", precioServicioConDescuento));
        }

        lblPrecioFinal.setText("Recargo por tipo de vehiculo: $"+recargoCochera);
        lblPrecioFinal.setText("Precio contrato: $"+precioTotal);

//        creamos una factura - que se guardara en la BD
        Facturacion factura = new Facturacion();
        factura.setFecha_factura(LocalDateTime.now());
        factura.setCodigo_cochera(cochera.getCodigoCochera());
        factura.setPatente(cochera.getVehiculo().getPatente());
        factura.setTipo_vehiculo(cochera.getVehiculo().getDescripcion());
        factura.setTipo_contrato(tipoContrato);
        factura.setFecha_ingreso(fecha_ingreso);
        factura.setFecha_egreso(fecha_egreso);
        factura.setDuracion(textoDuracion);
        factura.setServicio(servicioDescripcion);
        factura.setDescuento(descuentoServicio);
        factura.setMonto_total(BigDecimal.valueOf(precioTotal));

//        traemos el metodo para crear la factura
        FacturacionDAO facturaDAO = new FacturacionDAO();
//        guardamos la factura en la BD en la tabla facturacion
        Response<Facturacion> respFactura = facturaDAO.create(factura);
        if (!respFactura.isSuccess()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al guardar factura");
            alert.setHeaderText(null);
            alert.setContentText(respFactura.getMessage());
            alert.showAndWait();
            return;
        }
        System.out.println("Factura guardada correctamente");

//      libero la cochera
       Response<Cochera> respLiberar = cocheraDAO.liberarCochera(cochera.getCodigoCochera());
        if (!respLiberar.isSuccess()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al liberar cochera");
            alert.setHeaderText(null);
            alert.setContentText(respLiberar.getMessage());
            alert.showAndWait();
            return;
        }
        System.out.println("Cochera liberada correctamente");
    }
}
