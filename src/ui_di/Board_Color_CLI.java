package src.ui_di;

import src.enums.GameColor;
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
		String toDisplay = "     A   B   C   D   E   F   G   H\n\n";
		Piece piece;
		for(int i = 0; i < board.getWidth(); i++){
			toDisplay += (board.getWidth() - i) + "   ";
			for(int j = 0; j < board.getHeight(); j++){
				piece = (Piece)board.getPiece(i, j);
				if(((Square)board.getSquares()[i][j]).isWhite()){
					if(piece.getChessPieceType().getChessPieceLetter().equals(" ")){
						toDisplay += GameColor.WHITE.getBackground() + "    ";
					}
					else if(piece.isWhite()){
						toDisplay += GameColor.WHITE.getBackground() + " " + 
									 GameColor.WHITE.getText() + "w" +
									 piece.getChessPieceType().getChessPieceLetter() + " ";
					}																					
					else{
						toDisplay += GameColor.WHITE.getBackground() + " " + 
									 GameColor.BLACK.getText() + "b" + 
									 piece.getChessPieceType().getChessPieceLetter() + " ";
					}
				}
				else{
					if(piece.getChessPieceType().getChessPieceLetter().equals(" ")){
						toDisplay += GameColor.BLACK.getBackground() + "    ";
					}
					else if(piece.isWhite()){
						toDisplay += GameColor.BLACK.getBackground() + " " + 
									 GameColor.WHITE.getText() + "w" + 
									 piece.getChessPieceType().getChessPieceLetter() + " ";
					}
					else{
						toDisplay += GameColor.BLACK.getBackground() + " " + 
						GameColor.BLACK.getText() + "b" + 
						piece.getChessPieceType().getChessPieceLetter() + " ";
					}
				}
			}
			toDisplay += GameColor.reset_colorings() + "\n";
		}
		toDisplay += "Pieces taken by Player One: ";
		for(int i = 0; i < board.getBlackTakenPieces().size(); i++){
			toDisplay += GameColor.BLACK.getText() + "b" + board.getBlackTakenPieces().get(i) + " ";
		}
		toDisplay += GameColor.reset_colorings() + "\nPieces taken by Player Two: ";
		for(int i = 0; i < board.getWhiteTakenPieces().size(); i++){
			toDisplay += GameColor.WHITE.getText() + "w" + board.getWhiteTakenPieces().get(i) + " ";
		}
		toDisplay += GameColor.reset_colorings();
		System.out.println(toDisplay);	
	}
}
