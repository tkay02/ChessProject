package src.interfaces;
/**
 * Interface for color of the chess pieces and squares.
 * 
 * @author Nolan Flinchum, Thomas Kay, Joseph Oladeji, Levi Sweat
 * @version 3/27/2023
 */
import src.enums.GameColor;

public interface BlackAndWhiteIF {

	/**
	 * Retrieves the color of the chess piece.
	 * 
	 * @return The color of the chess piece.
	 */
	public GameColor getColor();

	/**
	 * Determines if the color of the chess piece is black.
	 * 
	 * @return True if the chess piece is black; false otherwise.
	 */
	public boolean isBlack();

	/**
	 * Determines if the color of the chess piece is white.
	 * 
	 * @return True if the chess piece is white; false otherwise.
	 */
	public boolean isWhite();

}