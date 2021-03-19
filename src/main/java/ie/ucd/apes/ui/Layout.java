package ie.ucd.apes.ui;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class Layout extends VBox {
    public Layout(){
        // TODO: add components here
        this.getChildren().add(new TopMenuBar());
        this.getChildren().add(new StagePane());
        this.getChildren().add(new ColorPane());
        this.getChildren().add(new OptionsPane());
        this.getChildren().add(new ScrollingPane());

    }
}
