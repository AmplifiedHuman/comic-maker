package ie.ucd.apes.ui;

import ie.ucd.apes.controller.CharacterController;
import ie.ucd.apes.controller.DialogueController;
import ie.ucd.apes.controller.NarrativeController;
import ie.ucd.apes.controller.PanelController;
import ie.ucd.apes.entity.Character;
import ie.ucd.apes.entity.*;
import ie.ucd.apes.ui.stage.CharacterView;
import ie.ucd.apes.ui.stage.DialogueView;
import ie.ucd.apes.ui.stage.NarrativeView;
import ie.ucd.apes.ui.stage.StageView;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;


public class Layout extends VBox {
    public Layout() {
        this.getChildren().add(new TopMenuBar());
        this.getStyleClass().add("background");
        VBox vbox = new VBox(16);
        HBox hbox = new HBox(50);
        HBox.setHgrow(this, Priority.ALWAYS);

        Narrative narrativeTop = new Narrative("CLICK HERE TO EDIT TOP TEXT", false);
        Narrative narrativeBottom = new Narrative("CLICK HERE TO EDIT BOTTOM TEXT", false);
        NarrativeController narrativeController = new NarrativeController(narrativeTop, narrativeBottom);
        NarrativeView narrativeView = new NarrativeView(narrativeController);

        Character characterLeft = new Character(Constants.BLANK_IMAGE, false, false);
        Character characterRight = new Character(Constants.BLANK_IMAGE, true, false);
        CharacterController characterController = new CharacterController(characterLeft, characterRight);
        CharacterView characterView = new CharacterView(characterController);

        Dialogue dialogueLeft = new Dialogue("", false, DialogueType.SPEECH);
        Dialogue dialogueRight = new Dialogue("", false, DialogueType.SPEECH);
        DialogueController dialogueController = new DialogueController(dialogueLeft, dialogueRight);
        DialogueView dialogueView = new DialogueView(dialogueController, characterView);

        PanelController panelController = new PanelController(characterController, dialogueController,
                narrativeController);
        StageView stageView = new StageView(characterView, dialogueView, narrativeView);
        ScrollingPane scrollingPane = new ScrollingPane(panelController, stageView);
        ColorPane colorPane = new ColorPane(characterView);
        characterView.setColorPane(colorPane);
        OptionsPane optionsPane = new OptionsPane(characterView, dialogueView, narrativeView, scrollingPane);

        hbox.getChildren().add(stageView);
        vbox.getChildren().add(colorPane);
        vbox.getChildren().add(optionsPane);
        HBox.setMargin(vbox, new Insets(0, 50, 0, 0));
        hbox.getChildren().add(vbox);
        this.getChildren().add(hbox);
        this.getChildren().add(scrollingPane);
    }
}
