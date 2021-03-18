package ie.ucd.apes.ui;

import javafx.scene.layout.VBox;

public class Layout extends VBox {
    public Layout() {
        // TODO: add components here
        this.getChildren().add(new TopMenuBar());
        this.getChildren().add(new ColorPane());
        this.getChildren().add(new OptionsPane());
    }
}
