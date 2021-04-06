package ie.ucd.apes.entity;

public class PanelState {
    private Character characterLeft;
    private Character characterRight;
    private Dialogue dialogueLeft;
    private Dialogue dialogueRight;
    private Narrative narrativeTop;
    private Narrative narrativeBottom;

    public PanelState() {
    }

    public Character getCharacterLeft() {
        return characterLeft;
    }

    public void setCharacterLeft(Character characterLeft) {
        this.characterLeft = characterLeft;
    }

    public Character getCharacterRight() {
        return characterRight;
    }

    public void setCharacterRight(Character characterRight) {
        this.characterRight = characterRight;
    }

    public Dialogue getDialogueLeft() {
        return dialogueLeft;
    }

    public void setDialogueLeft(Dialogue dialogueLeft) {
        this.dialogueLeft = dialogueLeft;
    }

    public Dialogue getDialogueRight() {
        return dialogueRight;
    }

    public void setDialogueRight(Dialogue dialogueRight) {
        this.dialogueRight = dialogueRight;
    }

    public Narrative getNarrativeTop() {
        return narrativeTop;
    }

    public void setNarrativeTop(Narrative narrativeTop) {
        this.narrativeTop = narrativeTop;
    }

    public Narrative getNarrativeBottom() {
        return narrativeBottom;
    }

    public void setNarrativeBottom(Narrative narrativeBottom) {
        this.narrativeBottom = narrativeBottom;
    }
}
