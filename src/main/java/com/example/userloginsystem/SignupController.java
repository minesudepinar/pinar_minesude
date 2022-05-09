package com.example.userloginsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SignupController {
    @FXML
    private Label login;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password1;
    @FXML
    private PasswordField password2;
    @FXML
    private Label warning;


    public void goLogIn(MouseEvent mouseEvent) {
        try {
            Stage stage = (Stage) login.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void saveNewUser(ActionEvent actionEvent) {
        if(username.getText().length()<3){
            warning.setStyle("-fx-text-fill: red;");
            warning.setText("Username is too short!");
        }else if(password1.getText().length()<3){
            warning.setStyle("-fx-text-fill: red;");
            warning.setText("Password is too short!");
        }else if (!password1.getText().equals(password2.getText())){
            warning.setStyle("-fx-text-fill: red;");
            warning.setText("Passwords are not same!");
        }else
            try {
                DatabaseConnect.addUser(username.getText(),password1.getText());
                Stage stage = (Stage) login.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("user-info-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                stage.setScene(scene);

            }catch (Exception e){
                warning.setStyle("-fx-text-fill: red;");
                warning.setText(e.getMessage());
            }

    }

    public void reset(ActionEvent actionEvent) {
        username.setText("");
        password1.setText("");
        password2.setText("");
    }
}
