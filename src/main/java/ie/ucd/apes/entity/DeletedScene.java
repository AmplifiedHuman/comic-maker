package ie.ucd.apes.entity;

import javafx.scene.image.Image;

public class DeletedScene {
    private Image image;
    private int position;
    private String id;

    public DeletedScene(Image image, int position, String id) {
        this.image = image;
        this.position = position;
        this.id = id;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
