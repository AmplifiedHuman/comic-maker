package ie.ucd.apes.ui;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;


public class StagePane extends VBox {
    public StagePane() {
        GridPane tiles = new GridPane();
        DialogueBox labelL, labelR;
        this.getChildren().add(new NarrativeBar("Some sample text for the narrative bar."));
        tiles.setMaxWidth(500);
        tiles.setHgap(20);

        // init labels
        labelL = new DialogueBox("Dialogue text of character on the left");
        labelR = new DialogueBox("Dialogue text of character on the right");

        // dialogue
        tiles.add(labelL, 0, 0);
        tiles.add(labelR, 1, 0);
        GridPane.setHalignment(labelR, HPos.RIGHT);

        // character models
        Image image1 = new Image(getClass().getResourceAsStream("/characters/angry.png"));
        Image image2 = new Image(getClass().getResourceAsStream("/characters/accusing.png"));
        ImageView character1 = new CharacterImage(image1);
        ImageView character2 = new CharacterImage(image2);
        character2.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        tiles.add(character1, 0, 2);
        tiles.add(character2, 1, 2);

        // background
        tiles.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        this.getChildren().add(tiles);
        this.getChildren().add(new NarrativeBar("Some sample text for the narrative bar."));
        HBox.setHgrow(this, Priority.ALWAYS);
    }

}
