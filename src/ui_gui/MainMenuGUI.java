package src.ui_gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import src.controller.GUIDriver;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 * 
 */
public class MainMenuGUI extends BorderPane {

	/** The current stage for the application **/
	Stage stage;
	
	/** The root scene for this app **/
	Scene rootScene;
	
	/** The button options of the menu **/
	Button b1, b2, b3, b4, b5, b6, b7, b8;
	
	/** Panes for the parts of a border pane **/
	// Pane top, bottom, left, right, center;
	
	/** Handles the screen changing **/
	ScreenChangeHandler screenChanger;
	
	/** Construct the menu **/
	public MainMenuGUI(){
		
		Pane top = new HBox();
		AnchorPane bottom = new AnchorPane();
		Pane left = new Pane();
		VBox right = new VBox();
		VBox center = new VBox();
		this.setTop(top);
		this.setBottom(bottom);
		this.setRight(right);
		this.setLeft(left);
		this.setCenter(center);
		this.getStyleClass().add("mainMenu");
		/////////// CENTER PART /////////////
		center.setAlignment(Pos.CENTER);
		center.setSpacing(15);

		Label label =  new Label("ChessMeister");
		label.getStyleClass().add("labelA");
		center.getChildren().add(label);

		b1 = new Button("Play Chess");  
		b1.getStyleClass().add("buttonStyleA");
		b1.getStyleClass().add("buttonSizeA");
		
		b2 = new Button("View Rules");  
		b2.getStyleClass().add("buttonStyleA");
		b2.getStyleClass().add("buttonSizeA");
		
		b3 = new Button("Sign In");  
		b3.getStyleClass().add("buttonStyleA");
		b3.getStyleClass().add("buttonSizeA");
		
		b4 = new Button("Sign Up");  
		b4.getStyleClass().add("buttonStyleA");
		b4.getStyleClass().add("buttonSizeA");
		
		b5 = new Button("Define Players");  
		b5.getStyleClass().add("buttonStyleA");
		b5.getStyleClass().add("buttonSizeA");
		
		b6 = new Button("Load Game");  
		b6.getStyleClass().add("buttonStyleA");
		b6.getStyleClass().add("buttonSizeA");

		b1.setOnAction(btnHandle);
		b2.setOnAction(btnHandle);
		
		center.getChildren().addAll(Arrays.asList(b1, b2, b3, b4, b5, b6));

		/////// BOTTOM ////////		
		b7 = new Button("Settings");  
		b7.getStyleClass().add("buttonStyleA");
		b7.getStyleClass().add("buttonSizeA");
		AnchorPane.setLeftAnchor(b7, 20.0);
		AnchorPane.setBottomAnchor(b7, 20.0);

		b8 = new Button("Exit");  
		b8.getStyleClass().add("buttonStyleA");
		b8.getStyleClass().add("buttonSizeA");
		AnchorPane.setRightAnchor(b8, 20.0);
		AnchorPane.setBottomAnchor(b8, 20.0);

		b7.setOnAction(btnHandle);
		b8.setOnAction(btnHandle);
		ImageView imv = new ImageView();
		Image image = new Image(GUIDriver.class.getResourceAsStream("PieceGIF3.gif"));
		imv.setImage(image);
		right.getChildren().add(imv);
		right.setAlignment(Pos.CENTER_RIGHT);
		bottom.getChildren().addAll(Arrays.asList(b7, b8));
		
	}

	public void setScreenChangeHandler(ScreenChangeHandler screen){
		this.screenChanger = screen;
	}

	EventHandler<ActionEvent> btnHandle = new EventHandler<ActionEvent>(){
		public void handle(ActionEvent event){
			if(screenChanger != null){
				Object o = event.getSource();

				if(o == b1){
				   screenChanger.changeScreen(ScreenFactory.Screen.MATCH_SCREEN);
				}
				else if(o == b2){
				   screenChanger.changeScreen(ScreenFactory.Screen.RULES_SCREEN);
				}
				else if(o == b7){
					screenChanger.changeScreen(ScreenFactory.Screen.SETTINGS_SCREEN);
				}
				else if(o == b8){
					System.exit(1);
				}
			}
		}
	};	
}
