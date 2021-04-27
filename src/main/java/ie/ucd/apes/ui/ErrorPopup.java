package ie.ucd.apes.ui;

import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.List;

public class ErrorPopup extends Alert {
    public ErrorPopup(List<String> errors) {
        super(AlertType.ERROR);
        setTitle("XML Import Error");
        setHeaderText("Error parsing XML file: Some properties are invalid and are replaced with default values.");
        getDialogPane().setMinWidth(900);
        setResizable(true);
        ScrollPane scrollPane = new ScrollPane();
        VBox content = new VBox();
        content.setMaxHeight(900);
        for (String error : errors) {
            content.getChildren().add(new Text(error));
        }
        scrollPane.setContent(content);
        getDialogPane().setContent(scrollPane);
        showAndWait();
    }
}
