package src.ui_gui.screens;

import javafx.scene.layout.Pane;

public class OverViewFactory {

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
