package exception;

import javafx.scene.control.Alert;

public class AlertMsg {
    private Alert alert;

    public AlertMsg(String title, String header, String context) {
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(context);
    }

    public void show(){
        alert.showAndWait();
    }
}
