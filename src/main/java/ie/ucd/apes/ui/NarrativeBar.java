package ie.ucd.apes.ui;

import javafx.scene.control.Label;

public class NarrativeBar extends Label {
    public NarrativeBar(String text) {
        setText(text);
        this.setMaxWidth(1240);
        this.setMaxHeight(150);
        this.setWrapText(true);
        getStyleClass().clear();
        getStyleClass().add("narrative-bar");
    }
}
