module com.example.databaseproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.databaseproject to javafx.fxml;
    exports com.example.databaseproject;
    exports com.example.databaseproject.Controllers;
    opens com.example.databaseproject.Controllers to javafx.fxml;
    exports com.example.databaseproject.ClassesOfTables;
    opens com.example.databaseproject.ClassesOfTables to javafx.fxml;
}