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
		String toDisplay = "     a   b   c   d   e   f   g   h\n\n";
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
			toDisplay += GameColor.WHITE.reset_colorings() + "\n";
		}
		System.out.println(toDisplay);	
	}
}
