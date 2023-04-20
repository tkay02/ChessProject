package src.interfaces;
/**
 * A class that provides functionality for saving a game state to a file using a specified
 * implementation of the SaveGameIF interface.
 * 
 * @author Nolan Flinchum, Thomas Kay, Joseph Oladeji, Levi Sweat
 * @version 4/19/2023
 */
public interface SaveGameIF{

    /**
    * Prompts the user to enter a file path to save the current game state to.
    * 
    * @return The absolute file path entered by the user.
    */
    public String promptSaveGame();

    /**
     * Saves the current game state to the specified file path with the specified content.
     *
     * @param filePath The absolute file path to save the game state to.
     * @param fileContent The content to be written to the file.
     */
    public void saveGame(String filePath, String fileContent);
}