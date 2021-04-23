package ie.ucd.apes.adapters.character;

import javax.xml.bind.UnmarshalException;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class AppearanceAdapter extends XmlAdapter<String, Boolean> {
    @Override
    public Boolean unmarshal(String s) throws UnmarshalException {
        if (!s.equalsIgnoreCase("male") && !s.equalsIgnoreCase("female")) {
            throw new UnmarshalException("[Error] Cannot unmarshal appearance/gender.");
        }
        return s.equalsIgnoreCase("male");
    }

    @Override
    public String marshal(Boolean isMale) {
        return isMale ? "male" : "female";
    }
}
