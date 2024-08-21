package it.unibz.pp2024.LudoKing;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void onGoodbyeButtonClick() {
        welcomeText.setText("Goodbye!");
    }
}
