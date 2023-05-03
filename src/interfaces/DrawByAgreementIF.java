package src.interfaces;

/**
 * An interface for classes that provide functionality for managing draw agreements between 
 * players using a command-line interface.
 * 
 * @author Joseph Oladeji, Levi Sweat, Nolan Flinchum, Thomas Kay
 */
public interface DrawByAgreementIF {

    /**
     * Prompts the player to either end the match as a draw or continue playing.
     *
     * @return The player's response as a String, which will be "Y" if they want to end the match
     * as a draw, or anything else if they want to continue playing.
     */
    public String respondToDraw();
}
