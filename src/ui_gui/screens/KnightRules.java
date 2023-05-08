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

public class KnightRules extends BorderPane {

	public KnightRules() {
		super();
		
		Label title = new Label("Knight Movement");
		title.setId("Title");
		this.setTop(title);
		
		VBox rules = new VBox();
		ArrayList<Label> subtitles = new ArrayList<>();
		
		Label s0 = new Label("The knight is 'odd' compared to the other pieces");
		subtitles.add(s0);
		Label s1 = new Label("Instead of moving in a straight line, the knight moves in \nan " +
				             "'L-shape' pattern");
		subtitles.add(s1);
		Label s2 = new Label("Which has two cases:");
		subtitles.add(s2);
		Label s3 = new Label("Case 1:");
		subtitles.add(s3);
		Label s4 = new Label(">The knight can move two squares vertically and one square " +
				             "\nhorizontally");
		subtitles.add(s4);
		Label s5 = new Label("Case 2:");
		subtitles.add(s5);
		Label s6 = new Label(">The knight can move two squares horizontally and one square "+
							 "\nvertically");
		subtitles.add(s6);
		Label s7 = new Label("Additional notes:");
		subtitles.add(s7);
		Label s8 = new Label(">The knight is the only piece that can 'jump over' pieces \nregardless "+
				             "of color");
		subtitles.add(s8);
		Label s9 = new Label(">A knight beginning on a black square will always end up on a \nwhite "+
                             "square and vice versa");
		subtitles.add(s9);
		Label s10 = new Label(">The only invalid spaces that a knight cannot move to is one "+
                              "\noccupied by a piece with its matching color");
		subtitles.add(s10);
		
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
					sq.setPiece(new Piece(ChessPieceType.KNIGHT, GameColor.WHITE));
					sq.setSelectedColor();
				}
				else if(((j == 1 || j == 3) && (i == 0 || i == 4)) ||
						((j == 0 || j == 4) && (i == 1 || i == 3))) {
					sq.setValidColor();
				}
				sq.disable();
				board.add(sq, j, i);
			}
		}
		this.setCenter(board);
	}
}
