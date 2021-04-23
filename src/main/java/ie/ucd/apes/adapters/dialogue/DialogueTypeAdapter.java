package ie.ucd.apes.adapters.dialogue;

import ie.ucd.apes.entity.DialogueType;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DialogueTypeAdapter extends XmlAdapter<String, DialogueType> {
    @Override
    public DialogueType unmarshal(String s) {
        if (s == null) {
            return null;
        }
        switch (s.toUpperCase()) {
            case "SPEECH":
                return DialogueType.SPEECH;
            case "THOUGHT":
                return DialogueType.THOUGHT;
        }
        return null;
    }

    @Override
    public String marshal(DialogueType dialogueType) {
        if (dialogueType == null) {
            return null;
        }
        return dialogueType.toString().toLowerCase();
    }
}