package src.ui_gui;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import src.interfaces.ScreenChangeHandler;

public final class ScreenFactory implements ScreenChangeHandler{

	public enum Screen {MAIN_SCREEN, SETTINGS_SCREEN, RULES_SCREEN, MATCH_SCREEN};
	
	private static MainMenuGUI mainScreen;
	
	private static SettingsGUI settingsScreen;
	
	// private static RulesGUI rulesScreen;
	
	private static MatchGUI matchScreen;
	
	private Scene scene;
	
	private static ScreenFactory oneInstance;

	private ScreenFactory(Scene scene){
		this.scene = scene;
		setScreen(Screen.MAIN_SCREEN);
	}

	public static ScreenFactory getInstance(Scene scene){
		if(oneInstance == null)
			oneInstance = new ScreenFactory(scene);
		return oneInstance;
	}
	
	public Pane setScreen(Screen screenChoice){
		Pane screen;
		
		switch(screenChoice){
			case SETTINGS_SCREEN:
				if(settingsScreen == null) settingsScreen = new SettingsGUI();
				screen = settingsScreen;
				break;
			case MATCH_SCREEN:
				if(matchScreen == null) matchScreen = new MatchGUI();
				screen = matchScreen;
				break;
			default:
				if(mainScreen == null){
					mainScreen = new MainMenuGUI();
					mainScreen.setScreenChangeHandler(this);
				}
				screen = mainScreen;
		}

		scene.setRoot(screen);
		return screen;
	}

	@Override
	public void changeScreen(ScreenFactory.Screen screenChoice) {
		Pane root = setScreen(screenChoice);
		scene.setRoot(root);
	}

}
