package src.ui_gui;

import java.util.Arrays;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import src.interfaces.ScreenChangeHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SettingsGUI extends VBox{

	/**The button options of the menu**/
	Button b1, b2, b3, b4, b5, b6, b7, b8;
	
	private ScreenChangeHandler screenChanger;
	/**Construct the menu**/
	public SettingsGUI(){
		
		this.setAlignment(Pos.CENTER);//SET the alignment of the  layout.
		this.setSpacing(20);//Spacing between components.
		this.getStyleClass().add("mainMenu");
		
		//Menu Title
		Label lbl = new Label("Settings");
		lbl.getStyleClass().add("screenTitle");
		this.getChildren().add(lbl);

		//colors label
		Label lb2 = new Label("Colors:");
		lb2.getStyleClass().add("screenTitle");
		//this.getChildren().add(lb2);
		
		VBox v1 = new VBox();
		this.getChildren().add(v1);
		v1.getChildren().add(lb2);
		
		
		CheckBox cb1 = new CheckBox("Show Moves");
		VBox v2 = new VBox();
		this.getChildren().add(v2);
		v2.getChildren().add(cb1);

		this.getStyleClass().add("mainMenu");
		b1 = new Button("Back to Main Menu");
		b1.getStyleClass().add("buttonStyleA");
		b1.getStyleClass().add("buttonSizeA");
		b1.setOnAction(btnHandle);
		b1.getStyleClass().add("buttonStyleA");
		b1.getStyleClass().add("buttonSizeA");
		AnchorPane.setLeftAnchor(b1, 20.0);
		AnchorPane.setBottomAnchor(b1, 20.0);
		getChildren().add(b1);
		
//		//menu option1
		// b1 = new Button("Play Chess");  
		// b1.getStyleClass().add("buttonStyleA");
		// b1.getStyleClass().add("buttonSizeS");
//	    
	    // Menu option2
		// b2 = new Button("View Rules");  
		// b2.getStyleClass().add("buttonStyleA");
		// b2.getStyleClass().add("buttonSizeS");
//	    
	    // Menu option3
	    // b3 = new Button("Sign In");  
		// b3.getStyleClass().add("buttonStyleA");
		// b3.getStyleClass().add("buttonSizeS");
	    // 
	    // b4 = new Button("Sign Up");  
	    // b4.getStyleClass().add("buttonStyleA");
	    // b4.getStyleClass().add("buttonSizeS");
	    
	    // b5 = new Button("Define Players");  
	    // b5.getStyleClass().add("buttonStyleA");
	    // b5.getStyleClass().add("buttonSizeS");
//	    
//	    
	    // b6 = new Button("Settings");  
	    // b6.getStyleClass().add("buttonStyleA");
	    // b6.getStyleClass().add("buttonSizeS");
	    // 
	    // b7 = new Button("Load Game");  
	    // b7.getStyleClass().add("buttonStyleA");
	    // b7.getStyleClass().add("buttonSizeS");
	    // 
	    // b8 = new Button("Quit");  
	    // b8.getStyleClass().add("buttonStyleA");
	    // b8.getStyleClass().add("buttonSizeS");
	    // this.getChildren().addAll(Arrays.asList(b1, b2, b3, b4, b5, b6, b7, b8));
	}
	
	public void setScreenChangeHandler(ScreenChangeHandler screen){
		this.screenChanger = screen;
	}
	
	EventHandler<ActionEvent> btnHandle = new EventHandler<ActionEvent>(){
		public void handle(ActionEvent event){
			if(screenChanger != null){
				Object o = event.getSource();

				if(o == b1){
				   screenChanger.changeScreen(ScreenFactory.Screen.MAIN_SCREEN);
				}
			}
		}
	};	
}

