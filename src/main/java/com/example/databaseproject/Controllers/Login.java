package com.example.databaseproject.Controllers;

import com.example.databaseproject.ClassesOfTables.Employee;
import com.example.databaseproject.DataStructure.LinkedList;
import com.example.databaseproject.DataAccess.EmployeeDAO;
import com.example.databaseproject.DataStructure.Node;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Login {
    @FXML
    private Label incorrectLabel;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordTextfield;

    @FXML
    private TextField usernameTextfield;
    private LinkedList employeeList;


    public Login() {
        try {
            EmployeeDAO employeeDAO = new EmployeeDAO();
            employeeList = employeeDAO.readEmplyees(); // Load all employees into LinkedList
            employeeDAO.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void loginAction() {
        String username = usernameTextfield.getText().trim();
        String password = passwordTextfield.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            incorrectLabel.setVisible(true);
            incorrectLabel.setText("Username or Password is empty");
            return;
        }

        // Search in the LinkedList for a matching username and password
        Employee employee = findEmployeeByUsernameAndPassword(username, password);


        if (employee != null) {
            incorrectLabel.setVisible(false);
            usernameTextfield.clear();
            passwordTextfield.clear();

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/databaseproject/CashTable.fxml"));
                Parent root = loader.load();

                Stage stage = (Stage) loginButton.getScene().getWindow();
                double currentX = stage.getX();
                double currentY = stage.getY();

                Scene scene = new Scene(root);
                stage.setScene(scene);

                stage.centerOnScreen();
                CashTableController controller = loader.getController();
                controller.setHandledBy(employee.getName());

            }catch (IOException e){
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to load Cash Table.");
            }
        }
        else {
            incorrectLabel.setVisible(true);
            incorrectLabel.setText("Incorrect username or password");
            passwordTextfield.clear();
        }
    }

    private Employee findEmployeeByUsernameAndPassword(String username, String password) {
        Node current = employeeList.getFront();
        while (current != null) {
            Employee employee = (Employee) current.getElement();
            if (employee.getName().equals(username) && employee.getPassword().equals(password)) {
                return employee;
            }
            current = current.getNext();
        }
        return null; // No match found
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}

