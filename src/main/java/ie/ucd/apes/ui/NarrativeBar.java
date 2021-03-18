package ie.ucd.apes.ui;

import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;

public class NarrativeBar extends HBox {
    TextArea narrative;

    public NarrativeBar() {
        narrative = new TextArea("This is for narration");
        narrative.setMaxHeight(70);
        narrative.setMinWidth(500);
        this.getChildren().add(narrative);
    }
}
