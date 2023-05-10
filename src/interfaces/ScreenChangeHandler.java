package src.interfaces;

import src.ui_gui.ScreenFactory;

/**
 * Interface for the screen change handler.
 * 
 * @author Nolan Flinchum (%50), Thomas Kay, Joseph Oladeji (50%), Levi Sweat
 * @version 4/19/2023
 */
public interface ScreenChangeHandler {

	public void changeScreen(ScreenFactory.Screen screenChoice);
}
