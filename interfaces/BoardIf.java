package interfaces;

interface BoardIf {
	
	 
	/**
	 * Initiates the chess board.
	 */
	void init_board();
	/**
	 * Sets up the pieces on the chess board.
	 */
	void setup();
	/**
	 * 
	 */
	void draw();
	/**
	 * Retrieves the collection of squares on the board.
	 * 
	 * @return A matrix of the squares on the board. 
	 */
	SquareIf[][] getSquares();
	/**
	 * @param BoardStratergy d.
	 */
	void setDrawStratergy(BoardStratergy d);
	/**
	 * Returns the width of the board.
	 * 
	 * @return The width of the board.
	 */ 
	int getWidth();
	/**
	 * Returns the height of the board.
	 * 
	 * @return The height of the board.
	 */ 
	int getHeight();
	/**
	 * Retrieves the piece based on the specific rank and file.
	 * 
	 * @param Rank r The specific rank on the board.
	 * @param File f The specific file on the board.
	 * 
	 * @return The chess piece on the specific rank and file.
	 */
	PieceIf getPiece(Rank r, File f);
	/**
	 * Retrieves the piece based on the specific column and row.
	 * 
	 * @param int col The number of the specific column.
	 * @param char row The character of the specific row.
	 * 
	 * @return The chess piece on the specific column and row.
	 */
	PieceIf getPiece(int col, char row);
	
	
}
