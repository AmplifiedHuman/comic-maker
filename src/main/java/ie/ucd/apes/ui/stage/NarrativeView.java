package ie.ucd.apes.ui.stage;

import ie.ucd.apes.controller.NarrativeController;
import ie.ucd.apes.entity.Selection;
import ie.ucd.apes.ui.AlertBox;
import ie.ucd.apes.ui.NarrativeBar;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class NarrativeView {
    private final NarrativeBar narrativeBarTop;
    private final NarrativeBar narrativeBarBottom;
    private final NarrativeController narrativeController;

    public NarrativeView(NarrativeController narrativeController) {
        this.narrativeController = narrativeController;
        narrativeBarTop = new NarrativeBar("");
        narrativeBarBottom = new NarrativeBar("");
        renderNarrativeBars();
    }

    public NarrativeBar getNarrativeBar(Selection selection) {
        return selection.equals(Selection.IS_TOP) ? narrativeBarTop : narrativeBarBottom;
    }

    public void toggleNarrativeBar(Selection selection) {
        if (selection.equals(Selection.IS_TOP)) {
            narrativeController.toggleNarrative(selection);
            narrativeBarTop.setVisible(narrativeController.isVisible(selection));
        } else {
            narrativeController.toggleNarrative(selection);
            narrativeBarBottom.setVisible(narrativeController.isVisible(selection));
        }
    }

    public void renderNarrativeBars() {
        narrativeBarTop.setText(narrativeController.getNarrativeText(Selection.IS_TOP));
        narrativeBarBottom.setText(narrativeController.getNarrativeText(Selection.IS_BOTTOM));
        narrativeBarTop.setVisible(narrativeController.isVisible(Selection.IS_TOP));
        narrativeBarBottom.setVisible(narrativeController.isVisible(Selection.IS_BOTTOM));
        narrativeBarTop.setOnMouseClicked((e) -> showNarrativeBarPopUp(Selection.IS_TOP));
        narrativeBarBottom.setOnMouseClicked((e) -> showNarrativeBarPopUp(Selection.IS_BOTTOM));
    }

    private void showNarrativeBarPopUp(Selection selection) {
        TextInputDialog popup = new TextInputDialog(narrativeController.getNarrativeText(selection));
        popup.setTitle("Narrative Bar");
        if (selection.equals(Selection.IS_TOP)) {
            popup.setHeaderText("Enter text for the top bar");
        } else {
            popup.setHeaderText("Please enter text for the bottom bar");
        }
        Optional<String> result = popup.showAndWait();
        if (result.isPresent()) {
            if (result.get().length() < 100) {
                result.ifPresent(text -> setNarrativeText(text, selection));
            } else {
                new AlertBox("Narrative");
            }
        }
    }

    private void setNarrativeText(String text, Selection selection) {
        NarrativeBar narrativeBar = getNarrativeBar(selection);
        if (text.isBlank()) {
            text = "CLICK HERE TO EDIT TEXT";
        }
        narrativeController.setNarrativeText(selection, text);
        narrativeBar.setText(text);
    }
}
