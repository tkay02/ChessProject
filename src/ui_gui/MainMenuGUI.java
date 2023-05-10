package src.ui_gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import src.controller.GUIDriver;
import src.interfaces.ScreenChangeHandler;
import src.ui_gui.ScreenFactory.Screen;
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

	/** The button options of the menu **/
	private Button b1, b2, b3, b4, b5, b6;

	/** Handles the screen changing **/
	private ScreenChangeHandler screenChanger;
	

	/**
	 * Constructor that creates the main menu by initializes and setting up the
	 * appropriate sections of the BorderPane.
	 */
	public MainMenuGUI(){
		AnchorPane bottom = new AnchorPane();
		VBox left = new VBox();
		VBox right = new VBox();
		VBox center = new VBox();
		VBox top = new VBox();

		makeBottom(bottom);
		makeSide(left, "PieceGIF2.gif");
		makeSide(right, "PieceGIF3.gif");
		makeCenter(center);
		makeTop(top);

		this.setBottom(bottom);
		this.setRight(right);
		this.setLeft(left);
		this.setCenter(center);
		this.setTop(top);
		this.getStyleClass().add("mainMenu");
	}

	/**
	 * Helper method that sets up the top section of the BorderPane.
	 * 
	 * @param top the reference to the top section
	 */
	private void makeTop(VBox top){
		top.setAlignment(Pos.CENTER);
		Label label = new Label("ChessMeister");
		label.getStyleClass().add("labelA");
		top.getChildren().add(label);
	}

	/**
	 * Helper method that sets up the bottom section of the BorderPane.
	 * 
	 * @param bottom the reference to the bottom section
	 */
	private void makeBottom(AnchorPane bottom){
		b5 = buttonMaker("Settings", "buttonStyleB", "buttonSizeB");
		AnchorPane.setLeftAnchor(b5, 20.0);
		AnchorPane.setBottomAnchor(b5, 20.0);

		b6 = buttonMaker("Exit", "buttonStyleB", "buttonSizeB");
		AnchorPane.setRightAnchor(b6, 20.0);
		AnchorPane.setBottomAnchor(b6, 20.0);

		bottom.getChildren().addAll(b5, b6);
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

		b1 = buttonMaker("Play Chess", "buttonStyleB", "buttonSizeB");
		b2 = buttonMaker("View Rules", "buttonStyleB", "buttonSizeB");
		b3 = buttonMaker("Sign In", "buttonStyleB", "buttonSizeB");
		b4 = buttonMaker("Sign Up", "buttonStyleB", "buttonSizeB");
		center.getChildren().addAll(b1, b2, b3, b4);
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

	/** Object that holds how a button press is handled */
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
				   screenChanger.changeScreen(ScreenFactory.Screen.DEFINE_PLAYERS);
				else if(o == b2)
				   screenChanger.changeScreen(ScreenFactory.Screen.RULES_SCREEN);
				else if(o == b3)
					screenChanger.changeScreen(ScreenFactory.Screen.SIGN_IN);
				else if(o == b4)
					screenChanger.changeScreen(ScreenFactory.Screen.SIGN_UP);
				else if(o == b5){
					ScreenFactory.prevScreen = Screen.MAIN_SCREEN;
					screenChanger.changeScreen(ScreenFactory.Screen.SETTINGS_SCREEN);
				}
				else if(o == b6)
					System.exit(1);
			}
		}
	};	
}
