package src.ui_gui.screens;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import src.controller.Chess;
import src.model.Board;
import src.model.Piece;
import src.model.Square;
import src.ui_gui.ChessPane;
import src.ui_gui.ChessSquare;

public class BoardSetup extends GridPane{
	
	public BoardSetup() {
		super();
		this.setAlignment(Pos.TOP_CENTER);
		//Label title = new Label("Board Setup");
		//title.setId("Title");
		//this.add(title,0,0,1,1);
		
		Board toDisplay = new Board(new Chess());
		String[] ranks = {"8", "7", "6", "5", "4", "3", "2", "1"};
		String[] files = {"A", "B", "C", "D", "E", "F", "G", "H"};
		Square[][] tiles = (Square[][])toDisplay.getSquares();
		for(int i = 0; i < tiles.length; i++) {
			this.add(new Label(ranks[i]), 0, i + 1);
			for(int j = 0; j < tiles[i].length; j++) {
				ChessSquare sq = new ChessSquare(tiles[i][j].isWhite(),(Piece)tiles[i][j].getPiece());
				sq.disable();
				this.add(sq, j + 1, i + 1);
			}
			for(int k = 0; k < files.length; k++) {
				this.add(new Label(files[k]), i+1, 11);
			}
		}
		//Label subtitle = new Label("The game is played on an 8x8 black and white board with each player " +
		//		   "receiving 16 pieces each");
		//this.add(subtitle,0,10,1,1);

		//Label subtitle2 = new Label(">8 pawns, 2 rooks, 2 knights, 2 bishops, 1 queen, and 1 king");
		//this.add(subtitle2,0,11);
	}

}
