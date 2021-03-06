import ie.ucd.apes.entity.Constants;
import ie.ucd.apes.utils.ColorUtils;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

public class ColorTest {
    @Test
    public void testToHexString() {
        Assertions.assertEquals("#FFE8D8", ColorUtils.toHexString(Constants.DEFAULT_SKIN_COLOR));
    }

    @Test
    public void testToFXColor() {
        Assertions.assertEquals(Constants.DEFAULT_SKIN_COLOR, ColorUtils.toFXColor("#FFE8D8"));
    }

    @Test
    public void testGetColorFromHexOrColorName() {
        Assertions.assertEquals(Color.PINK, ColorUtils.getColorFromHexOrColorName("pink"));
        Assertions.assertEquals(Color.web("#FFE8D8"), ColorUtils.getColorFromHexOrColorName("#FFE8D8"));
    }

    @Test
    public void testRegex() {
        Assertions.assertTrue(Pattern.matches("^#[0-9A-F]{6}$", "#FFE8D8"));
        Assertions.assertFalse(Pattern.matches("^#[0-9A-F]{6}$", "#FFED8"));
        Assertions.assertFalse(Pattern.matches("^#[0-9A-F]{6}$", "FFFED8"));
    }
}
