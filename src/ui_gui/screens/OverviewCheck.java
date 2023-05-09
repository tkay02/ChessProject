package src.ui_gui.screens;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import src.enums.ChessPieceType;
import src.enums.GameColor;
import src.model.Piece;
import src.ui_gui.ChessSquare;

public class OverviewCheck extends VBox {

	/**
	 * Displays information about check condition such as what the condition means, an example of
	 * being in check, and how to get out of check.
	 */
	public OverviewCheck() {
		super();
		
		//Title
		this.setAlignment(Pos.TOP_LEFT);
		Label title = new Label("Check Condition");
		title.setId("Title");
		this.getChildren().add(title);
		
		//Subtitles
		ArrayList<Label> subtitles = new ArrayList<>();
		Label s0 = new Label("The main objection of the game is to checkmate the enemy king");
		subtitles.add(s0);
		Label s1 = new Label("Whenever an opposing piece is placed in the direction of the king, "+
                			 "the king is placed in a state called check");
		subtitles.add(s1);
		Label s2 = new Label("An example of a situation that puts your king in check:");
		subtitles.add(s2);
		
		subtitles.forEach(n -> {
			n.getStyleClass().add("subtitle");
			this.getChildren().add(n);
		});
		subtitles.clear();
		
		//Board to display check example
		GridPane board = new GridPane();
		board.setAlignment(Pos.TOP_CENTER);
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				ChessSquare sq;
				if((i % 2 == 0 && j % 2 == 0) ||
				   (i % 2 != 0 && j % 2 != 0)) {
					sq = new ChessSquare(false, new Piece(ChessPieceType.EMPTY, GameColor.BLACK));
				}
				else {
					sq = new ChessSquare(true, new Piece(ChessPieceType.EMPTY, GameColor.BLACK));
				}
				if(i == 0 && j == 1) {
					sq.setPiece(new Piece(ChessPieceType.KING, GameColor.WHITE));
					sq.setCheckColor();
				}
				else if(i == 2 && j == 1) {
					sq.setPiece(new Piece(ChessPieceType.QUEEN, GameColor.BLACK));
				}
				sq.disable();
				board.add(sq, j, i);
			}
		}
		this.getChildren().add(board);
		
		//More subtitles
		Label s3 = new Label("When placed in check, the player only has three possible moves:");
		subtitles.add(s3);
		Label s4 = new Label(">Move the king away from incoming danger");
		subtitles.add(s4);
		Label s5 = new Label(">Capture the enemy piece that's putting the king in check");
		subtitles.add(s5);
		Label s6 = new Label(">Or have a chess piece block the incoming danger directed toward the "+
                             "king");
		subtitles.add(s6);
		Label s7 = new Label("Any other moves are invalid if the king is in check");
		subtitles.add(s7);
		
		subtitles.forEach(n -> {
			n.getStyleClass().add("subtitle");
			this.getChildren().add(n);
		});
	}
	
}
