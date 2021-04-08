package ie.ucd.apes.controller;

import ie.ucd.apes.entity.Character;
import ie.ucd.apes.entity.Constants;
import ie.ucd.apes.entity.Selection;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.Objects;

public class CharacterController {
    private Character characterLeft;
    private Character characterRight;

    public CharacterController(Character characterLeft, Character characterRight) {
        this.characterLeft = characterLeft;
        this.characterRight = characterRight;
    }

    public void setCharacterImageFileName(String imageFileName, Selection selection) {
        getCharacter(selection).setImageFileName(imageFileName);
    }

    public Image renderCharacterImage(String imageFileName) {
        if (imageFileName == null || imageFileName.equals(Constants.BLANK_IMAGE)) {
            return null;
        }
        String imagePath = String.format("/%s/%s", Constants.CHARACTER_FOLDER, imageFileName);
        return new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
    }

    public Image renderCharacterImage(Selection selection) {
        return renderCharacterImage(getCharacter(selection).getImageFileName());
    }

    public boolean isFlipped(Selection selection) {
        return getCharacter(selection).isFlipped();
    }

    public void flipCharacterOrientation(Selection selection) {
        getCharacter(selection).flipOrientation();
    }

    public void changeGender(Selection selection) {
        getCharacter(selection).changeGender();
    }

    public boolean isMale(Selection selection) {
        return getCharacter(selection).isMale();
    }

    public void resetState(Selection selection) {
        Character character = getCharacter(selection);
        character.setImageFileName(null);
        character.setMale(false);
        character.setSkinColor(Constants.DEFAULT_SKIN_COLOR);
        character.setHairColor(Constants.DEFAULT_HAIR_COLOR);
    }

    public Character getCharacter(Selection selection) {
        return selection.equals(Selection.IS_LEFT) ? characterLeft : characterRight;
    }

    public Color getSkinColor(Selection selection) {
        return getCharacter(selection).getSkinColor();
    }

    public void setSkinColor(Selection selection, Color newSkinColor) {
        getCharacter(selection).setSkinColor(newSkinColor);
    }

    public Color getHairColor(Selection selection) {
        return getCharacter(selection).getHairColor();
    }

    public void setHairColor(Selection selection, Color newHairColor) {
        getCharacter(selection).setHairColor(newHairColor);
    }

    public void setCharacter(Selection selection, Character character) {
        if (selection.equals(Selection.IS_LEFT)) {
            characterLeft = character;
        } else {
            characterRight = character;
        }
    }

    public boolean isDefaultState() {
        return characterLeft.equals(Constants.DEFAULT_LEFT_CHARACTER)
                && characterRight.equals(Constants.DEFAULT_RIGHT_CHARACTER);
    }

    public void reset() {
        characterLeft = new Character(Constants.DEFAULT_LEFT_CHARACTER);
        characterRight = new Character(Constants.DEFAULT_RIGHT_CHARACTER);
    }
}
