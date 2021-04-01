package ie.ucd.apes.ui;

import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ColorSelector extends VBox {
    private final ColorPicker colorPicker;

    public ColorSelector(Color defaultColor, String labelName) {
        colorPicker = new ColorPicker(defaultColor);
        colorPicker.setMinWidth(170);
        Label colorPickerLabel = new Label();
        colorPickerLabel.setTextFill(Color.WHITE);
        colorPickerLabel.getStyleClass().add("color-label");
        colorPickerLabel.setText(labelName);
        colorPickerLabel.setLabelFor(colorPicker);
        colorPicker.getCustomColors().add(defaultColor);
        this.getChildren().add(colorPickerLabel);
        this.getChildren().add(colorPicker);
    }

    public ColorPicker getColorPicker() {
        return colorPicker;
    }

    public void setColorPicker(Color color) {
        colorPicker.setValue(color);
    }
}
