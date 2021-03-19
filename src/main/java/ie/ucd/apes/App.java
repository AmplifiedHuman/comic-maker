package ie.ucd.apes;

import ie.ucd.apes.ui.Layout;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;

public class App extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        var scene = new Scene(new Layout(), 900, 800);
        stage.setMinWidth(900);
        stage.setMinHeight(800);
        stage.setScene(scene);
        if (Taskbar.getTaskbar().isSupported(Taskbar.Feature.ICON_IMAGE)) {
            ImageIcon icon = new ImageIcon(getClass().getResource("/logo.png"));
            Taskbar.getTaskbar().setIconImage(icon.getImage());
        }
        stage.show();
    }

}