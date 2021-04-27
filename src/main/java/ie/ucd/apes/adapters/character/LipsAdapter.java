package ie.ucd.apes.adapters.character;

import ie.ucd.apes.utils.ColorUtils;
import javafx.scene.paint.Color;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LipsAdapter extends XmlAdapter<String, Color> {

    @Override
    public Color unmarshal(String s) {
        if (s == null || s.isEmpty() || s.equalsIgnoreCase("default")) {
            return null;
        }
        return ColorUtils.getColorFromHexOrColorName(s);
    }

    @Override
    public String marshal(Color lipsColor) {
        if (lipsColor == null) {
            return null;
        }
        return ColorUtils.toHexString(lipsColor);
    }
}
