package com.example.databaseproject;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {
    private final NavPane navPane = new NavPane();
    private final CustomerPane addCustomerPane = new CustomerPane();
    private final BorderPane root = new BorderPane();

    @Override
    public void start(Stage stage) throws IOException {
        root.setTop(navPane.getTopPane());
        root.setCenter(addCustomerPane.getPane());

        // Load FXML and apply styles
        Scene scene = new Scene(root, 1162, 644);

        // Add the CSS file
        scene.getStylesheets().add(getClass().getResource("/com/example/databaseproject/style.css").toExternalForm());

        // Set the stage
        stage.setTitle("Croissant House");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
