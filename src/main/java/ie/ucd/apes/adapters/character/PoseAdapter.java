package ie.ucd.apes.adapters.character;

import ie.ucd.apes.io.FileIO;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class PoseAdapter extends XmlAdapter<String, String> {
    @Override
    public String unmarshal(String s) {
        if (s == null || s.isEmpty()) {
            return "blank";
        } else if (!FileIO.isValidCharacterPose(s)) {
            return null;
        }
        return s + ".png";
    }

    @Override
    public String marshal(String imageFileName) {
        if (imageFileName == null || imageFileName.equals("blank")) {
            return null;
        }
        return imageFileName.substring(0, imageFileName.indexOf("."));
    }
}
