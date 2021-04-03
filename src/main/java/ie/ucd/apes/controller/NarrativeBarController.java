package ie.ucd.apes.controller;

import ie.ucd.apes.entity.Narrative;
import ie.ucd.apes.entity.Selection;

public class NarrativeBarController {
  private final Narrative Top; 
  private final Narrative Bottom; 

  public NarrativeBarController(Narrative Top,Narrative Bottom){
    this.Top = Top;
    this.Bottom = Bottom;
  }

  public void toggleNarrative(Selection selection){
      Narrative narrative = getNarrative(selection);
      narrative.setVisible(!narrative.isVisible());
  }

  public boolean isVisible(Selection selection) {
    return getNarrative(selection).isVisible();
}

private Narrative getNarrative(Selection selection) {
    return selection.equals(Selection.IS_TOP) ? Top : Bottom;
}

public void setNarrativeText(Selection selection,String text){
    getNarrative(selection).setText(text);
}

public String getNarrativeText(Selection selection) {
    return getNarrative(selection).getText();
}


}
