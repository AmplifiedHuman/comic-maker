package ie.ucd.apes.entity;

public class Character {
    private String imageFileName;
    private boolean isFlipped;
    private boolean isMale;

    public Character(String imageFileName, boolean isFlipped, boolean isMale) {
        this.imageFileName = imageFileName;
        this.isFlipped = isFlipped;
        this.isMale = isMale;
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
}
