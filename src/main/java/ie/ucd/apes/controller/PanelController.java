package ie.ucd.apes.controller;

import ie.ucd.apes.entity.Character;
import ie.ucd.apes.entity.*;

import java.util.ArrayList;
import java.util.List;

public class PanelController {
    private final CharacterController characterController;
    private final DialogueController dialogueController;
    private final NarrativeController narrativeController;
    private final List<PanelState> panels;

    public PanelController(CharacterController characterController, DialogueController dialogueController,
                           NarrativeController narrativeController) {
        this.characterController = characterController;
        this.dialogueController = dialogueController;
        this.narrativeController = narrativeController;
        panels = new ArrayList<>();
    }

    public void savePanel() {
        PanelState panelState = new PanelState();
        try {
            panelState.setCharacterLeft((Character) characterController.getCharacter(Selection.IS_LEFT).clone());
            panelState.setCharacterRight((Character) characterController.getCharacter(Selection.IS_RIGHT).clone());
            panelState.setDialogueLeft((Dialogue) dialogueController.getDialogue(Selection.IS_LEFT).clone());
            panelState.setDialogueRight((Dialogue) dialogueController.getDialogue(Selection.IS_LEFT).clone());
            panelState.setNarrativeTop((Narrative) narrativeController.getNarrative(Selection.IS_TOP).clone());
            panelState.setNarrativeBottom((Narrative) narrativeController.getNarrative(Selection.IS_BOTTOM).clone());
            panels.add(panelState);
        } catch (CloneNotSupportedException exception) {
            System.out.println("Error: Cannot clone object");
        }
    }
}
