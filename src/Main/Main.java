package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import observer.Subject;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Subject subject = Subject.getInstance();

        Parent root = FXMLLoader.load(getClass().getResource("../gui/logIn.fxml"));
        primaryStage.setTitle("Welcome to the Chat Application!");
        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }
}
