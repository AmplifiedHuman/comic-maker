import ie.ucd.apes.entity.Character;
import ie.ucd.apes.entity.*;
import ie.ucd.apes.entity.xml.CharacterWrapper;
import ie.ucd.apes.entity.xml.ComicWrapper;
import ie.ucd.apes.entity.xml.PanelWrapper;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class XMLTest {
    @Test
    public void testSerialiseCharacter() {
        Character character = new Character(Constants.DEFAULT_LEFT_CHARACTER);
        character.setImageFileName("angry.png");
        character.setIsMale(true);
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Character.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(character, System.out);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSerialiseDialogue() {
        Dialogue dialogue = new Dialogue(Constants.DEFAULT_DIALOGUE);
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Dialogue.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(dialogue, System.out);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCharacterWrapper() {
        Dialogue dialogue = new Dialogue(Constants.DEFAULT_DIALOGUE);
        Character character = new Character(Constants.DEFAULT_LEFT_CHARACTER);
        CharacterWrapper wrapper = new CharacterWrapper(character, dialogue);
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(CharacterWrapper.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(wrapper, System.out);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSerialisePanelWrapper() {
        PanelWrapper wrapper = generateSamplePanelWrapper();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(PanelWrapper.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(wrapper, System.out);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSerialiseComicWrapper() {
        String premise = "Testing premise";
        List<PanelWrapper> figures = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            figures.add(generateSamplePanelWrapper());
        }
        Character character = new Character(Constants.DEFAULT_LEFT_CHARACTER);
        ComicWrapper wrapper = new ComicWrapper(premise, figures, Collections.singletonList(character));
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ComicWrapper.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(wrapper, System.out);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDeserialiseComicWrapper() {

    }

    private PanelWrapper generateSamplePanelWrapper() {
        Dialogue leftDialogue = new Dialogue(Constants.DEFAULT_DIALOGUE);
        Dialogue rightDialogue = new Dialogue("Something", true, DialogueType.THOUGHT);
        Character leftCharacter = new Character(Constants.DEFAULT_LEFT_CHARACTER);
        Character rightCharacter = new Character(Constants.DEFAULT_RIGHT_CHARACTER);
        CharacterWrapper leftWrapper = new CharacterWrapper(leftCharacter, leftDialogue);
        CharacterWrapper rightWrapper = new CharacterWrapper(rightCharacter, rightDialogue);
        Narrative above = new Narrative(Constants.DEFAULT_TOP_NARRATIVE);
        above.setVisible(true);
        Narrative below = new Narrative(Constants.DEFAULT_BOTTOM_NARRATIVE);
        Background background = new Background(Constants.BLANK_IMAGE);
        return new PanelWrapper(above, leftWrapper, rightWrapper, below, background);
    }
}
