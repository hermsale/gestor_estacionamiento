package com.example.gestorestacionamientofx.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

// importo el sql


public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}