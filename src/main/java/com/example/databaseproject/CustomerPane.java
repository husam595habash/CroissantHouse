package com.example.databaseproject;

import com.example.databaseproject.ClassesOfTables.Customer;
import com.example.databaseproject.DataAccess.CustomerDAO;
import com.example.databaseproject.DataStructure.LinkedList;
import com.example.databaseproject.DataStructure.Node;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.sql.SQLException;

public class CustomerPane {
    private GridPane pane;
    private TableView<Customer> tableView;
    private TextField inputField;
    private ObservableList<Customer> customerObservableList;


    public CustomerPane() {
        pane = new GridPane();
        pane.setStyle("-fx-background-color: white;");
        customerObservableList = FXCollections.observableArrayList();

        initializeTableView();
        setupRowSelectionHandler();
        loadCustomers();
    }

    private void initializeTableView() {
        tableView = new TableView<>();
        tableView.setPrefSize(750, 500);

        TableColumn<Customer, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setPrefWidth(60);

        TableColumn<Customer, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setPrefWidth(95);

        TableColumn<Customer, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailColumn.setPrefWidth(215);

        TableColumn<Customer, String> phoneColumn = new TableColumn<>("Phone");
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        phoneColumn.setPrefWidth(120);

        TableColumn<Customer, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        addressColumn.setPrefWidth(250);

        tableView.getColumns().addAll(idColumn, nameColumn, emailColumn, phoneColumn, addressColumn);
        tableView.setItems(customerObservableList);

        HBox tableViewBox = new HBox(20,tableView);

        inputField = new TextField();
        inputField.setPrefSize(165, 39);
        inputField.getStyleClass().add("text-field-Primary");

        inputField.textProperty().addListener((observable, oldValue, newValue) -> filterTableView(newValue));

        Label label = new Label(" Search by ID or Phone number:");
        label.setPrefSize(253, 35);
        label.setFont(Font.font("System", FontWeight.BLACK, 14));

        HBox inputBox = new HBox(20,label , inputField);
        inputBox.setAlignment(Pos.CENTER);

        HBox buttonBox = new HBox();
        buttonBox.setSpacing(20.0);
        buttonBox.setAlignment(Pos.CENTER);

        Button removeButton = new Button("Remove");
        removeButton.setPrefSize(81.0, 26.0);
        removeButton.getStyleClass().add("primary-button");
        removeButton.setOnAction(e -> removeCustomer());

        Button updateButton = new Button("Update");
        updateButton.setPrefSize(74.0, 22.0);
        updateButton.getStyleClass().add("primary-button");

        Button addNewCustomerButton = new Button("Add New Customer");
        addNewCustomerButton.setPrefWidth(120.0);
        addNewCustomerButton.getStyleClass().add("primary-button2");
        addNewCustomerButton.setOnAction(e -> addCustomer());

        VBox vBox = new VBox(20 , inputBox , buttonBox);
        vBox.setPadding(new Insets(50 , 0 , 0 , 50));
        buttonBox.getChildren().addAll(removeButton, updateButton, addNewCustomerButton);

        pane.add(tableViewBox ,0 , 0);
        pane.add(vBox , 1 , 0);
        pane.setAlignment(Pos.CENTER);
    }

    private void loadCustomers() {
        try {
            CustomerDAO customerDAO = new CustomerDAO();
            LinkedList customers = customerDAO.getAllCustomers();

            Node current = customers.getFront();
            while (current != null) {
                customerObservableList.add((Customer) current.getElement());
                current = current.getNext();
            }
            customerDAO.closeConnection();
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", "Could not load customer data. Please try again later.");
        }
    }

    private void filterTableView(String query) {
        ObservableList<Customer> filteredList = FXCollections.observableArrayList();

        for (Customer customer : customerObservableList) {
            if (String.valueOf(customer.getId()).contains(query) ||
                    (customer.getPhone() != null && customer.getPhone().contains(query))) {
                filteredList.add(customer);
            }
        }
        tableView.setItems(filteredList);
    }

