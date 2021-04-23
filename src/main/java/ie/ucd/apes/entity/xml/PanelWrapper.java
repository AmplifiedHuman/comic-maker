package ie.ucd.apes.entity.xml;

import ie.ucd.apes.adapters.narrative.NarrativeAdapter;
import ie.ucd.apes.entity.Narrative;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "panel")
@XmlType(propOrder = {"above", "left", "right", "below"})
public class PanelWrapper {
    private Narrative above;
    private CharacterWrapper left;
    private CharacterWrapper right;
    private Narrative below;

    public PanelWrapper() {
    }

    public PanelWrapper(Narrative above, CharacterWrapper left, CharacterWrapper right, Narrative below) {
        this.above = above;
        this.left = left;
        this.right = right;
        this.below = below;
    }

    public Narrative getAbove() {
        return above;
    }

    @XmlElement
    @XmlJavaTypeAdapter(NarrativeAdapter.class)
    public void setAbove(Narrative above) {
        this.above = above;
    }

    public CharacterWrapper getLeft() {
        return left;
    }

    public void setLeft(CharacterWrapper left) {
        this.left = left;
    }

    public CharacterWrapper getRight() {
        return right;
    }

    public void setRight(CharacterWrapper right) {
        this.right = right;
    }

    public Narrative getBelow() {
        return below;
    }

    @XmlElement
    @XmlJavaTypeAdapter(NarrativeAdapter.class)
    public void setBelow(Narrative below) {
        this.below = below;
    }
}
