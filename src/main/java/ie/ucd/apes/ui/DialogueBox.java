package ie.ucd.apes.ui;

import ie.ucd.apes.entity.DialogueType;
import ie.ucd.apes.entity.Selection;
import javafx.scene.control.Label;

public class DialogueBox extends Label {
    public DialogueBox() {
        super();
        setWrapText(true);
    }

    public void setDialogueStyle(Selection selection, DialogueType dialogueType) {
        if (dialogueType.equals(DialogueType.SPEECH)) {
            getStyleClass().clear();
            getStyleClass().add("speech-box");
        } else if (dialogueType.equals(DialogueType.THOUGHT)) {
            if (selection.equals(Selection.IS_LEFT)) {
                getStyleClass().clear();
                getStyleClass().add("thought-box-left");
            } else {
                getStyleClass().clear();
                getStyleClass().add("thought-box");
            }
        }
    }
}
