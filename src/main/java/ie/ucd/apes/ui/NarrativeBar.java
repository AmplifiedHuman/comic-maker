package ie.ucd.apes.ui;

import javafx.scene.control.Label;
import javafx.scene.layout.Region;

public class NarrativeBar extends Label {
    public NarrativeBar(String text) {
        setText(text);
        this.setMaxWidth(1240);
        this.setMaxHeight(150);
        this.setWrapText(true);
        setMinHeight(Region.USE_PREF_SIZE);
        getStyleClass().clear();
        getStyleClass().add("narrative-bar");
    }
}
