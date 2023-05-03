package src.interfaces;

/**
 * A interface that implements the LoadGameIF interface to provide functionality for
 * loading a saved game in the command-line interface (CLI). The LoadGameCLI class provides an
 * implementation for the `loadGame` method, which reads a saved game file and initializes the 
 * game state accordingly. The class also defines a constructor that sets up a `Scanner` object 
 * for user input and an instance variable to keep track of the saved game file name.
 *
 * @author Nolan Flinchum, Thomas Kay, Joseph Oladeji (100%), Levi Sweat
 * @version 4/19/2023
 */
public interface LoadGameIF{
    
    /**
     * Loads a saved game from a file specified by the given file path.
     * 
     * @param filePath The file path of the saved game.
     * @return A string containing the contents of the saved game file, or an empty string
     * if there was an error loading the file.
     */
    public String loadGame(String filePath);

    /**
     * Prompts the user to enter a file path and returns it as a String.
     * 
     * @return The file path entered by the user.
     */
    public String getFilePath();
}