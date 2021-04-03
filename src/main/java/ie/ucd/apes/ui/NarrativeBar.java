package ie.ucd.apes.ui;

import javafx.scene.control.Label;
public class NarrativeBar extends Label {
    public NarrativeBar(String text) {
        setText(text);
        this.setMaxWidth(620);
        this.setMaxHeight(150);
        this.setWrapText(true);
    }

    public void setNarrativeStyle(){
        getStyleClass().clear();
        getStyleClass().add("speech-box");
    }
}
