package src.interfaces;

/**
 * A interface that defines methods for the command line interface
 * to show the main menu of the chess game.
 * 
 * @author Nolan Flinchum, Thomas Kay, Joseph Oladeji (50%), Levi Sweat (50%)
 * @version 4/19/2023
 */
public interface MainMenuIF {

    /**
     * Displays the main menu of the game and prompts the user for input.
     *
     * @return A String representing the user's chosen menu option.
     */
    public String userInteraction();

    /**
     * Prompts the user to sign up with a given question and returns their response.
     *
     * @param question A String representing the question to ask the user.
     * @return A String representing the user's response.
     */
    public String promptSignUp(String question);

    /**
     * Prompts the user to sign in and returns their username and password as a String array.
     *
     * @return A String array containing the user's username and password.
     */
    public String[] promptSignIn();

}
