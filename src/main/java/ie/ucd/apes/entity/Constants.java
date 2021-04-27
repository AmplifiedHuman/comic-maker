package ie.ucd.apes.entity;

import ie.ucd.apes.utils.ColorUtils;
import javafx.scene.paint.Color;

public class Constants {
    public static final String CHARACTER_FOLDER = "characters";
    public static final String BLANK_IMAGE = "blank";
    // colors
    public static final Color DEFAULT_WIG_COLOR = ColorUtils.generateColor(240, 255, 0);
    public static final Color DEFAULT_HAIR_COLOR = ColorUtils.generateColor(249, 255, 0);
    public static final Color DEFAULT_SKIN_COLOR = ColorUtils.generateColor(255, 232, 216);
    public static final Color RIBBON_COLOR = ColorUtils.generateColor(236, 180, 181);
    public static final Color LIPS_COLOR = ColorUtils.generateColor(255, 0, 0);
    public static final Color REPLACEMENT_LIPS_COLOR = Color.LIGHTPINK;

    // replacement colours
    public static final Color REPLACEMENT_WIG_COLOR = ColorUtils.generateColor(255, 255, 254);
    public static final Color REPLACEMENT_RIBBON_COLOR = ColorUtils.generateColor(255, 254, 255);

    // Default properties / objects
    public static final Character DEFAULT_LEFT_CHARACTER = new Character(Constants.BLANK_IMAGE, false, false);
    public static final Character DEFAULT_RIGHT_CHARACTER = new Character(Constants.BLANK_IMAGE, true, false);
    public static final Dialogue DEFAULT_DIALOGUE = new Dialogue("", false, DialogueType.SPEECH);
    public static final Narrative DEFAULT_TOP_NARRATIVE = new Narrative("CLICK HERE TO EDIT TOP TEXT", false);
    public static final Narrative DEFAULT_BOTTOM_NARRATIVE = new Narrative("CLICK HERE TO EDIT BOTTOM TEXT", false);
}
