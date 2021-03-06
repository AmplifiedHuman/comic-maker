package ie.ucd.apes.controller;

import ie.ucd.apes.entity.Constants;
import ie.ucd.apes.entity.Dialogue;
import ie.ucd.apes.entity.DialogueType;
import ie.ucd.apes.entity.Selection;

public class DialogueController {
    private Dialogue dialogueLeft;
    private Dialogue dialogueRight;

    public DialogueController(Dialogue dialogueLeft, Dialogue dialogueRight) {
        this.dialogueLeft = dialogueLeft;
        this.dialogueRight = dialogueRight;
    }

    public void toggleDialogue(Selection selection) {
        Dialogue dialogue = getDialogue(selection);
        dialogue.setIsVisible(!dialogue.getIsVisible());
        if (!dialogue.getIsVisible()) {
            setDialogueText(selection, "");
        }
    }

    public boolean isVisible(Selection selection) {
        return getDialogue(selection).getIsVisible();
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

    public Dialogue getDialogue(Selection selection) {
        return selection.equals(Selection.IS_LEFT) ? dialogueLeft : dialogueRight;
    }

    public void setDialogue(Selection selection, Dialogue dialogue) {
        if (selection.equals(Selection.IS_LEFT)) {
            dialogueLeft = dialogue;
        } else {
            dialogueRight = dialogue;
        }
    }

    public boolean isDefaultState() {
        return dialogueLeft.equals(Constants.DEFAULT_DIALOGUE)
                && dialogueRight.equals(Constants.DEFAULT_DIALOGUE);
    }

    public void reset() {
        dialogueLeft = new Dialogue(Constants.DEFAULT_DIALOGUE);
        dialogueRight = new Dialogue(Constants.DEFAULT_DIALOGUE);
    }
}
