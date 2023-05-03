package src.ui_gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import src.interfaces.ScreenChangeHandler;

import java.util.Arrays;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MainMenuGUI extends BorderPane {

	/**The current stage for the application**/
	Stage stage;
	
	/**the root scene for this app**/
	Scene rootScene;
	
	/**The button options of the menu**/
	Button b1, b2, b3, b4, b5, b6, b7, b8;
	
	Pane top, bottom, left, right, center;

	HBox hBox;
	
	ScreenChangeHandler screenChanger;
	
	/**Construct the menu**/
	public MainMenuGUI(){
		Label label =  new Label("ChessMeister");
		HBox hBox = new HBox();
		Pane bottom = new AnchorPane();
		Pane left = new Pane();
		Pane right = new Pane();
		VBox center = new VBox();
		center.getChildren().add(label);
		hBox.setAlignment(Pos.CENTER);
		hBox.setPadding(new Insets(50, 0, 0, 0));
		hBox.setSpacing(20);
		center.setAlignment(Pos.CENTER);
		this.setTop(hBox);
		this.setBottom(bottom);
		this.setRight(right);
		this.setLeft(left);
		this.setCenter(center);
		b1 = new Button("Play Chess");  
		b1.getStyleClass().add("buttonStyleA");
		b1.getStyleClass().add("buttonSizeS");
		
		//Menu option2
		b2 = new Button("View Rules");  
		b2.getStyleClass().add("buttonStyleA");
		b2.getStyleClass().add("buttonSizeS");
		
		//Menu option3
		b3 = new Button("Sign In");  
		b3.getStyleClass().add("buttonStyleA");
		b3.getStyleClass().add("buttonSizeS");
		
		b4 = new Button("Sign Up");  
		b4.getStyleClass().add("buttonStyleA");
		b4.getStyleClass().add("buttonSizeS");
		
		b5 = new Button("Define Players");  
		b5.getStyleClass().add("buttonStyleA");
		b5.getStyleClass().add("buttonSizeS");
		
		b6 = new Button("Settings");  
		b6.getStyleClass().add("buttonStyleA");
		b6.getStyleClass().add("buttonSizeS");
		
		b7 = new Button("Load Game");  
		b7.getStyleClass().add("buttonStyleA");
		b7.getStyleClass().add("buttonSizeS");
		
		b8 = new Button("Quit");  
		b8.getStyleClass().add("buttonStyleA");
		b8.getStyleClass().add("buttonSizeS");
		center.getChildren().addAll(Arrays.asList(b1, b2, b3, b4, b5, b6, b7, b8));
		
		// scene = new Scene(bPane,800,600);
		// this.rootScene = scene;
		// ScreenFactory.getInstance(scene);

	



		// BorderPane bPane = new BorderPane();
		// this.rootScene = new Scene(bPane, 800, 600);

		// hBox = new HBox();
		// bottom = new AnchorPane();
		// left = new Pane();
		// right = new Pane();
	    // center = new VBox();

		// this.setTop(hBox);
	    // this.setBottom(bottom);
	    // this.setRight(right);
	    // this.setLeft(left);
	    // this.setCenter(center);

		// top.setAlignment(Pos.CENTER); //SET the alignment of the  layout.
		// top.setSpacing(20); //Spacing between components.
		
		//this.setId("Screen0");//The CSS id for this screen
		
		//Menu Title
		// Label lbl = new Label("ChessMeister");
		// lbl.getStyleClass().add("screenTitle");
		// center.getChildren().add(lbl);
		// this.getChildren().add(center);
		// //menu option1
		// b1 = new Button("Play Chess");  
		// b1.getStyleClass().add("buttonStyleA");
		// b1.getStyleClass().add("buttonSizeS");
	    
	    // //Menu option2
		// b2 = new Button("View Rules");  
		// b2.getStyleClass().add("buttonStyleA");
		// b2.getStyleClass().add("buttonSizeS");
	    
	    // //Menu option3
	    // b3 = new Button("Sign In");  
		// b3.getStyleClass().add("buttonStyleA");
		// b3.getStyleClass().add("buttonSizeS");
	    
	    // b4 = new Button("Sign Up");  
	    // b4.getStyleClass().add("buttonStyleA");
	    // b4.getStyleClass().add("buttonSizeS");
	    
	    // b5 = new Button("Define Players");  
	    // b5.getStyleClass().add("buttonStyleA");
	    // b5.getStyleClass().add("buttonSizeS");
	    
	    // b6 = new Button("Settings");  
	    // b6.getStyleClass().add("buttonStyleA");
	    // b6.getStyleClass().add("buttonSizeS");
	    
	    // b7 = new Button("Load Game");  
	    // b7.getStyleClass().add("buttonStyleA");
	    // b7.getStyleClass().add("buttonSizeS");
	    
	    // b8 = new Button("Quit");  
	    // b8.getStyleClass().add("buttonStyleA");
	    // b8.getStyleClass().add("buttonSizeS");
	    // center.getChildren().addAll(Arrays.asList(b1, b2, b3, b4, b5, b6, b7, b8));

	    
	}//end Screen0

	public void setScreenChangeHandler(ScreenChangeHandler screen){
		this.screenChanger = screen;
	}

	EventHandler<ActionEvent> btnHandle = new EventHandler<ActionEvent>(){
		public void handle(ActionEvent event){
			
		}
	};
	

}
