package src.ui_cli;
import src.interfaces.PlayChessIF;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Play_Chess_CLI implements PlayChessIF {
    
    /** The input to scan the user's input **/
    private Scanner input;

    /** ArrayList representing valid inputs to play */
    private ArrayList<String> playInput = new  ArrayList<>();

    public Play_Chess_CLI(boolean undoStatus, boolean showMovesStatus){
        this.input = new Scanner(System.in);

        //2 -> undo, 4 -> show moves
        String[] playArray = new String[]{"0", "1", "3", "5"};
        this.playInput.addAll(Arrays.asList(playArray));
        if(undoStatus) playInput.add("2");
        if(showMovesStatus) playInput.add("4");
    }

    public String playChessDisplay(){
        String result = "";
		boolean promptAgain = true;
		while(promptAgain){
            System.out.println("Play Chess:\n===========\n1: Move");	
            if(playInput.contains("2")) System.out.println("2: Undo");
            System.out.println("3: Redo");
            if(playInput.contains("4")) System.out.println("4: Show moves");
            System.out.println("5: Save Game\n0: Concede and exit game");
            result = input.nextLine();
			//If the player's input is part of the list of answers, ends loop
			//Repeats otherwise
			if(playInput.contains(result)) promptAgain = false;
            else System.out.println("\nIncorrect Input. Try Again.\n");
		}
        return result;
    }

    // public String playGame(){
    //     boolean keepGoing = true;
    //     String result = "";
    //     while(keepGoing){
    //         String userInput = playChessDisplay();
    //         switch(userInput){
    //             case "0":
    //                 keepGoing = false;
    //                 result = "0"
    //                 break;
    //             case "1":
    //                 result = "1";
    //         }
    //     }

    //     return result;
    // }
}
