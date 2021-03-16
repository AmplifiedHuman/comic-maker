package ie.ucd.apes.ui;

import javafx.scene.layout.VBox;

public class ColorPane extends VBox {
    public ColorPane() {
        this.getChildren().add(new ColorSelector("Skin Color"));
        this.getChildren().add(new ColorSelector("Hair Color"));
    }
}
