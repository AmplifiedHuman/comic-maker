package ie.ucd.apes.adapters.character;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class AppearanceAdapter extends XmlAdapter<String, Boolean> {
    @Override
    public Boolean unmarshal(String s) {
        if (s == null || !(s.equalsIgnoreCase("male") || s.equalsIgnoreCase("female"))) {
            return null;
        }
        return s.equalsIgnoreCase("male");
    }

    @Override
    public String marshal(Boolean isMale) {
        return isMale ? "male" : "female";
    }
}
