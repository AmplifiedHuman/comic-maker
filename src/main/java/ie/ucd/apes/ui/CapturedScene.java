package ie.ucd.apes.ui;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class CapturedScene extends HBox {
    public CapturedScene(Image image) {
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(200);
        this.getChildren().add(imageView);
        getStyleClass().add("captured-scene");
        setFocusTraversable(true);
        setMargin(this, new Insets(10, 10, 10, 10));
        setOnMouseClicked((e) -> requestFocus());
    }
}