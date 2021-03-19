package ie.ucd.apes.ui;


import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class ScrollingPane extends VBox {
    GridPane panelList = new GridPane();
    public ScrollingPane() {
        ScrollPane sp= new ScrollPane();
        panelList.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY,BorderWidths.DEFAULT)));
        panelList.setMaxWidth(800);
        panelList.setMinWidth(800);
        panelList.setMaxHeight(100);
        panelList.setMinHeight(100);
        sp.setContent(panelList);
        sp.setPrefWidth(800);
        sp.setPrefHeight(250);
        sp.isPannable();
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        this.getChildren().add(panelList);
        this.getChildren().add(sp);
    }
}
