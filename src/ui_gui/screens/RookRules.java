package src.ui_gui.screens;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import src.enums.ChessPieceType;
import src.enums.GameColor;
import src.model.Piece;
import src.ui_gui.ChessSquare;

public class RookRules extends GridPane {

	public RookRules() {
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
					sq.setPiece(new Piece(ChessPieceType.ROOK, GameColor.WHITE));
					sq.setSelectedColor();
				}
				else if(i == 2 || j == 2) {
					sq.setValidColor();
				}
				sq.disable();
				this.add(sq, j, i);
			}
		}
	}
}
