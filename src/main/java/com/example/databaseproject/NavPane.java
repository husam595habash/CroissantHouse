package com.example.databaseproject;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class NavPane {
    private AnchorPane topPane;
    private Button addCustomerButton , orderHistoryButton , cashSystemButton;

    public NavPane() {
        // Initialize the AnchorPane
        topPane = new AnchorPane();
        topPane.setPrefSize(1162, 89);
        topPane.setStyle("-fx-background-color: white;");

        // Add Customer Button with primary-button style
        addCustomerButton = new Button("Add Customer");
        addCustomerButton.setId("AddCustomerButton");
        addCustomerButton.setLayoutX(336);
        addCustomerButton.setLayoutY(29);
        addCustomerButton.getStyleClass().add("secondary-button");

        // Order History Button
        orderHistoryButton = new Button("Customer's Order History");
        orderHistoryButton.setId("orderHistoryButton");
        orderHistoryButton.setLayoutX(531);
        orderHistoryButton.setLayoutY(29);
        orderHistoryButton.setPrefSize(320, 51);
        orderHistoryButton.getStyleClass().add("secondary-button");

        // Cash System Button
        cashSystemButton = new Button("Cash System");
        cashSystemButton.setId("cashSystemButton");
        cashSystemButton.setLayoutX(153);
        cashSystemButton.setLayoutY(27);
        cashSystemButton.setPrefSize(197, 30);
        cashSystemButton.getStyleClass().add("secondary-button");

        // Logo Image
        ImageView logoImageView = new ImageView(new Image(getClass().getResource("/com/example/databaseproject/croissant house logo.png").toExternalForm()));
        logoImageView.setFitWidth(119);
        logoImageView.setFitHeight(97);
        logoImageView.setLayoutX(11);
        logoImageView.setPickOnBounds(true);
        logoImageView.setPreserveRatio(true);

        topPane.getChildren().addAll(addCustomerButton, orderHistoryButton, cashSystemButton, logoImageView);
    }

    public AnchorPane getTopPane() {
        return topPane;
    }
}
