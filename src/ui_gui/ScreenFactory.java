package src.ui_gui;

import javafx.css.Match;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import src.interfaces.ScreenChangeHandler;

public final class ScreenFactory implements ScreenChangeHandler{

	public enum Screen {SCREEN0, SCREEN1, SCREEN2, SCREEN3};
	
	private static MainMenuGUI screen0;
	
	private static SettingsGUI screen1;
	
	// private static RulesGUI screen2;
	
	private static MatchGUI screen3;
	
	
	private Scene scene;
	
	private static ScreenFactory oneInstance;

	public ScreenFactory(Scene scene){
		this.scene = scene;
		setScreen(Screen.SCREEN0);
	}

	public static ScreenFactory getInstance(Scene scene){
		if(oneInstance == null)
			oneInstance = new ScreenFactory(scene);
		return oneInstance;
	}
	
	public Pane setScreen(Screen screenChoice){
		Pane screen;
		
		switch(screenChoice){
			case SCREEN1:
				if(screen0 == null) screen0 = new MainMenuGUI();
				screen = screen0;
				break;
			case SCREEN2:
				if(screen1 == null) screen1 = new SettingsGUI();
				screen = screen1;
				break;
			case SCREEN3:
				if(screen3 == null) screen3 = new MatchGUI();
				screen = screen3;
				break;
				default:
					if(screen0== null) screen0 = new MainMenuGUI();
						screen0.setScreenChangeHandler(this);
					screen = screen0;
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
