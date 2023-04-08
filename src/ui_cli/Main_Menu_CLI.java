package src.ui_cli;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import src.interfaces.MainMenuIF;

public class Main_Menu_CLI implements MainMenuIF{
        
    /** ArrayList representing valid inputs for the main menu */
    private ArrayList<String> mainMenuInput = new  ArrayList<>();


    /** The input to scan the user's input **/
    private Scanner input;

    public Main_Menu_CLI(){
		this.input = new Scanner(System.in);

        String[] menuInput = new String[]{"0", "1", "2", "3", "4", "5"};
        this.mainMenuInput.addAll(Arrays.asList(menuInput));
    }

    public String userInteraction(){
        String result = "";
		boolean promptAgain = true;
		while(promptAgain){
            System.out.println("Main Menu:\n===========\n1: Play Chess\n2: View Rules\n" + 
            "3: Define Players\n4: Settings\n5: Load Game\n0: Quit");			
            result = input.nextLine();
			//If the player's input is part of the list of answers, ends loop
			//Repeats otherwise
			if(mainMenuInput.contains(result)) promptAgain = false;
            else System.out.println("\nIncorrect Input. Try Again.\n");
		}
        return result;
    }
}
