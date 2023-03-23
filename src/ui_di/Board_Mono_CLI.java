package ui_di;

import interfaces.BoardStrategy;

/**
 * Creates a colored version of chess board.
 * 
 * @author Levi, Thomas, Joseph, Nolan
 *
 */
public class Board_Mono_CLI implements BoardStrategy{

	/**
	 * Draws the board
	 * @param board
	 */
	public void draw(BoardIF board) {
		//White square- : :
		//Black square- [ ]
		//[0][0] -> a8 -> black
		//
		String toDisplay = "";
		boolean flip = false;
		String square = "";
		String pieceLetter = "";
		Piece piece;
		for(int i = 0; i < board.getWidth(); i++){
			for(int j = 0; j < board.getHeight(); j++){
				piece = (Piece)board.getPiece(i, j);
				if(piece.equals("empty")) toDisplay += flip ? ": :" : "[ ]";
				else if(piece.getColor().equals("white")){
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
		System.out.println(toDisplay);
	}
}
