package src.interfaces;
/**
 * Interface for the chess pieces.
 * 
 * @author Nolan Flinchum, Thomas Kay, Joseph Oladeji, Levi Sweat
 * @version 3/27/2023
 */
import src.enums.ChessPieceType;

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