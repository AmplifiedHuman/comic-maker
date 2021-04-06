package ie.ucd.apes.ui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class CapturedScene extends HBox {
    public CapturedScene(Image image) {
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(200);
        this.getChildren().add(imageView);
    }
}