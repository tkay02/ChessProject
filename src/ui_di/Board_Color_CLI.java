package src.ui_di;

import src.interfaces.*;
import src.model.*;

/**
 * Creates a colored version of chess board.
 * 
 * @author Nolan Flinchum, Thomas Kay, Joseph Oladeji, Levi Sweat
 * @version 3/27/2023
 */
public class Board_Color_CLI implements BoardStrategy{

	public Board_Color_CLI(){

	}
	
	/**
	 * Draws the board with color.
	 * 
	 * @param board the chess board to draw
	 */
	public void draw(BoardIF board) {
		String toDisplay = "";
		Piece piece;
		for(int i = 0; i < board.getWidth(); i++){
			for(int j = 0; j < board.getHeight(); j++){
				piece = (Piece)board.getPiece(i, j);
				if(((Square)board.getSquares()[i][j]).isWhite()){
					if(piece.getChessPieceType().getChessPieceLetter().equals(" ")){
						toDisplay += "\u001b[47m    ";
					}
					else if(piece.isWhite()){
						toDisplay += "\u001b[47m \u001b[34mw" + piece.getChessPieceType().getChessPieceLetter() + "\u001b[47m ";
					}
					else{
						toDisplay += "\u001b[47m \u001b[31mb" + piece.getChessPieceType().getChessPieceLetter() + "\u001b[47m ";
					}
				}
				else{
					if(piece.getChessPieceType().getChessPieceLetter().equals(" ")){
						toDisplay += "\u001b[40m    ";
					}
					else if(piece.isWhite()){
						toDisplay += "\u001b[40m \u001b[34mw" + piece.getChessPieceType().getChessPieceLetter() + "\u001b[40m ";
					}
					else{
						toDisplay += "\u001b[40m \u001b[31mb" + piece.getChessPieceType().getChessPieceLetter() + "\u001b[40m ";
					}
				}
			}
			toDisplay += "\u001b[0m\n";
		}
		System.out.println(toDisplay);	
	}
}
