package src.ui_gui;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import src.interfaces.ScreenChangeHandler;


public final class ScreenFactory implements ScreenChangeHandler{

	public enum Screen {MAIN_SCREEN, SETTINGS_SCREEN, RULES_SCREEN, MATCH_SCREEN,
	SIGN_IN,  SIGN_UP, DEFINE_PLAYERS, LOAD_GAME};
	
	private static MainMenuGUI mainScreen;
	
	private static SettingsGUI settingsScreen;
	
	private static RulesGUI rulesScreen;
	
	private static MatchGUI matchScreen;

	private static SignInGUI signInScreen;

	private static SignUpGUI signUpScreen;

	private static DefinePlayersGUI definePlayersGUI;

	private static LoadGameGUI loadGame;
	
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
				if(settingsScreen == null){
					settingsScreen = new SettingsGUI();
					settingsScreen.setScreenChangeHandler(this);
				} 
				screen = settingsScreen;
				break;
			case MATCH_SCREEN:
				if(matchScreen == null){
					matchScreen = new MatchGUI();
					matchScreen.setScreenChangeHandler(this);
				} 
				screen = matchScreen;
				break;
			case RULES_SCREEN:
				if(rulesScreen == null) {
					rulesScreen = new RulesGUI();
					rulesScreen.setScreenChangeHandler(this);
				}
				screen = rulesScreen.getRoot();
				break;
			case SIGN_IN:
				if(signInScreen == null){
					signInScreen = new SignInGUI();
					signInScreen.setScreenChangeHandler(this);
				} 
				screen = signInScreen;
				break;
			case SIGN_UP:
				if(signUpScreen == null){
					signUpScreen = new SignUpGUI();
					signUpScreen.setScreenChangeHandler(this);
				} 
				screen = signUpScreen;
				break;
			case DEFINE_PLAYERS:
				if(definePlayersGUI == null){
					definePlayersGUI = new DefinePlayersGUI();
					definePlayersGUI.setScreenChangeHandler(this);
				} 
				screen = definePlayersGUI;
				break;
			case LOAD_GAME:
				if(loadGame == null){
					loadGame = new LoadGameGUI();
					loadGame.setScreenChangeHandler(this);
				}
				screen = loadGame;
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
