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
        var scene = new Scene(new Layout(), 640, 480);
        stage.setScene(scene);
        ImageIcon icon = new ImageIcon(getClass().getResource("/logo.png"));
        if (Taskbar.isTaskbarSupported()) {
            Taskbar.getTaskbar().setIconImage(icon.getImage());
        }
        stage.show();
    }

}