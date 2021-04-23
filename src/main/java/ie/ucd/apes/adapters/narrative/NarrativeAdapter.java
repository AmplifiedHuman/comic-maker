package ie.ucd.apes.adapters.narrative;

import ie.ucd.apes.entity.Narrative;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class NarrativeAdapter extends XmlAdapter<String, Narrative> {
    @Override
    public Narrative unmarshal(String s) {
        if (s == null || s.isEmpty()) {
            return new Narrative("PLEASE ENTER SOME TEXT", false);
        }
        return new Narrative(s, true);
    }

    @Override
    public String marshal(Narrative narrative) {
        if (!narrative.isVisible()) {
            return null;
        }
        return narrative.getText();
    }
}
