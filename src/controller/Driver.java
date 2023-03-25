package src.controller;

import src.enums.*;
import src.interfaces.BoardStrategy;
import src.model.*;
import src.ui_di.*;

/**
 * Driver to run the Chess project.
 * @author Levi, Nolan, Thomas, Joseph
 *
 */
public class Driver {

	/**
	 * Main method, where project starts
	 * @param args
	 */
	public static void main(String[] args) {
		Chess match = new Chess();
		Board_Mono_CLI monoBoard = new Board_Mono_CLI();
		match.getBoard().setDrawStrategy(monoBoard);
		match.getBoard().draw();
	}

}
