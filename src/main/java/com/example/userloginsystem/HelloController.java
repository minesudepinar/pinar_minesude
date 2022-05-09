package com.example.userloginsystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class HelloController {
    @FXML
    private Label signup;
    @FXML
    TextField username;
    @FXML
    PasswordField password;
    @FXML
    Label warning;


    public void gosignup(MouseEvent mouseEvent) {
        try {
            Stage stage = (Stage) signup.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("signup-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void login(MouseEvent mouseEvent) {
        try {
            DatabaseConnect.logIn(username.getText(),password.getText());
            if(DatabaseConnect.getUser()==null){
                warning.setStyle("-fx-text-fill: red;");
                warning.setText("Username or Password is not correct!");
                return;
            }
            Stage stage = (Stage) signup.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("user-info-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}