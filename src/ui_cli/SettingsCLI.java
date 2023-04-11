package src.ui_cli;
import src.interfaces.BoardStrategy;
import src.interfaces.SettingsIF;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class SettingsCLI implements SettingsIF{

    /** The input to scan the user's input **/
    private Scanner input;

    /** ArrayList representing valid inputs for the settings */
    private ArrayList<String> settingsInput = new  ArrayList<>();

    private String undoStatus;

    private String showMovesStatus;

    private String boardStratStatus;

    public SettingsCLI(){
        this.input = new Scanner(System.in);
        
        this.undoStatus = "";
        this.showMovesStatus = "";
        this.boardStratStatus = "";

        String[] inputArray = new String[]{"0", "1", "2", "3", "4", "5", "6"};
        this.settingsInput.addAll(Arrays.asList(inputArray));
    }

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
