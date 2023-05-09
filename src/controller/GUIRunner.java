package src.controller;
	
import javafx.application.Application;
import javafx.stage.Stage;
import src.ui_gui.ScreenFactory;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Initial entry point for GUI application of the program. Called from the driver.
 * 
 * Authors: Levi Sweat, Joseph Oladeji, Nolan Flinchum, Thomas Kay
 */
public class GUIRunner extends Application {

	/** Buttons for screens **/
	Button but_screen1, but_screen2;

	/** The current stage for the application **/
	Stage stage;
	
	/** The root scene for this application **/
	Scene rootScene;

	/**
	 * Start our GUI application.
	 * 
	 * @param primaryStage The main application window
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			this.stage = primaryStage;
			primaryStage.setMaximized(true);
			primaryStage.setTitle("ChessMeister");
			
			Scene scene = new Scene(new Pane(),800,600);
			this.rootScene = scene;
			ScreenFactory.getInstance(scene);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setAlwaysOnTop(true); //This line makes sure the window is displayed
			//when run. This was an issue due to the CLI & GUI application of our program.
			//The following line is used to make sure dialog boxes appear in front of the window.
			primaryStage.setAlwaysOnTop(false);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Called from the driver, used to call the start function and begin the GUI application.
	 * 
	 * @param args user input args from the main method in the driver
	 */
	public static void run(String[] args) {
		launch(args);
	}
	
}	
