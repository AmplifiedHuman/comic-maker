package ie.ucd.apes.ui.stage;

import ie.ucd.apes.controller.CharacterController;
import ie.ucd.apes.entity.Constants;
import ie.ucd.apes.entity.PruneLevel;
import ie.ucd.apes.entity.Selection;
import ie.ucd.apes.ui.CharacterImage;
import ie.ucd.apes.ui.ColorPane;
import ie.ucd.apes.ui.OptionsPane;
import ie.ucd.apes.ui.ScrollingPane;
import ie.ucd.apes.utils.ColorUtils;
import javafx.geometry.NodeOrientation;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class CharacterView {
    private final CharacterController characterController;
    private final CharacterImage characterLeftView;
    private final CharacterImage characterRightView;
    private OptionsPane optionsPane;
    private ColorPane colorPane;
    private ScrollingPane scrollingPane;

    public CharacterView(CharacterController characterController) {
        this.characterController = characterController;
        characterLeftView = new CharacterImage(null);
        characterRightView = new CharacterImage(null);
        renderCharacters();
    }

    public void setScrollingPane(ScrollingPane scrollingPane) {
        this.scrollingPane = scrollingPane;
    }

    public void setOptionsPane(OptionsPane optionsPane) {
        this.optionsPane = optionsPane;
    }

    public void renderCharacterImage(Selection selection) {
        if (selection == null) {
            return;
        }
        ImageView imageView = getImageView(selection);
        imageView.setImage(characterController.renderCharacterImage(selection));
        if (imageView.getImage() != null) {
            // render by layers
            renderGender(imageView, selection);
            renderSkinColor(imageView, selection);
            renderHairColor(imageView, selection);
            renderOrientation(imageView, selection);
        }
    }

    public void updateCharacterImage(String newImageName, Selection selection) {
        if (scrollingPane != null && !scrollingPane.isClicked()) {
            characterController.resetState(selection);
            characterController.setCharacterImageFileName(newImageName, selection);
            renderCharacterImage(selection);
            ImageView imageView = getImageView(selection);
            colorPane.setSkinColorSelector(characterController.getSkinColor(selection));
            colorPane.setHairColorSelector(characterController.getHairColor(selection));
            getCharacterImage(selection).requestFocus();
            if (characterController.isFlipped(selection)) {
                imageView.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            }
        }
    }

    public void flipSelectedCharacterImage() {
        Selection selection = getFocusedCharacterSelection();
        if (selection != null) {
            characterController.flipCharacterOrientation(selection);
            flipImageViewOrientation(getImageView(selection));
        }
    }

    public void changeGender() {
        Selection selection = getFocusedCharacterSelection();
        if (selection != null) {
            characterController.changeGender(selection);
            renderCharacterImage(selection);
        }
    }

    public void changeSkinColor(Color newSkinColor) {
        Selection selection = getFocusedCharacterSelection();
        if (selection != null) {
            characterController.setSkinColor(selection, newSkinColor);
            renderCharacterImage(selection);
        }
    }

    public void changeHairColor(Color newHairColor) {
        Selection selection = getFocusedCharacterSelection();
        if (selection != null) {
            characterController.setHairColor(selection, newHairColor);
            renderCharacterImage(selection);
        }
    }

    public void setColorPane(ColorPane colorPane) {
        this.colorPane = colorPane;
    }

    public boolean isFocused(Selection selection) {
        return getFocusedCharacterSelection() == selection;
    }

    public void renderCharacters() {
        if (optionsPane != null && scrollingPane != null && scrollingPane.isClicked()) {
            optionsPane.setListView(Selection.IS_LEFT, characterController.getCharacterImageFileName(Selection.IS_LEFT));
            optionsPane.setListView(Selection.IS_RIGHT, characterController.getCharacterImageFileName(Selection.IS_RIGHT));
        }
        renderCharacterImage(Selection.IS_LEFT);
        renderCharacterImage(Selection.IS_RIGHT);
        characterLeftView.setFocusTraversable(true);
        characterRightView.setFocusTraversable(true);

        characterLeftView.setOnMouseClicked((e) -> {
            characterLeftView.requestFocus();
            colorPane.setSkinColorSelector(characterController.getSkinColor(Selection.IS_LEFT));
            colorPane.setHairColorSelector(characterController.getHairColor(Selection.IS_LEFT));
        });

        characterRightView.setOnMouseClicked((e) -> {
            characterRightView.requestFocus();
            colorPane.setSkinColorSelector(characterController.getSkinColor(Selection.IS_RIGHT));
            colorPane.setHairColorSelector(characterController.getHairColor(Selection.IS_RIGHT));
        });
    }

    public CharacterImage getCharacterImage(Selection selection) {
        return selection.equals(Selection.IS_LEFT) ? characterLeftView :
                characterRightView;
    }

    private ImageView getImageView(Selection selection) {
        return selection.equals(Selection.IS_LEFT) ? characterLeftView.getImageView() :
                characterRightView.getImageView();
    }

    private void flipImageViewOrientation(ImageView imageView) {
        if (imageView.getNodeOrientation().equals(NodeOrientation.LEFT_TO_RIGHT)) {
            imageView.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        } else {
            imageView.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        }
    }

    private Selection getFocusedCharacterSelection() {
        Selection selection = null;
        if (characterLeftView.isFocused()) {
            selection = Selection.IS_LEFT;
        } else if (characterRightView.isFocused()) {
            selection = Selection.IS_RIGHT;
        }
        return selection;
    }

    private void renderGender(ImageView imageView, Selection selection) {
        if (characterController.isMale(selection)) {
            ColorUtils.changeColor(imageView, Constants.DEFAULT_WIG_COLOR, Constants.REPLACEMENT_WIG_COLOR, PruneLevel.LOW);
            ColorUtils.changeColor(imageView, Constants.RIBBON_COLOR, Constants.REPLACEMENT_RIBBON_COLOR, PruneLevel.LOW);
        }
    }

    private void renderSkinColor(ImageView imageView, Selection selection) {
        Color characterSkinColor = characterController.getSkinColor(selection);
        ColorUtils.changeColor(imageView, Constants.DEFAULT_SKIN_COLOR, characterSkinColor, PruneLevel.NONE);
        if (characterController.isMale(selection) && characterController.getLipsColor(selection) == null) {
            ColorUtils.changeColor(imageView, Constants.LIPS_COLOR, Constants.REPLACEMENT_LIPS_COLOR, PruneLevel.MEDIUM);
        } else if (characterController.getLipsColor(selection) != null) {
            ColorUtils.changeColor(imageView, Constants.LIPS_COLOR, characterController.getLipsColor(selection),
                    PruneLevel.MEDIUM);
        }
    }

    private void renderOrientation(ImageView imageView, Selection selection) {
        if (characterController.isFlipped(selection)) {
            imageView.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        } else {
            imageView.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        }
    }

    private void renderHairColor(ImageView imageView, Selection selection) {
        Color characterHairColor = characterController.getHairColor(selection);
        ColorUtils.changeColor(imageView, Constants.DEFAULT_HAIR_COLOR, characterHairColor, PruneLevel.LOW);
        if (!characterController.isMale(selection)) {
            ColorUtils.changeColor(imageView, Constants.DEFAULT_WIG_COLOR, characterHairColor, PruneLevel.LOW);
        }
    }
}
