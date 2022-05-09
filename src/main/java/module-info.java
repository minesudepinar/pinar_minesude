module com.example.userloginsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.userloginsystem to javafx.fxml;
    exports com.example.userloginsystem;
}