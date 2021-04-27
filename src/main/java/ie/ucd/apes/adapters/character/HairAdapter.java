package ie.ucd.apes.adapters.character;

import ie.ucd.apes.entity.Constants;
import ie.ucd.apes.utils.ColorUtils;
import javafx.scene.paint.Color;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class HairAdapter extends XmlAdapter<String, Color> {

    @Override
    public Color unmarshal(String s) {
        if (s == null || s.isEmpty()) {
            return null;
        } else if (s.equalsIgnoreCase("default")) {
            return Constants.DEFAULT_HAIR_COLOR;
        }
        return ColorUtils.getColorFromHexOrColorName(s);
    }

    @Override
    public String marshal(Color skinColor) {
        if (skinColor.equals(Constants.DEFAULT_HAIR_COLOR)) {
            return "default";
        }
        return ColorUtils.toHexString(skinColor);
    }
}
