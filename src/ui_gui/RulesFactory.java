package src.ui_gui;

import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import src.ui_gui.screens.BoardSetup;
import src.ui_gui.screens.KingRules;
import src.ui_gui.screens.QueenRules;
import src.ui_gui.screens.RookRules;
import src.ui_gui.screens.BishopRules;
import src.ui_gui.screens.KnightRules;
import src.ui_gui.screens.PawnRules;
import src.ui_gui.screens.Overview;

public class RulesFactory {

	public Pane makeScreen(RuleScreens scr) {
		
		Pane pane = new TilePane();
		switch(scr) {
			case BOARD:
				pane = new BoardSetup();
				break;
			case KING:
				pane = new KingRules();
				break;
			case QUEEN:
				pane = new QueenRules();
				break;
			case BISHOP:
				pane = new BishopRules();
				break;
			case KNIGHT:
				pane = new KnightRules();
				break;
			case ROOK:
				pane = new RookRules();
				break;
			case PAWN:
				pane = new PawnRules();
				break;
			case OVERVIEW:
				pane = new Overview();
				break;
		}
		return pane;
	}

}
