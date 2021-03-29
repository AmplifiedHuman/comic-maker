package ie.ucd.apes.ui;

import javafx.scene.control.Label;

public class DialogueBox extends Label {
    public DialogueBox(String text) {
        super(text);
        setWrapText(true);
        getStyleClass().add("dialogue-box");
    }
}
