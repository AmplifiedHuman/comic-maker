package ie.ucd.apes.ui;

import javafx.scene.control.TextArea;

public class NarrativeBar extends TextArea {
    public NarrativeBar(String placeholder) {
        super(placeholder);
        this.setMaxWidth(620);
        this.setMaxHeight(150);
        this.setWrapText(true);
    }
}
