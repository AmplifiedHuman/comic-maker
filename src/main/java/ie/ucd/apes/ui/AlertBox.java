package ie.ucd.apes.ui;

import javafx.scene.control.Alert;

public class AlertBox extends Alert {
    public AlertBox(String type) {
        super(AlertType.WARNING);
        setTitle(String.format("%s Text Limit Exceeded", type));
        setHeaderText(String.format("%s Text Limit Exceeded", type));
        setContentText(String.format("%s text box cannot exceed 80 characters", type));
        showAndWait();
    }
}
