package src.ui_cli;

import src.interfaces.BoardIF;
import src.model.Piece;

/**
 * Implementation of BoardStrategy that creates a monotone version of the chess board.
 * 
 * @author Nolan Flinchum (25%), Thomas Kay, Joseph Oladeji (25%), Levi Sweat (50%)
 * @version 4/19/2023
 */
public class BoardMonoCLI extends BoardDisplayCLI{
	

	/**
	 * Creates the display of a white square on a chess board. The white squares are represented
	 * by colons. The display is affected by what kind of piece type is on it ranging from white
	 * to black to empty. The white pieces are represented by uppercase letters. The black pieces
	 * are represented by lowercase letters. 
	 * 
	 * @param Piece piece The current piece type on the square, with its type ranging from
	 * white, black, or empty.
	 * @return The display of the white square with its current piece type on the chess
	 * board.
	 */
	public String whiteSquare(Piece piece) {
		String result = "";
		String pieceLetter = piece.getChessPieceType().getChessPieceLetter();
		if(piece.isWhite()){
			result += ":" + pieceLetter + ":";
		}
		//if the piece is black, lowercase the letter to be displayed
		else{
			result += ":" + pieceLetter.toLowerCase() + ":";
		}
		return result;
	}

	/**
	 * Creates the display of a black square on a chess board. The black squares are represented
	 * by brackets. The display is affected by what kind of piece type is on it ranging from white 
	 * to black to empty.The white pieces are represented by uppercase letters. The black pieces
	 * are represented by lowercase letters.
	 * 
	 * @param Piece piece The current piece type on the square, with its type ranging from
	 * white, black, or empty.
	 * @return The display of the black square with its current piece type on the chess
	 * board.
	 */
	public String blackSquare(Piece piece) {
		String result = "";
		String pieceLetter = piece.getChessPieceType().getChessPieceLetter();
		//if piece is white, display the uppercase letter
		if(piece.isWhite()){
			result += "[" + pieceLetter + "]";
		}
		//if piece is black, lowercase the letter to display
		else{
			result += "[" + pieceLetter.toLowerCase() + "]";
		}
		return result;
	}

	 /**
	 * Displays all of the pieces that were captured by each player during the game at the
	 * specific moment during chess. First it shows the pieces that were captured by player
	 * one and then the pieces that were captured by player two. Captured pieces information
	 * is stored within the chess board.
	 * 
	 * @param BoardIF board The chess board that stores that current information of captured
	 * pieces.
	 * @param String player1 The name of the first player.
	 * @param String player2 The name of the second player.
	 * @return The visual display of the current capture progress in the game.
	 */
	public String takenPieces(BoardIF board, String player1, String player2){
		String result = "";
		result += "Pieces taken by " + player1 + ": ";
		//iterate through ArrayList of black pieces taken to be displayed
		for(int i = 0; i < board.getBlackTakenPieces().size(); i++){
			result += board.getBlackTakenPieces().get(i).toLowerCase() + " ";
		}
		result += "\nPieces taken by " + player2 + ": ";
		//iterate through ArrayList of white pieces taken to be displayed
		for(int i = 0; i < board.getWhiteTakenPieces().size(); i++){
			result += board.getWhiteTakenPieces().get(i)+ " ";
		}
		return result + "\n";
	}

	/**
	 * Retrieves the visual display of the specific rank/row number.
	 * 
	 * @param int rank The number that retrieves the rank at specific location.
	 * @return The visual display of the rank at that specific row.
	 */
	public String displayRank(int rank) {
		return String.valueOf(rank) + " ";
	}

	/**
	 * Shows the files order when facing the white player's side of the chess board.
	 * 
	 * @return The visual of the files from A to H.
	 */
	public String displayWhiteFile() {
		return "   A  B  C  D  E  F  G  H\n";
	}
	
	 /**
	 * Shows the files order when facing the black player's side of the chess board.
	 * 
	 * @return The visual of the files from H to A.
	 */
	public String displayBlackFile(){
		return "   H  G  F  E  D  C  B  A\n";
	}

	/**
	 * Displays a tile that shows a valid position for that's part of the piece's movement. The
	 * tile is represented by minus symbols.
	 * 
	 * @param Piece piece The chess piece type that is on the valid position tile.
	 * @result A visual of a square that represents a valid position.
	 */
	public String displayValidMoves(Piece piece){
	String result = "-";
	if(piece.isWhite()){
		result +=  piece.getChessPieceType().getChessPieceLetter() + "-";
	}
	else if(piece.isBlack()){
		result += piece.getChessPieceType().getChessPieceLetter().toLowerCase() + "-";
	}
	else{
		result += " -";
	}
	return result;
	}

}
