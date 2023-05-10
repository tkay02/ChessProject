package src.ui_gui;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import src.interfaces.ScreenChangeHandler;

/**
 * Screen Factory for handling creation of all screens within the
 * program. Also handling sreen changes within the application.
 * 
 * @author Nolan Flinchum (%50), Thomas Kay, Joseph Oladeji (50%), Levi Sweat
 * @version 5/8/2023
 */
public final class ScreenFactory implements ScreenChangeHandler{

	/** Enumerations for all screens within the  application*/
	public enum Screen {MAIN_SCREEN, SETTINGS_SCREEN, RULES_SCREEN, MATCH_SCREEN,
	SIGN_IN, SIGN_UP, DEFINE_PLAYERS};
	
	/** Main menu object reference */
	private static MainMenuGUI mainScreen;
	
	/** Settings menu object reference */
	private static SettingsGUI settingsScreen;
	
	/** Rules menu object reference */
	private static RulesGUI rulesScreen;
	
	/** Match User Interface object reference */
	private static MatchGUI matchScreen;

	/** Sign in menu object reference */
	private static SignInGUI signInScreen;

	/** Sign up menu object reference */
	private static SignUpGUI signUpScreen;

	/** Define players menu object reference */
	private static DefinePlayersGUI definePlayersGUI;

	/** Previous screen enumeartion */
	public static Screen prevScreen;
	
	/** Scene reference used to set the current scene within the application */
	private Scene scene;
	
	/** Reference to the ScreenFactory itself for the singleton pattern */
	private static ScreenFactory oneInstance;

	/**
	 * This constructor will initialize the scene to the first scene 
	 * passed from the parameter, which is the main menu.
	 * 
	 * @param scene - Initial scene to be set for the application
	 */
	private ScreenFactory(Scene scene){
		this.scene = scene;
		setScreen(Screen.MAIN_SCREEN);
	}

	/** This method will get the single instance of ScreenFactory, if it's
	 * not defined yet, then a screen factory instance will be created with the
	 * scene parameter and the ScreenFactory instance wil be returned.
	 * 
	 * @param scene - Scene to be set for the application
	 */
	public static ScreenFactory getInstance(Scene scene){
		if(oneInstance == null)
			oneInstance = new ScreenFactory(scene);
		return oneInstance;
	}
	
	/**
	 * This method will initialize any GUI classes if they haven't
	 * been initialized. It will then set the screen to that instance
	 * 
	 * @param screenChoice - Screen to be intialized to.
	 * @return screen - Screen that will be set to the root.
	 */
	public Pane setScreen(Screen screenChoice){
		Pane screen;
		
		switch(screenChoice){
			case SETTINGS_SCREEN:
				if(settingsScreen == null || prevScreen == null){
					settingsScreen = new SettingsGUI();
					settingsScreen.setScreenChangeHandler(this);
				} 
				screen = settingsScreen;
				break;
			case MATCH_SCREEN:
				Screen previous = ScreenFactory.prevScreen;
				if(previous == Screen.MAIN_SCREEN || previous == null){
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
				screen = rulesScreen;
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

	/**
	 * This method takes a screen enumeration and sets it to the correlating screen.
	 * 
	 * @param screenChoice - Chosen screen that will be set to the root
	 */
	public void changeScreen(ScreenFactory.Screen screenChoice) {
		Pane root = setScreen(screenChoice);
		scene.setRoot(root);
	}

}
