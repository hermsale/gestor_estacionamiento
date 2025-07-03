package com.example.gestorestacionamientofx.Controller;

import com.example.gestorestacionamientofx.DAO.CocheraDAO;
import com.example.gestorestacionamientofx.DAO.ServicioDAO;
import com.example.gestorestacionamientofx.DAO.TipoContratoDAO;
import com.example.gestorestacionamientofx.Model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class IngresoController {
    Cochera  cochera = new Cochera();
    @FXML
    private TextField patenteField;

    @FXML
    private ComboBox<String> tipoComboBox;

//    creo el comboBox para Tipos de contrato
    @FXML
    private ComboBox<TipoContrato> tipoContratoComboBox;
    //    creo el comboBox para servicios
    @FXML
    private ComboBox<Servicio> servicioComboBox;

//    inicializo los Box
    @FXML
    public void initialize() {
        tipoComboBox.getItems().addAll("Sedan", "SUV", "Pickup");

//        esto lo traemos de la BD
        ServicioDAO servicioDAO = new ServicioDAO();
        TipoContratoDAO tipoContratoDAO = new TipoContratoDAO();


//      traigo los servicios del controller para completar los comboBox
        Response<List<Servicio>> servicioResponse = servicioDAO.readAll();
        Response<List<TipoContrato>> contratoResponse = tipoContratoDAO.readAll();

        if(servicioResponse.isSuccess()) {
            servicioComboBox.getItems().addAll(servicioResponse.getEntity());
        }

        if(contratoResponse.isSuccess()) {
            tipoContratoComboBox.getItems().addAll(contratoResponse.getEntity());
        }

    }

//    logica para asignar una cochera
   @FXML
   public void asignarCochera(){
       //        Esto es propio de la clase Cochera

       System.out.println("asignando cochera");
       try {
//           traigo plano_estacionamiento
           FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestorestacionamientofx/plano_estacionamiento.fxml"));
           Parent root = loader.load();

           PlanoEstacionamientoController planoController = loader.getController();

//           declaro el cocheraDAO para acceder a sus metodos
           CocheraDAO cocheraDAO = new CocheraDAO();
//           obtengo el valor de estado_cochera con esta query
           Response<List<Cochera>> response = cocheraDAO.readEstado();

           if (response.isSuccess()) {
//               seteo la lista de cocheras
               planoController.setListaCocheras(response.getEntity());
               planoController.pintarCocherasPorEstado(); // ⚠️ Importante: pintás una vez cargado
           } else {
               System.out.println("❌ Error al obtener cocheras: " + response.getMessage());
           }

           Stage stage = new Stage();
           stage.setTitle("Plano del Estacionamiento");
           stage.setScene(new Scene(root));
//           con la siguiente linea, bloqueo la pantalla de menu cochera
           stage.initModality(Modality.APPLICATION_MODAL);
           stage.showAndWait();  // espera hasta que se cierre

//           invoco la funcion para traer el codigo_cochera
           int codigo_cochera = planoController.getCocheraSeleccionada();

           if(codigo_cochera != -1){
               System.out.println("Se eligio "+codigo_cochera);
               cochera.setCodigoCochera(codigo_cochera);
           }
//           stage.show();

       } catch (IOException e) {
           e.printStackTrace();
           System.out.println("No se pudo cargar la vista del plano de cocheras: " + e.getMessage());
       }
   }


//    boton para registrar
    @FXML
    public void registrarVehiculo() {
        String patente = patenteField.getText();
        String tipo = tipoComboBox.getValue();

//        Esto crea la instancia del vehiculo
        Vehiculo vehiculo = switch (tipo) {
            case "Sedan" -> new Sedan(patente);
            case "SUV" -> new Suv(patente);
            case "Pickup" -> new Pickup(patente);
            default -> null;
        };

        if (vehiculo != null) {
            System.out.println("Vehículo creado:");
            System.out.println("Patente: " + vehiculo.getPatente());
            System.out.println("Tipo: " + vehiculo.getDescripcion());
            System.out.println("Recargo: " + vehiculo.obtenerRecargo());

            System.out.println("Tipo Contrato: "+tipoContratoComboBox.getValue());
            System.out.println("Tipo Servicio: "+tipoComboBox.getValue());
            System.out.println("codigo cochera: "+cochera.getCodigoCochera());
            // Seteo datos en el objeto cochera
            cochera.setVehiculo(vehiculo);
            cochera.setContrato(tipoContratoComboBox.getValue());
            cochera.setServicio(servicioComboBox.getValue());
            cochera.setFechaIngreso(LocalDateTime.now());
            cochera.setEstadoCochera(EstadoCochera.OCUPADO); // le cambio el estado a ocupado

//            registro la fecha y hora del registro - seteo un formato para verlo por consola
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            System.out.println("Fecha y hora: " + cochera.getFechaIngreso().format(formatter));

            // Actualizo en la base de datos
            CocheraDAO cocheraDAO = new CocheraDAO();
            Response<Cochera> resp = cocheraDAO.ingresarVehiculo(cochera);


            // Mensaje de resultado
            if (resp.isSuccess()) {
                System.out.println("✅ " + resp.getMessage());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Exito");
                alert.setHeaderText(null);
                alert.setContentText("La patente '" + patente + "' se ingresó correctamente.");
                alert.showAndWait();
            } else {
                System.out.println("❌ " + resp.getMessage());
            }
        } else {
            System.out.println("❌ Tipo de vehículo inválido.");
        }
        }

}
