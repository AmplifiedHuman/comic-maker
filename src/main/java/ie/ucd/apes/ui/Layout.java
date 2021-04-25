package ie.ucd.apes.ui;

import ie.ucd.apes.controller.CharacterController;
import ie.ucd.apes.controller.DialogueController;
import ie.ucd.apes.controller.NarrativeController;
import ie.ucd.apes.controller.PanelController;
import ie.ucd.apes.entity.Character;
import ie.ucd.apes.entity.Constants;
import ie.ucd.apes.entity.Dialogue;
import ie.ucd.apes.entity.Narrative;
import ie.ucd.apes.ui.stage.CharacterView;
import ie.ucd.apes.ui.stage.DialogueView;
import ie.ucd.apes.ui.stage.NarrativeView;
import ie.ucd.apes.ui.stage.StageView;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Layout extends VBox {
    public Layout(Stage stage) {
        this.getStyleClass().add("background");
        VBox vbox = new VBox(16);
        HBox hbox = new HBox(50);
        HBox.setHgrow(this, Priority.ALWAYS);

        Narrative narrativeTop = new Narrative(Constants.DEFAULT_TOP_NARRATIVE);
        Narrative narrativeBottom = new Narrative(Constants.DEFAULT_BOTTOM_NARRATIVE);
        NarrativeController narrativeController = new NarrativeController(narrativeTop, narrativeBottom);
        NarrativeView narrativeView = new NarrativeView(narrativeController);

        Character characterLeft = new Character(Constants.DEFAULT_LEFT_CHARACTER);
        Character characterRight = new Character(Constants.DEFAULT_RIGHT_CHARACTER);
        CharacterController characterController = new CharacterController(characterLeft, characterRight);
        CharacterView characterView = new CharacterView(characterController);

        Dialogue dialogueLeft = new Dialogue(Constants.DEFAULT_DIALOGUE);
        Dialogue dialogueRight = new Dialogue(Constants.DEFAULT_DIALOGUE);
        DialogueController dialogueController = new DialogueController(dialogueLeft, dialogueRight);
        DialogueView dialogueView = new DialogueView(dialogueController, characterView);

        StageView stageView = new StageView(characterView, dialogueView, narrativeView);
        PanelController panelController = new PanelController(characterController, dialogueController,
                narrativeController, stageView);
        ScrollingPane scrollingPane = new ScrollingPane(panelController, stageView);
        panelController.setScrollingPane(scrollingPane);
        characterView.setScrollingPane(scrollingPane);
        ColorPane colorPane = new ColorPane(characterView);
        characterView.setColorPane(colorPane);
        OptionsPane optionsPane = new OptionsPane(characterView, dialogueView, narrativeView, scrollingPane);
        characterView.setOptionsPane(optionsPane);
        TopMenuBar topMenuBar = new TopMenuBar(stage, scrollingPane, panelController);

        HBox spacer = new HBox();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        hbox.getChildren().add(stageView);
        hbox.getChildren().add(spacer);
        vbox.getChildren().add(colorPane);
        vbox.getChildren().add(optionsPane);
        HBox.setMargin(vbox, new Insets(0, 50, 0, 0));
        hbox.getChildren().add(vbox);
        this.getChildren().add(topMenuBar);
        this.getChildren().add(hbox);
        this.getChildren().add(scrollingPane);
    }
}
