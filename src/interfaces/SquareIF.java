package src.interfaces;

/**
 * @author Thomas Kay
 * @author Joseph Oladeji
 * @author Nolan Flinchum
 * @author Levi Sweat
 * @version 3/21/2023
 *
 * Interface for the squares on the chess board.
 */
public interface SquareIF {
	
	/**
	 * Clears any pieces off of the square.
	 */
	public void clear();
	/**
	 * Sets a chess piece onto the square.
	 * 
	 * @param PieceIF p The specific chess piece that is being moved onto the square.
	 */
	public void setPiece(PieceIF p);
	/**
	 * Retrieves the chess piece from the square.
	 * 
	 * @return The specific chess piece that is on the spuare.
	 */
	public PieceIF getPiece();
}
