package ie.ucd.apes.entity.html;

import javafx.scene.image.Image;

import java.util.List;

public class HTMLWrapper {
    private String premise;
    private List<Image> images;

    public HTMLWrapper(String premise, List<Image> images) {
        this.premise = premise;
        this.images = images;
    }

    public String getPremise() {
        return premise;
    }

    public void setPremise(String premise) {
        this.premise = premise;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
