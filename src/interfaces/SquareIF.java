package src.interfaces;

import src.model.Position;

/**
 * Interface for the squares on the chess board.
 * 
 * @author Nolan Flinchum, Thomas Kay (100%), Joseph Oladeji, Levi Sweat
 * @version 4/19/2023
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

	/**
	 * Gets the the position object of the chess board square.
	 *
	 * @return A Position object representing the chosen chess board position.
	 */
	public Position getPosition();

}