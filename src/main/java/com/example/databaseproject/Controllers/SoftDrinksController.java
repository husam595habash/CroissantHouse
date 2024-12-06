package com.example.databaseproject.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class SoftDrinksController {

    @FXML
    private Button backButton;

    @FXML
    private Button cappyButton;

    @FXML
    private Button colaButton;

    @FXML
    private AnchorPane pane;

    @FXML
    private Button sodaButton;

    @FXML
    private Button spriteButton;

    @FXML
    private Button waterButton;

    @FXML
    private Button xlButton;

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
        pane.getScene().setRoot(mainPane);
    }


    @FXML
    void cappyAction(ActionEvent event) {

    }

    @FXML
    void colaAction(ActionEvent event) {

    }

    @FXML
    void sodaAction(ActionEvent event) {

    }

    @FXML
    void spriteAction(ActionEvent event) {

    }

    @FXML
    void waterAction(ActionEvent event) {

    }

    @FXML
    void xlAction(ActionEvent event) {

    }

}
