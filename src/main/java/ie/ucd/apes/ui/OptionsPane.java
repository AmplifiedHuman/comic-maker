package ie.ucd.apes.ui;

import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class OptionsPane extends VBox {
    public OptionsPane() {
        ToggleButton leftButton = new ToggleButton("", new ImageView("/left_button.png"));
        leftButton.setTooltip(new Tooltip("Edit Left Side"));
        ToggleButton rightButton = new ToggleButton("", new ImageView("/right_button.png"));
        rightButton.setTooltip(new Tooltip("Edit Right Side"));

        Button genderButton = new Button("", new ImageView("/gender_button.png"));
        genderButton.setTooltip(new Tooltip("Switch Gender"));

        Button mirrorButton = new Button("", new ImageView("/mirror_button.png"));
        mirrorButton.setTooltip(new Tooltip("Mirror Image"));

        ToggleButton bubbleButton1 = new ToggleButton("", new ImageView("/bubble1_button.png"));
        bubbleButton1.setTooltip(new Tooltip("Add Speech Bubble"));
        ToggleButton bubbleButton2 = new ToggleButton("", new ImageView("/bubble2_button.png"));
        bubbleButton2.setTooltip(new Tooltip("Add Thought Bubble"));

        Button textButton1 = new Button("", new ImageView("/text1_button.png"));
        textButton1.setTooltip(new Tooltip("Add Text Above"));
        Button textButton2 = new Button("", new ImageView("/text2_button.png"));
        textButton2.setTooltip(new Tooltip("Add Text Below"));

        ToggleGroup bubbleToggleGroup = new ToggleGroup();
        ToggleGroup sideToggleGroup = new ToggleGroup();

        leftButton.setToggleGroup(sideToggleGroup);
        rightButton.setToggleGroup(sideToggleGroup);
        bubbleButton1.setToggleGroup(bubbleToggleGroup);
        bubbleButton2.setToggleGroup(bubbleToggleGroup);

        GridPane optionsPane = new GridPane();

        optionsPane.add(leftButton, 0, 0, 1, 1);
        optionsPane.add(rightButton, 1, 0, 1, 1);
        optionsPane.add(mirrorButton, 0, 1, 1, 1);
        optionsPane.add(genderButton, 1, 1, 1, 1);
        optionsPane.add(bubbleButton1, 0, 2, 1, 1);
        optionsPane.add(textButton1, 1, 2, 1, 1);
        optionsPane.add(bubbleButton2, 0, 3, 1, 1);
        optionsPane.add(textButton2, 1, 3, 1, 1);

        optionsPane.setHgap(5);
        optionsPane.setVgap(5);

        this.getChildren().add(optionsPane);
    }
}
