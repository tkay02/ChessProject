package src.interfaces;

/**
 * An interface that defines the methods required to define a player in a game.
 * Classes that implement this interface must provide implementations for the 
 * definePlayer method.
 * @author Joseph Oladeji, Nolan Flinchum, Levi Sweat, Thomas Kay
 */
public interface DefinePlayerIF {

     /**
     * Prompts the user to enter the name of a player.
     *
     * @param playerNum The number of the player being defined.
     * @return The name of the player entered by the user as a String.
     */
    public String definePlayer(int playerNum);
}