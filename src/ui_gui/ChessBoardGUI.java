package src.ui_gui;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import src.controller.Chess;
import src.enums.ChessPieceType;
import src.enums.File;
import src.enums.GameColor;
import src.enums.Rank;
import src.model.Board;
import src.model.Piece;
import src.model.Position;
import src.model.Square;

public class ChessBoardGUI extends GridPane {

	/**Oops! All static variables!**/
	/**Board the contains the visual chess squares**/
	public static ChessSquare[][] board;
	/**Reference to the current chess game**/
	public static Chess game;
	/**Reference to the board in the chess game**/
	public static Board ogBoard;
	/**Boolean that indictates if it's the white player's turn or not**/
	public static boolean isWhite;
	/**Label that updates the status of the game**/
	public static Label playerTurn;
	/**The position where the chess piece is being moved from**/
	public static Position from = null;
	/**The position where the chess piece is being moved to**/
	public static Position to = null;
	/**The current chess square that contains the chess piece that the user selected**/
	public static ChessSquare currentChessPiece = null;
	/**Array that stores labels for rank rows**/
	public static Label[] rankLabel = new Label[8];
	/**Array that stores labels for file rows**/
	public static Label[] fileLabel = new Label[8];
	/**Array that stores rank values**/
	public static String[] ranks = {"1","2","3","4","5","6","7","8"};
	/**Array that stores file values**/
	public static String[] files = {"A","B","C","D","E","F","G","H"};
	
	/**
	 * Constructor for ChessBoardGUI.
	 * 
	 * @param Chess chessgame Chess game that that GUI is using.
	 */
	public ChessBoardGUI(Chess chessgame) {
		super();
		this.setAlignment(Pos.BASELINE_CENTER);
		ChessBoardGUI.board = new ChessSquare[8][8];
		ChessBoardGUI.game = chessgame;
		ChessBoardGUI.ogBoard = chessgame.getBoard();
		ChessBoardGUI.isWhite = true;
		Square[][] tiles = (Square[][])ogBoard.getSquares();
		this.draw(tiles);
	}
	
	/**
	 * Draws the layout of the chess board when the GUI is started.
	 * 
	 * @param Square[][] tiles The collection of chess squares that is used to build the chess
	 * board GUI off from. 
	 */
	private void draw(Square[][] tiles) {
		playerTurn = new Label("Player One's Turn");
		playerTurn.setId("Title");
		this.add(playerTurn, 0, 0, 9, 1);
		this.createGridLabels();
		for(int i = 0; i < tiles.length; i++) {
			this.add(rankLabel[i], 0, i + 1);
			for(int j = 0; j < tiles[i].length; j++) {
				Piece piece = (Piece)tiles[i][j].getPiece();
				board[i][j] = new ChessSquare(tiles[i][j].isWhite(), piece);
				this.add(board[i][j], j + 1, i + 1);
			}
		}
		for(int i = 0; i < ChessBoardGUI.files.length; i++) {
			this.add(fileLabel[i], i+1, 9);
		}
	}
	
	/**
	 * Helper method for draw. Initializes the starting values for rank and file label arrays when
	 * the chess game started.
	 */
	private void createGridLabels() {
		for(int i = 0; i < rankLabel.length; i++) {
			rankLabel[i] = new Label(ranks[ranks.length - (i+1)]);
			rankLabel[i].getStyleClass().add("rank_fileLabel");
			fileLabel[i] = new Label(files[i]);
			fileLabel[i].getStyleClass().add("rank_fileLabel");
		}
	}
	
