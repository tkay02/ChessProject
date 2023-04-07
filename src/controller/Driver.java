package src.controller;

import src.interfaces.BoardStrategy;
import src.ui_cli.*;

import java.util.Scanner;

/**
 * Driver to run the Chess project. Creates a Chess match to run the majority of the program, and
 * allows the user to select the boardStrategy from the main menu and begin playing.
 * 
 * @author Nolan Flinchum, Thomas Kay, Joseph Oladeji, Levi Sweat (100%)
 * @version 3/27/2023
 */

public class Driver {

	/**
	 * Main method where the project starts.
	 * 
	 * @param args array of arguments from the command line
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Chess match = null;
		Boolean correctInput = false;
		String input;
		BoardStrategy boardColor = null;
		System.out.println("Welcome to ChessMeister! Main Menu:\n");
		while(!correctInput){//continue the loop if the input is incorrect, reprompt user
			System.out.println("Please select a board color:\n1) Monotone Board\n2) Colored Board");
			input = in.nextLine(); //take in user's input
			switch(input){
				case "1":
					boardColor = new Board_Mono_CLI();
					correctInput = true;
					break;
				case "2":
					boardColor = new Board_Color_CLI();
					correctInput = true;
					break;	
			}
		}
		//creates a new Chess match based on the user's desired board color
		match = new Chess(boardColor);
		in.close();
	}

}
