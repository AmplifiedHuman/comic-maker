package ie.ucd.apes.controller;

import ie.ucd.apes.entity.Background;
import ie.ucd.apes.entity.Constants;

public class BackgroundController {
    private Background background;

    public BackgroundController(Background background) {
        this.background = background;
    }

    public Background getBackground() {
        return background;
    }

    public void setBackground(Background background) {
        this.background = background;
    }

    public String getBackgroundString() {
        return background.getBackgroundImage();
    }

    public void setBackgroundString(String newBackground) {
        background.setBackgroundImage(newBackground);
    }

    public void reset() {
        setBackgroundString(Constants.BLANK_IMAGE);
    }

    public boolean isBackgroundDefaultState() {
        return background.getBackgroundImage().equals(Constants.BLANK_IMAGE);
    }

    public boolean equals(Background otherBackground) {
        return background.equals(otherBackground);
    }
}
