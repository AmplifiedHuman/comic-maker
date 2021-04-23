package ie.ucd.apes.adapters.character;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class PosingAdapter extends XmlAdapter<String, String> {
    @Override
    public String unmarshal(String s) {
        return (s == null || s.isEmpty()) ? "blank" : s + ".png";
    }

    @Override
    public String marshal(String imageFileName) {
        if (imageFileName == null || imageFileName.equals("blank")) {
            return null;
        }
        return imageFileName.substring(0, imageFileName.indexOf("."));
    }
}
