package ie.ucd.apes.ui;

import ie.ucd.apes.controller.StageController;
import ie.ucd.apes.entity.CharacterEnum;
import ie.ucd.apes.entity.Constants;
import ie.ucd.apes.entity.PruneLevel;
import ie.ucd.apes.utils.ColorUtils;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;


public class StagePane extends VBox {
    private final StageController stageController;
    private CharacterImage characterLeftView;
    private CharacterImage characterRightView;
    private ColorPane colorPane;

    public StagePane(StageController stageController) {
        this.stageController = stageController;
        this.colorPane = null;
        initView();
    }

    private void initView() {
        GridPane tiles = new GridPane();
        DialogueBox labelL, labelR;
        this.getChildren().add(new NarrativeBar("Some sample text for the narrative bar."));
        tiles.setMaxWidth(600);
        tiles.setHgap(15);

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

        characterLeftView.setOnMouseClicked((e) -> {
            characterLeftView.requestFocus();
            colorPane.setSkinColorSelector(stageController.getSkinColor(CharacterEnum.IS_LEFT));
            colorPane.setHairColorSelector(stageController.getHairColor(CharacterEnum.IS_LEFT));
        });
        characterRightView.setOnMouseClicked((e) -> {
            characterRightView.requestFocus();
            colorPane.setSkinColorSelector(stageController.getSkinColor(CharacterEnum.IS_RIGHT));
            colorPane.setHairColorSelector(stageController.getHairColor(CharacterEnum.IS_RIGHT));
        });
    }

    // handle all rendering except orientation
    public void renderCharacterImage(CharacterEnum characterEnum) {
        if (characterEnum == null) {
            return;
        }
        ImageView imageView = getImageView(characterEnum);
        imageView.setImage(stageController.renderCharacterImage(characterEnum));
        if (imageView.getImage() != null) {
            // render by layers
            renderGender(imageView, characterEnum);
            renderSkinColor(imageView, characterEnum);
            renderHairColor(imageView, characterEnum);
        }
    }

    public void updateCharacterImage(String newImageName, CharacterEnum characterEnum) {
        stageController.resetState(characterEnum);
        stageController.setCharacterImageFileName(newImageName, characterEnum);
        renderCharacterImage(characterEnum);
        ImageView imageView = getImageView(characterEnum);
        colorPane.setSkinColorSelector(stageController.getSkinColor(characterEnum));
        colorPane.setHairColorSelector(stageController.getHairColor(characterEnum));
        getCharacterImage(characterEnum).requestFocus();
        if (stageController.isFlipped(characterEnum)) {
            imageView.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        }
    }

    public void flipSelectedCharacterImage() {
        CharacterEnum characterEnum = getFocusedCharacterEnum();
        if (characterEnum != null) {
            stageController.flipCharacterOrientation(characterEnum);
            flipImageViewOrientation(getImageView(characterEnum));
        }
    }

    public void setColorPane(ColorPane colorPane) {
        this.colorPane = colorPane;
    }

    private void flipImageViewOrientation(ImageView imageView) {
        if (imageView.getNodeOrientation().equals(NodeOrientation.LEFT_TO_RIGHT)) {
            imageView.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        } else {
            imageView.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        }
    }

    public void changeGender() {
        CharacterEnum characterEnum = getFocusedCharacterEnum();
        if (characterEnum != null) {
            stageController.changeGender(characterEnum);
            renderCharacterImage(characterEnum);
        }
    }

    private void renderGender(ImageView imageView, CharacterEnum characterEnum) {
        if (stageController.isMale(characterEnum)) {
            ColorUtils.changeColor(imageView, Constants.DEFAULT_WIG_COLOR, Constants.REPLACEMENT_WIG_COLOR, PruneLevel.LOW);
            ColorUtils.changeColor(imageView, Constants.RIBBON_COLOR, Constants.REPLACEMENT_RIBBON_COLOR, PruneLevel.LOW);
        }
    }

    public void changeSkinColor(Color newSkinColor) {
        CharacterEnum characterEnum = getFocusedCharacterEnum();
        if (characterEnum != null) {
            stageController.setSkinColor(characterEnum, newSkinColor);
            renderCharacterImage(characterEnum);
        }
    }

    private void renderSkinColor(ImageView imageView, CharacterEnum characterEnum) {
        Color characterSkinColor = stageController.getSkinColor(characterEnum);
        ColorUtils.changeColor(imageView, Constants.DEFAULT_SKIN_COLOR, characterSkinColor, PruneLevel.NONE);
        if (stageController.isMale(characterEnum)) {
            ColorUtils.changeColor(imageView, Constants.LIPS_COLOR, characterSkinColor, PruneLevel.MEDIUM);
        }
    }

    public void changeHairColor(Color newHairColor) {
        CharacterEnum characterEnum = getFocusedCharacterEnum();
        if (characterEnum != null) {
            stageController.setHairColor(characterEnum, newHairColor);
            renderCharacterImage(characterEnum);
        }
    }

    private void renderHairColor(ImageView imageView, CharacterEnum characterEnum) {
        Color characterHairColor = stageController.getHairColor(characterEnum);
        ColorUtils.changeColor(imageView, Constants.DEFAULT_HAIR_COLOR, characterHairColor, PruneLevel.LOW);
        if (!stageController.isMale(characterEnum)) {
            ColorUtils.changeColor(imageView, Constants.DEFAULT_WIG_COLOR, characterHairColor, PruneLevel.LOW);
        }
    }

    private CharacterEnum getFocusedCharacterEnum() {
        CharacterEnum characterEnum = null;
        if (characterLeftView.isFocused()) {
            characterEnum = CharacterEnum.IS_LEFT;
        } else if (characterRightView.isFocused()) {
            characterEnum = CharacterEnum.IS_RIGHT;
        }
        return characterEnum;
    }

    private CharacterImage getCharacterImage(CharacterEnum characterEnum) {
        return characterEnum.equals(CharacterEnum.IS_LEFT) ? characterLeftView :
                characterRightView;
    }

    private ImageView getImageView(CharacterEnum characterEnum) {
        return characterEnum.equals(CharacterEnum.IS_LEFT) ? characterLeftView.getImageView() :
                characterRightView.getImageView();
    }
}
