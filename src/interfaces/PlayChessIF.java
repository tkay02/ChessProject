package src.interfaces;

/**
 * A interface that defines methods for the command line interface to play chess.
 *
 * @author Nolan Flinchum, Thomas Kay, Joseph Oladeji, Levi Sweat (100%)
 * @version 4/19/2023
 */
public interface PlayChessIF {
    
     /**
     * Menu layout to navigate through the features within the game of chess. Goes on an infinite 
     * loop to accept input from the user to determine which part of the features that they want to 
     * choose for the game. The sections are divided into move, undo (if enabled), redo, show moves
     * (if enabled), save game, and concede. Selecting a valid input ends the loop.
     * 
     * @return The string of valid input that the user inputted.
     */
     public String playChessDisplay();

    /**
     * Asks the user to input starting position and destination for moving a chess piece. If the
     * user inputs a position that is not valid or in the correct format, the user will be asked
     * again to put in a proper input while showing an example of one.
     * 
     * @return A string array that contains the ranks and files of the piece starting position
     * and final position.
     */
    public String[] makeMove();

}
