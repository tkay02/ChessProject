package src.ui_cli;

import src.interfaces.BoardIF;
import src.model.Piece;
/**
 * Implementation of BoardStrategy that creates a monotone version of the chess board.
 * 
 * @author Nolan Flinchum (25%), Thomas Kay, Joseph Oladeji (25%), Levi Sweat (50%)
 * @version 3/27/2023
 */
public class BoardMonoCLI extends BoardDisplayCLI{
	

	@Override
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

	@Override
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

	@Override
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

	@Override
	public String displayRank(int rank) {
		return String.valueOf(rank) + " ";
	}

	@Override
	public String displayWhiteFile() {
		return "   A  B  C  D  E  F  G  H\n";
	}
	
	@Override
	public String displayBlackFile(){
		return "   H  G  F  E  D  C  B  A\n";
	}

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
