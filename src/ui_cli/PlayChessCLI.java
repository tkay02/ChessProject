package src.ui_cli;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import src.interfaces.PlayChessIF;

public class PlayChessCLI implements PlayChessIF {
    
    /** The input to scan the user's input **/
    private Scanner input;

    /** ArrayList representing valid inputs to play **/
    private ArrayList<String> playInput = new ArrayList<>();

    private ArrayList<String> rankInput = new ArrayList<>();

    private ArrayList<String> fileInput = new ArrayList<>();
    

    public PlayChessCLI(boolean undoStatus, boolean showMovesStatus){
        this.input = new Scanner(System.in);

        //2 -> undo, 4 -> show moves
        this.playInput.addAll(Arrays.asList("0", "1", "3", "5"));
        if(undoStatus) playInput.add("2");
        if(showMovesStatus) playInput.add("4");

        this.rankInput.addAll(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8"));
        this.fileInput.addAll(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H"));

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
			if(playInput.contains(result)) promptAgain = false;
            else System.out.println("\nIncorrect Input. Try Again.\n");
		}
        return result;
    }

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
            } catch(Exception e){}
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
        }
        return parts;
    }
}
