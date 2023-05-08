package src.ui_gui.screens;

import javafx.scene.layout.Pane;

public class OverViewFactory {

	/**
	 * Produces screen to be used for the Overview section of the RulesGUI, which the user selects
	 * which screen to be produced.
	 * 
	 * @param int screen Number that represents the specific screen chosen by the user, with 1
	 * representing check conditions, 2 representing checkmate conditions, 3 representing draw
	 * conditions, and 4 representing chess notation. 
	 * @return The screen that the user selected.
	 */
	public Pane makeOverviewScreen(int screen) {
		Pane pane = new Pane();
		if(screen == 1) {
			pane = new OverviewCheck();
		}
		else if(screen == 2) {
			pane = new OverviewCheckmate();
		}
		else if(screen == 3) {
			pane = new OverviewDraw();
		}
		else if(screen == 4) {
			pane = new OverviewChessNotation();
		}
		return pane;
	}
	
}
