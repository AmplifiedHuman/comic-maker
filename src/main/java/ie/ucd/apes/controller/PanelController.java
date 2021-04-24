package ie.ucd.apes.controller;

import ie.ucd.apes.entity.Character;
import ie.ucd.apes.entity.*;
import ie.ucd.apes.entity.xml.CharacterWrapper;
import ie.ucd.apes.entity.xml.ComicWrapper;
import ie.ucd.apes.entity.xml.PanelWrapper;
import ie.ucd.apes.ui.ScrollingPane;
import ie.ucd.apes.ui.stage.StageView;

import java.util.*;

public class PanelController {
    private final CharacterController characterController;
    private final DialogueController dialogueController;
    private final NarrativeController narrativeController;
    private final StageView stageView;
    private final Map<String, PanelState> panelStateIdMap;
    private final List<PanelState> panelStates;
    private final Stack<PanelState> deletedPanelStates;
    private PanelState currentState;
    private ScrollingPane scrollingPane;

    public PanelController(CharacterController characterController, DialogueController dialogueController,
                           NarrativeController narrativeController, StageView stageView) {
        this.characterController = characterController;
        this.dialogueController = dialogueController;
        this.narrativeController = narrativeController;
        this.stageView = stageView;
        panelStateIdMap = new HashMap<>();
        panelStates = new ArrayList<>();
        currentState = new PanelState();
        deletedPanelStates = new Stack<>();
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
            deletedPanelStates.push(panelStateIdMap.get(id));
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

    public void swapStates(int position1, int position2) {
        String position1Id = panelStates.get(position1).getPanelId();
        String position2Id = panelStates.get(position2).getPanelId();

        PanelState temp = panelStates.get(position1);

        //swap panelStates of the two positions
        panelStates.set(position1, panelStates.get(position2));
        panelStates.set(position2, temp);

        //swap panel id's
        panelStates.get(position1).setPanelId(position1Id);
        panelStates.get(position2).setPanelId(position2Id);

        // update id map
        panelStateIdMap.put(position1Id, panelStates.get(position1));
        panelStateIdMap.put(position2Id, panelStates.get(position2));
    }

    public void setScrollingPane(ScrollingPane scrollingPane) {
        this.scrollingPane = scrollingPane;
    }

    public ComicWrapper exportToComicWrapper() {
        String premise = "Default Premise";
        List<PanelWrapper> panels = new ArrayList<>();
        for (PanelState panelState : panelStates) {
            CharacterWrapper left = new CharacterWrapper(panelState.getCharacterLeft(), panelState.getDialogueLeft());
            CharacterWrapper right = new CharacterWrapper(panelState.getCharacterRight(), panelState.getDialogueRight());
            panels.add(new PanelWrapper(panelState.getNarrativeTop(), left, right, panelState.getNarrativeBottom()));
        }
        return new ComicWrapper(premise, panels, new ArrayList<>());
    }

    public void resetAllState() {
        panelStates.clear();
        panelStateIdMap.clear();
    }

    public void importFromComicWrapper(ComicWrapper comicWrapper) {
        resetAllState();
        scrollingPane.resetAllState();
        for (PanelWrapper panelWrapper : comicWrapper.getPanels()) {
            reset();
            scrollingPane.requestFocus();
            characterController.setCharacter(Selection.IS_LEFT, panelWrapper.getLeft().getCharacter());
            characterController.setCharacter(Selection.IS_RIGHT, panelWrapper.getRight().getCharacter());
            dialogueController.setDialogue(Selection.IS_LEFT, panelWrapper.getLeft().getDialogue());
            dialogueController.setDialogue(Selection.IS_RIGHT, panelWrapper.getRight().getDialogue());
            narrativeController.setNarrative(Selection.IS_TOP, panelWrapper.getAbove() == null ?
                    new Narrative(Constants.DEFAULT_TOP_NARRATIVE) : panelWrapper.getAbove());
            narrativeController.setNarrative(Selection.IS_BOTTOM, panelWrapper.getBelow() == null ?
                    new Narrative(Constants.DEFAULT_BOTTOM_NARRATIVE) : panelWrapper.getBelow());
            stageView.render();
            scrollingPane.saveToScrollingPane();
        }
    }

    public void restoreState(int position) {
        PanelState restoredState = deletedPanelStates.pop();
        panelStates.add(position, restoredState);
        panelStateIdMap.put(restoredState.getPanelId(), restoredState);
    }
}
