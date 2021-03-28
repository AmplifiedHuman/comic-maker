package ie.ucd.apes.ui;

import ie.ucd.apes.controller.StageController;
import ie.ucd.apes.entity.Character;
import ie.ucd.apes.entity.Constants;
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

        Character characterLeft = new Character(Constants.BLANK_IMAGE, false, false);
        Character characterRight = new Character(Constants.BLANK_IMAGE, true, false);
        StageController stageController = new StageController(characterLeft, characterRight);

        StagePane stagePane = new StagePane(stageController);
        ColorPane colorPane = new ColorPane(stagePane);
        OptionsPane optionsPane = new OptionsPane(stagePane);
        stagePane.setColorPane(colorPane);
        hbox.getChildren().add(stagePane);
        vbox.getChildren().add(colorPane);
        vbox.getChildren().add(optionsPane);
        HBox.setMargin(vbox, new Insets(0, 30, 0, 0));
        hbox.getChildren().add(vbox);
        this.getChildren().add(hbox);
        this.getChildren().add(new ScrollingPane());
    }
}
