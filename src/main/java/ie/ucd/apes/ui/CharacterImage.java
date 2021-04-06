package ie.ucd.apes.ui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;


public class CharacterImage extends HBox {
    private final ImageView imageView;

    public CharacterImage(Image image) {
        imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(300);
        imageView.setFitWidth(300);
        this.getChildren().add(imageView);
        this.getStyleClass().add("characterImage");
    }

    public void setImage(Image image) {
        imageView.setImage(image);
    }

    public ImageView getImageView() {
        return imageView;
    }
}
