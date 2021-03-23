package ie.ucd.apes.ui;

import ie.ucd.apes.entity.Constants;
import ie.ucd.apes.io.FileLoader;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

public class OptionsPane extends VBox {
    private MenuButton leftButton;
    private MenuButton rightButton;

    public OptionsPane() {
        initLeftAndRightButton();
        Button genderButton = new Button("", new ImageView("/gender_button.png"));
        genderButton.setTooltip(new Tooltip("Switch Gender"));

        Button mirrorButton = new Button("", new ImageView("/mirror_button.png"));
        mirrorButton.setTooltip(new Tooltip("Mirror Image"));

        ToggleButton bubbleButton1 = new ToggleButton("", new ImageView("/bubble1_button.png"));
        bubbleButton1.setTooltip(new Tooltip("Add Speech Bubble"));
        ToggleButton bubbleButton2 = new ToggleButton("", new ImageView("/bubble2_button.png"));
        bubbleButton2.setTooltip(new Tooltip("Add Thought Bubble"));

        Button textButton1 = new Button("", new ImageView("/text1_button.png"));
        textButton1.setTooltip(new Tooltip("Add Text Above"));
        Button textButton2 = new Button("", new ImageView("/text2_button.png"));
        textButton2.setTooltip(new Tooltip("Add Text Below"));

        GridPane optionsPane = new GridPane();

        optionsPane.add(leftButton, 0, 0, 1, 1);
        optionsPane.add(rightButton, 1, 0, 1, 1);
        optionsPane.add(mirrorButton, 0, 1, 1, 1);
        optionsPane.add(genderButton, 1, 1, 1, 1);
        optionsPane.add(bubbleButton1, 0, 2, 1, 1);
        optionsPane.add(textButton1, 1, 2, 1, 1);
        optionsPane.add(bubbleButton2, 0, 3, 1, 1);
        optionsPane.add(textButton2, 1, 3, 1, 1);

        optionsPane.setHgap(5);
        optionsPane.setVgap(5);

        this.getChildren().add(optionsPane);
    }

    private void initLeftAndRightButton() {
        leftButton = new MenuButton("", new ImageView("/left_button.png"));
        leftButton.setTooltip(new Tooltip("Edit Left Side"));
        rightButton = new MenuButton("", new ImageView("/right_button.png"));
        rightButton.setTooltip(new Tooltip("Edit Right Side"));
        try {
            leftButton.getItems().add(loadCharactersMenuItem());
            rightButton.getItems().add(loadCharactersMenuItem());
        } catch (IOException ioException) {
            System.out.println("Cannot load characters.");
        }
    }

    private CustomMenuItem loadCharactersMenuItem() throws IOException {
        List<String> files = FileLoader.getFileNames(Constants.characterFolder);
        ListView<String> listView = new ListView<>(FXCollections.observableList(files));
        listView.setCellFactory(param -> new ListCell<>() {
            private final ImageView imageView = new ImageView();

            @Override
            public void updateItem(String name, boolean empty) {
                super.updateItem(name, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    Image iconImage = new Image(getClass()
                            .getResourceAsStream(String.format("/%s/%s", Constants.characterFolder, name)));
                    imageView.setFitHeight(100);
                    imageView.setFitWidth(100);
                    imageView.setSmooth(true);
                    imageView.setImage(iconImage);
                    setText(name);
                    setGraphic(imageView);
                }
            }
        });
        return new CustomMenuItem(listView, true);
    }
}
