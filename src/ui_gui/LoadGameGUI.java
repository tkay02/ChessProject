package src.ui_gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

/**
 * GUI class for the dialog box that pops up when the user chooses to load in a saved game.
 * 
 * @author Nolan Flinchum (%50), Thomas Kay, Joseph Oladeji (50%), Levi Sweat
 * @version 5/8/2023
 */
public class LoadGameGUI extends Dialog<String> {

    /** Absolute path the file that holds a saved game */
    private static String filePath = "";

    /**
     * Constructor that builds the dialog box used for loading in a game.
     * 
     * @param fileName name of the file that holds a saved game
     */
    public LoadGameGUI() {
        super();
        this.setTitle("Load Game");
        buildGUI();
    }

    /**
     * Build the GUI that appears on the dialog box.
     */
    private void buildGUI() {
        // Define text field to display chosen file and the button to choose a file
        VBox window = new VBox();
        TextField tf = new TextField(filePath);
        tf.setEditable(false);
        window.getChildren().add(tf);
        Button loadButton = new Button("Choose a file to load");
        loadButton.setOnAction((event) -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File(System.getProperty("user.dir") + "/src/databases"));
            FileChooser.ExtensionFilter eFilter = new FileChooser.ExtensionFilter("Text Files", "*.txt");
            fileChooser.getExtensionFilters().add(eFilter);
            System.out.println(fileChooser.getInitialFileName());
            File selectedFile = fileChooser.showOpenDialog(loadButton.getScene().getWindow());
            if(selectedFile != null){
                setFilePath(selectedFile.getAbsolutePath());
                tf.setText(selectedFile.getName());
            }
        });
        window.getChildren().add(loadButton);
        window.setAlignment(Pos.CENTER);

        // add OK and CANCEL buttons
        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        Button button = (Button) getDialogPane().lookupButton(ButtonType.OK);
        button.addEventFilter(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (filePath.isEmpty()) event.consume();
                else{
                    String gameContent = loadGame(filePath);
                    // start a new chess game using game content
                    System.out.println(gameContent);
                }
            }
        });



        // Add the pane with the text field and button to dialog box
        getDialogPane().setContent(window);
        getDialogPane().setPrefSize(400, 200);
    }

    /**
     * Loads a saved game from a file specified by the given file path.
     * 
     * @param filePath The file path of the saved game.
     * @return A string containing the contents of the saved game file, or an empty string
     * if there was an error loading the file.
     */
    public String loadGame(String filePath){
        String fileContent = "";
        try{
            FileReader gameFile = new FileReader(filePath);
            Scanner fileScanner = new Scanner(gameFile);
            fileContent = fileScanner.nextLine();
            fileScanner.close();
        }catch(FileNotFoundException e){
            System.out.println("There was trouble loading/opening the game instance!");
        }
        return fileContent;
    }

    /**
     * Setter for the file field that holds the name of a file.
     * 
     * @param file name of the file that holds a saved game
     */
    private void setFilePath(String filePath){
        LoadGameGUI.filePath = filePath;
    }

    /**
     * This method will retrieve and return the file path initialized
     * from the fileChooser.
     * @return filePath - Chosen file path the user would like to load a game from.
     */
    public static String getFilePath(){
        return filePath;
    }

    
}