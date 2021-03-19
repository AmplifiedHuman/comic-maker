package ie.ucd.apes.ui;


import javafx.scene.control.ScrollPane;

public class ScrollingPane extends ScrollPane {
    public ScrollingPane() {
        this.setPrefWidth(800);
        this.setPrefHeight(250);
        this.isPannable();
        this.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        this.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        this.setFitToHeight(true);
    }
}
