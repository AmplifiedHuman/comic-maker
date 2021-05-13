package ie.ucd.apes.ui;

import javafx.scene.control.Label;
import javafx.scene.text.TextAlignment;

public class NarrativeBar extends Label {
    public NarrativeBar(String text) {
        setText(text);
        this.setMaxWidth(1240);
        this.setMaxHeight(150);
        this.setWrapText(true);
        this.setTextAlignment(TextAlignment.CENTER);
        getStyleClass().clear();
        getStyleClass().add("narrative-bar");
    }

    public void setNarrativeSizeTop() {
        if (this.getText().length() < 70) {
            getStyleClass().clear();
            getStyleClass().add("narrative-bar");
            getStyleClass().add("narrative-bar-top-large");
        } else {
            getStyleClass().clear();
            getStyleClass().add("narrative-bar");
            getStyleClass().add("narrative-bar-top-small");
        }
    }

    public void setNarrativeSizeBottom() {
        if (this.getText().length() < 70) {
            getStyleClass().clear();
            getStyleClass().add("narrative-bar");
            getStyleClass().add("narrative-bar-bottom-large");
        } else {
            getStyleClass().clear();
            getStyleClass().add("narrative-bar");
            getStyleClass().add("narrative-bar-bottom-small");
        }
    }
}
