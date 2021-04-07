package ie.ucd.apes.ui;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class CapturedScene extends HBox {
    private final int position;
    private final ImageView imageView;

    public CapturedScene(Image image, int position) {
        imageView = new ImageView(image);
        this.position = position;
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(200);
        this.getChildren().add(imageView);
        getStyleClass().add("captured-scene");
        setFocusTraversable(true);
        setMargin(this, new Insets(10, 10, 10, 10));
    }

    public void setImage(Image image) {
        imageView.setImage(image);
    }

    public int getPosition() {
        return position;
    }
}