package com.example.databaseproject.DataAccess;

import com.example.databaseproject.ClassesOfTables.Customer;
import com.example.databaseproject.DataStructure.LinkedList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerDAO {
    private Connection connection;

    public CustomerDAO() throws SQLException {
        String url = "jdbc:mysql://127.0.0.1:3306/croissanthouse";
        String username = "root";
        String password = "Trs@13081970";
        connection = DriverManager.getConnection(url, username, password);
    }

    public LinkedList getAllCustomers() throws SQLException {
        LinkedList customerList = new LinkedList();
        String query = "SELECT * FROM Client";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                // Create a new Customer object from the result set
                Customer customer = new Customer(
                        resultSet.getInt("ID"),
                        resultSet.getString("Name"),
                        resultSet.getString("Email"),
                        resultSet.getString("Phone"),
                        resultSet.getString("Address")
                );
                customerList.addLast(customer);
            }
        }
        return customerList;
    }

    public void deleteCustomerById(int id) throws SQLException {
        String query = "DELETE FROM Client WHERE ID = " + id;

        try (Statement statement = connection.createStatement()) {
            // Execute the DELETE query
            statement.executeUpdate(query);
        }
    }

    public void addCustomer(Customer customer) throws SQLException {
        String query = "INSERT INTO Client (ID, Name, Email, Phone, Address) VALUES ("
                + customer.getId() + ", '"
                + customer.getName() + "', '"
                + customer.getEmail() + "', '"
                + customer.getPhone() + "', '"
                + customer.getAddress() + "')";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            String message = e.getMessage();
            System.out.println(message+"      hello");
            if (e.getMessage().contains("Duplicate entry")) {
                if (e.getMessage().contains("for key 'client.PRIMARY'")) {
                    throw new SQLException("The ID already exists. Please use a unique ID.");
                } else if (e.getMessage().contains("for key 'Email'")) {
                    throw new SQLException("The Email already exists. Please use a unique Email.");
                } else if (e.getMessage().contains("for key 'Phone'")) {
                    throw new SQLException("The Phone number already exists. Please use a unique Phone number.");
                }
            }
            throw e;
        }
    }



    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}

