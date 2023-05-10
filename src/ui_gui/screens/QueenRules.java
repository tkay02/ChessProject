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

/**
 * Class provides a decription about the rules of a queen.
 * 
 * @author Nolan Flinchum, Thomas Kay (100%), Joseph Oladeji, Levi Sweat
 * @date 5/9/2023
 */
public class QueenRules extends BorderPane {
	
	/**
	 * Displays information about the queen, including a picture that displays all of the
	 * possible moves that a queen piece can do.
	 */
	public QueenRules() {
		super();
		
		//Title
		Label title = new Label("Queen Movement");
		title.setId("Title");
		this.setTop(title);
		
		//Pane that displays the subtitles
		VBox rules = new VBox();
		ArrayList<Label> subtitles = new ArrayList<>();
		
		//Subtitles
		Label s0 = new Label("The queen is the most powerful piece in the game");
		subtitles.add(s0);
		Label s1 = new Label("The queen can move in all possible directions vertically, "+
                             "\nhorizontally, and diagonally");
		subtitles.add(s1);
		Label s2 = new Label("However, the queen cannot past through over pieces like \nthe knight");
		subtitles.add(s2);
		Label s3 = new Label("Additional notes:");
		subtitles.add(s3);
		Label s4 = new Label(">The queen cannot move if its path is blocked by a piece with \nthe "+
			                 "same color as the queen or after capturing an opposing piece");
		subtitles.add(s4);
		Label s5 = new Label(">Even though each side starts with one queen, another queen \ncan be "+
				             "brought into the game if a pawn successfully promotes");
		subtitles.add(s5);
		subtitles.forEach(n -> {
			n.getStyleClass().add("subtitle");
			rules.getChildren().add(n);
		});
		
		this.setLeft(rules);
		
		//Board to display queen movement
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
					sq.setPiece(new Piece(ChessPieceType.QUEEN, GameColor.WHITE));
					sq.setSelectedColor();
				}
				else if(i == 2 || j == 2 || i == j || (i == 5 - (j+1))) {
					sq.setValidColor();
				}
				sq.disable();
				board.add(sq, j, i);
			}
		}
		this.setCenter(board);
	}

}
