package src.ui_gui;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import src.interfaces.ScreenChangeHandler;

public final class ScreenFactory implements ScreenChangeHandler{

	public enum Screen {SCREEN0, SCREEN1, SCREEN2, SCREEN3};
	
	private static MainMenuGUI screen0;
	
	private static SettingsGUI screen1;
	
	private static RulesGUI screen2;
	
	private static MatchGUI screen3;
	
	private Scene scene;

	public void changeScreen(ScreenFactory.Screen screenChoice) {
		Pane root = setScreen(screenChoice);
		scene.setRoot(root);
	}
	
	   public Pane setScreen(Screen screenChoice){
		   Pane screen;
		   
//		   switch(screenChoice){
//			   case SCREEN1:
//				   if(screen1 == null)
//					   screen1 = new MainMenuGUI();
//				   screen = screen1;
//				   break;
//			   case SCREEN2:
//				   if(screen2 == null)
//					   screen2 = new SettingsGUI();
//				   screen = screen2;
//				   break;
//			   case SCREEN3:
//				   if(screen3 == null)
//					   screen3 = new Screen3_Music();
//				   screen = screen3;
//				   break;
//				 default:
//					  if(screen0== null){
//						   screen0 = new Screen0_Menu();
//						   screen0.setScreenChangeHandler(this);
//					  }
//				
//					   screen = screen0;
		   }///end switch
	   }
}
