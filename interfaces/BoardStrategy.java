package interfaces;

/**
 * @author Thomas Kay
 * @author Joseph Oladeji
 * @author Nolan Flinchum
 * @author Levi Sweat
 * @version 3/21/2023
 * 
 * Interface for the way that the chess board is drawn in regards to color.
 */
public interface BoardStrategy {

	/**
	 * Draws the board in an unique color scheme such as mono.
	 * 
	 * @param BoardIF board The chess board whose color scheme is being changed.
	 */
	public void draw(BoardIF board);
	
}
