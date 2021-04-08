package ie.ucd.apes.entity;

import javafx.scene.paint.Color;

import java.util.Objects;

import static ie.ucd.apes.entity.Constants.DEFAULT_HAIR_COLOR;
import static ie.ucd.apes.entity.Constants.DEFAULT_SKIN_COLOR;

public class Character {
    private String imageFileName;
    private boolean isFlipped;
    private boolean isMale;
    private Color skinColor;
    private Color hairColor;

    public Character(String imageFileName, boolean isFlipped, boolean isMale) {
        this.imageFileName = imageFileName;
        this.isFlipped = isFlipped;
        this.isMale = isMale;
        skinColor = DEFAULT_SKIN_COLOR;
        hairColor = DEFAULT_HAIR_COLOR;
    }

    public Character(Character copy) {
        this.imageFileName = copy.imageFileName;
        this.isFlipped = copy.isFlipped;
        this.isMale = copy.isMale;
        this.skinColor = copy.skinColor;
        this.hairColor = copy.hairColor;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public boolean isFlipped() {
        return isFlipped;
    }

    public void setFlipped(boolean flipped) {
        isFlipped = flipped;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    public void flipOrientation() {
        isFlipped = !isFlipped;
    }

    public void changeGender() {
        isMale = !isMale;
    }

    public Color getSkinColor() {
        return skinColor;
    }

    public void setSkinColor(Color skinColor) {
        this.skinColor = skinColor;
    }

    public Color getHairColor() {
        return hairColor;
    }

    public void setHairColor(Color hairColor) {
        this.hairColor = hairColor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Character character = (Character) o;
        return isFlipped == character.isFlipped && isMale == character.isMale && imageFileName.equals(character.imageFileName) && skinColor.equals(character.skinColor) && hairColor.equals(character.hairColor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageFileName, isFlipped, isMale, skinColor, hairColor);
    }
}
