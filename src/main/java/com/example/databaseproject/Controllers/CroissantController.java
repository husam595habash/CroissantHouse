package com.example.databaseproject.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CroissantController {
    private Stage stage;  // Store the reference to the stage

    @FXML
    private TextField amountField;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button cancelButton;

    @FXML
    private Button enterButton;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    void cancelAction() {
        if (stage != null) {
            stage.close();  // Close the stage that was passed from CashTableController
        }
    }

    @FXML
    void enterAction(ActionEvent event) {

    }

}
