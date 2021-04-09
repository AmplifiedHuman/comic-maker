package ie.ucd.apes.ui;


import ie.ucd.apes.controller.PanelController;
import ie.ucd.apes.ui.stage.StageView;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ScrollingPane extends ScrollPane {
    private final HBox container;
    private final PanelController panelController;
    private final StageView stageView;
    private final Map<String, Integer> orderingMap;

    public ScrollingPane(PanelController panelController, StageView stageView) {
        this.panelController = panelController;
        this.stageView = stageView;
        orderingMap = new HashMap<>();
        setPrefWidth(800);
        setPrefHeight(200);
        setMinHeight(200);
        isPannable();
        setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        setFitToHeight(true);
        setFitToWidth(true);
        HBox.setHgrow(this, Priority.ALWAYS);
        VBox.setVgrow(this, Priority.ALWAYS);
        container = new HBox();
        setContent(container);
        getStyleClass().clear();
        getStyleClass().add("scroll-pane");
    }

    public void saveToScrollingPane() {
        if (panelController.isCurrentStateNew()) {
            save(stageView.takeScreenshot(), panelController.getCurrentId());
            panelController.save(container.getChildren().size() - 1);
        } else {
            int position = orderingMap.getOrDefault(panelController.getCurrentId(), -1);
            update(stageView.takeScreenshot(), position);
            panelController.save(position);
        }
        panelController.reset();
        stageView.render();
    }

    public void deleteFromScrollingPane() {
        if (alertDelete()) {
            String currentId = panelController.getCurrentId();
            if (orderingMap.size() > 0 && orderingMap.containsKey(currentId)) {
                int targetPosition = orderingMap.get(currentId);
                orderingMap.remove(currentId);
                container.getChildren().remove(targetPosition);
                updateScenePositions(targetPosition);
                panelController.delete(targetPosition, currentId);
                // load next closest pane
                if (orderingMap.isEmpty()) {
                    // if empty then just reset pane
                    panelController.reset();
                    stageView.render();
                } else if (targetPosition >= orderingMap.size()) {
                    // previous last item was deleted, so load current last
                    loadData(orderingMap.size() - 1);
                    container.getChildren().get(orderingMap.size() - 1).requestFocus();
                } else {
                    // load new pane at current position
                    loadData(targetPosition);
                    container.getChildren().get(targetPosition).requestFocus();
                }
            } else if (!orderingMap.containsKey(currentId)) {
                panelController.reset();
                stageView.render();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error");
                alert.setHeaderText("Illegal Action!");
                alert.setContentText("Cannot delete from empty scroll pane");
                alert.showAndWait();
            }
        }
    }

    private void updateScenePositions(int targetPosition) {
        // update entries that come after currentPosition
        for (Map.Entry<String, Integer> entry : orderingMap.entrySet()) {
            int currentPosition = entry.getValue();
            if (currentPosition > targetPosition) {
                entry.setValue(currentPosition - 1);
            }
        }
    }

    private void update(Image image, int position) {
        CapturedScene scene = (CapturedScene) container.getChildren().get(position);
        scene.setImage(image);
    }

    // frontend changes
    private void save(Image image, String id) {
        CapturedScene scene = new CapturedScene(image);
        scene.setOnMouseClicked((e) -> {
            if (panelController.isEdited() && !panelController.isDefaultState() && alertSave()) {
                saveToScrollingPane();
            }
            scene.requestFocus();
            loadData(orderingMap.get(id));
        });
        orderingMap.put(panelController.getCurrentId(), container.getChildren().size());
        container.getChildren().add(scene);
    }

    private void loadData(int position) {
        panelController.loadPanel(position);
        stageView.render();
    }

    public boolean alertSave() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Save or discard?");
        alert.setContentText("Do you want to save any changes made?");
        ButtonType save = new ButtonType("Save", ButtonBar.ButtonData.YES);
        ButtonType discard = new ButtonType("Discard", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(save, discard);
        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == save;
    }

    public boolean alertDelete() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Scene");
        if (panelController.isCurrentStateNew()) {
            alert.setContentText("Are you sure you want to clear this scene?");
        } else {
            alert.setContentText("Are you sure you want to delete this scene?");
        }
        String message = panelController.isCurrentStateNew() ? "Clear" : "Delete";
        ButtonType delete = new ButtonType(message, ButtonBar.ButtonData.YES);
        ButtonType keep = new ButtonType("Keep", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(delete, keep);
        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == delete;
    }
}
