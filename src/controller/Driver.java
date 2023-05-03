package src.controller;

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
		Chess ch = new Chess();
		ch.go();
	}
	

}
