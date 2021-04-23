package ie.ucd.apes.entity.xml;

import ie.ucd.apes.entity.Character;
import ie.ucd.apes.entity.Dialogue;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "CharacterWrapper")
public class CharacterWrapper {
    private Character character;
    private Dialogue dialogue;

    public CharacterWrapper() {
    }

    public CharacterWrapper(Character character, Dialogue dialogue) {
        this.character = character;
        this.dialogue = dialogue;
    }

    public Character getCharacter() {
        return character;
    }

    @XmlElement(name = "figure")
    public void setCharacter(Character character) {
        this.character = character;
    }

    public Dialogue getDialogue() {
        return dialogue;
    }

    @XmlElement(name = "balloon")
    public void setDialogue(Dialogue dialogue) {
        this.dialogue = dialogue;
    }
}
