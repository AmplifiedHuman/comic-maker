package ie.ucd.apes.controller;

import ie.ucd.apes.entity.Character;
import ie.ucd.apes.entity.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PanelController {
    private final CharacterController characterController;
    private final DialogueController dialogueController;
    private final NarrativeController narrativeController;
    private final Map<String, PanelState> panelStateIdMap;
    private final List<PanelState> panelStates;
    private PanelState currentState;

    public PanelController(CharacterController characterController, DialogueController dialogueController,
                           NarrativeController narrativeController) {
        this.characterController = characterController;
        this.dialogueController = dialogueController;
        this.narrativeController = narrativeController;
        panelStateIdMap = new HashMap<>();
        panelStates = new ArrayList<>();
        currentState = new PanelState();
    }

    public boolean isCurrentStateNew() {
        return !panelStateIdMap.containsKey(currentState.getPanelId());
    }

    public boolean isDefaultState() {
        return characterController.isDefaultState() && dialogueController.isDefaultState()
                && narrativeController.isDefaultState();
    }

    public String getCurrentId() {
        return currentState.getPanelId();
    }

    public boolean isEdited() {
        if (isCurrentStateNew()) {
            return true;
        }
        PanelState originalPanel = panelStateIdMap.get(currentState.getPanelId());
        return !characterController.getCharacter(Selection.IS_LEFT).equals(originalPanel.getCharacterLeft())
                || !characterController.getCharacter(Selection.IS_RIGHT).equals(originalPanel.getCharacterRight())
                || !dialogueController.getDialogue(Selection.IS_LEFT).equals(originalPanel.getDialogueLeft())
                || !dialogueController.getDialogue(Selection.IS_RIGHT).equals(originalPanel.getDialogueRight())
                || !narrativeController.getNarrative(Selection.IS_TOP).equals(originalPanel.getNarrativeTop())
                || !narrativeController.getNarrative(Selection.IS_BOTTOM).equals(originalPanel.getNarrativeBottom());
    }

    public void loadPanel(int position) {
        currentState = panelStates.get(position);
        // operate on copy so original state not modified
        characterController.setCharacter(Selection.IS_LEFT, new Character(currentState.getCharacterLeft()));
        characterController.setCharacter(Selection.IS_RIGHT, new Character(currentState.getCharacterRight()));
        dialogueController.setDialogue(Selection.IS_LEFT, new Dialogue(currentState.getDialogueLeft()));
        dialogueController.setDialogue(Selection.IS_RIGHT, new Dialogue(currentState.getDialogueRight()));
        narrativeController.setNarrative(Selection.IS_TOP, new Narrative(currentState.getNarrativeTop()));
        narrativeController.setNarrative(Selection.IS_BOTTOM, new Narrative(currentState.getNarrativeBottom()));
    }

    public void delete(int position, String id) {
        if (position >= 0 && position < panelStates.size() && panelStateIdMap.containsKey(id)) {
            panelStates.remove(position);
            panelStateIdMap.remove(id);
        }
    }

    public void save(int position) {
        currentState.setCharacterLeft(new Character(characterController.getCharacter(Selection.IS_LEFT)));
        currentState.setCharacterRight(new Character(characterController.getCharacter(Selection.IS_RIGHT)));
        currentState.setDialogueLeft(new Dialogue(dialogueController.getDialogue(Selection.IS_LEFT)));
        currentState.setDialogueRight(new Dialogue(dialogueController.getDialogue(Selection.IS_RIGHT)));
        currentState.setNarrativeTop(new Narrative(narrativeController.getNarrative(Selection.IS_TOP)));
        currentState.setNarrativeBottom(new Narrative(narrativeController.getNarrative(Selection.IS_BOTTOM)));
        if (position >= panelStates.size()) {
            panelStates.add(currentState);
        } else {
            panelStates.set(position, currentState);
        }
        panelStateIdMap.put(currentState.getPanelId(), currentState);
    }

    public void reset() {
        currentState = new PanelState();
        characterController.reset();
        dialogueController.reset();
        narrativeController.reset();
    }
}
