package ie.ucd.apes.entity;

import java.util.Objects;

public class Background {
    private String backgroundImage;

    public Background(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public Background(Background newBackground) {
        this(newBackground.getBackgroundImage());
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Background character = (Background) o;
        return character.backgroundImage.equals(backgroundImage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(backgroundImage);
    }
}
