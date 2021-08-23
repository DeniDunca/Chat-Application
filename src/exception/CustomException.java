package exception;

import javafx.scene.control.Alert;

public class CustomException {
    private Alert alert;

    public CustomException(String title, String header, String context) {
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(context);
    }

    public void show(){
        alert.showAndWait();
    }
}
