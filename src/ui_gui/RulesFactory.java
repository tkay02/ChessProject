package src.ui_gui;

import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

public class RulesFactory {

	public Pane makeScreen(RuleScreens scr) {
		
		Pane pane = new TilePane();
		switch(scr) {
			case BOARD:
				pane = new BoardSetup().getRoot();
				break;
			case KING:
				pane = new KingRules().getRoot();
				break;
			case QUEEN:
				pane = new QueenRules().getRoot();
				break;
			case BISHOP:
				pane = new BishopRules().getRoot();
				break;
			case KNIGHT:
				pane = new KnightRules().getRoot();
				break;
			case ROOK:
				pane = new RookRules().getRoot();
				break;
			case PAWN:
				pane = new PawnRules().getRoot();
				break;
			case OVERVIEW:
				pane = new Overview().getRoot();
				break;
		}
		return pane;
	}

}
