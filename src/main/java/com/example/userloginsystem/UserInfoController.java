package com.example.userloginsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class UserInfoController implements Initializable {
    @FXML
    private Button exit;
    @FXML
    private Label username;
    @FXML
    private TextField email_field;
    @FXML
    private TextField phone_field;
    @FXML
    private TextArea address_field;
    @FXML
    private Label warning;
    @FXML
    private Button save;

    public void exit(MouseEvent mouseEvent) {
        try {
            DatabaseConnect.user = null;
            Stage stage = (Stage) exit.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        User user = DatabaseConnect.getUser();
        username.setText(user.getName());
        if(user.isInfoAdded()){
            email_field.setText(user.getEmail());
            address_field.setText(user.getAddress());
            phone_field.setText(user.getPhone());

            save.setText("UPDATE");
        }
    }

    public void saveUserInfo(ActionEvent actionEvent) {
        try {
            if(DatabaseConnect.user.isInfoAdded()){
                DatabaseConnect.updateUserInfo(email_field.getText(),phone_field.getText(),address_field.getText());
                warning.setStyle("-fx-text-fill: green;");
                warning.setText("Updated Successfully");
            }else{
                DatabaseConnect.saveUserInfo(email_field.getText(),phone_field.getText(),address_field.getText());
                warning.setStyle("-fx-text-fill: green;");
                warning.setText("Saved Successfully");
                save.setText("UPDATE");
                DatabaseConnect.user.setInfoAdded(true);
            }

        }catch (Exception e){
            warning.setStyle("-fx-text-fill: red;");
            warning.setText(e.getMessage());
        }
    }
}
