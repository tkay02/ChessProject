package src.controller;

/**
 * Begins the CLI application of our project.
 * 
 * @author Nolan Flinchum, Thomas Kay, Joseph Oladeji, Levi Sweat (100%)
 * @version 3/27/2023
 */
public class CLIRunner {

	/**
	 * Function that is called from the driver.
	 */
	public static void run(){
		Chess ch = new Chess(); //creates a new game of chess
		ch.go();//begins the CLI application
	}

}
