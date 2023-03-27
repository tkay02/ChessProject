package src.controller;
/**
 * Driver to run the Chess project.
 * 
 * @author Nolan Flinchum, Thomas Kay, Joseph Oladeji, Levi Sweat
 * @version 3/27/2023
 */
import src.interfaces.BoardStrategy;
import src.ui_di.*;
import java.util.Scanner;

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
		System.out.println("Welcome to ChessMeister!\n\n");
		while(!correctInput){
			System.out.println("Please select a board color:\n1) Monotone Board\n2) Colored Board");
			input = in.nextLine();
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
		match = new Chess(boardColor);
		in.close();
	}

}
