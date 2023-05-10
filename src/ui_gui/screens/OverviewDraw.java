package src.ui_gui.screens;

import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import src.enums.ChessPieceType;
import src.enums.GameColor;
import src.model.Piece;
import src.ui_gui.ChessSquare;

/**
 * Class that provides a general description of draws, including the different
 * kinds of draws.
 * 
 * @author Nolan Flinchum, Thomas Kay (100%), Joseph Oladeji, Levi Sweat
 * @date 5/9/2023
 */
public class OverviewDraw extends VBox {

	/**
	 * Displays information about draw condition such as draw by stalemate (with an exmaple), draw
	 * by fifty-move rule, and draw by threefold repetition.
	 */
	public OverviewDraw() {
		
		//Title
		this.setAlignment(Pos.TOP_LEFT);
		Label title = new Label("Draw Conditions");
		title.setId("Title");
		this.getChildren().add(title);
		
		ArrayList<Label> subtitles = new ArrayList<>();
		
		//Subtitles
		Label s0 = new Label("However, checkmate is not the only condition that could end the "+
                           "game");
		subtitles.add(s0);
		Label s1 = new Label("A draw can occur if checkmate is not possible");
		subtitles.add(s1);
		Label s2 = new Label("One condition that a draw can occur is a stalemate if there are no "+
                             "possible legal moves that could be made");
		subtitles.add(s2);
		Label s3 = new Label("By no legal moves, meaning that the king moves and make itself into "+
                             "check");
		subtitles.add(s3);
		Label s4 = new Label("An example of a stalemate:");
		subtitles.add(s4);
		
		subtitles.forEach(n -> {
			n.getStyleClass().add("subtitle");
			this.getChildren().add(n);
		});
		subtitles.clear();
		
		//Stalemate example
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
				if(i == 0 && j == 0) {
					sq.setPiece(new Piece(ChessPieceType.KING, GameColor.WHITE));
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
		Label s5 = new Label("In this example, with the king being the only piece left, cannot "+
                             "move as all possible moves will bring itself to be \n"+
							 "checked by the enemy queen");
		subtitles.add(s5);
		Label s6 = new Label("Other conditions that cause a draw are the fifty-move rule, where "+
							 "a draw is called if 50 moves were made without \na capture or a pawn "+
							 "moving, and threefold repetition, where a player moves " +
							 "one of their pieces to the same \nposition 3 times");
		subtitles.add(s6);
		subtitles.forEach(n -> {
			n.getStyleClass().add("subtitle");
			this.getChildren().add(n);
		});
		
	}
}
