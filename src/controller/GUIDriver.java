package src.controller;
	
import javafx.application.Application;
import javafx.stage.Stage;
import src.ui_gui.ScreenFactory;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GUIDriver extends Application {

	/** Buttons for screens **/
	Button but_screen1, but_screen2;

	/** The current stage for the application **/
	Stage stage;
	
	/** The root scene for this application **/
	Scene rootScene;

	/**
	 * Start our application
	 * @param primaryStage The main application window
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			this.stage = primaryStage;
			primaryStage.setFullScreen(true);
			
			Scene scene = new Scene(new Pane(),800,600);
			this.rootScene = scene;
			ScreenFactory.getInstance(scene);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}
