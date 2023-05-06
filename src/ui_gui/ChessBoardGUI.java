package src.ui_gui;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import src.enums.File;
import src.enums.Rank;
import src.model.Board;
import src.model.Piece;
import src.model.Position;
import src.model.Square;

public class ChessBoardGUI extends GridPane {

	static ChessSquare[][] board;
	public static Board ogBoard;
	public static ChessSquare currentChessPiece = null;
	String[] ranks = {"1","2","3","4","5","6","7","8"};
	String[] files = {"A","B","C","D","E","F","G","H"};
	
	public ChessBoardGUI(Board board, boolean isWhite) {
		super();
		this.setAlignment(Pos.BASELINE_CENTER);
		ChessBoardGUI.board = new ChessSquare[8][8];
		ChessBoardGUI.ogBoard = board;
		Square[][] tiles = (Square[][])ogBoard.getSquares();
		if(isWhite) this.drawWhite(tiles);
		else this.drawBlack(tiles);
	}
	
	private void drawWhite(Square[][] tiles) {
		for(int i = 0; i < tiles.length; i++) {
			this.add(new Label(this.ranks[this.ranks.length - (i+1)]), 0, i);
			for(int j = 0; j < tiles[i].length; j++) {
				Piece piece = (Piece)tiles[i][j].getPiece();
				this.board[i][j] = new ChessSquare(tiles[i][j].isWhite(), piece);
				this.add(this.board[i][j], j + 1, i);
			}
		}
		for(int i = 0; i < this.files.length; i++) {
			this.add(new Label(this.files[i]), i+1, 8);
		}
	}
	
	private void drawBlack(Square[][] tiles) {
		for(int i = tiles.length - 1; i >= 0; i--) {
			this.add(new Label(this.ranks[i]), 0, i);
			for(int j = tiles.length - 1; j >= 0; j--) {
				Piece piece = (Piece)tiles[i][j].getPiece();
				board[i][j] = new ChessSquare(tiles[i][j].isWhite(), piece);
				this.add(board[i][j], tiles[i].length - j, tiles.length - (i+1));
			}
		}
		for(int i = this.files.length - 1; i >= 0; i--) {
			this.add(new Label(this.files[i]), this.files.length - i, 8);
		}
	}
	
	public static void updateCurrentChessPiece(ChessSquare square) {
		ChessBoardGUI.currentChessPiece = square;
		int[] pos = ChessBoardGUI.clear();
		Piece piece = (Piece)ChessBoardGUI.ogBoard.getPiece(pos[0], pos[1]);
		Position space = new Position(Rank.getRankByIndex(pos[0]),File.getFileByIndex(pos[1]));
		ArrayList<Position> moves = piece.showMoves(space);
		ChessBoardGUI.showValidMoves(moves);
		/*
		for(int row = 0; row < board.length; row++) {
			for(int col = 0; col < board[row].length; col++) {
				if(board[row][col] != square && board[row][col].getId().equals("SelectedSquare")) {
					 board[row][col].setSquareColor();
				}
			}
		}
		*/
	}
	
	public static int[] clear() {
		int[] pos = new int[2];
		for(int row = 0; row < board.length; row++) {
			for(int col = 0; col < board[row].length; col++) {
				if(board[row][col] != ChessBoardGUI.currentChessPiece) {
					board[row][col].setSquareColor();
				}
				else {
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

	
}