    private void removeCustomer() {
        Customer selectedCustomer = tableView.getSelectionModel().getSelectedItem();

        if (selectedCustomer != null) {
            Alert confirmationAlert = showAlertRemove(Alert.AlertType.CONFIRMATION, "Confirm Deletion",
                    "Are you sure you want to delete this customer?");

            confirmationAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    try {
                        CustomerDAO customerDAO = new CustomerDAO();
                        customerDAO.deleteCustomerById(selectedCustomer.getId());
                        customerDAO.closeConnection();

                        customerObservableList.remove(selectedCustomer);
                        tableView.setItems(customerObservableList);

                        showAlert(Alert.AlertType.INFORMATION, "Customer Deleted",
                                "Customer successfully deleted!");
                        inputField.clear();
                    } catch (SQLException e) {
                        showAlert(Alert.AlertType.ERROR, "Database Error", "Could not delete customer. Please try again later.");
                    }
                }
            });
        } else {
            showAlert(Alert.AlertType.WARNING, "No Selection", "Please select a customer to remove.");
        }
    }

    private void addCustomer() {
        Stage addCustomerStage = new Stage();
        addCustomerStage.setTitle("Add New Customer");

        // Create input fields
        TextField idField = new TextField();
        idField.setPromptText("ID");
        idField.getStyleClass().add("text-field-Primary");

        TextField nameField = new TextField();
        nameField.setPromptText("Name");
        nameField.getStyleClass().add("text-field-Primary");

        TextField emailField = new TextField();
        emailField.setPromptText("Email");
        emailField.getStyleClass().add("text-field-Primary");

        TextField phoneField = new TextField();
        phoneField.setPromptText("Phone");
        phoneField.getStyleClass().add("text-field-Primary");

        TextField addressField = new TextField();
        addressField.setPromptText("Address");
        addressField.getStyleClass().add("text-field-Primary");

        // Create labels
        Label idLabel = new Label("ID:");
        Label nameLabel = new Label("Name:");
        Label emailLabel = new Label("Email:");
        Label phoneLabel = new Label("Phone:");
        Label addressLabel = new Label("Address:");

        // Add fields and labels to a grid
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new javafx.geometry.Insets(20));
        grid.add(idLabel, 0, 0);
        grid.add(idField, 1, 0);
        grid.add(nameLabel, 0, 1);
        grid.add(nameField, 1, 1);
        grid.add(emailLabel, 0, 2);
        grid.add(emailField, 1, 2);
        grid.add(phoneLabel, 0, 3);
        grid.add(phoneField, 1, 3);
        grid.add(addressLabel, 0, 4);
        grid.add(addressField, 1, 4);

        // Create buttons
        Button addButton = new Button("Add");
        addButton.getStyleClass().add("primary-button");
        Button cancelButton = new Button("Cancel");
        cancelButton.getStyleClass().add("primary-button");

        // Button actions
        addButton.setOnAction(event -> {
            System.out.println("Add button clicked"); // Debug log
            if (validateCustomerFields(idField, nameField, emailField, phoneField, addressField)) {
                try {
                    // Parse ID as an integer
                    int id = Integer.parseInt(idField.getText());

                    // Create a new Customer object
                    Customer newCustomer = new Customer(id, nameField.getText(), emailField.getText(),
                            phoneField.getText(), addressField.getText());

                    // Insert the customer into the database
                    CustomerDAO customerDAO = new CustomerDAO();
                    System.out.println("Attempting to add customer to database"); // Debug log
                    customerDAO.addCustomer(newCustomer);

                    // Add to the ObservableList and update TableView
                    customerObservableList.add(newCustomer);
                    tableView.setItems(customerObservableList);
                    customerDAO.closeConnection();

                    // Show success alert and close the stage
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Customer added successfully!");
                    addCustomerStage.close();
                } catch (NumberFormatException e) {
                    System.out.println("Invalid ID input"); // Debug log
                    showAlert(Alert.AlertType.ERROR, "Invalid Input", "ID must be a valid integer.");
                } catch (SQLException e) {
                    // Display specific error messages for unique constraint violations
                    System.out.println("SQL exception occurred: " + e.getMessage()); // Debug log
                    showAlert(Alert.AlertType.ERROR, "Database Error", e.getMessage());
                }
            } else {
                System.out.println("Validation failed"); // Debug log
                showAlert(Alert.AlertType.WARNING, "Invalid Input", "Please fill in all fields.");
            }
        });




        cancelButton.setOnAction(event -> addCustomerStage.close());

        // Create an HBox for buttons
        HBox buttonBox = new HBox(10, addButton, cancelButton);
        buttonBox.setPadding(new Insets(10, 0, 0, 0));

        // Create a VBox to hold the grid and buttons
        VBox layout = new VBox(10, grid, buttonBox);
        layout.setStyle("-fx-background-color: white");
        layout.setPadding(new Insets(20));

        // Set up the stage
        Scene scene = new Scene(layout, 400, 400);
        scene.getStylesheets().add(getClass().getResource("/com/example/databaseproject/style.css").toExternalForm());
        addCustomerStage.setScene(scene);
        addCustomerStage.show();
    }


    private boolean validateCustomerFields(TextField idField, TextField nameField, TextField emailField, TextField phoneField, TextField addressField) {
        return !idField.getText().isEmpty() &&
                !nameField.getText().isEmpty() &&
                !emailField.getText().isEmpty() &&
                !phoneField.getText().isEmpty() &&
                !addressField.getText().isEmpty();
    }



    // Set up row selection handler for the table view
    private void setupRowSelectionHandler() {
        tableView.setOnMouseClicked(e -> {
            if (e.getClickCount() == 1) {
                Customer customer = tableView.getSelectionModel().getSelectedItem();
                if (customer != null) {
                    inputField.setText(customer.getId()+"");
                }
            }
        });
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait(); // Ensure the alert is displayed
    }

    private Alert showAlertRemove(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        return alert;
    }


    public GridPane getPane() {
        return pane;
    }

    public TableView<Customer> getTableView() {
        return tableView;
    }
}
