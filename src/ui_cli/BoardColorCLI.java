package src.ui_cli;
import src.enums.GameColor;
import src.interfaces.BoardIF;
import src.model.Piece;

/**
 * Creates a colored version of chess board. Implements board strategy, and uses GameColor enums
 * to get the unique color of black and white squares and pieces.
 * 
 * @author Nolan Flinchum, Thomas Kay, Joseph Oladeji, Levi Sweat (100%)
 * @version 4/19/2023
 */
public class BoardColorCLI extends BoardDisplayCLI{	

	/**
	 * Creates the display of a white square on a chess board. The white square is represented
	 * as an actual white square. The display is affected by what kind of piece type is on it 
	 * ranging from white to black to empty. White pieces are displayed with a blue color and 
	 * a w in front of the chess piece type. Black pieces are displayed with a red color and a 
	 * b in front of the chess piece type.
	 * 
	 * @param Piece piece The current piece type on the square, with its type ranging from
	 * white, black, or empty.
	 * @return The display of the white square with its current piece type on the chess
	 * board.
	 */
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

	/**
	 * Creates the display of a black square on a chess board. The black square is represented
	 * as an actual black square. The display is affected by what kind of piece type is on it 
	 * ranging from white to black to empty. White pieces are displayed with a blue color and 
	 * a w in front of the chess piece type. Black pieces are displayed with a red color and a b 
	 * in front of the chess piece type.
	 * 
	 * @param Piece piece The current piece type on the square, with its type ranging from
	 * white, black, or empty.
	 * @return The display of the black square with its current piece type on the chess
	 * board.
	 */
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
			result += GameColor.BLACK.getText() + "b" + board.getBlackTakenPieces().get(i) +" ";
		}
		result += GameColor.resetColor() + "\nPieces taken by " + player2 + ": ";
		//iterate through ArrayList of white pieces taken to be displayed
		for(int i = 0; i < board.getWhiteTakenPieces().size(); i++){
			result += GameColor.WHITE.getText() + "w" + board.getWhiteTakenPieces().get(i) +" ";
		}

		return result + GameColor.resetColor() + "\n";

	}

	/**
	 * Retrieves the visual display of the specific rank/row number.
	 * 
	 * @param int rank The number that retrieves the rank at specific location.
	 * @return The visual display of the rank at that specific row.
	 */
	public String displayRank(int rank) {
		return String.valueOf(rank) + "  ";
	}

	/**
	 * Shows the files order when facing the white player's side of the chess board.
	 * 
	 * @return The visual of the files from A to H.
	 */
	public String displayWhiteFile() {
		return "    A   B   C   D   E   F   G   H\n";
	}

	/**
	 * Shows the files order when facing the black player's side of the chess board.
	 * 
	 * @return The visual of the files from H to A.
	 */
	public String displayBlackFile(){
		return "    H   G   F   E   D   C   B   A\n";
	}

	/**
	 * Displays a tile that shows a valid position for that's part of the piece's movement. The
	 * tile is represented by a pink color.
	 * 
	 * @param Piece piece The chess piece type that is on the valid position tile.
	 * @result A visual of a square that represents a valid position.
	 */
	public String displayValidMoves(Piece piece){
		String result = GameColor.showMoveColor() + " ";
		if(piece.isWhite()){
			result += GameColor.WHITE.getText() + "w" + piece.getChessPieceType().getChessPieceLetter() + " ";
		}
		else if(piece.isBlack()){
			result += GameColor.BLACK.getText() + "b" + piece.getChessPieceType().getChessPieceLetter() + " ";
		}
		else{
			result += "   ";
		}
		return result + GameColor.resetColor();
	}
}