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
 * Displays information about the king, including a picture that displays all of the
 * possible moves that a king piece can do.
 * 
 * @author Nolan Flinchum, Thomas Kay (100%), Joseph Oladeji, Levi Sweat
 * @date 5/9/2023
 */
public class KingRules extends BorderPane {
	
	/**
	 * Constructor for king rules.
	 */
	public KingRules() {
		super();
		
		//Title
		Label title = new Label("King Movement");
		title.setId("Title");
		this.setTop(title);
		
		//Pane that stores subtitles
		VBox rules = new VBox();
		ArrayList<Label> subtitles = new ArrayList<>();
		
		//Subtitles
		Label s0 = new Label("The king is the most important piece in the game");
		subtitles.add(s0);
		Label s1 = new Label("The king can move in any direction vertically, horizontally, \nand "+
							 "diagonally");
		subtitles.add(s1);
		Label s2 = new Label("However, the king could move only 1 space compared to the queen");
		subtitles.add(s2);
		Label s3 = new Label("Additional notes:");
		subtitles.add(s3);
		Label s4 = new Label(">The king cannot move that will makes itself into check");
		subtitles.add(s4);
		Label s5 = new Label(">The king cannot move if its path is blocked by a \npiece with the "+
				             "same color as the king or after capturing an \nopposing piece");
		subtitles.add(s5);
		Label s6 = new Label(">The king can capture opposing pieces");
		subtitles.add(s6);
		
		subtitles.forEach(n -> {
			n.getStyleClass().add("subtitle");
			rules.getChildren().add(n);
		});
		
		this.setLeft(rules);
		
		//Board to display king movement
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
					sq.setPiece(new Piece(ChessPieceType.KING, GameColor.WHITE));
					sq.setSelectedColor();
				}
				else if(i >= 1 && i <= 3 && j >= 1 && j <= 3) {
					sq.setValidColor();
				}
				sq.disable();
				board.add(sq, j, i);
			}
		}
		this.setCenter(board);
	}
	
}
