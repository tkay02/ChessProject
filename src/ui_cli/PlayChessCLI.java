package src.ui_cli;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import src.interfaces.PlayChessIF;

/**
 * A class that provides a command line interface for playing chess.
 *
 * 
 * @author Nolan Flinchum, Thomas Kay, Joseph Oladeji, Levi Sweat
 * @version 4/19/2023
 */
public class PlayChessCLI implements PlayChessIF {
    
    /** The input to scan the user's input **/
    private Scanner input;

    /** ArrayList representing valid inputs to play **/
    private ArrayList<String> playInput = new ArrayList<>();

    /** ArrayList that stores all valid rank input **/
    private ArrayList<String> rankInput = new ArrayList<>();

    /** ArrayList that stores all valid file input **/
    private ArrayList<String> fileInput = new ArrayList<>();
    

    /**
     * Constructor for PlayChessCLI. Initializes input as user input. Has boolean values to append
     * additional features to the menu as undo and showing the valid moves for pieces.
     * 
     * @param boolean undoStatus Determines if undo is included as a feature. Enables feature if
     * true.
     * @param boolean showMovesStatus Determines if show moves is included as a feature. Enables
     * feature if true.
     */
    public PlayChessCLI(boolean undoStatus, boolean showMovesStatus){
        this.input = new Scanner(System.in);

        //2 -> undo, 4 -> show moves
        this.playInput.addAll(Arrays.asList("0", "1", "3", "5", "6"));
        if(undoStatus) playInput.add("2");
        if(showMovesStatus) playInput.add("4");

        this.rankInput.addAll(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8"));
        this.fileInput.addAll(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H"));

    }

    /**
     * Menu layout to navigate through the features within the game of chess. Goes on an infinite 
     * loop to accept input from the user to determine which part of the features that they want to 
     * choose for the game. The sections are divided into move, undo (if enabled), redo, show moves
     * (if enabled), save game, and concede. Selecting a valid input ends the loop.
     * 
     * @return The string of valid input that the user inputted.
     */
    public String playChessDisplay(){
        String result = "";
		boolean promptAgain = true;
		while(promptAgain){
            System.out.println("Play Chess:\n===========\n1: Move");	
            if(playInput.contains("2")) System.out.println("2: Undo");
            System.out.println("3: Redo");
            if(playInput.contains("4")) System.out.println("4: Show moves");
            System.out.println("5: Save Game\n6: Offer draw\n0: Concede and exit game");
            result = input.nextLine();
			//If the player's input is part of the list of answers, ends loop
			if(playInput.contains(result)) promptAgain = false;
            else System.out.println("\nIncorrect Input. Try Again.\n");
		}
        return result;
    }

    /**
     * Asks the user to input starting position and destination for moving a chess piece. If the
     * user inputs a position that is not valid or in the correct format, the user will be asked
     * again to put in a proper input while showing an example of one.
     * 
     * @return A string array that contains the ranks and files of the piece starting position
     * and final position.
     */
    public String[] makeMove(){
        String result = "";
        boolean promptAgain = true;
        String[] parts = new String[]{};
        while(promptAgain){
            System.out.println("Make Move:");
            try{
                result = input.nextLine();
                parts = result.split(",");
                parts[0] = parts[0].trim();
                parts[1] = parts[1].trim();
                if(fileInput.contains(String.valueOf(parts[0].charAt(0))) && 
                rankInput.contains(String.valueOf(parts[0].charAt(1))) &&
                fileInput.contains(String.valueOf(parts[1].charAt(0))) &&
                rankInput.contains(String.valueOf(parts[1].charAt(1)))){
                    promptAgain = false;
                }
                else{
                    System.out.println("Incorrect Input. You're input should look like: " + 
                                       "A1, A2. Try Again.");
                }
            } catch(Exception e){
                System.out.println("Incorrect Input. You're input should look like: " + 
                                   "A1, A2. Try Again.");
            }
        }
        return parts;
    }
}
