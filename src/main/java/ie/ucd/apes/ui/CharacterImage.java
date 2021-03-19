package ie.ucd.apes.ui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class CharacterImage extends ImageView {
    public CharacterImage(Image image) {
        super(image);
        this.setPreserveRatio(true);
        this.setFitHeight(300);
        this.setFitWidth(300);
        this.setPreserveRatio(true);
    }
}
