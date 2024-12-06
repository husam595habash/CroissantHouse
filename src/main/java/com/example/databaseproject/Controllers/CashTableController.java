package com.example.databaseproject.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class CashTableController {

    @FXML
    private Button AddCustomerButton;

    @FXML
    private BorderPane BorderPane;

    @FXML
    private AnchorPane MenuPane;

    @FXML
    private Label NumberOfTotal;

    @FXML
    private Button cakeButton;

    @FXML
    private Button cashSystemButton;

    @FXML
    private Button coldDrinksButton;

    @FXML
    private Button croissantButton;

    @FXML
    private Button deleteAllButton;

    @FXML
    private Button deleteOnePieceButton;

    @FXML
    private ImageView exitButton;

    @FXML
    private Button frozenBoxesButton;

    @FXML
    private Label handledByLabel;

    @FXML
    private Button hotDrinksButton;

    @FXML
    private Label nameOfEmployee;

    @FXML
    private Label numberOfOrder;

    @FXML
    private Button orderHistoryButton;

    @FXML
    private Label orderIdLabel;

    @FXML
    private Button snacksButton;

    @FXML
    private Button softDrinksButton;

    @FXML
    private Button sweetTreatsButton;

    @FXML
    private Label totalOrderAmountLabel;
    private Stage stage;

    @FXML
    void AddCustomerAction(ActionEvent event) {

    }

    @FXML
    void cakeAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/databaseproject/Cake.fxml"));
        AnchorPane pane = fxmlLoader.load();
        BorderPane.setLeft(pane); // Assuming BorderPane's left is being updated.
    }

    @FXML
    void cashSystemAction(ActionEvent event) {

    }

    @FXML
    void coldDrinksAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/databaseproject/ColdDrinks.fxml"));
        AnchorPane pane = fxmlLoader.load();
        BorderPane.setLeft(pane); // Assuming BorderPane's left is being updated.
    }

    @FXML
    void croissantAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/databaseproject/Croissant.fxml"));
        AnchorPane pane = fxmlLoader.load();
        Scene scene = new Scene(pane);

        // Create a new stage for Croissant window
        Stage newStage = new Stage();
        newStage.setScene(scene);

        // Get the controller of the newly loaded FXML
        CroissantController croissantController = fxmlLoader.getController();

        // Pass the reference of the new stage to the CroissantController
        croissantController.setStage(newStage);

        newStage.show();
    }

    @FXML
    void deleteAllAction(ActionEvent event) {

    }

    @FXML
    void deleteOnePieceAction(ActionEvent event) {

    }

    @FXML
    void exitAction() throws IOException {
        // Optional: Show a confirmation dialog before exiting
        boolean confirmExit = showAlert();
        if (!confirmExit) {
            return; // Do nothing if the user cancels
        }

        Stage stage = (Stage) exitButton.getScene().getWindow();

        // Load the login scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/databaseproject/Login.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.centerOnScreen();
    }

    @FXML
    void frozenBoxesAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/databaseproject/FrozenBoxes.fxml"));
        AnchorPane pane = fxmlLoader.load();
        BorderPane.setLeft(pane); // Assuming BorderPane's left is being updated.
    }

    @FXML
    void hotDrinksAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/databaseproject/HotDrinks.fxml"));
        AnchorPane pane = fxmlLoader.load();
        BorderPane.setLeft(pane); // Assuming BorderPane's left is being updated.
    }

    @FXML
    void orderHistoryAction(ActionEvent event) {

    }

    @FXML
    void snacksAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/databaseproject/Snacks.fxml"));
        AnchorPane pane = fxmlLoader.load();
        BorderPane.setLeft(pane); // Assuming BorderPane's left is being updated.
    }

    @FXML
    void softDrinksAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/databaseproject/SoftDrinks.fxml"));
        AnchorPane pane = fxmlLoader.load();
        BorderPane.setLeft(pane); // Assuming BorderPane's left is being updated.
    }

    @FXML
    void sweetTreatsAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/databaseproject/SweetTreats.fxml"));
        AnchorPane pane = fxmlLoader.load();
        BorderPane.setLeft(pane); // Assuming BorderPane's left is being updated.
    }

    private boolean showAlert() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit Confirmation");
        alert.setHeaderText("Are you sure you want to exit?");
        return alert.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK;
    }



    public void setHandledBy(String employeeName) {
        nameOfEmployee.setText(employeeName);
    }

    public AnchorPane getMenuPane() {
        return MenuPane;
    }

    public Stage getStage() {
        return stage;
    }
}
