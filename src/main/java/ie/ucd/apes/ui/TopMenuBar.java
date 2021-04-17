package ie.ucd.apes.ui;

import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class TopMenuBar extends MenuBar {
    public TopMenuBar() {
        Menu menu1 = new Menu("File");
        menu1.getItems().add(new MenuItem("Save"));
        menu1.getItems().add(new MenuItem("Export"));

        Label helpLabel = new Label("Help");
        helpLabel.setOnMouseClicked(e -> new HelpBox());
        Menu helpMenu = new Menu("", helpLabel);
        this.getMenus().addAll(menu1, helpMenu);
    }
}
