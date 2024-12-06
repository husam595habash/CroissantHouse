package com.example.databaseproject.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class FrozenBoxesController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button backButton;

    @FXML
    private Button cheeseBallsButton;

    @FXML
    private Button cheeseBorekButton;

    @FXML
    private Button kibbehButton;

    @FXML
    private Button pizzaRollsButton;

    @FXML
    private Button potatoBorekButton;

    @FXML
    private Button shishbarakButton;

    @FXML
    void backAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/databaseproject/CashTable.fxml"));

        // Load the original CashTable.fxml
        BorderPane mainPane = loader.load();

        // Retrieve the controller of the CashTable
        CashTableController cashTableController = loader.getController();

        // Restore the MenuPane on the left
        if (cashTableController != null) {
            mainPane.setLeft(cashTableController.getMenuPane());
        } else {
            System.err.println("Failed to retrieve the CashTableController.");
        }

        // Replace the current scene's content with the mainPane (or update its region)
        anchorPane.getScene().setRoot(mainPane);
    }

    @FXML
    void cheeseBallsAction(ActionEvent event) {

    }

    @FXML
    void cheeseBorekAction(ActionEvent event) {

    }

    @FXML
    void kibbehAction(ActionEvent event) {

    }

    @FXML
    void pizzaRollsAction(ActionEvent event) {

    }

    @FXML
    void potatoBorekAction(ActionEvent event) {

    }

    @FXML
    void shishbarakAction(ActionEvent event) {

    }

}

