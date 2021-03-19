package ie.ucd.apes.ui;


import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class ScrollingPane extends ScrollPane {
    public ScrollingPane() {
        this.setPrefWidth(800);
        this.setPrefHeight(200);
        this.setMinHeight(200);
        this.isPannable();
        this.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        this.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        this.setFitToHeight(true);
        this.setFitToWidth(true);
        HBox.setHgrow(this, Priority.ALWAYS);
    }
}
