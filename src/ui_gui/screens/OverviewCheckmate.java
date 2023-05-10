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
 * Screen that gives a general overview about what checkmate is and how it works.
 * 
 * @author Nolan Flinchum, Thomas Kay (100%), Joseph Oladeji, Levi Sweat
 * @date 5/9/2023
 */
public class OverviewCheckmate extends VBox {
	
	/**
	 * Displays information about checkmate such as it checkmate means and an example.
	 */
	public OverviewCheckmate() {
		
		//Title
		this.setAlignment(Pos.TOP_LEFT);
		Label title = new Label("Checkmate Condition");
		title.setId("Title");
		this.getChildren().add(title);
		
		//Subtitles
		ArrayList<Label> subtitles = new ArrayList<>();
		
		Label s0 = new Label("When the king's in check and there's no possible moves to get out "+
                   "of out of check, then that's checkmate");
		subtitles.add(s0);
		Label s1 = new Label("An example of a checkmate:");
		subtitles.add(s1);
		subtitles.forEach(n -> {
			n.getStyleClass().add("subtitle");
			this.getChildren().add(n);
		});
		subtitles.clear();
		
		//Board to represent example
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
					sq.setCheckColor();
				}
				else if(i == 2 && j == 0) {
					sq.setPiece(new Piece(ChessPieceType.ROOK, GameColor.BLACK));
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
		Label s2 = new Label("In this example, checkmate is achieved by the rook makes the enemy " +
				             "king into check vertically and the queen makes \nthe king " +
				             "into check horizontally and diagonally with nothing that the enemy "+
	                           "can do");
		s2.getStyleClass().add("subtitle");
		this.getChildren().add(s2);
	}

}
