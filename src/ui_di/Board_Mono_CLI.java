package src.ui_di;
/**
 * Creates a monotone version of the chess board.
 * 
 * @author Nolan Flinchum, Thomas Kay, Joseph Oladeji, Levi Sweat
 * @version 3/27/2023
 */
import src.interfaces.*;
import src.enums.*;
import src.model.*;
import src.controller.*;

public class Board_Mono_CLI implements BoardStrategy{

	/**
	 * Constructor for a monotone board.
	 */
	public Board_Mono_CLI(){
		
	}

	/**
	 * Draws the board passed in.
	 * 
	 * @param board the board to draw
	 */
	public void draw(BoardIF board) {
		//White square- : :
		//Black square- [ ]
		//[0][0] -> a8 -> black
		//
		
		//boolean flip = false;
		//String square = "";
		//String pieceLetter = "";
		String toDisplay = "";
		Piece piece;
		String pieceLetter;
		
		for(int i = 0; i < board.getWidth(); i++){
			for(int j = 0; j < board.getHeight(); j++){
				piece = (Piece)board.getPiece(i, j);
				pieceLetter = piece.getChessPieceType().getChessPieceLetter();
				if(((Square)board.getSquares()[i][j]).isWhite()){
					if(piece.isWhite()){
						toDisplay += ":" + pieceLetter + ":";
					}
					else{
						toDisplay += ":" + pieceLetter.toLowerCase() + ":";
					}
				}
				else{
					if(piece.isWhite()){
						toDisplay += "[" + pieceLetter + "]";
					}
					else{
						toDisplay += "[" + pieceLetter.toLowerCase() + "]";
					}
				}
			}

			toDisplay += (board.getWidth() - i) + "\n";
		}
		toDisplay += " a  b  c  d  e  f  g  h";
		System.out.println(toDisplay);
	}
}
