package ie.ucd.apes.ui;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class TopMenuBar extends MenuBar {
    public TopMenuBar() {
        Menu menu1 = new Menu("File");
        menu1.getItems().add(new MenuItem("Save"));
        menu1.getItems().add(new MenuItem("Export"));
        Menu menu2 = new Menu("View");
        Menu menu3 = new Menu("Configure");
        this.getMenus().addAll(menu1, menu2, menu3);
    }
}
