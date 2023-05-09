package src.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import src.controller.GUIRunner;
import src.controller.CLIRunner;

/**
 * Entry Point of program, prompts the user to ask if they would like to play chess through the
 * command line or through the GUI. Calls the respective runner class to run each type.
 * 
 * Authors: Levi Sweat, Joseph Oladeji, Nolan Flinchum, Thomas Kay
 */
public class Driver {

    /**
     * Main function, entry point for program, determines if using CLI or GUI.
     * @param args any input when the file is initially run
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
        ArrayList<String> possibleInput = new  ArrayList<>();
        boolean promptAgain = true;
        possibleInput.addAll(Arrays.asList("1", "2")); //adds possible inputs to array
        String userInput = "";
        while(promptAgain){ //continue while the user does not input one or two
            System.out.println("Would you like to play chess through the command line, or" + 
                               "through the GUI application?\nType 1 for command line, 2 for" + 
                               " GUI application:");
            userInput = sc.nextLine();
            if(possibleInput.contains(userInput)) promptAgain = false;
        }
        if(userInput.equals("1")){ //CLI chess
            CLIRunner.run();
        }
        else if(userInput.equals("2")){ //GUI chess
            GUIRunner.run(args);
        }
	}
}
