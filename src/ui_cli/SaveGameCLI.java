package src.ui_cli;

import java.io.FileWriter;
import java.util.Scanner;

import src.interfaces.SaveGameIF;

/**
 * A class that provides functionality for saving a game state to a file using a specified
 * implementation of the SaveGameIF interface.
 * @author Nolan Flinchum, Thomas Kay, Joseph Oladeji, Levi Sweat
 * @version 4/19/2023
 */
public class SaveGameCLI implements SaveGameIF {
    
    /** Scanner for user input */
    private Scanner input;

    /** This method initializes the scanner to use System in. */
    public SaveGameCLI(){
        input = new Scanner(System.in);
    }

   /**
    * Prompts the user to enter a file path to save the current game state to.
    * 
    * @return The absolute file path entered by the user.
    */
    public String promptSaveGame(){
        System.out.print("Enter file path (YOU MUST ENTER AN ABSOLUTE PATH ie." + 
        " C:\\cms\\Downloads\\Game.txt): ");
        return input.next();
    }


    /**
     * Saves the current game state to the specified file path with the specified content.
     *
     * @param filePath The absolute file path to save the game state to.
     * @param fileContent The content to be written to the file.
     */
    public void saveGame(String filePath, String fileContent){
        try{
            FileWriter gameFile = new FileWriter(filePath, false); 
            gameFile.write(fileContent);
            gameFile.close();
        }catch(Exception e){
            System.out.println("There was trouble saving the game instance!");
        }
    }

}
