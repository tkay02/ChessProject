package src.ui_gui.screens;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import src.enums.ChessPieceType;
import src.enums.GameColor;
import src.model.Piece;
import src.ui_gui.ChessSquare;

public class OverviewChessNotation extends VBox {

	/**
	 * Displays chess notation such as the format and two examples.
	 */
	public OverviewChessNotation() {
		super();
		this.setAlignment(Pos.TOP_LEFT);
		
		//Title
		Label title = new Label("Chess Notation");
		title.setId("Title");
		this.getChildren().add(title);
		
		ArrayList<Label> subtitles = new ArrayList<>();
		
		//Subtitles
		Label s0 = new Label("To select specific pieces/positions on the chessboard, the format "+
                           "goes like this:");
		subtitles.add(s0);
		Label s1 = new Label("\t\t\t\t\t\t\tFile of Position + Rank of Position\n\n");
		subtitles.add(s1);
		Label s2 = new Label("An example of this notation would be like this:");
		subtitles.add(s2);
		
		subtitles.forEach(n -> {
			n.getStyleClass().add("subtitle");
			this.getChildren().add(n);
		});
		subtitles.clear();
		
		//Example One
		GridPane board1 = new GridPane();
		board1.setAlignment(Pos.TOP_CENTER);
		Label rank = new Label("1");
		rank.getStyleClass().add("rank_fileLabel");
		board1.add(rank, 0, 0);
		for(int i = 0; i < 3; i++) {
			ChessSquare sq;
			if(i % 2 == 0) {
				sq = new ChessSquare(false, new Piece(ChessPieceType.EMPTY, GameColor.BLACK));
			}
			else {
				sq = new ChessSquare(true, new Piece(ChessPieceType.EMPTY, GameColor.BLACK));
			}
			if(i == 1) {
				sq.setPiece(new Piece(ChessPieceType.KNIGHT, GameColor.WHITE));
			}
			sq.disable();
			board1.add(sq, i+1, 0);
		}
		String files[] = {"A", "B", "C"};
		for(int i = 0; i < files.length; i++) {
			Label file = new Label(files[i]);
			file.getStyleClass().add("rank_fileLabel");
			board1.add(file, i+1, 1);
		}
		this.getChildren().add(board1);
		
		//More subtitles
		Label s3 = new Label("The white knight is on the position B1");
		subtitles.add(s3);
		Label s4 = new Label("Quick reminder: The file is written as a capital in regards to "+
                          "notation!");
		subtitles.add(s4);
		Label s5 = new Label("Another example:");
		subtitles.add(s5);
		subtitles.forEach(n -> {
			n.getStyleClass().add("subtitle");
			this.getChildren().add(n);
		});
		
		//Pane to store movement example
		HBox move = new HBox();
		move.setAlignment(Pos.CENTER);
		move.setSpacing(45);
		
		
		String[] ranks = {"3","2","1"};
		
		//From position
		GridPane board2 = new GridPane();
		for(int i = 0; i < 3; i++) {
			rank = new Label(ranks[i]);
			rank.getStyleClass().add("rank_fileLabel");
			board2.add(rank, 0, i);
			for(int j = 0; j < 3; j++) {
				ChessSquare sq;
				if((i % 2 == 0 && j % 2 == 0)||
				   (i % 2 != 0 && j % 2 != 0)) {
					sq = new ChessSquare(false,new Piece(ChessPieceType.EMPTY, GameColor.BLACK));
				}
				else {
					sq = new ChessSquare(true,new Piece(ChessPieceType.EMPTY, GameColor.BLACK));
				}
				if(i == 2 && j == 1) {
					sq.setPiece(new Piece(ChessPieceType.KNIGHT, GameColor.WHITE));
				}
				sq.disable();
				board2.add(sq, j+1, i);
			}
		}
		for(int i = 0; i < files.length; i++) {
			Label file = new Label(files[i]);
			file.getStyleClass().add("rank_fileLabel");
			board2.add(file, i+1, 3);
		}
		move.getChildren().add(board2);
		
		//To position
		GridPane board3 = new GridPane();
		for(int i = 0; i < 3; i++) {
			rank = new Label(ranks[i]);
			rank.getStyleClass().add("rank_fileLabel");
			board3.add(rank, 0, i);
			for(int j = 0; j < 3; j++) {
				ChessSquare sq;
				if((i % 2 == 0 && j % 2 == 0)||
				   (i % 2 != 0 && j % 2 != 0)) {
					sq = new ChessSquare(false,new Piece(ChessPieceType.EMPTY, GameColor.BLACK));
				}
				else {
					sq = new ChessSquare(true,new Piece(ChessPieceType.EMPTY, GameColor.BLACK));
				}
				if(i == 0 && j == 2) {
					sq.setPiece(new Piece(ChessPieceType.KNIGHT, GameColor.WHITE));
				}
				sq.disable();
				board3.add(sq, j+1, i);
			}
		}
		for(int i = 0; i < files.length; i++) {
			Label file = new Label(files[i]);
			file.getStyleClass().add("rank_fileLabel");
			board3.add(file, i+1, 3);
		}
		move.getChildren().add(board3);
		
		this.getChildren().add(move);
		
		//How to display from position to to position
		Label s6 = new Label("The white knight moved from B1 to C3");
		s6.getStyleClass().add("subtitle");
		this.getChildren().add(s6);
	}
}
