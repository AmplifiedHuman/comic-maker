package ie.ucd.apes.ui;

import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ColorSelector extends VBox {
    public ColorSelector(String labelName) {
        ColorPicker colorPicker = new ColorPicker();
        Label colorPickerLabel = new Label();
        colorPickerLabel.setText(labelName);
        colorPickerLabel.setLabelFor(colorPicker);
        this.getChildren().add(colorPickerLabel);
        this.getChildren().add(colorPicker);
    }
}
