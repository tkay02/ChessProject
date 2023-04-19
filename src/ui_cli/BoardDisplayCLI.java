package src.ui_cli;
import src.interfaces.BoardStrategy;

import java.util.ArrayList;

import src.model.Position;

import src.interfaces.BoardIF;
import src.model.Piece;
import src.model.Square;

/**
 *  This class allows for methods to draw a chess board with colored pieces,
where blue pieces represent white pieces and red pieces represent black pieces.
It also displays the rank and file to help users make moves, and shows

the pieces that have been taken thus far by each player.
 */
public abstract class BoardDisplayCLI implements BoardStrategy{
    
    /** String built to display the board once completed. **/
	private String toDisplay;

	/** Piece representing the current piece when building the board. **/
	private Piece piece;

	/**
	 * Constructor for Board_Color_CLI class. Initilaizes toDisplay.
	 */
	public BoardDisplayCLI(){
		this.toDisplay = "";
	}
    
    /**
	 * Draws the board facing the white player's perspective. Also displays the rank and file to 
	 * help user when making moves, and displays the pieces that have been taken thus far by each
	 * player. Displays the white player's side of the board, with white pieces being displayed at
	 * the bottom, black pieces being displayed at the top, with ranks 1-8 from bottom to top and 
	 * files A-H.
	 * 
	 * @param BoardIF board The chess board that has the current piece locations and capture
	 * history.
	 * @param ArrayList<Position> validMoves The list that contains the valid moves for a piece or
	 * pieces.
	 * @param String player1 The name of player 1.
	 * @param String player2 The name of player 2.
	 */
	public void drawWhite(BoardIF board, ArrayList<Position> validMoves, String player1, String player2) {
        toDisplay = "\n";
		for(int i = 0; i < board.getWidth(); i++){ //iterate through board
			toDisplay += displayRank(board.getWidth() - i); //rank to display on side of board
			for(int j = 0; j < board.getHeight(); j++){
				
				piece = (Piece)board.getPiece(i, j);
				boolean validPosition = false;
				for(Position validMove : validMoves){
					if(validMove.getRank().getArrayRank() == i && validMove.getFile().getArrayFile() == j){
						validPosition = true;
						toDisplay += showMoves(piece);
					}
				}
				if(!validPosition){
					//if the square is white
					if(((Square)board.getSquares()[i][j]).isWhite()){
						toDisplay += whiteSquare(piece);
					}
					else{//square must be black
						toDisplay += blackSquare(piece);
					}
				}
			}
            toDisplay += "\n";
		}
        toDisplay += displayWhiteFile(); //display the file along the bottom of the board
        toDisplay += takenPieces(board, player1, player2); //display the pieces that have been taken
		System.out.println(toDisplay); //print out the string
	}

    /**
	 * Draws the board with facing the black player's perspective. Also displays the rank and 
	 * file to help user when making moves, and displays the pieces that have been taken thus far 
	 * by each player. Displays the black player's side of the board, with black pieces being 
	 * displayed at the bottom, white pieces being displayed at the top, with ranks 1-8 from top to
	 * bottom and files H-A.
	 * 
	 * @param BoardIF board The chess board that has the current piece locations and capture
	 * history.
	 * @param ArrayList<Position> validMoves The list that contains the valid moves for a piece or
	 * pieces.
	 * @param String player1 The name of player 1.
	 * @param String player2 The name of player 2.
	 */
	public void drawBlack(BoardIF board, ArrayList<Position> validMoves, String player1, String player2) {
        toDisplay = "\n";
		for(int i = board.getWidth() - 1; i >= 0; i--){ //iterate through board
			toDisplay += displayRank(board.getWidth() - i); //rank to display on side of board
			for(int j = board.getHeight() - 1; j >= 0; j--){
				piece = (Piece)board.getPiece(i, j);
				
				boolean validPosition = false;
				for(Position validMove : validMoves){
					if(validMove.getRank().getArrayRank() == i && validMove.getFile().getArrayFile() == j){
						validPosition = true;
						toDisplay += showMoves(piece);
					}
				}
				if(!validPosition){
					//if the square is white
					if(((Square)board.getSquares()[i][j]).isWhite()){
						toDisplay += whiteSquare(piece);
					}
					else{//square must be black
						toDisplay += blackSquare(piece);
					}
				}

				//Position pos = board.getSquares()[i][j].getPosition();
				//toDisplay += showMoves(validMoves, pos);
			}
            toDisplay += "\n";
		}
        toDisplay += displayBlackFile(); //display the file along the bottom of the board
        toDisplay += takenPieces(board, player1, player2); //display the pieces that have been taken
		System.out.println(toDisplay); //print out the string
	}

	/**
	 * Shows a valid position of a chess piece on the chess board.
	 * 
	 * @param Piece piece The chess piece on the specific square.
	 * @return A visual of a valid position for a chess piece.
	 */
	public String showMoves(Piece piece){
		return displayValidMoves(piece);
	}

	// public void showMoves(BoardIF board, ArrayList<Position>){
    //     toDisplay = "\n";
	// 	for(int i = 7; i >= 0; i--){ //iterate through board
	// 		toDisplay += displayRank(board.getWidth() - i); //rank to display on side of board
	// 		for(int j = 7; j >= 0; j--){
	// 			piece = (Piece)board.getPiece(i, j);
	// 			//if the square is white
	// 			if(((Square)board.getSquares()[i][j]).isWhite()){
    //                 toDisplay += whiteSquare(piece);
    //             }
	// 			else{//square must be black
    //                 toDisplay += blackSquare(piece);
	// 			}
	// 		}
    //         toDisplay += "\n";
	// 	}
	// }

	//Abstract methods

	/**
	 * Creates the display of a white square on a chess board. The display is affected
	 * by what kind of piece type is on it ranging from white to black to empty.
	 * 
	 * @param Piece piece The current piece type on the square, with its type ranging from
	 * white, black, or empty.
	 * @return The display of the white square with its current piece type on the chess
	 * board.
	 */
	public abstract String whiteSquare(Piece piece);

    /**
	 * Creates the display of a black square on a chess board. The display is affected
	 * by what kind of piece type is on it ranging from white to black to empty.
	 * 
	 * @param Piece piece The current piece type on the square, with its type ranging from
	 * white, black, or empty.
	 * @return The display of the black square with its current piece type on the chess
	 * board.
	 */
	public abstract String blackSquare(Piece piece);

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
	public abstract String takenPieces(BoardIF Board, String player1, String player2);

    /**
	 * Retrieves the visual display of the specific rank/row number.
	 * 
	 * @param int rank The number that retrieves the rank at specific location.
	 * @return The visual display of the rank at that specific row.
	 */
	public abstract String displayRank(int rank);

    /**
	 * Shows the files order when facing the white player's side of the chess board.
	 * 
	 * @return The visual of the files from A to H.
	 */
	public abstract String displayWhiteFile();

    /**
	 * Shows the files order when facing the black player's side of the chess board.
	 * 
	 * @return The visual of the files from H to A.
	 */
	public abstract String displayBlackFile();

	/**
	 * Displays a tile that shows a valid position for that's part of the piece's movement.
	 * 
	 * @param Piece piece The chess piece type that is on the valid position tile.
	 * @result A visual of a square that represents a valid position.
	 */
	public abstract String displayValidMoves(Piece piece);

}
