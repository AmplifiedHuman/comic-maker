package ie.ucd.apes.controller;

import ie.ucd.apes.entity.Dialogue;
import ie.ucd.apes.entity.DialogueType;
import ie.ucd.apes.entity.Selection;

public class DialogueController {
    private final Dialogue dialogueLeft;
    private final Dialogue dialogueRight;

    public DialogueController(Dialogue dialogueLeft, Dialogue dialogueRight) {
        this.dialogueLeft = dialogueLeft;
        this.dialogueRight = dialogueRight;
    }

    public void toggleDialogue(Selection selection) {
        Dialogue dialogue = getDialogue(selection);
        dialogue.setVisible(!dialogue.isVisible());
    }

    public boolean isVisible(Selection selection) {
        return getDialogue(selection).isVisible();
    }

    public void setDialogueText(Selection selection, String text) {
        getDialogue(selection).setText(text);
    }

    public String getDialogueText(Selection selection) {
        return getDialogue(selection).getText();
    }

    public DialogueType getDialogueType(Selection selection) {
        return getDialogue(selection).getDialogueType();
    }

    public void setDialogueType(Selection selection, DialogueType dialogueType) {
        getDialogue(selection).setDialogueType(dialogueType);
    }

    private Dialogue getDialogue(Selection selection) {
        return selection.equals(Selection.IS_LEFT) ? dialogueLeft : dialogueRight;
    }
}
