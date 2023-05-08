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

public class BishopRules extends BorderPane {

	public BishopRules() {
		super();
		
		Label title = new Label("Bishop Movement");
		title.setId("Title");
		this.setTop(title);
		
		VBox rules = new VBox();
		ArrayList<Label> subtitles = new ArrayList<>();
		
		Label s0 = new Label("The bishop moves in an unique way to say the least");
		subtitles.add(s0);
		Label s1 = new Label("Like the king and queen, the bishop moves in diagonal directions");
		subtitles.add(s1);
		Label s2 = new Label("However, unlike the rook, the bishop cannot move horizontally or "+
				             "\nvertically");
		subtitles.add(s2);
		Label s3 = new Label("Additional notes:");
		subtitles.add(s3);
		Label s4 = new Label(">The bishop cannot move if its path is blocked by a piece \nwith the "+
				              "same color as the bishop or after capturing \nan opposing piece");
		subtitles.add(s4);
		Label s5 = new Label(">A bishop can only move onto squares that match the color of \nits "+
				             "initial starting position");
		subtitles.add(s5);
		
		subtitles.forEach(n -> {
			n.getStyleClass().add("subtitle");
			rules.getChildren().add(n);
		});
		
		this.setLeft(rules);
		
		GridPane board = new GridPane();
		board.setAlignment(Pos.CENTER);
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				ChessSquare sq;
				if((i % 2 == 0 && j % 2 == 0)||
				   (i % 2 != 0 && j % 2 != 0)) {
					sq = new ChessSquare(false,new Piece(ChessPieceType.EMPTY, GameColor.BLACK));
				}
				else {
					sq = new ChessSquare(true,new Piece(ChessPieceType.EMPTY, GameColor.BLACK));
				}
				if(i == 2 && j == 2) {
					sq.setPiece(new Piece(ChessPieceType.BISHOP, GameColor.WHITE));
					sq.setSelectedColor();
				}
				else if(i == j || (i == 5 - (j+1))) {
					sq.setValidColor();
				}
				sq.disable();
				board.add(sq, j, i);
			}
		}
		this.setCenter(board);
	}
}
