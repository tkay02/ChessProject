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
		Chess match = new Chess();
		//Board_Mono_CLI monoBoard = new Board_Mono_CLI();
		//Board_Color_CLI colorBoard = new Board_Color_CLI();
		Scanner in = new Scanner(System.in);
		Boolean correctInput = false;
		String input;
		BoardStrategy boardColor = new Board_Mono_CLI(); //default is monotone
		while(!correctInput){
			System.out.println("Choose Board color:\nFor monotone: Board_Mono_CLI\tFor color: Board_Color_CLI");
			input = in.nextLine();
			switch(input){
				case "Board_Mono_CLI":
					boardColor = new Board_Mono_CLI();
					correctInput = true;
					break;
				case "Board_Color_CLI":
					boardColor = new Board_Color_CLI();
					correctInput = true;
					break;
			}
		}
		match.getBoard().setDrawStrategy(boardColor);
		match.getBoard().draw();
	}

}
