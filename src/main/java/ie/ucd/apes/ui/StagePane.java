package ie.ucd.apes.ui;

import ie.ucd.apes.controller.StageController;
import ie.ucd.apes.entity.CharacterEnum;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;


public class StagePane extends VBox {
    private final StageController stageController;
    private ImageView characterLeftView;
    private ImageView characterRightView;

    public StagePane(StageController stageController) {
        this.stageController = stageController;
        initView();
    }

    private void initView() {
        GridPane tiles = new GridPane();
        DialogueBox labelL, labelR;
        this.getChildren().add(new NarrativeBar("Some sample text for the narrative bar."));
        tiles.setMaxWidth(600);
        tiles.setHgap(20);

        // init labels
        labelL = new DialogueBox("Dialogue text of character on the left");
        labelR = new DialogueBox("Dialogue text of character on the right");

        // dialogue
        tiles.add(labelL, 0, 0);
        tiles.add(labelR, 1, 0);
        GridPane.setHalignment(labelR, HPos.RIGHT);

        // character models
        initCharacters();
        tiles.add(characterLeftView, 0, 2);
        tiles.add(characterRightView, 1, 2);

        // background
        tiles.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        this.getChildren().add(tiles);
        this.getChildren().add(new NarrativeBar("Some sample text for the narrative bar."));
        HBox.setHgrow(this, Priority.ALWAYS);
    }

    private void initCharacters() {
        characterLeftView = new CharacterImage(stageController.renderCharacterImage(CharacterEnum.IS_LEFT));
        characterRightView = new CharacterImage(stageController.renderCharacterImage(CharacterEnum.IS_RIGHT));
        if (stageController.isFlipped(CharacterEnum.IS_LEFT)) {
            characterLeftView.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        }
        if (stageController.isFlipped(CharacterEnum.IS_RIGHT)) {
            characterRightView.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        }
        characterLeftView.setFocusTraversable(true);
        characterRightView.setFocusTraversable(true);

        characterLeftView.setOnMouseClicked((e) -> characterLeftView.requestFocus());
        characterRightView.setOnMouseClicked((e) -> characterRightView.requestFocus());
    }

    public void updateCharacterImage(String newImageName, CharacterEnum characterEnum) {
        stageController.setCharacterImageFileName(newImageName, characterEnum);
        if (characterEnum.equals(CharacterEnum.IS_LEFT)) {
            characterLeftView.setImage(stageController.renderCharacterImage(CharacterEnum.IS_LEFT));
        } else {
            characterRightView.setImage(stageController.renderCharacterImage(CharacterEnum.IS_RIGHT));
        }
    }

    public void flipSelectedCharacterImage() {
        if (characterLeftView.isFocused()) {
            stageController.flipCharacterOrientation(CharacterEnum.IS_LEFT);
            flipOrientation(characterLeftView);
        } else if (characterRightView.isFocused()) {
            stageController.flipCharacterOrientation(CharacterEnum.IS_RIGHT);
            flipOrientation(characterRightView);
        }
    }

    private void flipOrientation(ImageView imageView) {
        if (imageView.getNodeOrientation().equals(NodeOrientation.LEFT_TO_RIGHT)) {
            imageView.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        } else {
            imageView.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        }
    }
}
