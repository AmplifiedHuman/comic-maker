package ie.ucd.apes.ui;

import ie.ucd.apes.entity.Constants;
import ie.ucd.apes.entity.DialogueType;
import ie.ucd.apes.entity.Selection;
import ie.ucd.apes.io.FileLoader;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;

public class OptionsPane extends VBox {
    private final StagePane stagePane;
    private MenuButton leftButton;
    private MenuButton rightButton;
    private Button flipButton;
    private Button genderButton;
    private Button speechButton;
    private Button thoughtButton;
    private Button topNarrativeButton;
    private Button bottomNarrativeButton;

    public OptionsPane(StagePane stagePane) {
        this.stagePane = stagePane;
        initLeftAndRightButton();
        initFlipButton();
        initGenderButton();
        initSpeechButton();
        initThoughtButton();
        initNarrativeButtons();

        GridPane optionsPane = new GridPane();

        optionsPane.add(leftButton, 0, 0, 1, 1);
        optionsPane.add(rightButton, 1, 0, 1, 1);
        optionsPane.add(flipButton, 0, 1, 1, 1);
        optionsPane.add(genderButton, 1, 1, 1, 1);
        optionsPane.add(speechButton, 0, 2, 1, 1);
        optionsPane.add(topNarrativeButton, 1, 2, 1, 1);
        optionsPane.add(thoughtButton, 0, 3, 1, 1);
        optionsPane.add(bottomNarrativeButton, 1, 3, 1, 1);

        optionsPane.setHgap(5);
        optionsPane.setVgap(5);

        this.getChildren().add(optionsPane);
    }

    private void initNarrativeButtons() {
        topNarrativeButton = new Button("", new ImageView("/buttons/text1_button.png"));
        topNarrativeButton.setTooltip(new Tooltip("Add Text Above"));
        topNarrativeButton.setMinWidth(80);
        topNarrativeButton.setOnMouseClicked((e) -> stagePane.toggleNarrativeBar(Selection.IS_TOP));

        bottomNarrativeButton = new Button("", new ImageView("/buttons/text2_button.png"));
        bottomNarrativeButton.setTooltip(new Tooltip("Add Text Below"));
        bottomNarrativeButton.setMinWidth(80);
        bottomNarrativeButton.setOnMouseClicked((e) -> stagePane.toggleNarrativeBar(Selection.IS_BOTTOM));
    }

    private void initThoughtButton() {
        thoughtButton = new Button("", new ImageView("/buttons/bubble2_button.png"));
        thoughtButton.setTooltip(new Tooltip("Add Thought Bubble"));
        thoughtButton.setFocusTraversable(false);
        thoughtButton.setOnMouseClicked((e) -> stagePane.toggleFocusedDialogue(DialogueType.THOUGHT));
        thoughtButton.setMinWidth(80);
    }

    private void initSpeechButton() {
        speechButton = new Button("", new ImageView("/buttons/bubble1_button.png"));
        speechButton.setTooltip(new Tooltip("Add Speech Bubble"));
        speechButton.setFocusTraversable(false);
        speechButton.setOnMouseClicked((e) -> stagePane.toggleFocusedDialogue(DialogueType.SPEECH));
        speechButton.setMinWidth(80);
    }

    private void initFlipButton() {
        flipButton = new Button("", new ImageView("/buttons/mirror_button.png"));
        flipButton.setTooltip(new Tooltip("Mirror Image"));
        flipButton.setFocusTraversable(false);
        flipButton.setOnMouseClicked((e) -> stagePane.flipSelectedCharacterImage());
        flipButton.setMinWidth(80);
    }

    private void initLeftAndRightButton() {
        leftButton = new MenuButton("", new ImageView("/buttons/left_button.png"));
        leftButton.setTooltip(new Tooltip("Edit Left Side"));
        rightButton = new MenuButton("", new ImageView("/buttons/right_button.png"));
        rightButton.setTooltip(new Tooltip("Edit Right Side"));
        leftButton.setFocusTraversable(false);
        rightButton.setFocusTraversable(false);
        try {
            leftButton.getItems().add(loadCharactersMenuItem(Selection.IS_LEFT));
            rightButton.getItems().add(loadCharactersMenuItem(Selection.IS_RIGHT));
        } catch (IOException | URISyntaxException ioException) {
            System.out.println("Cannot load characters.");
        }
        leftButton.setMaxWidth(80);
        rightButton.setMaxWidth(80);
    }

    private void initGenderButton() {
        genderButton = new Button("", new ImageView("/buttons/gender_button.png"));
        genderButton.setTooltip(new Tooltip("Gender Change"));
        genderButton.setFocusTraversable(false);
        genderButton.setOnMouseClicked((e) -> stagePane.changeGender());
        genderButton.setMinWidth(80);
    }

    private CustomMenuItem loadCharactersMenuItem(Selection selection) throws IOException, URISyntaxException {
        List<String> files = FileLoader.getFileNames(Constants.CHARACTER_FOLDER);
        Collections.sort(files);
        files.add(0, Constants.BLANK_IMAGE);
        ListView<String> listView = new ListView<>(FXCollections.observableList(files));
        listView.setCellFactory(param -> new ListCell<>() {
            private final ImageView imageView = new ImageView();

            @Override
            public void updateItem(String name, boolean empty) {
                super.updateItem(name, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else if (name.equals(Constants.BLANK_IMAGE)) {
                    setText(Constants.BLANK_IMAGE);
                    setGraphic(null);
                } else {
                    Image iconImage = new Image(getClass()
                            .getResourceAsStream(String.format("/%s/%s", Constants.CHARACTER_FOLDER, name)));
                    imageView.setFitHeight(100);
                    imageView.setFitWidth(100);
                    imageView.setSmooth(true);
                    imageView.setImage(iconImage);
                    setText(name);
                    setGraphic(imageView);
                }
            }
        });
        // update character listener
        listView.getSelectionModel().selectedItemProperty().addListener(
                (observableValue, oldValue, newValue) -> stagePane.updateCharacterImage(newValue, selection));
        return new CustomMenuItem(listView, true);
    }
}
