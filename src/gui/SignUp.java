package gui;

import exception.AlertMsg;
import exception.CustomException;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import service.userService;
import validation.Validator;


public class SignUp  {

    @FXML
    TextField usernameField;

    @FXML
    PasswordField passwordField;

    @FXML
    PasswordField confirmPasswordField;

    @FXML
    TextField firstNameField;

    @FXML
    TextField lastNameField;

    Validator validator = new Validator();

    public void addUser(){
        String pass = passwordField.getText();
        String passCheck = confirmPasswordField.getText();

        if(!validator.validateName(usernameField.getText())){
            CustomException customException = new CustomException("Error","Not a valid username","Don't use special characters!");
            customException.show();
            return;
        }

        if(!validator.validatePassword(passwordField.getText())){
            CustomException customException = new CustomException("Error","Not a valid password","Don't use special characters!");
            customException.show();
            return;
        }

        if(!pass.equals(passCheck))
        {
            CustomException customException = new CustomException("Error","ur mom!","Passwords don't match");
            customException.show();
            return;
        }

        String username = usernameField.getText();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();

        userService userService = new userService();
        if(userService.addUser(username,pass,firstName,lastName))
        {
            AlertMsg alertMsg = new AlertMsg("Congrats!","Account created!","Gj wp");
            alertMsg.show();
        }
    }
}
