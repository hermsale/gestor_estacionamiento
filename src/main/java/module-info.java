module com.example.gestorestacionamientofx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;

    opens com.example.gestorestacionamientofx to javafx.fxml;
    exports com.example.gestorestacionamientofx;
    exports com.example.gestorestacionamientofx.Controller;
    opens com.example.gestorestacionamientofx.Controller to javafx.fxml;
    opens com.example.gestorestacionamientofx.Model to javafx.fxml, javafx.base;
}
