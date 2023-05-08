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

public class RookRules extends BorderPane {

	/**
	 * Displays information about the rook, including a picture that displays all of the
	 * possible moves that a rook piece can do.
	 */
	public RookRules() {
		super();
		
		//Title
		Label title = new Label("Rook Movement");
		title.setId("Title");
		this.setTop(title);
		
		//Pane to display subtitles
		VBox rules = new VBox();
		ArrayList<Label> subtitles = new ArrayList<>();
		
		//Subtitles
		Label s0 = new Label("Among chess players, the rook is the most vital chess piece \nafter "+
				             "the queen");
		subtitles.add(s0);
		Label s1 = new Label("The rook can move onto all unoccupied squares vertically and "+
                             "\nhorizontally");
		subtitles.add(s1);
		Label s2 = new Label("The only weakness that the rook has compared to the queen is its "+
				             "\ninability to move diagonally");
		subtitles.add(s2);
		Label s3 = new Label("Additional notes:");
		subtitles.add(s3);
		Label s4 = new Label(">The rook cannot move if its path is blocked by a piece with the \nsame"+
                             "color as the rook or after capturing an opposing piece");
		subtitles.add(s4);
		
		subtitles.forEach(n -> {
			n.getStyleClass().add("subtitle");
			rules.getChildren().add(n);
		});
		
		this.setLeft(rules);
		
		//Board to display rook movement
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
					sq.setPiece(new Piece(ChessPieceType.ROOK, GameColor.WHITE));
					sq.setSelectedColor();
				}
				else if(i == 2 || j == 2) {
					sq.setValidColor();
				}
				sq.disable();
				board.add(sq, j, i);
			}
		}
		this.setCenter(board);
	}
}
