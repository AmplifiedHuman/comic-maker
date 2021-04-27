package ie.ucd.apes.entity;

import ie.ucd.apes.adapters.character.*;
import javafx.scene.paint.Color;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Objects;

import static ie.ucd.apes.entity.Constants.*;

@XmlRootElement(name = "figure")
@XmlType(propOrder = {"name", "isMale", "skinColor", "hairColor", "lipsColor", "imageFileName", "isFlipped"})
public class Character {
    private String imageFileName;
    private Boolean isFlipped;
    private Boolean isMale;
    private Color skinColor;
    private Color hairColor;
    private Color lipsColor;

    private String name;

    public Character() {
    }

    public Character(String imageFileName, boolean isFlipped, boolean isMale) {
        this.imageFileName = imageFileName;
        this.isFlipped = isFlipped;
        this.isMale = isMale;
        skinColor = DEFAULT_SKIN_COLOR;
        hairColor = DEFAULT_HAIR_COLOR;
        name = "";
        lipsColor = null;
    }

    public Character(Character copy) {
        this.imageFileName = copy.imageFileName;
        this.isFlipped = copy.isFlipped;
        this.isMale = copy.isMale;
        this.skinColor = copy.skinColor;
        this.hairColor = copy.hairColor;
        this.name = copy.name;
        this.lipsColor = copy.lipsColor;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    @XmlElement(name = "pose", nillable = true)
    @XmlJavaTypeAdapter(PosingAdapter.class)
    public void setImageFileName(String imageFileName) {
        if (imageFileName == null) {
            imageFileName = BLANK_IMAGE;
        }
        this.imageFileName = imageFileName;
    }

    public Boolean getIsFlipped() {
        return isFlipped;
    }

    @XmlElement(name = "facing", required = true)
    @XmlJavaTypeAdapter(FacingAdapter.class)
    public void setIsFlipped(Boolean flipped) {
        isFlipped = flipped;
    }

    public Boolean getIsMale() {
        return isMale;
    }

    @XmlElement(name = "appearance", required = true)
    @XmlJavaTypeAdapter(AppearanceAdapter.class)
    public void setIsMale(Boolean male) {
        this.isMale = male;
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

    @XmlElement(name = "skin", required = true)
    @XmlJavaTypeAdapter(SkinAdapter.class)
    public void setSkinColor(Color skinColor) {
        this.skinColor = skinColor;
    }

    public Color getHairColor() {
        return hairColor;
    }

    @XmlElement(name = "hair", required = true)
    @XmlJavaTypeAdapter(HairAdapter.class)
    public void setHairColor(Color hairColor) {
        this.hairColor = hairColor;
    }

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public Color getLipsColor() {
        return lipsColor;
    }

    @XmlElement(name = "lips", required = true)
    @XmlJavaTypeAdapter(LipsAdapter.class)
    public void setLipsColor(Color lipsColor) {
        this.lipsColor = lipsColor;
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
