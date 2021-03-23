package ie.ucd.apes.controller;

import ie.ucd.apes.entity.Character;
import ie.ucd.apes.entity.CharacterEnum;
import ie.ucd.apes.entity.Constants;
import javafx.scene.image.Image;

public class StageController {
    private final Character characterLeft;
    private final Character characterRight;

    public StageController(Character characterLeft, Character characterRight) {
        this.characterLeft = characterLeft;
        this.characterRight = characterRight;
    }

    public void setCharacterImageFileName(String imageFileName, CharacterEnum characterEnum) {
        getCharacter(characterEnum).setImageFileName(imageFileName);
    }

    public Image renderCharacterImage(String imageFileName) {
        String imagePath = String.format("/%s/%s", Constants.characterFolder, imageFileName);
        return new Image(getClass().getResourceAsStream(imagePath));
    }

    public Image renderCharacterImage(CharacterEnum characterEnum) {
        return renderCharacterImage(getCharacter(characterEnum).getImageFileName());
    }

    public boolean isFlipped(CharacterEnum characterEnum) {
        return getCharacter(characterEnum).isFlipped();
    }

    public void flipCharacterOrientation(CharacterEnum characterEnum) {
        getCharacter(characterEnum).flipOrientation();
    }

    private Character getCharacter(CharacterEnum characterEnum) {
        return characterEnum.equals(CharacterEnum.IS_LEFT) ? characterLeft : characterRight;
    }

    public void changeGender(CharacterEnum characterEnum) {
        getCharacter(characterEnum).changeGender();
    }

    public boolean isMale(CharacterEnum characterEnum) { return getCharacter(characterEnum).isMale(); }
}
