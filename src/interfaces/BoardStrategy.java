package src.interfaces;

import java.util.ArrayList;
/**
 * Interface for the way that the chess board is drawn in regards to color.
 * 
 * @author Nolan Flinchum, Thomas Kay (100%), Joseph Oladeji, Levi Sweat
 * @version 3/27/2023
 */

public interface BoardStrategy {

	/**
	 * Draws the board in an unique color scheme such as mono.
	 * 
	 * @param BoardIF board The chess board whose color scheme is being changed.
	 */
	public void drawWhite(BoardIF board, ArrayList<src.model.Position> validMoves, String player1, String player2);

	/**
	 * Draws the board in an unique color scheme such as mono.
	 * 
	 * @param BoardIF board The chess board whose color scheme is being changed.
	 */
	public void drawBlack(BoardIF board, ArrayList<src.model.Position> validMoves, String player1, String player2);

	
}
