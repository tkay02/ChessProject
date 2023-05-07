package src.ui_gui.screens;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import src.enums.ChessPieceType;
import src.enums.GameColor;
import src.model.Piece;
import src.ui_gui.ChessSquare;

public class KingRules extends GridPane {
	
	public KingRules() {
		super();
		this.setAlignment(Pos.TOP_CENTER);
		
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
				this.add(sq, j, i);
			}
		}
		
	}
	
}
