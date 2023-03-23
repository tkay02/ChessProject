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
	for(int i = 0; i < board.getWidth(); i++){
		for(int j = 0; j < board.getHeight(); j++){
			if(j % 2 == 0){
				if(i % 2 == 0){
					if(board.getPiece(i, j) == PieceIF.empty) toDisplay += ": :";
					else toDisplay += ":" + board.getPiece(i, j) + ":";
				}				
				else{
					toDisplay += "[ ]";
				}
			}
			else{
				if(i % 2 == 0){
					toDisplay += "[ ]";
				}
				else{
					toDisplay += ": :";
				}
			}
		}
	}
	}
}
