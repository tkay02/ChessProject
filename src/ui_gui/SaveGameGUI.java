package src.ui_gui;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * GUI class for the dialog box that pops up when the user chooses to save a game.
 * 
 * @author Nolan Flinchum, Thomas Kay, Joseph Oladeji, Levi Sweat
 * @version 5/8/2023
 */
public class SaveGameGUI extends Dialog<String> {

    /** Name of the file that the user names their game */
    private String fileName = "";

    /**
     * Constructor that builds the dialog box used for saving a game.
     * 
     * @param fileName name of the file that will hold a saved game
     */
    public SaveGameGUI() {
        super();
        this.setTitle("Save Game");
        buildGUI();
    }

    /**
     * Build the GUI that appears on the dialog box.
     */
    private void buildGUI() {
        // Define label and text field
        VBox window = new VBox();
        String message = "Name of the file you would like to save to (without the extension):";
        Label label = new Label(message);
        TextField tf = new TextField();
        window.getChildren().addAll(label, tf);
        window.setAlignment(Pos.CENTER);

        // Add the pane with the text field and label to dialog box
        getDialogPane().setContent(window);
        getDialogPane().setPrefSize(400, 200);

        // add OK and CANCEL buttons
        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        Button button = (Button) getDialogPane().lookupButton(ButtonType.OK);
        button.addEventFilter(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String text = tf.getText();
                if (text.isEmpty()) event.consume();
                else setFileName(text);
            }
        });
    }

    /**
     * Setter for the file field that holds the name of a file.
     * 
     * @param file name of the file that will hold a saved game
     */
    private void setFileName(String fileName){
        this.fileName = fileName;
    }

}
