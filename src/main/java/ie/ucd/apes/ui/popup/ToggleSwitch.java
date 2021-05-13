package ie.ucd.apes.ui.popup;

import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class ToggleSwitch extends VBox {
    private final ToggleGroup toggleGroup;

    public ToggleSwitch(String label) {
        toggleGroup = new ToggleGroup();
        Label switchLabel = new Label(label);
        RadioButton rb1 = new RadioButton("Yes");
        rb1.setToggleGroup(toggleGroup);
        rb1.setSelected(true);
        RadioButton rb2 = new RadioButton("No");
        rb2.setToggleGroup(toggleGroup);
        getChildren().addAll(switchLabel, new VBox(rb1, rb2));
    }

    public boolean isEnabled() {
        RadioButton radioButton = (RadioButton) toggleGroup.getSelectedToggle();
        return radioButton.getText().equals("Yes");
    }
}
