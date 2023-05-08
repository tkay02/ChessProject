package src.ui_gui.screens;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import src.enums.ChessPieceType;
import src.enums.GameColor;
import src.model.Piece;
import src.ui_gui.ChessSquare;

public class PawnRules extends BorderPane {

	public PawnRules() {
		super();
		
		Label title = new Label("Pawn Movement");
		title.setId("Title");
		this.setTop(title);
		
		VBox rule = new VBox();
		ArrayList<Label> subtitles = new ArrayList<>();
		
		Label s0 = new Label("The pawn is the most diverse piece in regards to movement");
		subtitles.add(s0);
		Label s1 = new Label("The pawn is also the only piece in chess that moves forward, \nnever "+
                             "backwards");
		subtitles.add(s1);
		Label s2 = new Label("The pawn can usually only move one space forward; however, it \ndoes "+
                             "have some exceptions:");
		subtitles.add(s2);
		Label s3 = new Label(">A pawn can move two spaces forward from its starting position");
		subtitles.add(s3);
		Label s4 = new Label(">A pawn can move forward diagonally by capturing an opposing piece");
		subtitles.add(s4);
		Label s5 = new Label(">However, a pawn cannot move if there is another piece in front of "+
                             "\nit, which includes other pawns");
		subtitles.add(s5);
		Label s6 = new Label("Additional notes:");
		subtitles.add(s6);
		Label s7 = new Label(">Due to the unique way that pawns capture, pawns can be efficent "+
                             "\nblockers against other pawns");
		subtitles.add(s7);
		Label s8 = new Label(">If the pawn is able to reach the end of its respective side, \nthen "+
                             "the pawn can promote to a different piece such as a queen," +
                             "\nknight, rook, or bishop");
		subtitles.add(s8);
		
		subtitles.forEach(n -> {
			n.getStyleClass().add("subtitle");
			rule.getChildren().add(n);
		});
		
		this.setLeft(rule);
		
		GridPane board = new GridPane();
		board.setAlignment(Pos.TOP_CENTER);
		
		for(int i = 0; i < 3; i++) {
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
					sq.setPiece(new Piece(ChessPieceType.PAWN, GameColor.WHITE));
					sq.setSelectedColor();
				}
				else if(i == 1 && j == 1) {
					sq.setValidColor();
				}
				sq.disable();
				board.add(sq, j, i);
			}
		}
		board.add(new Label("\n"), 0, 3, 3, 1);
		
		for(int i = 0; i < 3; i++) {
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
					sq.setPiece(new Piece(ChessPieceType.PAWN, GameColor.WHITE));
					sq.setSelectedColor();
				}
				else if((i == 1 || i == 0) && j == 1) {
					sq.setValidColor();
				}
				sq.disable();
				board.add(sq, j, i+4);
			}
		}
		board.add(new Label("\n"),0, 7, 3, 1);
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				ChessSquare sq;
				if((i % 2 == 0 && j % 2 == 0)||
				   (i % 2 != 0 && j % 2 != 0)) {
					sq = new ChessSquare(false,new Piece(ChessPieceType.EMPTY, GameColor.BLACK));
				}
				else {
					sq = new ChessSquare(true,new Piece(ChessPieceType.EMPTY, GameColor.BLACK));
				}
				if(i == 1) {
					sq.setPiece(new Piece(ChessPieceType.PAWN, GameColor.BLACK));
				}
				if(i == 2 && j == 1) {
					sq.setPiece(new Piece(ChessPieceType.PAWN, GameColor.WHITE));
					sq.setSelectedColor();
				}
				else if(i == 1 && j != 1) {
					sq.setValidColor();
				}
				sq.disable();
				board.add(sq, j, i+8);
			}
		}
		this.setCenter(board);
	}

}
