package ie.ucd.apes.ui;

import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ColorSelector extends VBox {
    private ColorPicker colorPicker;
    public ColorSelector(String labelName) {
        colorPicker = new ColorPicker();
        Label colorPickerLabel = new Label();
        colorPickerLabel.setText(labelName);
        colorPickerLabel.setLabelFor(colorPicker);
        this.getChildren().add(colorPickerLabel);
        this.getChildren().add(colorPicker);
    }

    public ColorPicker getColorPicker() {
        return colorPicker;
    }
}
