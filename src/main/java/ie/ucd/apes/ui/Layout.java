package ie.ucd.apes.ui;

import ie.ucd.apes.controller.CharacterController;
import ie.ucd.apes.controller.DialogueController;
import ie.ucd.apes.controller.NarrativeBarController;
import ie.ucd.apes.entity.Character;
import ie.ucd.apes.entity.Constants;
import ie.ucd.apes.entity.Dialogue;
import ie.ucd.apes.entity.DialogueType;
import ie.ucd.apes.entity.Narrative;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;


public class Layout extends VBox {
    public Layout() {
        this.getChildren().add(new TopMenuBar());
        this.getStyleClass().add("background");
        VBox vbox = new VBox(16);
        HBox hbox = new HBox(50);
        HBox.setHgrow(this, Priority.ALWAYS);

        Narrative narrativeTop = new Narrative("CLICK HERE TO EDIT TEXT", false);
        Narrative narrativeBottom = new Narrative("CLICK HERE TO EDIT TEXT", false);
        NarrativeBarController narrativeBarController = new NarrativeBarController(narrativeTop, narrativeBottom);
        
        Character characterLeft = new Character(Constants.BLANK_IMAGE, false, false);
        Character characterRight = new Character(Constants.BLANK_IMAGE, true, false);
        CharacterController characterController = new CharacterController(characterLeft, characterRight);

        Dialogue dialogueLeft = new Dialogue("", false, DialogueType.SPEECH);
        Dialogue dialogueRight = new Dialogue("", false, DialogueType.SPEECH);
        DialogueController dialogueController = new DialogueController(dialogueLeft, dialogueRight);

        StagePane stagePane = new StagePane(characterController, dialogueController, narrativeBarController);
        ColorPane colorPane = new ColorPane(stagePane);
        OptionsPane optionsPane = new OptionsPane(stagePane);
        stagePane.setColorPane(colorPane);
        hbox.getChildren().add(stagePane);
        vbox.getChildren().add(colorPane);
        vbox.getChildren().add(optionsPane);
        HBox.setMargin(vbox, new Insets(0, 50, 0, 0));
        hbox.getChildren().add(vbox);
        this.getChildren().add(hbox);
//        this.getChildren().add(new ScrollingPane());
    }
}
