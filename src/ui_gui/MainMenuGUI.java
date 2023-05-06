package src.ui_gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import src.controller.GUIDriver;
import src.interfaces.ScreenChangeHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Graphical user interface for the main menu.
 * 
 * @author Nolan Flinchum, Thomas Kay, Joseph Oladeji, Levi Sweat
 * @version 5/8/2023
 */
public class MainMenuGUI extends BorderPane {

	/** The current stage for the application **/
	Stage stage;
	
	/** The root scene for this app **/
	Scene rootScene;
	
	/** The button options of the menu **/
	Button b1, b2, b3, b4, b5, b7, b8;

	/** Handles the screen changing **/
	ScreenChangeHandler screenChanger;
	
	/**
	 * Constructor that creates the main menu by initializes and setting up the
	 * appropriate sections of the BorderPane.
	 */
	public MainMenuGUI(){
		AnchorPane bottom = new AnchorPane();
		VBox left = new VBox();
		VBox right = new VBox();
		VBox center = new VBox();

		makeBottom(bottom);
		makeSide(left, "PieceGIF2.gif");
		makeSide(right, "PieceGIF3.gif");
		makeCenter(center);

		this.setBottom(bottom);
		this.setRight(right);
		this.setLeft(left);
		this.setCenter(center);
		this.getStyleClass().add("mainMenu");
	}

	/**
	 * Helper method that sets up the bottom section of the BorderPane.
	 * 
	 * @param bottom the reference to the bottom section
	 */
	private void makeBottom(AnchorPane bottom){
		b7 = buttonMaker("Settings", "buttonStyleB", "buttonSizeB");
		AnchorPane.setLeftAnchor(b7, 20.0);
		AnchorPane.setBottomAnchor(b7, 20.0);

		b8 = buttonMaker("Exit", "buttonStyleB", "buttonSizeB");
		AnchorPane.setRightAnchor(b8, 20.0);
		AnchorPane.setBottomAnchor(b8, 20.0);

		bottom.getChildren().addAll(b7, b8);
	}

	/**
	 * Helper method that sets up the left and right section of the BorderPane.
	 * 
	 * @param side the reference to the left section or right section
	 * @param gif the filename of the gif for the left or right side
	 */
	private void makeSide(VBox side, String gif){
		side.setMinWidth(500);
		side.setAlignment(Pos.CENTER);
		ImageView imv = new ImageView();
		imv.setImage(new Image(GUIDriver.class.getResourceAsStream(gif)));
		side.getChildren().add(imv);
	}

	/**
	 * Helper method that sets up the center section of the BorderPane.
	 * 
	 * @param center the reference to the center section
	 */
	private void makeCenter(VBox center){
		center.setAlignment(Pos.CENTER);
		center.setSpacing(15);

		Label label = new Label("ChessMeister");
		label.getStyleClass().add("labelA");
		center.getChildren().add(label);
		
		b1 = buttonMaker("Play Chess", "buttonStyleB", "buttonSizeB");
		b2 = buttonMaker("View Rules", "buttonStyleB", "buttonSizeB");
		b3 = buttonMaker("Sign In", "buttonStyleB", "buttonSizeB");
		b4 = buttonMaker("Sign Up", "buttonStyleB", "buttonSizeB");
		b5 = buttonMaker("Define Players", "buttonStyleB", "buttonSizeB");
		center.getChildren().addAll(b1, b2, b3, b4, b5);
	}

	/**
	 * Helper method that creates and returns a button.
	 * 
	 * @param text words that appear on the button
	 * @param style the CSS style of the button
	 * @param size designated size of the button
	 * @return fully created button
	 */
	private Button buttonMaker(String text, String style, String size){
		Button button = new Button(text);
		button.getStyleClass().add(style);
		button.getStyleClass().add(size);
		button.setOnAction(btnHandle);
		return button;
	}

	/**
	 * Setter method for the screen change handler.
	 * 
	 * @param screen screen change handler to use
	 */
	public void setScreenChangeHandler(ScreenChangeHandler screen){
		this.screenChanger = screen;
	}

	// Variable that holds how a button press is handled
	EventHandler<ActionEvent> btnHandle = new EventHandler<ActionEvent>(){

		/**
		 * Defines what action to do based on what button is pressed.
		 * 
		 * @param event the action event that occured
		 */
		public void handle(ActionEvent event){
			if(screenChanger != null){
				Object o = event.getSource();
				if(o == b1)
				   screenChanger.changeScreen(ScreenFactory.Screen.MATCH_SCREEN);
				else if(o == b2)
				   screenChanger.changeScreen(ScreenFactory.Screen.RULES_SCREEN);
				else if(o == b3)
					screenChanger.changeScreen(ScreenFactory.Screen.SIGN_IN);
				else if(o == b4)
					screenChanger.changeScreen(ScreenFactory.Screen.SIGN_UP);
				else if(o == b5)
					screenChanger.changeScreen(ScreenFactory.Screen.DEFINE_PLAYERS);
				else if(o == b7)
					screenChanger.changeScreen(ScreenFactory.Screen.SETTINGS_SCREEN);
				else if(o == b8)
					System.exit(1);
			}
		}
	};	
}
