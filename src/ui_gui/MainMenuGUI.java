package src.ui_gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.Arrays;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MainMenuGUI extends VBox {

	/**The current stage for the application**/
	Stage stage;
	
	/**the root scene for this app**/
	Scene rootScene;
	
	/**The button options of the menu**/
	Button b1, b2, b3, b4, b5, b6, b7, b8;
	/**Construct the menu**/
	public MainMenuGUI(){
		
		this.setAlignment(Pos.CENTER);//SET the alignment of the  layout.
		this.setSpacing(20);//Spacing between components.
		this.setId("Screen0");//The CSS id for this screen
		
		//Menu Title
		Label lbl = new Label("ChessMeister");
		lbl.getStyleClass().add("screenTitle");
		this.getChildren().add(lbl);
		
		//menu option1
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
	    this.getChildren().addAll(Arrays.asList(b1, b2, b3, b4, b5, b6, b7, b8));
	    
	}//end Screen0
	
    

 
	
}
