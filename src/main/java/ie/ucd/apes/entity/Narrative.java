package ie.ucd.apes.entity;

public class Narrative {
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
}
