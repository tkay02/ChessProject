package ui_di;

/**
 * Creates a colored version of chess board.
 * 
 * @author Levi, Thomas, Joseph, Nolan
 *
 */
public class Board_Color_CLI {
	
	/**
	 * Draws the board
	 * @param board
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
