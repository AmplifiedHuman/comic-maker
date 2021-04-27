package ie.ucd.apes.adapters.dialogue;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class VisibleAdapter extends XmlAdapter<String, Boolean> {
    @Override
    public Boolean unmarshal(String s) {
        if (s == null || s.isEmpty()) {
            return true;
        }
        switch (s.toLowerCase()) {
            case "true":
                return true;
            case "false":
                return false;
        }
        return null;
    }

    @Override
    public String marshal(Boolean isVisible) {
        return isVisible ? "true" : "false";
    }
}