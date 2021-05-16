package ie.ucd.apes.ui;


import ie.ucd.apes.controller.PanelController;
import ie.ucd.apes.entity.DeletedScene;
import ie.ucd.apes.ui.stage.StageView;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;

import java.awt.image.BufferedImage;
import java.util.*;

public class ScrollingPane extends ScrollPane {
    private final HBox container;
    private final PanelController panelController;
    private final StageView stageView;
    private final Map<String, Integer> orderingMap;
    private final Stack<DeletedScene> deletedScenes;
    private Boolean isClicked;

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
        setFocusTraversable(true);
        getStyleClass().add("scroll-pane");
        isClicked = false;
        deletedScenes = new Stack<>();
    }

    public boolean isClicked() {
        return isClicked;
    }

    public void restoreDeleted() {
        if (deletedScenes.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("Illegal Action!");
            alert.setContentText("There are no deleted panels remaining.");
            alert.initStyle(StageStyle.UTILITY);
            alert.showAndWait();
        } else {
            DeletedScene deletedScene = deletedScenes.pop();
            int position = deletedScene.getPosition();
            String id = deletedScene.getId();
            Image image = deletedScene.getImage();
            for (Map.Entry<String, Integer> entry : orderingMap.entrySet()) {
                int currentPosition = entry.getValue();
                if (currentPosition >= position) {
                    entry.setValue(currentPosition + 1);
                }
            }
            orderingMap.put(id, position);
            CapturedScene capturedScene = initPanelListeners(image, id);
            container.getChildren().add(position, capturedScene);
            // update backend changes
            panelController.restoreState(position);
        }
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
        isClicked = true;
        stageView.render();
        isClicked = false;
    }

    public List<BufferedImage> getImages() {
        List<BufferedImage> images = new ArrayList<>();
        for (Node n : container.getChildren()) {
            CapturedScene capturedScene = (CapturedScene) n;
            images.add(SwingFXUtils.fromFXImage(capturedScene.getImage(), null));
        }
        return images;
    }

    public void deleteFromScrollingPane() {
        if (alertDelete()) {
            String currentId = panelController.getCurrentId();
            if (orderingMap.size() > 0 && orderingMap.containsKey(currentId)) {
                int targetPosition = orderingMap.get(currentId);
                orderingMap.remove(currentId);
                deletedScenes.push(new DeletedScene(((CapturedScene) container.getChildren().get(targetPosition)).getImage(),
                        targetPosition, currentId));
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
                alert.initStyle(StageStyle.UTILITY);
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
        CapturedScene scene = initPanelListeners(image, id);
        orderingMap.put(panelController.getCurrentId(), container.getChildren().size());
        container.getChildren().add(scene);
    }

    private CapturedScene initPanelListeners(Image image, String id) {
        CapturedScene scene = new CapturedScene(image);

        scene.setOnMouseClicked((e) -> {
            if (panelController.isEdited() && !panelController.isDefaultState() && alertSave()) {
                saveToScrollingPane();
            }
            scene.requestFocus();
            isClicked = true;
            loadData(orderingMap.get(id));
            isClicked = false;
        });

        scene.getMoveLeftButton().setOnMouseClicked((e) -> moveLeftInScrollingPane(id));
        scene.getMoveRightButton().setOnMouseClicked((e) -> moveRightInScrollingPane(id));
        return scene;
    }

    private void moveRightInScrollingPane(String currentId) {
        if (panelController.isEdited() && !panelController.isDefaultState() && alertSave()) {
            saveToScrollingPane();
        }
        if (orderingMap.containsKey(currentId)) {
            int position = orderingMap.get(currentId);
            if (position < orderingMap.size() - 1) {
                panelController.swapStates(position, position + 1);
                swapScrollPanelImages(position, position + 1);
                saveSwappedPanels(position, position + 1);
                loadData(position + 1);
                setHvalue(position * 1.0 / orderingMap.size());
            }
        }
    }

    private void moveLeftInScrollingPane(String currentId) {
        if (panelController.isEdited() && !panelController.isDefaultState() && alertSave()) {
            saveToScrollingPane();
        }
        if (orderingMap.containsKey(currentId)) {
            int position = orderingMap.get(currentId);
            if (position > 0) {
                panelController.swapStates(position, position - 1);
                swapScrollPanelImages(position, position - 1);
                saveSwappedPanels(position, position - 1);
                loadData(position - 1);
                setHvalue((position - 1) * 1.0 / orderingMap.size());
            }
        }
    }

    private void swapScrollPanelImages(int position1, int position2) {
        CapturedScene position1Scene = (CapturedScene) container.getChildren().get(position1);
        CapturedScene position2Scene = (CapturedScene) container.getChildren().get(position2);
        Image tempSceneImage = position1Scene.getImage();

        //swap images of panels
        update(position2Scene.getImage(), position1);
        update(tempSceneImage, position2);
    }

    private void saveSwappedPanels(int position1, int position2) {
        panelController.loadPanel(position1);
        panelController.save(position1);
        panelController.loadPanel(position2);
        panelController.save(position2);
    }

    private void loadData(int position) {
        panelController.loadPanel(position);
        stageView.render();
    }

    public boolean alertSave() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Save or discard?");
        alert.initStyle(StageStyle.UTILITY);
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
        alert.initStyle(StageStyle.UTILITY);
        String message = panelController.isCurrentStateNew() ? "Clear" : "Delete";
        ButtonType delete = new ButtonType(message, ButtonBar.ButtonData.YES);
        ButtonType keep = new ButtonType("Keep", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(delete, keep);
        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == delete;
    }

    public List<Image> getThumbnails() {
        List<Image> thumbnails = new ArrayList<>();
        for (Node n : container.getChildren()) {
            CapturedScene capturedScene = (CapturedScene) n;
            thumbnails.add(capturedScene.getImage());
        }
        return thumbnails;
    }

    public void resetAllState() {
        container.getChildren().clear();
        orderingMap.clear();
    }
}
