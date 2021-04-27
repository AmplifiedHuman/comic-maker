package ie.ucd.apes.adapters.character;

import javax.xml.bind.UnmarshalException;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class FacingAdapter extends XmlAdapter<String, Boolean> {
    @Override
    public Boolean unmarshal(String s) throws UnmarshalException {
        if (s == null) {
            return null;
        } else if (!s.equalsIgnoreCase("left") && !s.equalsIgnoreCase("right")) {
            throw new UnmarshalException("[Error] Cannot unmarshal facing/orientation.");
        }
        return s.equalsIgnoreCase("left");
    }

    @Override
    public String marshal(Boolean isFlipped) {
        return isFlipped ? "left" : "right";
    }
}
