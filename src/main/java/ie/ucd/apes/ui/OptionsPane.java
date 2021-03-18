package ie.ucd.apes.ui;

import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class OptionsPane extends VBox {
    public OptionsPane() {
        ToggleButton leftButton = new ToggleButton("Left");
        ToggleButton rightButton = new ToggleButton("Right");
        ToggleButton genderButton1 = new ToggleButton("Male");
        ToggleButton genderButton2 = new ToggleButton("Female");
        Button orientationButton = new Button("Flip Orientation");
        ToggleButton bubbleButton1 = new ToggleButton("Speech Bubble");
        ToggleButton bubbleButton2 = new ToggleButton("Thought Bubble");
        Button textButton1 = new Button("Add Text Above");
        Button textButton2 = new Button("Add Text Below");

        ToggleGroup genderToggleGroup = new ToggleGroup();
        ToggleGroup bubbleToggleGroup = new ToggleGroup();
        ToggleGroup sideToggleGroup = new ToggleGroup();

        leftButton.setToggleGroup(sideToggleGroup);
        rightButton.setToggleGroup(sideToggleGroup);
        genderButton1.setToggleGroup(genderToggleGroup);
        genderButton2.setToggleGroup(genderToggleGroup);
        bubbleButton1.setToggleGroup(bubbleToggleGroup);
        bubbleButton2.setToggleGroup(bubbleToggleGroup);

        GridPane optionsPane = new GridPane();

        optionsPane.add(leftButton, 0, 0, 1, 1);
        optionsPane.add(rightButton, 1, 0, 2, 1);
        optionsPane.add(orientationButton, 0, 1, 1, 1);
        optionsPane.add(genderButton1, 1, 1, 1, 1);
        optionsPane.add(genderButton2, 2, 1, 1, 1);
        optionsPane.add(bubbleButton1, 0, 2, 1, 1);
        optionsPane.add(textButton1, 1, 2, 2, 1);
        optionsPane.add(bubbleButton2, 0, 3, 1, 1);
        optionsPane.add(textButton2, 1, 3, 2, 1);

        optionsPane.setHgap(5);
        optionsPane.setVgap(5);

        this.getChildren().add(optionsPane);
    }
}
