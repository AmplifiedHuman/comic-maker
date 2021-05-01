package ie.ucd.apes;

import ie.ucd.apes.ui.HelpBox;
import ie.ucd.apes.ui.Layout;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Apes {
    public static void main(String[] args) {
        App.main(args);
    }

    public static class App extends Application {
        public static void main(String[] args) {
            launch();
        }

        @Override
        public void start(Stage stage) {
            var scene = new Scene(new Layout(stage), 900, 750);
            stage.setMinWidth(900);
            stage.setMinHeight(800);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
            stage.setScene(scene);
            stage.getIcons().add(new Image("/buttons/logo.png"));
            if (Taskbar.getTaskbar().isSupported(Taskbar.Feature.ICON_IMAGE)) {
                ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/buttons/logo.png")));
                Taskbar.getTaskbar().setIconImage(icon.getImage());
            }
            stage.show();
            new HelpBox().showAndWait();
        }

    }
}
