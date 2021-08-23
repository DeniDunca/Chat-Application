package gui;


import exception.CustomException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.userService;

import java.io.IOException;

public class LogIn {

    private userService userService = new userService();

    @FXML
    TextField usernameField;

    @FXML
    PasswordField passwordField;


    public void signInPressed(){
        String username = usernameField.getText();
        String password = passwordField.getText();

        if(!userService.checkUser(username,password)) {
            CustomException customException = new CustomException("Error!","Invalid data!","Wrong username or password!");
            customException.show();
            return;
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("mainWindow.fxml"));
            Parent root = fxmlLoader.load();

            MainWindow mainWindow = fxmlLoader.getController();
            mainWindow.setUser(username,password);

            Stage stage = new Stage();
            stage.setScene(new Scene(root,600,400));
            //stage.setTitle("Welcome!");

            mainWindow.setTitle(stage);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void signUpPressed(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("signUp.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setTitle("Sign up, loser");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
