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

public class Driver {

	/**
	 * Main method where the project starts.
	 * 
	 * @param args array of arguments from the command line
	 */
	public static void main(String[] args) {
		Chess match = new Chess();
		Board_Mono_CLI monoBoard = new Board_Mono_CLI();
		match.getBoard().setDrawStrategy(monoBoard);
		match.getBoard().draw();
	}

}
