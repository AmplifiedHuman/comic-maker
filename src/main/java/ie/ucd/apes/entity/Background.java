package ie.ucd.apes.entity;

import java.util.Objects;

public class Background {
    private String backgroundString;

    public Background(String backgroundString) {
        this.backgroundString = backgroundString;
    }

    public Background(Background newBackground) {
        this(newBackground.getBackgroundString());
    }

    public String getBackgroundString() {
        return backgroundString;
    }

    public void setBackgroundString(String backgroundString) {
        this.backgroundString = backgroundString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Background character = (Background) o;
        return character.backgroundString.equals(backgroundString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(backgroundString);
    }
}
