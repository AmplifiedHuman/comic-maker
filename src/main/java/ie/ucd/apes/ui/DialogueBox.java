package ie.ucd.apes.ui;

import javafx.scene.layout.HBox;
import javafx.scene.control.TextArea;

public class DialogueBox extends HBox {
    TextArea dialogue;
    public DialogueBox(String text){
        dialogue = new TextArea(text);
        dialogue.setMinWidth(200);
        dialogue.setMinHeight(200);
        dialogue.setWrapText(true);
        this.getChildren().add(dialogue);
    }

    public TextArea getDialogue() {
        return dialogue;
    }

    public void setDialogue(TextArea dialogue) {
        this.dialogue = dialogue;
    }
}
