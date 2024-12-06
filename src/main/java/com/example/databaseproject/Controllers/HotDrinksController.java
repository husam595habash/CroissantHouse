package com.example.databaseproject.Controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class HotDrinksController {

    @FXML
    private Button americanoButton;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button backButton;

    @FXML
    private Button chaiLatteButton;

    @FXML
    private Button coffeeLatteButton;

    @FXML
    private Button coffeeMochaButton;

    @FXML
    private Button espressoButton;

    @FXML
    private Button frenchVanillaButton;

    @FXML
    private Button hotChocolateButton;

    @FXML
    private Button macchiatoButton;

    @FXML
    private Button nescafeButton;

    @FXML
    private Button saltedCaramelButton;

    @FXML
    private Button spanishLatteButton;

    @FXML
    private Button teaButton;

    @FXML
    void americanoAction(ActionEvent event) {

    }

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
    void chaiLatteAction(ActionEvent event) {

    }

    @FXML
    void coffeeLatteAction(ActionEvent event) {

    }

    @FXML
    void coffeeMochaAction(ActionEvent event) {

    }

    @FXML
    void espressoAction(ActionEvent event) {

    }

    @FXML
    void frenchVanillaAction(ActionEvent event) {

    }

    @FXML
    void hotChocolateAction(ActionEvent event) {

    }

    @FXML
    void macchiatoAction(ActionEvent event) {

    }

    @FXML
    void nescafeAction(ActionEvent event) {

    }

    @FXML
    void saltedCaramelAction(ActionEvent event) {

    }

    @FXML
    void spanishLatteAction(ActionEvent event) {

    }

    @FXML
    void teaAction(ActionEvent event) {

    }
}