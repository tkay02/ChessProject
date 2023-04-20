package src.interfaces;

import src.enums.Rank;
import java.util.ArrayList;
import src.enums.File;

/**
 * Interface for the chess board.
 * 
 * @author Nolan Flinchum, Thomas Kay (100%), Joseph Oladeji, Levi Sweat
 * @version 4/19/2023
 */
public interface BoardIF {
	
	/**
	 * Initiates the chess board.
	 */
	public void init_board();

	/**
	 * Sets up the pieces on the chess board.
	 */
	public void setup();

	/**
	 * Draws the chess board in unique color scheme such as mono.
	 */
	public void draw(boolean drawWhite, ArrayList<src.model.Position> validMoves, String player1, String player2);

	/**
	 * Retrieves the collection of squares on the board.
	 * 
	 * @return A matrix of the squares on the board. 
	 */
	public SquareIF[][] getSquares();

	/**
	 * Sets/changes the way that the chess board is drawn.
	 * 
	 * @param d The new board strategy in which the chess board is drawn.
	 */
	public void setDrawStrategy(BoardStrategy d);

	/**
	 * Returns the width of the board.
	 * 
	 * @return The width of the board.
	 */ 
	public int getWidth();

	/**
	 * Returns the height of the board.
	 * 
	 * @return The height of the board.
	 */ 
	public int getHeight();

	/**
	 * Retrieves the piece based on the specific rank and file.
	 * 
	 * @param Rank r The specific rank on the board.
	 * @param File f The specific file on the board.
	 * 
	 * @return The chess piece on the specific rank and file.
	 */
	public PieceIF getPiece(Rank r, File f);

	/**
	 * Retrieves the piece based on the specific column and row.
	 * 
	 * @param int col The number of the specific column.
	 * @param int row The character of the specific row.
	 * 
	 * @return The chess piece on the specific column and row.
	 */
	public PieceIF getPiece(int col, int row);

    /**
     * Getter for the ArrayList of white pieces that were taken.
     * 
     * @return ArrayList of white pieces that were taken
     */
    public ArrayList<String> getWhiteTakenPieces();

	/**
     * Getter for the ArrayList of black pieces that were taken.
     * 
     * @return ArrayList of black pieces that were taken
     */
    public ArrayList<String> getBlackTakenPieces();	


}
