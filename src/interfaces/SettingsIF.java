package src.interfaces;

/** An Interface that defines the settings of a
 * game in the command-line interface (CLI). 
 * 
 * @author Nolan Flinchum, Thomas Kay, Joseph Oladeji, Levi Sweat
 * @version 4/19/2023
 */
public interface SettingsIF {

    /**
     * Displays the current game settings, including the type of board (mono or color), the 
     * status of the undo and show moves features. Prompts the user to select a new setting, and returns 
     * the user's input as a String.
     *
     * @param boardStrat a BoardStrategy object representing the current board strategy being used.
     * @param undo a boolean indicating whether or not the undo feature is currently enabled.
     * @param showMoves a boolean indicating whether or not the show moves feature is currently enabled.
     * @return a String representing the user's selected setting.
     */
    public String displaySettings(BoardStrategy boardStrat, boolean undo, boolean showMoves);
}
