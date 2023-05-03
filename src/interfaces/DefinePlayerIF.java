package src.interfaces;

/**
 * Interface used when prompting the user for the players of the match.
 * 
 * @author Nolan Flinchum, Thomas Kay, Joseph Oladeji (100%), Levi Sweat
 * @version 3/27/2023
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