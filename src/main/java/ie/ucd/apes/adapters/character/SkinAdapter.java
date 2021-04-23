package ie.ucd.apes.adapters.character;

import ie.ucd.apes.entity.Constants;
import ie.ucd.apes.utils.ColorUtils;
import javafx.scene.paint.Color;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class SkinAdapter extends XmlAdapter<String, Color> {

    @Override
    public Color unmarshal(String s) {
        if (s.equalsIgnoreCase("default")) {
            return Constants.DEFAULT_SKIN_COLOR;
        }
        return ColorUtils.getColorFromHexOrColorName(s);
    }

    @Override
    public String marshal(Color skinColor) {
        if (skinColor.equals(Constants.DEFAULT_SKIN_COLOR)) {
            return "default";
        }
        return ColorUtils.getColorNameOrHex(skinColor);
    }
}
