package com.example.databaseproject.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class ColdDrinksController {

    @FXML
    private Button backButton;

    @FXML
    private Button iceAmericanoButton;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button iceCaramelButton;

    @FXML
    private Button iceChocolateButton;

    @FXML
    private Button iceCoffeeButton;

    @FXML
    private Button iceLatteButton;

    @FXML
    private Button iceMochaButton;

    @FXML
    private Button iceTeaButton;

    @FXML
    private Button iceVanillaButton;

    @FXML
    private Button lemonButton;

    @FXML
    private Button mojitoButton;

    @FXML
    private Button orangeButton;

    @FXML
    private Button spanishLatteButton;

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
    void iceAmericanoAction(ActionEvent event) {

    }

    @FXML
    void iceCaramelAction(ActionEvent event) {

    }

    @FXML
    void iceChocolateAction(ActionEvent event) {

    }

    @FXML
    void iceCoffeeAction(ActionEvent event) {

    }

    @FXML
    void iceLatteAction(ActionEvent event) {

    }

    @FXML
    void iceMochaAction(ActionEvent event) {

    }

    @FXML
    void iceTeaAction(ActionEvent event) {

    }

    @FXML
    void iceVanillaAction(ActionEvent event) {

    }

    @FXML
    void lemonAction(ActionEvent event) {

    }

    @FXML
    void mojitoAction(ActionEvent event) {

    }

    @FXML
    void orangeAction(ActionEvent event) {

    }

    @FXML
    void spanishLatteAction(ActionEvent event) {

    }

}
