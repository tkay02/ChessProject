*package src.ui_cli;
/**
 * Implementation of BoardStrategy that creates a monotone version of the chess board.
 * 
 * @author Nolan Flinchum (25%), Thomas Kay, Joseph Oladeji (25%), Levi Sweat (50%)
 * @version 3/27/2023
 */

import src.interfaces.BoardIF;
import src.interfaces.BoardStrategy;
import src.model.Piece;
import src.model.Square;

public class Board_Mono_CLI implements BoardStrategy{
	
	/** String built to display the board once completed. **/
	private String toDisplay;

	/** Piece representing the current piece when building the board. **/
	private Piece piece;
	
	/** Represents letter of current piece **/
	private String pieceLetter;

	/**
	 * Constructor for a monotone board. Initializes toDisplay.
	 */
	public Board_Mono_CLI(){
		this.toDisplay = "";
	}

	/**
	 * Draws the board passed in, using ': :' to represent white squares and '[ ]' to represent
	 * black squares. White pieces are capitalized letters, while black pieces are loewrcase.
	 * 
	 * @param board the chess board to draw
	 */
	public void draw(BoardIF board) {
		toDisplay = "";		
		for(int i = 0; i < board.getWidth(); i++){ //iterate through board
			for(int j = 0; j < board.getHeight(); j++){
				piece = (Piece)board.getPiece(i, j); //current piece in iteration
				pieceLetter = piece.getChessPieceType().getChessPieceLetter();
				//if the square is white
				if(((Square)board.getSquares()[i][j]).isWhite()){
					//if piece is white, display the pieceLetter
					if(piece.isWhite()){
						toDisplay += ":" + pieceLetter + ":";
					}
					//if the piece is black, lowercase the letter to be displayed
					else{
						toDisplay += ":" + pieceLetter.toLowerCase() + ":";
					}
				}
				else{//square must be black
					//if piece is white, display the uppercase letter
					if(piece.isWhite()){
						toDisplay += "[" + pieceLetter + "]";
					}
					//if piece is black, lowercase the letter to display
					else{
						toDisplay += "[" + pieceLetter.toLowerCase() + "]";
					}
				}
			}
			toDisplay += (board.getWidth() - i) + "\n"; //rank to display on side of board
		}
		toDisplay += " A  B  C  D  E  F  G  H\n";
		toDisplay += "Pieces taken by Player One: ";
		//iterate through ArrayList of black pieces taken to be displayed
		for(int i = 0; i < board.getBlackTakenPieces().size(); i++){
			toDisplay += board.getBlackTakenPieces().get(i).toLowerCase() + " ";
		}
		toDisplay += "\nPieces taken by Player Two: ";
		//iterate through ArrayList of white pieces taken to be displayed
		for(int i = 0; i < board.getWhiteTakenPieces().size(); i++){
			toDisplay += board.getWhiteTakenPieces().get(i)+ " ";
		}
		System.out.println(toDisplay); //print out board
	}
	
}
