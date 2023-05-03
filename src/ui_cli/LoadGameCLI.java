package src.ui_cli;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import src.interfaces.LoadGameIF;

/**
 * A class that implements the LoadGameIF interface to provide functionality for
 * loading a saved game in the command-line interface (CLI). The LoadGameCLI class provides an
 * implementation for the `loadGame` method, which reads a saved game file and initializes the 
 * game state accordingly. The class also defines a constructor that sets up a `Scanner` object 
 * for user input and an instance variable to keep track of the saved game file name.
 *
 * @author Nolan Flinchum, Thomas Kay, Joseph Oladeji (100%), Levi Sweat
 * @version 4/19/2023
 */
public class LoadGameCLI implements LoadGameIF{
    
    /** The input to scan the user's input **/
    private Scanner input;

    /**
     * Constructs a LoadGameCLI object and sets up a Scanner object for user input.
     */
    public LoadGameCLI(){
        input = new Scanner(System.in);
    }

    /**
     * Prompts the user to enter a file path and returns it as a String.
     * 
     * @return The file path entered by the user.
     */
    public String getFilePath(){
        System.out.println("\nEnter file path: ");
        return input.next();
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
    
}