	/**
	 * Updates the player label when a new turn begins; when it's the white's player, changes
	 * the text to display their turn and vice versa.
	 */
	private static void changePlayerLabel() {
		if(isWhite) playerTurn.setText("Player One's Turn");
		else playerTurn.setText("Player Two's Turn");
	}
	
	
	/**
	 * Grabs selected square and updates the chess game. From position is updated if the
	 * current piece is on a selected square. To position is updated if the current piece
	 * is on a valid square. If the from and to positions are valid, then the game is updated
	 * and ends the current player's turn.
	 * 
	 * @param ChessSquare square The square that contains the current chess piece.
	 */
	public static void updateCurrentChessPiece(ChessSquare square) {
		//Updates current piece
		ChessBoardGUI.currentChessPiece = square;
		//Grabs position of selected piece
		int[] pos = ChessBoardGUI.clear();
		Piece piece = (Piece)ChessBoardGUI.ogBoard.getPiece(pos[0], pos[1]);
		Position space = new Position(Rank.getRankByIndex(pos[0]),File.getFileByIndex(pos[1]));
		//Checks if the current piece is equal to the current player's side, if the square is
		//empty, or if the current piece is an enemy piece.
		if(piece.isWhite() == ChessBoardGUI.isWhite || 
		   piece.getChessPieceType() == ChessPieceType.EMPTY ||
		   (currentChessPiece.getId().equals("ValidSquare") && piece.isWhite() != isWhite)) {
			//If the current piece is on a selected square or a checked square
			if(ChessBoardGUI.currentChessPiece.getId().equals("SelectedSquare") ||
			   ChessBoardGUI.currentChessPiece.getId().equals("CheckSquare")) {
				//Updates from position and shows all valid moves of chess piece
				ChessBoardGUI.from = space;
				ArrayList<Position> moves = piece.showMoves(space);
				ChessBoardGUI.showValidMoves(moves);
			}
			//If the current piece is on a valid square
			else if(ChessBoardGUI.currentChessPiece.getId().equals("ValidSquare")) {
				//Updates to position
				ChessBoardGUI.to = space;
			}
			else {
				//Sets from and to positions to null
				ChessBoardGUI.from = null; ChessBoardGUI.to = null;
			}
			//If from and to positions are valid
			if(ChessBoardGUI.from != null && ChessBoardGUI.to != null) {
				//Moves piece
				ChessBoardGUI.game.move(from.getFile(), from.getRank(),
										to.getFile(), to.getRank());
				currentChessPiece.setSquareColor();
				//Updates the board
				update();
				displayCapture();
				//Ends the player's turn
				endTurn();
			}
		}
	}
	
	/**
	 * Ends the current turn and checks for check and endgame conditions. If either of the endgame
	 * conditions were met, the game ends; otherwise, switches to the next player.
	 */
	public static void endTurn() {
		//Sets from and to positions to null
		from = null; to = null;
		//Changes player
		ChessBoardGUI.isWhite = !isWhite;
		//Checks for check status
		updateCheck();
		//If there's no valid moves
		if(game.checkNoValidMoves(isWhite)) {
			//Checks for checkmates; otherwise it's draw by stalemate
			if(isWhite && game.getCheck()) {
				ChessBoardGUI.playerTurn.setText("Player Two wins by Checkmate!");
			}
			else if(!isWhite && game.getCheck()) {
				ChessBoardGUI.playerTurn.setText("Player One wins by Checkmate!");
			}
			else {
				ChessBoardGUI.playerTurn.setText("Draw by Stalemate!");
			}
			disableAll();
		}
		//Checks for threefold repetition condition
		else if(game.threeFoldRepetition()) {
			ChessBoardGUI.playerTurn.setText("Draw by Threefold Repetition");
			disableAll();
		}
		//Checks for fifty move rule condition
		else if(game.getFiftyMove() == 50) {
			ChessBoardGUI.playerTurn.setText("Draw by Fifty Move Rule");
			disableAll();
		}
		else {
			//Swaps board and change player
			swap();
			changePlayerLabel();
		}
	}
	
	/**
	 * Checks if the player's turn puts the other player into check. If the other player was caught
	 * in check, then updates the board with the check square around their king as a warning.
	 */
	public static void updateCheck() {
		Position king;
		//Checks for check during white player's or black player's turn
		if(isWhite) king = ogBoard.getWhiteKingPos();
		else king = ogBoard.getBlackKingPos();
		game.check(king, isWhite);
		int row = king.getRank().getArrayRank();
		int col = king.getFile().getArrayFile();
		//If current player is in check, updates board with checked square; otherwise resets it
		if(game.getCheck()) board[row][col].setCheckColor();
		else board[row][col].setSquareColor();
	}
	
