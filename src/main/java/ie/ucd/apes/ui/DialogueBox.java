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
            setDialogueSize();
        } else if (dialogueType.equals(DialogueType.THOUGHT)) {
            if (selection.equals(Selection.IS_LEFT)) {
                getStyleClass().clear();
                getStyleClass().add("thought-box-left");
                setDialogueSize();
            } else {
                getStyleClass().clear();
                getStyleClass().add("thought-box");
                setDialogueSize();
            }
        }
    }

    private void setDialogueSize() {
        if (this.getText().length() < 50) {
            getStyleClass().add("small-dialogue");
        } else if (this.getText().length() < 100 && this.getText().length() > 50) {
            getStyleClass().add("medium-dialogue");
        } else {
            getStyleClass().add("large-dialogue");
        }
    }
}
