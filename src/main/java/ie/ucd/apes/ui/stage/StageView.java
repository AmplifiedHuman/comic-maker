package ie.ucd.apes.ui.stage;

import ie.ucd.apes.controller.BackgroundController;
import ie.ucd.apes.entity.Constants;
import ie.ucd.apes.entity.Selection;
import ie.ucd.apes.ui.popup.DialogueBox;
import ie.ucd.apes.ui.NarrativeBar;
import ie.ucd.apes.ui.OptionsPane;
import javafx.geometry.*;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.Objects;


public class StageView extends VBox {
    private final CharacterView characterView;
    private final DialogueView dialogueView;
    private final NarrativeView narrativeView;
    private final BackgroundController backgroundController;
    private OptionsPane optionsPane;

    public StageView(CharacterView characterView, DialogueView dialogueView, NarrativeView narrativeView,
                     BackgroundController backgroundController) {
        this.characterView = characterView;
        this.dialogueView = dialogueView;
        this.narrativeView = narrativeView;
        this.backgroundController = backgroundController;
        initView();
    }

    private void initView() {
        GridPane tiles = new GridPane();
        setAlignment(Pos.CENTER_RIGHT);
        setMinWidth(620);
        setMaxWidth(620);
        tiles.setHgap(49);
        setMinHeight(520);
        setMaxHeight(520);

        NarrativeBar narrativeBarTop = narrativeView.getNarrativeBar(Selection.IS_TOP);
        NarrativeBar narrativeBarBottom = narrativeView.getNarrativeBar(Selection.IS_BOTTOM);
        tiles.add(narrativeBarTop, 0, 0, 3, 1);
        tiles.add(narrativeBarBottom, 0, 3, 3, 1);

        DialogueBox leftDialogueBox = dialogueView.getDialogueBox(Selection.IS_LEFT);
        DialogueBox rightDialogueBox = dialogueView.getDialogueBox(Selection.IS_RIGHT);
        GridPane.setMargin(leftDialogueBox, new Insets(0,0,0,5));
        GridPane.setMargin(rightDialogueBox, new Insets(0,5,0,0));
        tiles.add(leftDialogueBox, 0, 1);
        tiles.add(rightDialogueBox, 2, 1);
        GridPane.setValignment(leftDialogueBox, VPos.BOTTOM);
        GridPane.setValignment(rightDialogueBox, VPos.BOTTOM);
        GridPane.setHalignment(leftDialogueBox, HPos.CENTER);
        GridPane.setHalignment(rightDialogueBox, HPos.CENTER);

        // character models
        HBox box = new HBox();
        HBox.setHgrow(box, Priority.ALWAYS);
        tiles.add(characterView.getCharacterImage(Selection.IS_LEFT), 0, 2);
        tiles.add(box, 1, 2);
        tiles.add(characterView.getCharacterImage(Selection.IS_RIGHT), 2, 2);
        GridPane.setMargin(characterView.getCharacterImage(Selection.IS_LEFT), new Insets(0, 0, 15, 0));
        GridPane.setMargin(characterView.getCharacterImage(Selection.IS_RIGHT), new Insets(0, 0, 15, 0));
        // background
        setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        tiles.setPadding(new Insets(0, 10, 0, 10));

        getChildren().add(tiles);
        HBox.setHgrow(this, Priority.ALWAYS);
    }

    public void renderBackgroundImage() {
        String backgroundName = backgroundController.getBackgroundString();
        if (optionsPane != null) {
            optionsPane.setBackgroundsListView(backgroundName);
        }
        if(!backgroundName.contains(".png")) {
            switch (backgroundName){
                case "blue":
                    setBackground(new Background(new BackgroundFill(Color.web("41B0F6"), CornerRadii.EMPTY, Insets.EMPTY)));
                    break;
                case "green":
                    setBackground(new Background(new BackgroundFill(Color.web("89F94F"), CornerRadii.EMPTY, Insets.EMPTY)));
                    break;
                case "yellow":
                    setBackground(new Background(new BackgroundFill(Color.web("F9F789"), CornerRadii.EMPTY, Insets.EMPTY)));
                    break;
                case "red":
                    setBackground(new Background(new BackgroundFill(Color.web("FF634D"), CornerRadii.EMPTY, Insets.EMPTY)));
                    break;
                case "pink":
                    setBackground(new Background(new BackgroundFill(Color.web("FF8EC6"), CornerRadii.EMPTY, Insets.EMPTY)));
                    break;
                default:
                    setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
            }
        }
        else {
            Image backgroundImage = new Image(Objects.requireNonNull(getClass()
                    .getResourceAsStream(String.format("/%s/%s", Constants.BACKGROUNDS_FOLDER, backgroundName))));
            setBackground(new Background(new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        }
    }

    public void setOptionsPane(OptionsPane optionsPane) {
        this.optionsPane = optionsPane;
    }

    public void render() {
        characterView.renderCharacters();
        dialogueView.renderDialogues();
        narrativeView.renderNarrativeBars();
        renderBackgroundImage();
    }

    public WritableImage takeScreenshot() {
        return snapshot(new SnapshotParameters(), null);
    }

    public void updateBackgroundImage(String newValue) {
        backgroundController.setBackgroundString(newValue);
        renderBackgroundImage();
    }
}
