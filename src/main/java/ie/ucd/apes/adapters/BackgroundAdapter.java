package ie.ucd.apes.adapters;

import ie.ucd.apes.entity.Background;
import ie.ucd.apes.entity.Constants;
import ie.ucd.apes.io.FileIO;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class BackgroundAdapter extends XmlAdapter<String, Background> {
    @Override
    public Background unmarshal(String s) {
        if (s == null || s.isEmpty()) {
            return new Background(Constants.BLANK_IMAGE);
        } else if (!FileIO.isValidBackground(s)) {
            return new Background("");
        }
        return new Background(s + ".png");
    }

    @Override
    public String marshal(Background background) {
        if (background == null || background.getBackgroundImage().equals(Constants.BLANK_IMAGE)) {
            return null;
        }
        return background.getBackgroundImage().substring(0, background.getBackgroundImage().indexOf("."));
    }
}
