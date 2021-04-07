package ie.ucd.apes.ui;


import ie.ucd.apes.controller.PanelController;
import ie.ucd.apes.ui.stage.StageView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.Map;

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
    }

    public void saveToScrollingPane() {
        if (panelController.isCurrentStateNew()) {
            save(stageView.takeScreenshot());
            panelController.saveAndResetPanel(container.getChildren().size() - 1);
        } else {
            int position = orderingMap.getOrDefault(panelController.getCurrentId(), -1);
            update(stageView.takeScreenshot(), position);
            panelController.saveAndResetPanel(position);
        }
        stageView.render();
    }

    private void update(Image image, int position) {
        CapturedScene scene = (CapturedScene) container.getChildren().get(position);
        scene.setImage(image);
    }

    // frontend changes
    private void save(Image image) {
        CapturedScene scene = new CapturedScene(image, container.getChildren().size());
        scene.setOnMouseClicked((e) -> {
            scene.requestFocus();
            loadData(scene.getPosition());
        });
        orderingMap.put(panelController.getCurrentId(), container.getChildren().size());
        container.getChildren().add(scene);
    }

    private void loadData(int position) {
        panelController.loadPanel(position);
        stageView.render();
    }
}
