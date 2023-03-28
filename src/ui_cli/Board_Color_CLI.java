package src.ui_cli;
/**
 * Creates a colored version of chess board. Implements board strategy, and uses GameColor enums
 * to get the unique color of black and white squares and pieces.
 * 
 * @author Nolan Flinchum, Thomas Kay, Joseph Oladeji, Levi Sweat (100%)
 * @version 3/27/2023
 */

import src.enums.GameColor;
import src.interfaces.BoardStrategy;
import src.interfaces.BoardIF;
import src.model.Piece;
import src.model.Square;

public class Board_Color_CLI implements BoardStrategy{
	
	/* String built to display the board once completed. */
	private String toDisplay;

	/* Piece representing the current piece when building the board. */
	private Piece piece;

	/**
	 * Constructor for Board_Color_CLI class. Initilaizes toDisplay.
	 */
	public Board_Color_CLI(){
		this.toDisplay = "";
	}
	
	/**
	 * Draws the board with colors, with blue colored pieces representing white pieces and red 
	 * colored pieces representing black pieces. Also displays the rank and file to help user
	 * when making moves, and displays the pieces that have been taken thus far by each player.
	 * 
	 * @param board the chess board to draw
	 */
	public void draw(BoardIF board) {
		toDisplay = "     A   B   C   D   E   F   G   H\n\n";
		for(int i = 0; i < board.getWidth(); i++){ //iterate through board
			toDisplay += (board.getWidth() - i) + "   "; //rank to display on side of board
			for(int j = 0; j < board.getHeight(); j++){
				piece = (Piece)board.getPiece(i, j);
				//if the square is white
				if(((Square)board.getSquares()[i][j]).isWhite()){
					//if the square is empty, just display the white background
					if(piece.getChessPieceType().getChessPieceLetter().equals(" ")){
						toDisplay += GameColor.WHITE.getBackground() + "    ";
					}
					//if the piece is white, display a 'w' before getting the piece letter
					else if(piece.isWhite()){
						toDisplay += GameColor.WHITE.getBackground() + " " + 
									 GameColor.WHITE.getText() + "w" +
									 piece.getChessPieceType().getChessPieceLetter() + " ";
					}																					
					else{
					//if the piece is black, display a 'b' before getting the piece letter
						toDisplay += GameColor.WHITE.getBackground() + " " + 
									 GameColor.BLACK.getText() + "b" + 
									 piece.getChessPieceType().getChessPieceLetter() + " ";
					}
				}
				else{//square must be black
					//if the square is empty, just display the black background
					if(piece.getChessPieceType().getChessPieceLetter().equals(" ")){
						toDisplay += GameColor.BLACK.getBackground() + "    ";
					}
					//if the piece is white, display a 'w' before getting the piece letter
					else if(piece.isWhite()){
						toDisplay += GameColor.BLACK.getBackground() + " " + 
									 GameColor.WHITE.getText() + "w" + 
									 piece.getChessPieceType().getChessPieceLetter() + " ";
					}
					//if the piece is black, display a 'b' before getting the piece letter
					else{
						toDisplay += GameColor.BLACK.getBackground() + " " + 
						GameColor.BLACK.getText() + "b" + 
						piece.getChessPieceType().getChessPieceLetter() + " ";
					}
				}
			}
			//reset the ascii code so that it doesn't extend past the board
			toDisplay += GameColor.resetColor() + "\n";
		}
		toDisplay += "Pieces taken by Player One: ";
		//iterate through ArrayList of black pieces taken to be displayed
		for(int i = 0; i < board.getBlackTakenPieces().size(); i++){
			toDisplay += GameColor.BLACK.getText() + "b" + board.getBlackTakenPieces().get(i) +" ";
		}
		toDisplay += GameColor.resetColor() + "\nPieces taken by Player Two: ";
		//iterate through ArrayList of white pieces taken to be displayed
		for(int i = 0; i < board.getWhiteTakenPieces().size(); i++){
			toDisplay += GameColor.WHITE.getText() + "w" + board.getWhiteTakenPieces().get(i) +" ";
		}
		//reset ascii code so the changes don't continue after playing
		toDisplay += GameColor.resetColor() + "\n\n";
		System.out.println(toDisplay);	
	}
}