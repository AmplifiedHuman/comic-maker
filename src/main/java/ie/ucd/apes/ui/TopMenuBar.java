package ie.ucd.apes.ui;

import ie.ucd.apes.io.FileIO;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class TopMenuBar extends MenuBar {
    private final Stage stage;
    private final ScrollingPane scrollingPane;
    private Menu fileMenu;
    private Menu helpMenu;

    public TopMenuBar(Stage stage, ScrollingPane scrollingPane) {
        this.stage = stage;
        this.scrollingPane = scrollingPane;
        initFileMenu();
        initHelpMenu();
        this.getMenus().addAll(fileMenu, helpMenu);
    }

    private void initFileMenu() {
        fileMenu = new Menu("File");
        MenuItem gifMenuItem = new MenuItem("Export As GIF");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("GIF (*.gif)", "*.gif"));
        gifMenuItem.setOnAction(actionEvent -> {
            if(!scrollingPane.getImages().isEmpty()){
                File file = fileChooser.showSaveDialog(stage);
                if (file != null) {
                    FileIO.exportGIF(file, scrollingPane.getImages());
                }
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Project Empty!");
                alert.setContentText("There is nothing to save.");
                alert.showAndWait();
            }
        });
        fileMenu.getItems().addAll(new MenuItem("Save As XML"), gifMenuItem);
    }

    private void initHelpMenu() {
        Label helpLabel = new Label("Help");
        helpLabel.setOnMouseClicked(e -> new HelpBox());
        helpMenu = new Menu("", helpLabel);
    }
}
