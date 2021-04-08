package ie.ucd.apes.controller;

import ie.ucd.apes.entity.Character;
import ie.ucd.apes.entity.*;

import java.util.HashMap;
import java.util.Map;

public class PanelController {
    private final CharacterController characterController;
    private final DialogueController dialogueController;
    private final NarrativeController narrativeController;
    private final Map<String, PanelState> panelStateIdMap;
    private final Map<Integer, PanelState> panelStatePositionMap;
    private PanelState currentState;

    public PanelController(CharacterController characterController, DialogueController dialogueController,
                           NarrativeController narrativeController) {
        this.characterController = characterController;
        this.dialogueController = dialogueController;
        this.narrativeController = narrativeController;
        panelStateIdMap = new HashMap<>();
        panelStatePositionMap = new HashMap<>();
        currentState = new PanelState();
    }

    public boolean isCurrentStateNew() {
        return !panelStateIdMap.containsKey(currentState.getPanelId());
    }

    public String getCurrentId() {
        return currentState.getPanelId();
    }

    public boolean isEdited() {
        if(isCurrentStateNew()) {
            return true;
        }
        PanelState originalPanel = panelStateIdMap.get(currentState.getPanelId());
        return !characterController.getCharacter(Selection.IS_LEFT).equals(originalPanel.getCharacterLeft()) || !characterController.getCharacter(Selection.IS_RIGHT).equals(originalPanel.getCharacterRight()) ||
                !dialogueController.getDialogue(Selection.IS_LEFT).equals(originalPanel.getDialogueLeft()) || !dialogueController.getDialogue(Selection.IS_RIGHT).equals(originalPanel.getDialogueRight()) ||
                !narrativeController.getNarrative(Selection.IS_TOP).equals(originalPanel.getNarrativeTop()) || !narrativeController.getNarrative(Selection.IS_BOTTOM).equals(originalPanel.getNarrativeBottom());
    }

    public void loadPanel(int position) {
        try {
            currentState = panelStatePositionMap.get(position);
            // operate on copy so original state not modified
            characterController.setCharacter(Selection.IS_LEFT, (Character) currentState.getCharacterLeft().clone());
            characterController.setCharacter(Selection.IS_RIGHT, (Character) currentState.getCharacterRight().clone());
            dialogueController.setDialogue(Selection.IS_LEFT, (Dialogue) currentState.getDialogueLeft().clone());
            dialogueController.setDialogue(Selection.IS_RIGHT, (Dialogue) currentState.getDialogueRight().clone());
            narrativeController.setNarrative(Selection.IS_TOP, (Narrative) currentState.getNarrativeTop().clone());
            narrativeController.setNarrative(Selection.IS_BOTTOM, (Narrative) currentState.getNarrativeBottom().clone());
        } catch (CloneNotSupportedException exception) {
            System.out.println("Error: Cannot clone object");
        }
    }

    public void saveAndResetPanel(int position) {
        try {
            currentState.setCharacterLeft((Character) characterController.getCharacter(Selection.IS_LEFT).clone());
            currentState.setCharacterRight((Character) characterController.getCharacter(Selection.IS_RIGHT).clone());
            currentState.setDialogueLeft((Dialogue) dialogueController.getDialogue(Selection.IS_LEFT).clone());
            currentState.setDialogueRight((Dialogue) dialogueController.getDialogue(Selection.IS_RIGHT).clone());
            currentState.setNarrativeTop((Narrative) narrativeController.getNarrative(Selection.IS_TOP).clone());
            currentState.setNarrativeBottom((Narrative) narrativeController.getNarrative(Selection.IS_BOTTOM).clone());
            panelStatePositionMap.put(position, currentState);
            panelStateIdMap.put(currentState.getPanelId(), currentState);
            reset();
        } catch (CloneNotSupportedException exception) {
            System.out.println("Error: Cannot clone object");
        }
    }

    private void reset() {
        currentState = new PanelState();
        characterController.reset();
        dialogueController.reset();
        narrativeController.reset();
    }
}
