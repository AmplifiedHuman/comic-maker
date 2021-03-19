package ie.ucd.apes.ui;

import javafx.scene.control.TextArea;

public class DialogueBox extends TextArea {
    public DialogueBox(String text) {
        super(text);
        this.setMaxWidth(250);
        this.setWrapText(true);
    }
}
