package ie.ucd.apes.ui;

import javafx.scene.control.Alert;

public class HelpBox extends Alert {
    public HelpBox() {
        super(AlertType.INFORMATION);
        setTitle("How To Use");
        setHeaderText("Help");
        getDialogPane().setMinWidth(900);
        setResizable(true);
        setContentText("1.\tYou can first select a character by clicking the left or right button.\n" +
                "2.\tTo flip character, first click on the character and then click the flip button.\n" +
                "3.\tTo change gender, first click on the desired character and then click the gender button.\n" +
                "4.\tTo add left and right dialogues/thought bubbles, select a character and click dialogue/thought button, after that click on the text box and enter text.\n" +
                "5.\tTo add top and bottom narrative bar, click on the top/bottom bottom button and click on the text box to enter text.\n" +
                "6.\tTo save the current pane, just click on the save button and the current pane will be saved.\n" +
                "7.\tTo load a pane, click on the thumbnail in the scroll pane and it will load it into the working pane/\n" +
                "8.\tTo work on a new pane, save the current pane first and a new pane will be generated automatically.\n"
        );
        showAndWait();
    }
}
