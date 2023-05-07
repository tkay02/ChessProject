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
	static ChessSquare[][] board;
	public static Chess game;
	public static Board ogBoard;
	public static boolean isWhite;
	public static Label playerTurn;
	public static Position from = null;
	public static Position to = null;
	public static ChessSquare currentChessPiece = null;
	public static Label[] rankLabel = new Label[8];
	public static Label[] fileLabel = new Label[8];
	public static String[] ranks = {"1","2","3","4","5","6","7","8"};
	public static String[] files = {"A","B","C","D","E","F","G","H"};
	
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
		for(int i = 0; i < this.files.length; i++) {
			this.add(fileLabel[i], i+1, 9);
		}
	}
	
	private void createGridLabels() {
		for(int i = 0; i < rankLabel.length; i++) {
			rankLabel[i] = new Label(ranks[ranks.length - (i+1)]);
			rankLabel[i].getStyleClass().add("rank_fileLabel");
			fileLabel[i] = new Label(files[i]);
			fileLabel[i].getStyleClass().add("rank_fileLabel");
		}
	}
	
	private static void changePlayerLabel() {
		if(isWhite) playerTurn.setText("Player One's Turn");
		else playerTurn.setText("Player Two's Turn");
	}
	
	
	public static void updateCurrentChessPiece(ChessSquare square) {
		ChessBoardGUI.currentChessPiece = square;
		int[] pos = ChessBoardGUI.clear();
		Piece piece = (Piece)ChessBoardGUI.ogBoard.getPiece(pos[0], pos[1]);
		Position space = new Position(Rank.getRankByIndex(pos[0]),File.getFileByIndex(pos[1]));
		if(piece.isWhite() == ChessBoardGUI.isWhite || 
		   piece.getChessPieceType() == ChessPieceType.EMPTY ||
		   (currentChessPiece.getId().equals("ValidSquare") && piece.isWhite() != isWhite)) {
			if(ChessBoardGUI.currentChessPiece.getId().equals("SelectedSquare")) {
				ChessBoardGUI.from = space;
				ArrayList<Position> moves = piece.showMoves(space);
				ChessBoardGUI.showValidMoves(moves);
			}
			else if(ChessBoardGUI.currentChessPiece.getId().equals("ValidSquare")) {
				ChessBoardGUI.to = space;
			}
			else {
				ChessBoardGUI.from = null; ChessBoardGUI.to = null;
			}
			if(ChessBoardGUI.from != null && ChessBoardGUI.to != null) {
				ChessBoardGUI.game.move(from.getFile(), from.getRank(),
										to.getFile(), to.getRank());
				currentChessPiece.setSquareColor();
				update();
				displayCapture();
				endTurn();
			}
		}
	}
	
	public static void endTurn() {
		from = null; to = null;
		ChessBoardGUI.isWhite = !isWhite;
		updateCheck();
		if(game.checkNoValidMoves(isWhite)) {
			if(isWhite && game.getCheck()) {
				ChessBoardGUI.playerTurn.setText("Player Two wins by Checkmate!");
				disableAll();
			}
			else if(!isWhite && game.getCheck()) {
				ChessBoardGUI.playerTurn.setText("Player One wins by Checkmate!");
				disableAll();
			}
			else {
				ChessBoardGUI.playerTurn.setText("Draw by Stalemate!");
				disableAll();
			}
		}
		else if(game.threeFoldRepetition()) {
			ChessBoardGUI.playerTurn.setText("Draw by Threefold Repetition");
			disableAll();
		}
		else if(game.getFiftyMove() == 50) {
			ChessBoardGUI.playerTurn.setText("Draw by Fifty Move Rule");
			disableAll();
		}
		else {
			swap();
			changePlayerLabel();
		}
	}
	
	public static void updateCheck() {
		Position king;
		if(isWhite) king = ogBoard.getWhiteKingPos();
		else king = ogBoard.getBlackKingPos();
		game.check(king, isWhite);
		int row = king.getRank().getArrayRank();
		int col = king.getFile().getArrayFile();
		if(game.getCheck()) board[row][col].setCheckColor();
		else board[row][col].setSquareColor();
	}
	
	public static void displayCapture() {
		int oldRow = from.getRank().getArrayRank();
		int oldCol = from.getFile().getArrayFile();
		Piece prev = board[oldRow][oldCol].getPiece();
		if(prev.getChessPieceType() != ChessPieceType.EMPTY) {
			board[oldRow][oldCol].setPiece((Piece)ogBoard.getPiece(oldRow, oldCol));
		}
	}
	
	public static void update() {
		int fromRow = from.getRank().getArrayRank();
		int fromCol = from.getFile().getArrayFile();
		int toRow = to.getRank().getArrayRank();
		int toCol = to.getFile().getArrayFile();
		Piece temp = board[fromRow][fromCol].getPiece();
		board[fromRow][fromCol].setPiece(board[toRow][toCol].getPiece());
		board[toRow][toCol].setPiece(temp);
	}
	
	public static void swap() {
		for(int i = 0; i < board.length/2; i++) {
			for(int j = 0; j < board[i].length; j++) {
				String oldId = board[i][j].getId();
				Piece oldPiece = board[i][j].getPiece();
				ChessSquare old = board[i][j];
				String newId = board[board.length-(i+1)][board.length-(j+1)].getId();
				Piece newPiece = board[board.length-(i+1)][board.length-(j+1)].getPiece();
				ChessSquare neu = board[board.length-(i+1)][board.length-(j+1)];
				board[i][j].setId(newId);
				board[i][j].setPiece(newPiece);
				board[i][j] = neu;
				board[board.length-(i+1)][board.length-(j+1)].setId(oldId);
				board[board.length-(i+1)][board.length-(j+1)].setPiece(oldPiece);
				board[board.length-(i+1)][board.length-(j+1)] = old;
				swapLabels();
			}
		}
	}
	
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
	
	public static int[] clear() {
		int[] pos = new int[2];
		for(int row = 0; row < board.length; row++) {
			for(int col = 0; col < board[row].length; col++) {
				if(board[row][col] != ChessBoardGUI.currentChessPiece ||
				   board[row][col].getId().equals("CheckSquare")) {
					board[row][col].setSquareColor();
				}
				else if(board[row][col] == ChessBoardGUI.currentChessPiece) {
					pos[0] = row;
					pos[1] = col;
				}
			}
		}
		return pos;
	}
	
	public static void showValidMoves(ArrayList<Position> moves) {
		for(int i = 0; i < moves.size(); i++) {
			Position pos = moves.get(i);
			int row = pos.getRank().getArrayRank();
			int col = pos.getFile().getArrayFile();
			board[row][col].setValidColor();
		}
	}
	
	public static void disableAll() {
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board.length; j++) {
				board[i][j].disable();
			}
		}
	}
	
}
