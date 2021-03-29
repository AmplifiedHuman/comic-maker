package ie.ucd.apes.entity;

public class Dialogue {
    private String text;
    private boolean isVisible;
    private DialogueType dialogueType;

    public Dialogue(String text, boolean isVisible, DialogueType dialogueType) {
        this.text = text;
        this.isVisible = isVisible;
        this.dialogueType = dialogueType;
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

    public DialogueType getDiagolueType() {
        return dialogueType;
    }

    public void setDiagolueType(DialogueType dialogueType) {
        this.dialogueType = dialogueType;
    }
}
