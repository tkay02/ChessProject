package src.ui_cli;

import java.util.Scanner;
import src.interfaces.DefinePlayerIF;

/**
 * A class that provides functionality for defining players using a command-line interface.
 * @author Joseph Oladeji, Levi Sweat, Nolan Flinchum, Thomas Kay
 */
public class DefinePlayerCLI implements DefinePlayerIF {

    
    /** The input to scan the user's input **/
    private Scanner input;

    /** This method initializes the scanner to use System in. */
    public DefinePlayerCLI() {
        input = new Scanner(System.in);
    }

    /**
     * Prompts the user to enter the name of a player.
     *
     * @param playerNum The number of the player being defined.
     * @return The name of the player entered by the user as a String.
     */
    public String definePlayer(int playerNum){
        System.out.print("Enter Player name " + playerNum + ": ");
        return input.nextLine();
    }

}
