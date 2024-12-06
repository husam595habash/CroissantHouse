/*
package com.example.databaseproject;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.example.databaseproject.ClassesOfTables.Employee;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class EmployeeFX {
    private ArrayList<Employee> employeeList;
    private TableView<Employee> employeeTable;
    private ObservableList<Employee> employeeOb;
    private Connection con;
    private Employee temp;
    private DBConn db;
    private int maxId;

    public EmployeeFX(ArrayList<Employee> employeeList, DBConn db) throws SQLException, ClassNotFoundException {
        this.employeeList = employeeList;
        employeeOb = FXCollections.observableArrayList(employeeList);
        this.db = db;
    }

    public VBox getEmployeeManagement() {
        VBox vbox = new VBox(20);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(10));
        vbox.setStyle("-fx-background-color:white;");

        // Logo
        ImageView logo = new ImageView("logo.jpg");
        logo.setFitWidth(150);
        logo.setFitHeight(90);

        Label employeeManagementLabel = new Label("Employee Management");
        employeeManagementLabel.setFont(Font.font("Elephant", FontWeight.BOLD, 20));
        employeeManagementLabel.setStyle("-fx-text-fill: rgb(230, 0, 0);");

        HBox headerBox = new HBox(20);
        headerBox.setAlignment(Pos.CENTER_LEFT);
        headerBox.setPadding(new Insets(10));
        headerBox.getChildren().addAll(logo, employeeManagementLabel);

        Separator separator = new Separator();
        separator.setPrefWidth(200);

        // Search Box
        HBox searchBox = new HBox(20);
        searchBox.setAlignment(Pos.TOP_LEFT);
        searchBox.setPadding(new Insets(0, 100, 0, 100));

        Button search = new Button("Search for Employee");
        search.setStyle("-fx-background-color: rgb(230, 0, 0); -fx-text-fill: white; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 20px;-fx-background-radius: 30; -fx-border-radius: 30;");
        search.setPrefWidth(230);
        search.setPrefHeight(50);

        TextField searchField = new TextField();
        searchField.setStyle("-fx-border-color: rgb(230, 0, 0); -fx-border-width: 2;-fx-pref-width: 250px;-fx-pref-height: 40px; -fx-font-size: 20px;");

        searchBox.getChildren().addAll(search, searchField);

        // TableView
        employeeTable = new TableView<>();
        employeeTable.setMaxWidth(1300);
        employeeTable.setMaxHeight(900);
        employeeTable.setStyle("-fx-border-color:red;-fx-border-width:3;-fx-text-fill: white;-fx-font-weight: bold;");

        TableColumn<Employee, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setMinWidth(100);
        idColumn.setStyle("-fx-alignment: CENTER;");

        TableColumn<Employee, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setMinWidth(150);
        nameColumn.setStyle("-fx-alignment: CENTER;");

        TableColumn<Employee, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailColumn.setMinWidth(200);
        emailColumn.setStyle("-fx-alignment: CENTER;");

        TableColumn<Employee, String> phoneColumn = new TableColumn<>("Phone");
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        phoneColumn.setMinWidth(150);
        phoneColumn.setStyle("-fx-alignment: CENTER;");

        TableColumn<Employee, String> passwordColumn = new TableColumn<>("Password");
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        passwordColumn.setMinWidth(150);
        passwordColumn.setStyle("-fx-alignment: CENTER;");

        TableColumn<Employee, Integer> managerIdColumn = new TableColumn<>("Manager ID");
        managerIdColumn.setCellValueFactory(new PropertyValueFactory<>("managerId"));
        managerIdColumn.setMinWidth(100);
        managerIdColumn.setStyle("-fx-alignment: CENTER;");

        employeeTable.getColumns().addAll(idColumn, nameColumn, emailColumn, phoneColumn, passwordColumn, managerIdColumn);
        employeeTable.setItems(employeeOb);

        // Buttons
        Button add = new Button("Add Employee");
        Button update = new Button("Update Employee");
        Button refresh = new Button("Refresh");
        Button remove = new Button("Remove Employee");

        styleButton(add);
        styleButton(update);
        styleButton(refresh);
        styleButton(remove);

        HBox buttonBox = new HBox(20);
        buttonBox.setAlignment(Pos.TOP_LEFT);
        buttonBox.setPadding(new Insets(0, 100, 0, 100));
        buttonBox.getChildren().addAll(add, update, remove, refresh);

        // Button Actions
        add.setOnAction(e -> addEmployeeStage());
        update.setOnAction(e -> updateEmployeeStage());
        refresh.setOnAction(e -> {
            updateEmployeeObList();
            employeeTable.refresh();
        });
        remove.setOnAction(e -> removeEmployee());

        search.setOnAction(e -> {
            int searchId = Integer.parseInt(searchField.getText());
            Employee searchedEmployee = searchForEmployee(searchId);
            if (searchedEmployee != null) {
                employeeOb.clear();
                employeeOb.add(searchedEmployee);
                employeeTable.refresh();
            } else {
                showAlert("Employee not found!");
            }
        });

        vbox.getChildren().addAll(headerBox, separator, searchBox, employeeTable, buttonBox);

        return vbox;
    }

    private void styleButton(Button button) {
        button.setStyle("-fx-background-color: rgb(230, 0, 0); -fx-text-fill: white; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 20px;-fx-background-radius: 30; -fx-border-radius: 30;");
        button.setPrefWidth(200);
        button.setPrefHeight(50);
    }

    private void addEmployeeStage() {
        // Create and implement the Add Employee Stage UI here
        // Add logic to add employee to the database and refresh the table
    }

    private void updateEmployeeStage() {
        // Create and implement the Update Employee Stage UI here
        // Add logic to update employee details in the database
    }

    private void removeEmployee() {
        Employee selected = employeeTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            String query = "DELETE FROM Employee WHERE id = " + selected.getId();
            executeQuery(query);
            employeeList.remove(selected);
            updateEmployeeObList();
        } else {
            showAlert("No employee selected!");
        }
    }

    private void updateEmployeeObList() {
        employeeOb.clear();
        employeeOb.addAll(employeeList);
    }

    private Employee searchForEmployee(int id) {
        for (Employee employee : employeeList) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        return null;
    }

    private void executeQuery(String query) {
        try {
            con = db.connectDB();
            Statement stmt = con.createStatement();
            stmt.execute(query);
            stmt.close();
            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
*/
