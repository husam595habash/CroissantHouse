package com.example.databaseproject.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class CakeController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button backButton;

    @FXML
    private Button largeButton;

    @FXML
    private Button smallButton;

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
    void largeCackeAction(ActionEvent event) {
    }

    @FXML
    void smallCackeAction(ActionEvent event) {

    }

}

