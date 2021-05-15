package ie.ucd.apes.adapters;

import ie.ucd.apes.entity.Background;
import ie.ucd.apes.entity.Constants;
import ie.ucd.apes.io.FileIO;
import ie.ucd.apes.utils.ColorUtils;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class BackgroundAdapter extends XmlAdapter<String, Background> {
    @Override
    public Background unmarshal(String s) {
        if (s == null || s.isEmpty()) {
            return new Background(Constants.BLANK_IMAGE);
        }
        s = s.toLowerCase();
        if (ColorUtils.getColorFromHexOrColorName(s) != null) {
            return new Background(s);
        } else if (!FileIO.isValidBackground(s)) {
            return new Background("");
        }
        return new Background(s + ".png");
    }

    @Override
    public String marshal(Background background) {
        if (background == null || background.getBackgroundString().equals(Constants.BLANK_IMAGE)) {
            return null;
        } else if (background.getBackgroundString().endsWith(".png")) {
            return background.getBackgroundString().substring(0, background.getBackgroundString().indexOf("."));
        }
        return background.getBackgroundString();
    }
}
