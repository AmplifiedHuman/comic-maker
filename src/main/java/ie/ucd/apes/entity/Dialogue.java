package ie.ucd.apes.entity;

import java.util.Objects;

public class Dialogue {
    private String text;
    private boolean isVisible;
    private DialogueType dialogueType;

    public Dialogue(String text, boolean isVisible, DialogueType dialogueType) {
        this.text = text;
        this.isVisible = isVisible;
        this.dialogueType = dialogueType;
    }

    public Dialogue(Dialogue copy) {
        this.text = copy.text;
        this.isVisible = copy.isVisible;
        this.dialogueType = copy.dialogueType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public DialogueType getDialogueType() {
        return dialogueType;
    }

    public void setDialogueType(DialogueType dialogueType) {
        this.dialogueType = dialogueType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dialogue dialogue = (Dialogue) o;
        return isVisible == dialogue.isVisible && text.equals(dialogue.text) && dialogueType == dialogue.dialogueType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, isVisible, dialogueType);
    }
}
