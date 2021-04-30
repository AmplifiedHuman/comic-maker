package ie.ucd.apes.ui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.text.TextAlignment;

public class NarrativeBar extends Label {
    public NarrativeBar(String text) {
        setText(text);
        this.setMaxWidth(1240);
        this.setMaxHeight(150);
        this.setWrapText(true);
        this.setTextAlignment(TextAlignment.CENTER);
        setMinHeight(Region.USE_PREF_SIZE);
        getStyleClass().clear();
        getStyleClass().add("narrative-bar");
    }
}
