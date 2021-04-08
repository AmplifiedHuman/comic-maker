package ie.ucd.apes.entity;

import java.util.Objects;

public class Narrative implements Cloneable {
    private String text;
    private boolean isVisible;

    public Narrative(String text, boolean isVisible) {
        this.text = text;
        this.isVisible = isVisible;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Narrative narrative = (Narrative) o;
        return isVisible == narrative.isVisible && text.equals(narrative.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, isVisible);
    }
}
