package ie.ucd.apes.adapters.character;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class FacingAdapter extends XmlAdapter<String, Boolean> {
    @Override
    public Boolean unmarshal(String s) {
        if (s == null || (!s.equalsIgnoreCase("left") && !s.equalsIgnoreCase("right"))) {
            return null;
        }
        return s.equalsIgnoreCase("left");
    }

    @Override
    public String marshal(Boolean isFlipped) {
        return isFlipped ? "left" : "right";
    }
}
