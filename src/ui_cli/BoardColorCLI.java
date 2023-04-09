package src.ui_cli;
import src.enums.GameColor;
import src.interfaces.BoardIF;
import src.model.Piece;

/**
 * Creates a colored version of chess board. Implements board strategy, and uses GameColor enums
 * to get the unique color of black and white squares and pieces.
 * 
 * @author Nolan Flinchum, Thomas Kay, Joseph Oladeji, Levi Sweat (100%)
 * @version 3/27/2023
 */
public class BoardColorCLI extends BoardDisplayCLI{
	
	/**
	 * Constructor for Board_Color_CLI class. Initilaizes toDisplay.
	 */
	public BoardColorCLI(){
	}
	

	@Override
	public String whiteSquare(Piece piece) {
		String result = "";
		//if the square is empty, just display the white background
		if(piece.getChessPieceType().getChessPieceLetter().equals(" ")){
			result += GameColor.WHITE.getBackground() + "    ";
		}
		//if the piece is white, display a 'w' before getting the piece letter
		else if(piece.isWhite()){
			result += GameColor.WHITE.getBackground() + " " + 
							GameColor.WHITE.getText() + "w" +
							piece.getChessPieceType().getChessPieceLetter() + " ";
		}																					
		else{
		//if the piece is black, display a 'b' before getting the piece letter
			result += GameColor.WHITE.getBackground() + " " + 
							GameColor.BLACK.getText() + "b" + 
							piece.getChessPieceType().getChessPieceLetter() + " ";
		}

		return result + GameColor.resetColor();
	}

	@Override
	public String blackSquare(Piece piece) {
		String result = "";
		//if the square is empty, just display the black background
		if(piece.getChessPieceType().getChessPieceLetter().equals(" ")){
			result += GameColor.BLACK.getBackground() + "    ";
		}
		//if the piece is white, display a 'w' before getting the piece letter
		else if(piece.isWhite()){
			result += GameColor.BLACK.getBackground() + " " + 
							GameColor.WHITE.getText() + "w" + 
							piece.getChessPieceType().getChessPieceLetter() + " ";
		}
		//if the piece is black, display a 'b' before getting the piece letter
		else{
			result += GameColor.BLACK.getBackground() + " " + 
			GameColor.BLACK.getText() + "b" + 
			piece.getChessPieceType().getChessPieceLetter() + " ";
		}
		return result + GameColor.resetColor();
	}

	@Override
	public String takenPieces(BoardIF board){
		String result = "";
		result += "Pieces taken by Player One: ";
		//iterate through ArrayList of black pieces taken to be displayed
		for(int i = 0; i < board.getBlackTakenPieces().size(); i++){
			result += GameColor.BLACK.getText() + "b" + board.getBlackTakenPieces().get(i) +" ";
		}
		result += GameColor.resetColor() + "\nPieces taken by Player Two: ";
		//iterate through ArrayList of white pieces taken to be displayed
		for(int i = 0; i < board.getWhiteTakenPieces().size(); i++){
			result += GameColor.WHITE.getText() + "w" + board.getWhiteTakenPieces().get(i) +" ";
		}

		return result + GameColor.resetColor() + "\n";

	}

	@Override
	public String displayRank(int rank) {
		return String.valueOf(rank) + "  ";
	}

	@Override
	public String displayWhiteFile() {
		return "    A   B   C   D   E   F   G   H\n";
	}

	@Override
	public String displayBlackFile(){
		return "    H   G   F   E   D   C   B   A\n";
	}
}