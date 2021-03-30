package ie.ucd.apes.ui;

import ie.ucd.apes.entity.DialogueType;
import javafx.scene.control.Label;

public class DialogueBox extends Label {
    public DialogueBox(String text) {
        setText(text);
        setWrapText(true);
    }

    public void setDialogueStyle(DialogueType dialogueType) {
        if (dialogueType.equals(DialogueType.SPEECH)) {
            getStyleClass().clear();
            getStyleClass().add("speech-box");
        } else if (dialogueType.equals(DialogueType.THOUGHT)) {
            getStyleClass().clear();
            getStyleClass().add("thought-box");
        }
    }
}
