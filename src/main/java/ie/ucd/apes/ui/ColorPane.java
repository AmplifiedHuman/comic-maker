package ie.ucd.apes.ui;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.layout.VBox;

public class ColorPane extends VBox {
    private ColorSelector skinColorSelector;
    private final StagePane stagePane;

    public ColorPane(StagePane stagePane) {
        this.stagePane = stagePane;
        initSkinColorSelector();
        this.getChildren().add(skinColorSelector);
        this.getChildren().add(new ColorSelector("Hair Color"));
    }

    private void initSkinColorSelector() {
        skinColorSelector = new ColorSelector("Skin Color");
        skinColorSelector.getColorPicker().setFocusTraversable(false);
        skinColorSelector.getColorPicker().setOnAction((e) -> stagePane.changeSelectedCharacterImageSkinColor(skinColorSelector.getColorPicker().getValue()));
    }
}
