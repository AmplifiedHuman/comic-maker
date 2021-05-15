package ie.ucd.apes.ui.stage;

import ie.ucd.apes.controller.DialogueController;
import ie.ucd.apes.entity.DialogueType;
import ie.ucd.apes.entity.Selection;
import ie.ucd.apes.ui.DialogueBox;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class DialogueView {
    private final DialogueController dialogueController;
    private final CharacterView characterView;
    private final DialogueBox leftDialogueBox;
    private final DialogueBox rightDialogueBox;

    public DialogueView(DialogueController dialogueController, CharacterView characterView) {
        this.dialogueController = dialogueController;
        this.characterView = characterView;
        leftDialogueBox = new DialogueBox();
        rightDialogueBox = new DialogueBox();
        renderDialogues();
    }

    public DialogueBox getDialogueBox(Selection selection) {
        return selection.equals(Selection.IS_LEFT) ? leftDialogueBox : rightDialogueBox;
    }

    public void toggleFocusedDialogue(DialogueType dialogueType) {
        Selection selection = getFocusedDialogueSelection();
        if (selection != null) {
            DialogueBox dialogueBox = getDialogueBox(selection);
            if (dialogueType.equals(dialogueController.getDialogueType(selection))) {
                dialogueController.toggleDialogue(selection);
            } else {
                // different dialogue type than current type, only toggle if it's not visible
                if (!dialogueController.isVisible(selection)) {
                    dialogueController.toggleDialogue(selection);
                }
                // change type
                dialogueController.setDialogueType(selection, dialogueType);
            }
            dialogueBox.setDialogueStyle(selection, dialogueType);
            dialogueBox.setVisible(dialogueController.isVisible(selection));
            dialogueBox.setText(getTruncatedText(dialogueController.getDialogueText(selection)));
        }
    }

    public void renderDialogues() {
        leftDialogueBox.setText(getTruncatedText(dialogueController.getDialogueText(Selection.IS_LEFT)));
        rightDialogueBox.setText(getTruncatedText(dialogueController.getDialogueText(Selection.IS_RIGHT)));
        leftDialogueBox.setDialogueStyle(Selection.IS_LEFT, dialogueController.getDialogueType(Selection.IS_LEFT));
        rightDialogueBox.setDialogueStyle(Selection.IS_RIGHT, dialogueController.getDialogueType(Selection.IS_RIGHT));
        leftDialogueBox.setVisible(dialogueController.isVisible(Selection.IS_LEFT));
        rightDialogueBox.setVisible(dialogueController.isVisible(Selection.IS_RIGHT));
        leftDialogueBox.setOnMouseClicked((e) -> showDialoguePopup(Selection.IS_LEFT));
        rightDialogueBox.setOnMouseClicked((e) -> showDialoguePopup(Selection.IS_RIGHT));
    }

    private Selection getFocusedDialogueSelection() {
        Selection selection = null;
        if (characterView.isFocused(Selection.IS_LEFT)) {
            selection = Selection.IS_LEFT;
        } else if (characterView.isFocused(Selection.IS_RIGHT)) {
            selection = Selection.IS_RIGHT;
        }
        return selection;
    }

    private void setDialogueText(String text, Selection selection) {
        DialogueBox dialogueBox = getDialogueBox(selection);
        dialogueController.setDialogueText(selection, text);
        dialogueBox.setText(getTruncatedText(text));
    }

    private String getTruncatedText(String text) {
        if (text.length() > 210) {
            return text.substring(0, 210) + "...";
        }
        return text;
    }

    private void showDialoguePopup(Selection selection) {
        DialogueBox dialogueBox = getDialogueBox(selection);
        TextInputDialog popup = new TextInputDialog(dialogueController.getDialogueText(selection));
        popup.setTitle("Speech Dialogue");
        if (selection.equals(Selection.IS_LEFT)) {
            popup.setHeaderText("Please enter the speech text for the left character");
        } else {
            popup.setHeaderText("Please enter the speech text for the right character");
        }
        Optional<String> result = popup.showAndWait();
        if (result.isPresent()) {
            result.ifPresent(text -> setDialogueText(text, selection));
            dialogueBox.setDialogueStyle(selection, dialogueController.getDialogueType(selection));
        }
    }
}
