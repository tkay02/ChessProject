package src.controller;
	
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import src.ui_gui.MainMenuGUI;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class TempMain extends Application {
	/** buttons for screens**/
	Button but_screen1, but_screen2;

	/**The current stage for the application**/
	Stage stage;
	
	/**the root scene for this app**/
	Scene rootScene;
	

	/**Start our application
	 * @param primaryStage The main application window**/
	@Override
	public void start(Stage primaryStage) {
		try {
			this.stage = primaryStage;
			
			primaryStage.setFullScreen(true);
			
			Scene scene = new Scene(new MainMenuGUI(),800,600);
			this.rootScene = scene;
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}//end start
	

    /**Change the root scene for this app**/
    private void setScreen(Pane root){
    	rootScene.setRoot(root);
    }
    

	/**Where it all begins**/
	public static void main(String[] args) {
		launch(args);
	}


	
}//end class
