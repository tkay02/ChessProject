package src.ui_gui;

import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import src.ui_gui.screens.BoardSetup;
import src.ui_gui.screens.KingRules;

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
				break;
			case BISHOP:
				break;
			case KNIGHT:
				break;
			case ROOK:
				break;
			case PAWN:
				break;
			case OVERVIEW:
				break;
		}
		return pane;
	}

}
