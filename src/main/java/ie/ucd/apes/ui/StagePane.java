package ie.ucd.apes.ui;

import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.awt.*;


public class StagePane extends VBox {

    GridPane tiles = new GridPane();
    Circle circle_Red, circle_Blue, circle_Gray, circle_Orange;
    TextArea labelL, labelR;

    public StagePane() {
        this.getChildren().add(new NarrativeBar());
        //give pane a border
        tiles.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        tiles.setMaxWidth(500);

        tiles.setHgap(20);
        //add object to stage area
        //temporarily use a circle instead of image -- adding image is to be implemented
        circle_Red = new Circle(50.0f, Color.RED);
        circle_Red.setCursor(Cursor.HAND);
        circle_Blue = new Circle(50.0f, Color.BLUE);
        circle_Blue.setCursor(Cursor.HAND);
        circle_Gray = new Circle(30.0f, Color.GRAY);
        circle_Gray.setCursor(Cursor.HAND);
        circle_Orange = new Circle(30.0f, Color.ORANGE);
        circle_Orange.setCursor(Cursor.HAND);

        //init labels
        labelL = new TextArea("Dialogue text of character on the left");
        labelR = new TextArea("Dialogue text of character on the right");
        labelL.setMinWidth(200);
        labelR.setMinWidth(200);
        labelL.setMinHeight(200);
        labelR.setMinHeight(200);
        labelL.setWrapText(true);
        labelR.setWrapText(true);

        //dialogue
        tiles.add(labelL, 0,0,1,1);
        tiles.add(labelR, 1,0,1,1);

        //types of speech
        tiles.add(circle_Gray,0,1,1,1);
        tiles.add(circle_Orange,1,1,1,1);
        tiles.setHalignment(circle_Orange, HPos.RIGHT);

        //character models
        tiles.add(circle_Blue, 0,2,1,1);
        tiles.add(circle_Red, 1,2,1,1);
        tiles.setHalignment(circle_Red, HPos.RIGHT);

        this.getChildren().add(tiles);
        this.getChildren().add(new NarrativeBar());
    }

}
