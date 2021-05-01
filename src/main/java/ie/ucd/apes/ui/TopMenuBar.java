package ie.ucd.apes.ui;

import ie.ucd.apes.controller.PanelController;
import ie.ucd.apes.entity.xml.ComicWrapper;
import ie.ucd.apes.io.FileIO;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.Objects;
import java.util.Optional;

public class TopMenuBar extends MenuBar {
    private final Stage stage;
    private final ScrollingPane scrollingPane;
    private final PanelController panelController;
    private Menu fileMenu;
    private Menu helpMenu;
    private Menu undoDeleteMenu;
    private HelpBox helpBox;

    public TopMenuBar(Stage stage, ScrollingPane scrollingPane, PanelController panelController) {
        this.stage = stage;
        this.scrollingPane = scrollingPane;
        this.panelController = panelController;
        initFileMenu();
        initHelpMenu();
        initUndoDelete();
        this.getMenus().addAll(fileMenu, helpMenu, undoDeleteMenu);
    }

    private void initFileMenu() {
        fileMenu = new Menu("File");
        // export as GIF
        MenuItem gifMenuItem = new MenuItem("Export As GIF");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("GIF (*.gif)", "*.gif"));
        gifMenuItem.setOnAction(actionEvent -> {
            if (!scrollingPane.getImages().isEmpty()) {
                File file = fileChooser.showSaveDialog(stage);
                if (file != null) {
                    FileIO.exportGIF(file, scrollingPane.getImages());
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Project Empty!");
                alert.setContentText("There is nothing to save.");
                alert.showAndWait();
            }
        });
        // export as XML
        MenuItem xmlMenuItem = new MenuItem("Export As XML");
        FileChooser xmlFileChooser = new FileChooser();
        xmlFileChooser.setTitle("Save");
        xmlFileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XML (*.xml)", "*.xml"));
        xmlMenuItem.setOnAction(actionEvent -> {
            TextInputDialog dialog = new TextInputDialog("Comic Premise");
            dialog.setTitle("Premise");
            dialog.setHeaderText("Enter Premise");
            dialog.setContentText("Please enter a premise for the comic:");
            Optional<String> result = dialog.showAndWait();
            result.ifPresent(s -> panelController.setPremise(result.get()));
            File file = xmlFileChooser.showSaveDialog(stage);
            if (file != null) {
                FileIO.exportXML(file, panelController.exportToComicWrapper());
            }
        });
        // import xml
        MenuItem importXMLMenuItem = new MenuItem("Import XML");
        FileChooser importXMLFileChooser = new FileChooser();
        xmlFileChooser.setTitle("Import XML");
        xmlFileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XML (*.xml)", "*.xml"));
        importXMLMenuItem.setOnAction(actionEvent -> {
            File file = importXMLFileChooser.showOpenDialog(stage);
            if (file != null) {
                ComicWrapper wrapper = FileIO.importXML(file);
                panelController.importFromComicWrapper(Objects.requireNonNull(wrapper));
            }
        });
        // add to menu
        fileMenu.getItems().addAll(xmlMenuItem, importXMLMenuItem, gifMenuItem);
    }

    private void initHelpMenu() {
        Label helpLabel = new Label("Help");
        helpBox = new HelpBox();
        helpLabel.setOnMouseClicked(e -> helpBox.showAndWait());
        helpMenu = new Menu("", helpLabel);
    }

    private void initUndoDelete() {
        Label undoDeleteLabel = new Label("Undo Delete");
        undoDeleteLabel.setOnMouseClicked(e -> scrollingPane.restoreDeleted());
        undoDeleteMenu = new Menu("", undoDeleteLabel);
    }
}