	/**
	 * Checks when a capture occurs and updates the board to remove the captured piece.
	 */
	public static void displayCapture() {
		//Uses from position as player and enemy pieces swap when moving
		int oldRow = from.getRank().getArrayRank();
		int oldCol = from.getFile().getArrayFile();
		Piece prev = board[oldRow][oldCol].getPiece();
		//If piece is not an empty space
		if(prev.getChessPieceType() != ChessPieceType.EMPTY) {
			board[oldRow][oldCol].setPiece((Piece)ogBoard.getPiece(oldRow, oldCol));
		}
	}
	
	/**
	 * Updates the board to display move from player.
	 */
	public static void update() {
		int fromRow = from.getRank().getArrayRank();
		int fromCol = from.getFile().getArrayFile();
		int toRow = to.getRank().getArrayRank();
		int toCol = to.getFile().getArrayFile();
		//Grabs piece of from position
		Piece temp = board[fromRow][fromCol].getPiece();
		//Switches from piece to to piece
		board[fromRow][fromCol].setPiece(board[toRow][toCol].getPiece());
		//Switches to piece to from piece
		board[toRow][toCol].setPiece(temp);
	}
	
	/**
	 * Swaps the board layout when a new turn occurs. When it's the black player's turn, displays 
	 * their pieces from their perspective and vice versa.
	 */
	public static void swap() { 
		for(int i = 0; i < board.length/2; i++) {
			for(int j = 0; j < board[i].length; j++) {
				String oldId = board[i][j].getId();
				Piece oldPiece = board[i][j].getPiece();
				ChessSquare old = board[i][j];
				String newId = board[board.length-(i+1)][j].getId();
				Piece newPiece = board[board.length-(i+1)][j].getPiece();
				ChessSquare neu = board[board.length-(i+1)][j];
				board[i][j].setId(newId);
				board[i][j].setPiece(newPiece);
				board[i][j] = neu;
				board[board.length-(i+1)][j].setId(oldId);
				board[board.length-(i+1)][j].setPiece(oldPiece);
				board[board.length-(i+1)][j].setChessView(old.getChessView());
				board[board.length-(i+1)][j] = old;
				//Updates the labels to represent positions from other player's perspective
				swapLabels();
			}
		}
	}
	
	/**
	 * Helper method for swap. Updates the rank and file labels when the board layout is being
	 * swapped from one player to another.
	 */
	public static void swapLabels() {
		for(int i = 0; i < rankLabel.length; i++) {
			if(isWhite) {
				rankLabel[i].setText(ranks[ranks.length - (i+1)]);
				fileLabel[i].setText(files[i]);
			}
			else {
				rankLabel[i].setText(ranks[i]);
				fileLabel[i].setText(files[files.length - (i+1)]);
			}
		}
	}
	
	/**
	 * Clears off squares except for the current selected one. For the current selected piece,
	 * it's row and column number are retrieved and stored in an integer array. 
	 * 
	 * @return An integer array that stores the row and col of the current selected square.
	 */
	public static int[] clear() {
		int[] pos = new int[2];
		for(int row = 0; row < board.length; row++) {
			for(int col = 0; col < board[row].length; col++) {
				if(board[row][col] != ChessBoardGUI.currentChessPiece) {
					board[row][col].setSquareColor();
				}
				else if(board[row][col] == ChessBoardGUI.currentChessPiece) {
					//Grabs row and col for position
					pos[0] = row;
					pos[1] = col;
				}
			}
		}
		return pos;
	}
	
	/**
	 * Displays all of the valid positions of the current piece onto the GUI.
	 * 
	 * @param moves The list of all of the valid positions that the current chess piece can move
	 * to,
	 */
	public static void showValidMoves(ArrayList<Position> moves) {
		for(int i = 0; i < moves.size(); i++) {
			Position pos = moves.get(i);
			int row = pos.getRank().getArrayRank();
			int col = pos.getFile().getArrayFile();
			board[row][col].setValidColor();
		}
	}
	
	/**
	 * Disables all mouse events on the chess squares when an end game condition is met.
	 */
	public static void disableAll() {
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board.length; j++) {
				board[i][j].disable();
			}
		}
	}
	
}
