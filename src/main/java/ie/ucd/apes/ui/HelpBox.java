package ie.ucd.apes.ui;

import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

import javax.imageio.ImageIO;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class HelpBox extends Alert {
    private List<Image> tutorialImages;
    private final ImageView image;
    private List<String> captions;
    private final Label caption;
    private int index = 0;


    public HelpBox() {
        super(AlertType.INFORMATION);
        setTitle("Help");
        setHeaderText("QuickStart Guide");
        getDialogPane().setMinWidth(700);
        getDialogPane().setMinHeight(550);
        setResizable (false);

        loadCaptions();     //load slide captions from text file
        loadImages();       //load slide images from directory

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

        showAndWait();
    }

    private void nextSlide() {
        if(index == tutorialImages.size() - 1 || index == captions.size() - 1) index = 0;
        else index++;
        image.setImage(tutorialImages.get(index));
        caption.setText(captions.get(index));
    }

    private void previousSlide() {
        if(index == 0) index = tutorialImages.size() - 1;
        else index--;
        image.setImage(tutorialImages.get(index));
        caption.setText(captions.get(index));
    }

    private void loadImages() {
        tutorialImages = new LinkedList<>();
        for (int i = 1; i <= captions.size(); i++){
            File file = new File("src/main/resources/tutorialScreenshots/" + i + ".png");
            try {
                tutorialImages.add(SwingFXUtils.toFXImage(ImageIO.read(file), null));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void loadCaptions() {
        captions = new LinkedList<>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("src/main/resources/tutorialScreenshots/tutorialCaptions.txt"));
            String line = reader.readLine();
            while (line != null) {
                captions.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
