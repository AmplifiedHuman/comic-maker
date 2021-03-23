package ie.ucd.apes.entity;

public class Character {
    private String imageLink;
    private CharacterGender gender;

    public Character() {
    }

    public Character(String imageLink, CharacterGender gender) {
        this.imageLink = imageLink;
        this.gender = gender;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public CharacterGender getGender() {
        return gender;
    }

    public void setGender(CharacterGender gender) {
        this.gender = gender;
    }
}
