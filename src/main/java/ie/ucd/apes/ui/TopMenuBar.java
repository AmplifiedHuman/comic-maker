package ie.ucd.apes.ui;

import ie.ucd.apes.controller.PanelController;
import ie.ucd.apes.entity.xml.ComicWrapper;
import ie.ucd.apes.io.FileIO;
import ie.ucd.apes.ui.popup.ErrorPopup;
import ie.ucd.apes.ui.popup.HelpBox;
import ie.ucd.apes.ui.popup.ToggleSwitch;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.Collections;
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
        this.getMenus().addAll(fileMenu, undoDeleteMenu, helpMenu);
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
                alert.initStyle(StageStyle.UTILITY);
                alert.showAndWait();
            }
        });
        // export as XML
        MenuItem xmlMenuItem = new MenuItem("Export As XML");
        FileChooser xmlFileChooser = new FileChooser();
        xmlFileChooser.setTitle("Save");
        xmlFileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XML (*.xml)", "*.xml"));
        xmlMenuItem.setOnAction(actionEvent -> {
            if (setPremise()) {
                File file = xmlFileChooser.showSaveDialog(stage);
                if (file != null) {
                    FileIO.exportXML(file, panelController.exportToComicWrapper());
                }
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
                ComicWrapper wrapper = null;
                try {
                    wrapper = FileIO.importXML(file);
                } catch (JAXBException e) {
                    String errorMessage = e.getCause().toString();
                    new ErrorPopup(Collections.singletonList(errorMessage.replace("org.xml.sax.SAXParseException; ", "")),
                            "Error parsing XML file: Syntax Error");
                }
                if (wrapper != null) {
                    panelController.importFromComicWrapper(wrapper);
                }
            }
        });
        // export as HTML
        MenuItem htmlMenuItem = new MenuItem("Export As HTML");
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Export As HTML");
        htmlMenuItem.setOnAction(actionEvent -> {
            Results options = getHTMLOptions();
            if (options != null) {
                panelController.setPremise(options.getPremise());
                File directory = directoryChooser.showDialog(stage);
                if (directory != null) {
                    FileIO.exportHTML(directory.getAbsolutePath(), panelController.exportToHTMLWrapper(),
                            options.getTheme(), options.getBackgroundEnabled(), options.isFontEnabled(), options.isEndingEnabled());
                }
            }
        });
        // add to menu
        fileMenu.getItems().addAll(xmlMenuItem, htmlMenuItem, importXMLMenuItem, gifMenuItem);
    }

    private boolean setPremise() {
        TextInputDialog dialog = new TextInputDialog(panelController.getPremise());
        dialog.setTitle("Title");
        dialog.setHeaderText("Enter Title");
        dialog.setContentText("Please enter a title for the comic:");
        dialog.initStyle(StageStyle.UTILITY);
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(s -> panelController.setPremise(result.get()));
        return result.isPresent();
    }

    private Results getHTMLOptions() {
        Dialog<Results> dialog = new Dialog<>();
        dialog.setTitle("HTML Export Options");
        dialog.setHeaderText("Options");
        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.setMinWidth(500);
        dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        dialog.initStyle(StageStyle.UTILITY);

        Label premiseLabel = new Label("Please enter the comic title:");
        TextField premise = new TextField(panelController.getPremise());

        Label themeLabel = new Label("Select a theme:");
        ToggleButton themeButton1 = new ToggleButton("Action");
        ToggleButton themeButton2 = new ToggleButton("Notebook");
        ToggleButton themeButton3 = new ToggleButton("Horror");
        ToggleGroup themeToggleGroup = new ToggleGroup();
        themeButton1.setToggleGroup(themeToggleGroup);
        themeButton2.setToggleGroup(themeToggleGroup);
        themeButton3.setToggleGroup(themeToggleGroup);
        themeToggleGroup.selectToggle(themeButton2);

        //mandatory selection of theme toggle
        themeToggleGroup.selectedToggleProperty().addListener((obsVal, oldVal, newVal) -> {
            if (newVal == null)
                oldVal.setSelected(true);
        });

        HBox themeButtons = new HBox(themeButton1, themeButton2, themeButton3);
        ToggleSwitch backgroundOption = new ToggleSwitch("Enable decorated background?");
        ToggleSwitch fontOption = new ToggleSwitch("Enable decorated title font?");
        ToggleSwitch endingOption = new ToggleSwitch("Enable ending panel?");

        VBox dialogContent = new VBox(new VBox(premiseLabel, premise), new VBox(themeLabel, themeButtons), backgroundOption, fontOption, endingOption);
        dialogContent.setSpacing(15);
        dialogPane.setContent(dialogContent);
        dialog.setResultConverter((ButtonType button) -> {
            if (button == ButtonType.OK) {
                return new Results(premise.getText(), themeToggleGroup.getSelectedToggle().toString(),
                        backgroundOption.isEnabled(), fontOption.isEnabled(), endingOption.isEnabled());
            }
            return null;
        });
        Optional<Results> optionalResult = dialog.showAndWait();
        return optionalResult.orElse(null);
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

    private static class Results {
        private final String premise;
        private final String theme;
        private final boolean isBackgroundEnabled;
        private final boolean isFontEnabled;
        private final boolean isEndingEnabled;

        public Results(String premise, String theme, boolean enableBackground, boolean enableFont, boolean enableEnding) {
            this.premise = premise;
            this.theme = theme;
            this.isBackgroundEnabled = enableBackground;
            this.isFontEnabled = enableFont;
            this.isEndingEnabled = enableEnding;
        }

        public String getPremise() {
            return premise;
        }

        public String getTheme() {
            if (theme.contains("Action")) {
                return "Action";
            } else if (theme.contains("Horror")) {
                return "Horror";
            } else {
                return "Notebook";
            }
        }

        public Boolean getBackgroundEnabled() {
            return isBackgroundEnabled;
        }

        public boolean isFontEnabled() {
            return isFontEnabled;
        }

        public boolean isEndingEnabled() {
            return isEndingEnabled;
        }
    }
}
