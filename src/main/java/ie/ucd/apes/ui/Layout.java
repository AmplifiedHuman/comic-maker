package ie.ucd.apes.ui;

import ie.ucd.apes.controller.*;
import ie.ucd.apes.entity.*;
import ie.ucd.apes.entity.Character;
import ie.ucd.apes.ui.stage.CharacterView;
import ie.ucd.apes.ui.stage.DialogueView;
import ie.ucd.apes.ui.stage.NarrativeView;
import ie.ucd.apes.ui.stage.StageView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Layout extends VBox {
    public Layout(Stage stage) {
        this.getStyleClass().add("background");
        VBox vbox = new VBox(16);
        HBox hbox = new HBox(50);

        Narrative narrativeTop = new Narrative(Constants.DEFAULT_TOP_NARRATIVE);
        Narrative narrativeBottom = new Narrative(Constants.DEFAULT_BOTTOM_NARRATIVE);
        NarrativeController narrativeController = new NarrativeController(narrativeTop, narrativeBottom);
        NarrativeView narrativeView = new NarrativeView(narrativeController);

        Background background = new Background(Constants.BLANK_IMAGE);
        BackgroundController backgroundController = new BackgroundController(background);

        Character characterLeft = new Character(Constants.DEFAULT_LEFT_CHARACTER);
        Character characterRight = new Character(Constants.DEFAULT_RIGHT_CHARACTER);
        CharacterController characterController = new CharacterController(characterLeft, characterRight);
        CharacterView characterView = new CharacterView(characterController);

        Dialogue dialogueLeft = new Dialogue(Constants.DEFAULT_DIALOGUE);
        Dialogue dialogueRight = new Dialogue(Constants.DEFAULT_DIALOGUE);
        DialogueController dialogueController = new DialogueController(dialogueLeft, dialogueRight);
        DialogueView dialogueView = new DialogueView(dialogueController, characterView);

        StageView stageView = new StageView(characterView, dialogueView, narrativeView, backgroundController);
        PanelController panelController = new PanelController(characterController, dialogueController,
                narrativeController, backgroundController, stageView);
        ScrollingPane scrollingPane = new ScrollingPane(panelController, stageView);
        panelController.setScrollingPane(scrollingPane);
        characterView.setScrollingPane(scrollingPane);
        ColorPane colorPane = new ColorPane(characterView);
        characterView.setColorPane(colorPane);
        OptionsPane optionsPane = new OptionsPane(characterView, dialogueView, narrativeView, scrollingPane, stageView);
        characterView.setOptionsPane(optionsPane);
        stageView.setOptionsPane(optionsPane);
        TopMenuBar topMenuBar = new TopMenuBar(stage, scrollingPane, panelController);

        hbox.getChildren().add(stageView);
        vbox.getChildren().add(colorPane);
        vbox.getChildren().add(optionsPane);
        HBox.setMargin(vbox, new Insets(5, 50, 0, 0));
        HBox.setMargin(stageView, new Insets(5, 0, 5, 85));
        hbox.getChildren().add(vbox);
        this.getChildren().add(topMenuBar);
        this.getChildren().add(hbox);
        this.getChildren().add(scrollingPane);
        hbox.setAlignment(Pos.CENTER);
        setAlignment(Pos.CENTER);
    }
}
