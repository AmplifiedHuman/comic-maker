package ie.ucd.apes;

import ie.ucd.apes.ui.popup.HelpBox;
import ie.ucd.apes.ui.Layout;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Apes {
    public static void main(String[] args) {
        App.main(args);
    }

    public static class App extends Application {
        private static final int SPLASH_WIDTH = 830;
        private Pane splashPane;
        private ProgressBar progressBar;

        public static void main(String[] args) {
            launch();
        }

        @Override
        public void init() {
            ImageView splash = new ImageView("/splash_screen.png");
            progressBar = new ProgressBar();
            progressBar.setPrefWidth(SPLASH_WIDTH);
            splashPane = new VBox();
            splashPane.getChildren().addAll(splash, progressBar);
            splashPane.setStyle(
                    "-fx-padding: 5; " +
                            "-fx-background-color: white; " +
                            "-fx-border-width:5; " +
                            "-fx-border-color: " +
                            "linear-gradient(" +
                            "to bottom, " +
                            "#3C3F41, " +
                            "derive(#3C3F41, 50%)" +
                            ");"
            );
            splashPane.setEffect(new DropShadow());
        }

        @Override
        public void start(Stage stage) {
            final Task<Void> task = new Task<>() {
                @Override
                protected Void call() throws InterruptedException {
                    Thread.sleep(1000);
                    return null;
                }
            };
            showSplashScreen(
                    stage,
                    task,
                    this::showMainStage
            );
            new Thread(task).start();
        }

        private void showMainStage() {
            Stage mainStage = new Stage(StageStyle.DECORATED);
            var scene = new Scene(new Layout(mainStage), 900, 790);
            mainStage.setMinWidth(900);
            mainStage.setMinHeight(790);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
            mainStage.setScene(scene);
            mainStage.getIcons().add(new Image("/buttons/logo.png"));
            if (Taskbar.getTaskbar().isSupported(Taskbar.Feature.ICON_IMAGE)) {
                ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/buttons/logo.png")));
                Taskbar.getTaskbar().setIconImage(icon.getImage());
            }
            mainStage.show();
            new HelpBox().show();
        }

        private void showSplashScreen(
                final Stage splashStage,
                Task<?> task,
                InitCompletionHandler initCompletionHandler
        ) {
            progressBar.progressProperty().bind(task.progressProperty());
            task.stateProperty().addListener((observableValue, oldState, newState) -> {
                if (newState == Worker.State.SUCCEEDED) {
                    progressBar.progressProperty().unbind();
                    progressBar.setProgress(1);
                    splashStage.toFront();
                    FadeTransition fadeSplash = new FadeTransition(Duration.seconds(1.2), splashPane);
                    fadeSplash.setFromValue(1.0);
                    fadeSplash.setToValue(0.0);
                    fadeSplash.setOnFinished(actionEvent -> splashStage.hide());
                    fadeSplash.play();

                    initCompletionHandler.complete();
                }
            });

            Scene splashScene = new Scene(splashPane);
            splashStage.setScene(splashScene);
            splashStage.initStyle(StageStyle.TRANSPARENT);
            splashStage.setAlwaysOnTop(true);
            splashStage.show();
        }

        public interface InitCompletionHandler {
            void complete();
        }
    }
}
