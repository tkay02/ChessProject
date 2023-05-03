package src.interfaces;

/**
 * A interface to define the rules of a game in the 
 * command-line interface (CLI).
 * 
 * @author Nolan Flinchum, Thomas Kay, Joseph Oladeji, Levi Sweat
 * @version 4/19/2023
 */
public interface RulesIF {
    
    /**
     * Menu layout to navigate through the rules for playing chess. Goes on an infinite loop to accept input
     * from the user to determine which part of the rules that they want to read. The sections are divided into
     * board setup, the king's movement, the queen's movement, the bishop's movement, the knight's movement, the
     * rook's movement, the pawn's movement, and the overview of the game from 1-8. Input of 0 ends the loop.
     * 
     * @return The string of 0 when the user is finished reading over the rules.
     */
    public String displayRules();
}
