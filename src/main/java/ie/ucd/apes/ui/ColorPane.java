package ie.ucd.apes.ui;

import ie.ucd.apes.entity.Constants;
import ie.ucd.apes.ui.stage.CharacterView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ColorPane extends VBox {
    private final CharacterView characterView;
    private ColorSelector skinColorSelector;
    private ColorSelector hairColorSelector;

    public ColorPane(CharacterView characterView) {
        this.characterView = characterView;
        initSkinColorSelector();
        initHairColorSelector();
        this.getChildren().add(skinColorSelector);
        this.getChildren().add(hairColorSelector);
    }

    private void initSkinColorSelector() {
        skinColorSelector = new ColorSelector(Constants.DEFAULT_SKIN_COLOR, "Skin Color");
        skinColorSelector.getColorPicker().setFocusTraversable(false);
        skinColorSelector.getColorPicker().setOnAction((e) ->
                characterView.changeSkinColor(skinColorSelector.getColorPicker().getValue()));
    }

    private void initHairColorSelector() {
        hairColorSelector = new ColorSelector(Constants.DEFAULT_HAIR_COLOR, "Hair Color");
        hairColorSelector.getColorPicker().setFocusTraversable(false);
        hairColorSelector.getColorPicker().setOnAction((e) ->
                characterView.changeHairColor(hairColorSelector.getColorPicker().getValue()));
    }

    public void setSkinColorSelector(Color color) {
        skinColorSelector.setColorPicker(color);
    }

    public void setHairColorSelector(Color color) {
        hairColorSelector.setColorPicker(color);
    }
}
