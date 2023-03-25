package src.ui_di;
/**
 * Creates a colored version of chess board.
 * 
 * @author Nolan Flinchum, Thomas Kay, Joseph Oladeji, Levi Sweat
 * @version 3/27/2023
 */
import src.interfaces.BoardIF;
import src.model.Piece;

public class Board_Color_CLI {
	
	/**
	 * Draws the board with color.
	 * 
	 * @param board the chess board to draw
	 */
	public void draw(BoardIF board) {
		String toDisplay = "";
		boolean flip = false;
		String square = "";
		String pieceLetter = "";
		Piece piece;
		for(int i = 0; i < board.getWidth(); i++){
			for(int j = 0; j < board.getHeight(); j++){
				piece = (Piece)board.getPiece(i, j).getChessPieceType();
				//first displays the white background, second displays the black background
				if(piece.equals("Empty")) toDisplay += flip ? "\u001b[47m    " : "\u001b[40m    ";
				else if(piece.getColor().equals("\u001b[37m")){
					toDisplay += ":" + piece.getChessPieceType().getChessPieceLetter() + ":";
				}
				else{
					toDisplay += "[" + piece.getChessPieceType().getChessPieceLetter().toLowerCase() + "]";
				}
				flip = !flip;
			}
			flip = !flip;
			toDisplay += "\n";
		}
		toDisplay += "\u001b[0m"; //reset colors back to normal
		System.out.println(toDisplay);	
	}
}
