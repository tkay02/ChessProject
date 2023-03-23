package src.interfaces;
import src.enums.GameColor;

/**
 * @author Thomas Kay
 * @author Joseph Oladeji
 * @author Nolan Flinchum
 * @author Levi Sweat
 * @version 3/21/2023
 * 
 * Interface for color of the chess pieces.
 */
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
