package ie.ucd.apes.controller;

import ie.ucd.apes.entity.Dialogue;
import ie.ucd.apes.entity.DialogueType;
import ie.ucd.apes.entity.Narrative;
import ie.ucd.apes.entity.Selection;

public class NarrativeBarController {
    private final Narrative narrativeTop;
    private final Narrative narrativeBottom;

    public NarrativeBarController(Narrative narrativeTop, Narrative narrativeBottom) {
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

    private Narrative getNarrative(Selection selection) {
        return selection.equals(Selection.IS_TOP) ? narrativeTop : narrativeBottom;
    }
}
