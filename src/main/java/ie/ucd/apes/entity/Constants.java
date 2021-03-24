package ie.ucd.apes.entity;

import ie.ucd.apes.utils.ColorChange;
import javafx.scene.paint.Color;

public class Constants {
    public static final String CHARACTER_FOLDER = "characters";
    public static final String BLANK_IMAGE = "blank";
    // colors
    public static final Color DEFAULT_WIG_COLOR = ColorChange.generateColor(240, 255, 0);
    public static final Color DEFAULT_SKIN_COLOR = ColorChange.generateColor(255,232,216);
    public static final Color RIBBON_COLOR = ColorChange.generateColor(236, 180, 181);
    public static final Color LIPS_COLOR = ColorChange.generateColor(255, 0, 0);

    // replacement colours
    public static final Color REPLACEMENT_WIG_COLOR = ColorChange.generateColor(255, 255, 254);
    public static final Color REPLACEMENT_RIBBON_COLOR = ColorChange.generateColor(255, 254, 255);
    public static final Color REPLACEMENT_LIPS_COLOR = ColorChange.generateColor(255,232,215);
}
