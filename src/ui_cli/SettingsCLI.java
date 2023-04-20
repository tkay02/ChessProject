package src.ui_cli;
import src.interfaces.BoardStrategy;
import src.interfaces.SettingsIF;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A class that implements the SettingsIF interface to define the settings of a
 * game in the command-line interface (CLI). The SettingsCLI class provides 
 * implementations for the displaySettings method, which is required by the SettingsIF interface.
 * The class also defines instance variables to keep track of the current settings, 
 * including the board strategy, undo status, and show moves status.
 * 
 * @author Nolan Flinchum, Thomas Kay, Joseph Oladeji, Levi Sweat (100%)
 * @version 4/19/2023
 */
public class SettingsCLI implements SettingsIF{

    /** The input to scan the user's input **/
    private Scanner input;

    /** ArrayList representing valid inputs for the settings */
    private ArrayList<String> settingsInput = new  ArrayList<>();

    /** Current undo status of the game */
    private String undoStatus;

    /** Current show moves status of the game */
    private String showMovesStatus;

    /** Current Board Strategy status of the game */
    private String boardStratStatus;

    /**
     * Constructs a new SettingsCLI object with a new Scanner object that reads from the 
     * standard input stream. Initializes the `undoStatus`, `showMovesStatus`, and 
     *`boardStratStatus` fields to empty strings.  Adds the values "0", "1", "2", "3", "4", "5",
      and "6" to the `settingsInput` list.
     */
    public SettingsCLI(){
        this.input = new Scanner(System.in);
        
        this.undoStatus = "";
        this.showMovesStatus = "";
        this.boardStratStatus = "";

        this.settingsInput.addAll(Arrays.asList("0", "1", "2", "3", "4", "5", "6"));
    }

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
    public String displaySettings(BoardStrategy boardStrat, boolean undo, boolean showMoves) {
        String result = "";
        boolean promptAgain = true;
        boardStratStatus = (boardStrat instanceof BoardColorCLI) ? "color" : "mono";
        undoStatus = undo ? "on" : "off";
        showMovesStatus = showMoves ? "on" : "off";
		while(promptAgain){
            System.out.println("Settings:\n===========\nBoard: " + boardStratStatus + "\nUndo is "
                               + undoStatus + "\nShow moves is " + showMovesStatus + "\n\n" + 
                               "1: Set Mono board\n2: Set color board\n3: Undo on\n4: Undo off" +
                               "\n5: Show moves on\n6: Show moves off\n0: Main Menu");	
            result = input.nextLine();
			//If the player's input is part of the list of answers, ends loop
			//Repeats otherwise
			if(settingsInput.contains(result)) promptAgain = false;
            else System.out.println("\nIncorrect Input. Try Again.\n");
		}
        return result;
    }
    
}
