package ie.ucd.apes.entity.xml;

import ie.ucd.apes.entity.Character;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlRootElement(name = "comic")
@XmlType(propOrder = {"premise", "figures", "panels"})
public class ComicWrapper {
    private List<PanelWrapper> panels;
    private List<Character> figures;
    private String premise;

    public ComicWrapper() {
    }

    public ComicWrapper(String premise, List<PanelWrapper> panels, List<Character> figures) {
        this.panels = panels;
        this.premise = premise;
        this.figures = figures;
    }

    public List<PanelWrapper> getPanels() {
        return panels;
    }

    @XmlElement(name = "panel")
    @XmlElementWrapper(name = "panels")
    public void setPanels(List<PanelWrapper> panels) {
        this.panels = panels;
    }

    public String getPremise() {
        return premise;
    }

    @XmlElement
    public void setPremise(String premise) {
        this.premise = premise;
    }

    public List<Character> getFigures() {
        return figures;
    }

    @XmlElement(name = "figure")
    @XmlElementWrapper(name = "figures")
    public void setFigures(List<Character> figures) {
        this.figures = figures;
    }
}
