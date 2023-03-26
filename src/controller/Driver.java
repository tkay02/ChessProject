package src.controller;
/**
 * Driver to run the Chess project.
 * 
 * @author Nolan Flinchum, Thomas Kay, Joseph Oladeji, Levi Sweat
 * @version 3/27/2023
 */
import src.enums.*;
import src.interfaces.BoardStrategy;
import src.model.*;
import src.ui_di.*;
import java.util.Scanner;

public class Driver {

	/**
	 * Main method where the project starts.
	 * 
	 * @param args array of arguments from the command line
	 */
	public static void main(String[] args) {

		//Board_Mono_CLI monoBoard = new Board_Mono_CLI();
		//Board_Color_CLI colorBoard = new Board_Color_CLI();
		Scanner in = new Scanner(System.in);
		Chess match = null;
		Boolean correctInput = false;
		String input;
		BoardStrategy boardColor = new Board_Mono_CLI(); //default is monotone
		System.out.println("Welcome to ChessMeister!\n\n");
		while(!correctInput){
			System.out.println("Please select an option:\n1) New Game\n2) Color Settings");
			input = in.nextLine();
			switch(input){
				case "1":
					match = new Chess(boardColor);
					//match.getBoard().setDrawStrategy(boardColor);
					correctInput = true;
					break;
				case "2":
					System.out.println("Select a color\n1) Monotone Board\n2) Colored Board");
					input = in.nextLine();
					switch(input){
						case "1":
							boardColor = new Board_Mono_CLI();
							break;
						case "2":
							boardColor = new Board_Color_CLI();
							break;
					}
			}
		}
		in.close();
	}

}
