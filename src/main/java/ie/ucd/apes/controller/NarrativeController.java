package ie.ucd.apes.controller;

import ie.ucd.apes.entity.Constants;
import ie.ucd.apes.entity.Narrative;
import ie.ucd.apes.entity.Selection;

public class NarrativeController {
    private Narrative narrativeTop;
    private Narrative narrativeBottom;

    public NarrativeController(Narrative narrativeTop, Narrative narrativeBottom) {
        this.narrativeTop = narrativeTop;
        this.narrativeBottom = narrativeBottom;
    }

    public void toggleNarrative(Selection selection) {
        Narrative narrative = getNarrative(selection);
        narrative.setVisible(!narrative.isVisible());
    }

    public boolean isVisible(Selection selection) {
        return getNarrative(selection).isVisible();
    }

    public void setNarrativeText(Selection selection, String text) {
        getNarrative(selection).setText(text);
    }

    public String getNarrativeText(Selection selection) {
        return getNarrative(selection).getText();
    }

    public Narrative getNarrative(Selection selection) {
        return selection.equals(Selection.IS_TOP) ? narrativeTop : narrativeBottom;
    }

    public void setNarrative(Selection selection, Narrative narrative) {
        if (selection.equals(Selection.IS_TOP)) {
            narrativeTop = narrative;
        } else {
            narrativeBottom = narrative;
        }
    }

    public boolean isDefaultState() {
        return narrativeTop.equals(Constants.DEFAULT_TOP_NARRATIVE)
                && narrativeBottom.equals(Constants.DEFAULT_BOTTOM_NARRATIVE);
    }

    public void reset() {
        narrativeTop = new Narrative(Constants.DEFAULT_TOP_NARRATIVE);
        narrativeBottom = new Narrative(Constants.DEFAULT_BOTTOM_NARRATIVE);
    }
}
