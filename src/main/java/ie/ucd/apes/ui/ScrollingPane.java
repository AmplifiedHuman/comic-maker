package ie.ucd.apes.ui;


import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class ScrollingPane extends ScrollPane {
    private final HBox container;

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
        VBox.setVgrow(this, Priority.ALWAYS);
        container = new HBox();
        setContent(container);
    }

    public void addScene(Image image) {
        container.getChildren().add(0, new CapturedScene(image));
    }
}
