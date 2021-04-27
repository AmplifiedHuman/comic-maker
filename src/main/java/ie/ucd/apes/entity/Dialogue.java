package ie.ucd.apes.entity;

import ie.ucd.apes.adapters.dialogue.DialogueTypeAdapter;
import ie.ucd.apes.adapters.dialogue.VisibleAdapter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Objects;

@XmlRootElement(name = "balloon")
public class Dialogue {
    private String text;
    private Boolean isVisible;
    private DialogueType dialogueType;

    public Dialogue() {
        text = "";
        isVisible = true;
    }

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

    @XmlElement(name = "content")
    public void setText(String text) {
        this.text = text;
    }

    public Boolean getIsVisible() {
        return isVisible;
    }

    @XmlElement(name = "visible")
    @XmlJavaTypeAdapter(VisibleAdapter.class)
    public void setIsVisible(Boolean visible) {
        isVisible = visible;
    }

    public DialogueType getDialogueType() {
        return dialogueType;
    }

    @XmlAttribute(name = "status")
    @XmlJavaTypeAdapter(DialogueTypeAdapter.class)
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
