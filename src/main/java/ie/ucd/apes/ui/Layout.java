package ie.ucd.apes.ui;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;


public class Layout extends VBox {
    public Layout() {
        // TODO: add components here
        this.getChildren().add(new TopMenuBar());
        VBox vbox = new VBox(16);
        HBox hbox = new HBox(50);
        HBox.setHgrow(this, Priority.ALWAYS);
        hbox.getChildren().add(new StagePane());
        vbox.getChildren().add(new ColorPane());
        vbox.getChildren().add(new OptionsPane());
        HBox.setMargin(vbox, new Insets(0, 30, 0, 0));
        hbox.getChildren().add(vbox);
        this.getChildren().add(hbox);
        this.getChildren().add(new ScrollingPane());
    }
}
