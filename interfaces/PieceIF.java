package interfaces;
import enums.ChessPieceType;

/**
 * @author Thomas Kay
 * @author Joseph Oladeji
 * @author Nolan Flinchum
 * @author Levi Sweat
 * @version 3/21/2023
 *
 * Interface for the chess pieces.
 */
public interface PieceIF {

	/**
	 * Retrieves the specific value that the chess piece represents.
	 * 
	 * @return The specific chess type that the piece represents.
	 */
	public ChessPieceType getChessPieceType();
	/**
	 * Sets the chess piece to a specific value such as king, queen, pawns, etc.
	 * 
	 * @param ChessPieceType piece The specific value to set the piece.
	 */
	public void setChessPieceType(ChessPieceType piece);

}
