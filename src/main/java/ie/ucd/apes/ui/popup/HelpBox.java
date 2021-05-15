package ie.ucd.apes.ui.popup;

import ie.ucd.apes.entity.Constants;
import ie.ucd.apes.io.FileIO;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HelpBox extends Alert {
    private final ImageView image;
    private final Label caption;
    private List<Image> tutorialImages;
    private List<String> captions;
    private int index = 0;


    public HelpBox() {
        super(AlertType.INFORMATION);
        setTitle("Help");
        setHeaderText("QuickStart Guide");
        getDialogPane().setMinWidth(700);
        getDialogPane().setMinHeight(550);
        setResizable(false);

        loadCaptions();
        loadImages();

        VBox slideShow = new VBox();
        HBox slideShowImage = new HBox();
        Button arrowLeft = new Button("←");
        Button arrowRight = new Button("→");
        image = new ImageView(tutorialImages.get(index));
        caption = new Label(captions.get(index));

        slideShow.setAlignment(Pos.CENTER);
        slideShowImage.setAlignment(Pos.CENTER);
        slideShowImage.setSpacing(5);
        slideShow.setSpacing(5);

        caption.setWrapText(true);
        caption.setAlignment(Pos.TOP_CENTER);
        caption.setTextAlignment(TextAlignment.CENTER);
        caption.setMinHeight(40);
        caption.setStyle("-fx-font-size: large;");
        caption.setMaxWidth(600);

        image.setFitHeight(400);
        image.setFitWidth(600);

        arrowLeft.setOnMouseClicked((e) -> previousSlide());
        arrowRight.setOnMouseClicked((e) -> nextSlide());

        slideShowImage.getChildren().addAll(arrowLeft, image, arrowRight);
        slideShow.getChildren().addAll(slideShowImage, caption);
        getDialogPane().setContent(slideShow);
        initStyle(StageStyle.UTILITY);
    }

    private void nextSlide() {
        if (index == tutorialImages.size() - 1 || index == captions.size() - 1) {
            index = 0;
        } else {
            index++;
        }
        image.setImage(tutorialImages.get(index));
        caption.setText(captions.get(index));
    }

    private void previousSlide() {
        if (index == 0) {
            index = tutorialImages.size() - 1;
        } else {
            index--;
        }
        image.setImage(tutorialImages.get(index));
        caption.setText(captions.get(index));
    }

    private void loadImages() {
        tutorialImages = new ArrayList<>();
        try {
            List<String> fileNames = FileIO.getFileNames(Constants.HELP_FOLDER);
            int size = fileNames.size() - 1;
            for (int i = 1; i <= size; i++) {
                tutorialImages.add(new Image(Objects.requireNonNull(getClass()
                        .getResourceAsStream(String.format("/%s/%s.png", Constants.HELP_FOLDER, i)))));
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            System.out.println("[Error] Cannot load help popup images.");
        }
    }

    public void loadCaptions() {
        try {
            captions = FileIO.loadTextResource("help/captions.txt");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("[Error] Cannot load help popup caption.");
        }
    }
}
