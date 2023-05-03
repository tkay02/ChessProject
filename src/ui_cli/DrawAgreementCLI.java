package src.ui_cli;

import java.util.Scanner;
import src.interfaces.DrawByAgreementIF;

/**
 * A class that provides functionality for managing draw agreements between players using a
 * command-line interface.
 * 
 * @author Nolan Flinchum, Thomas Kay, Joseph Oladeji, Levi Sweat (100%)
 * @version 4/19/2023
 */
public class DrawAgreementCLI implements DrawByAgreementIF{

    /**
     * Prompts the player to either end the match as a draw or continue playing.
     *
     * @return The player's response as a String, which will be "Y" if they want to end the match
     * as a draw, or anything else if they want to continue playing.
     */
    public String respondToDraw(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Type Y to end the match as a draw. " +
                           "Enter anything else to continue playing");
        String input = sc.nextLine().toUpperCase();
        return input;
    }
    
}
