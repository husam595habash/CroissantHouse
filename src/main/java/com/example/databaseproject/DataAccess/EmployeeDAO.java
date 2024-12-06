package com.example.databaseproject.DataAccess;

import com.example.databaseproject.ClassesOfTables.Employee;
import com.example.databaseproject.DataStructure.LinkedList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeDAO {
    private Connection connection;
    private LinkedList employeeList;

    public EmployeeDAO() throws SQLException {
        String url = "jdbc:mysql://127.0.0.1:3306/croissanthouse";
        String username = "root";
        String password = "Trs@13081970";

        connection = DriverManager.getConnection(url, username, password);
        employeeList = new LinkedList();
    }


    // Method to fill LinkedList of Employee
    public LinkedList readEmplyees() throws SQLException {
        String query = "SELECT * FROM Employee";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                // Create a new Employee object from the result set
                Employee employee = new Employee(  resultSet.getInt("ID"),
                        resultSet.getString("Name"),
                        resultSet.getString("Email"),
                        resultSet.getString("Password"),
                        resultSet.getString("phone"),
                        resultSet.getInt("ManagerID"));
                // Add Employee to the LinkedList
                employeeList.addLast(employee);
            }
        }
        return employeeList;
    }



    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
