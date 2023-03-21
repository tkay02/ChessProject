package interfaces;
import enums.Rank;
import enums.File;

/**
 * @author Thomas Kay
 * @author Joseph Oladeji
 * @author Nolan Flinchum
 * @author Levi Sweat
 * @version 3/21/2023
 *
 * Interface for the chess board.
 */
interface BoardIF {
	
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
	public void draw();
	/**
	 * Retrieves the collection of squares on the board.
	 * 
	 * @return A matrix of the squares on the board. 
	 */
	public SquareIF[][] getSquares();
	/**
	 * Sets/changes the way that the chess board is drawn.
	 * 
	 * @param BoardStrategy d The new board strategy in which the chess board is drawn.
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
	 * @param char row The character of the specific row.
	 * 
	 * @return The chess piece on the specific column and row.
	 */
	public PieceIF getPiece(int col, char row);
	
	
}
