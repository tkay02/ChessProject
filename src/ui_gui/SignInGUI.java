package src.ui_gui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import src.interfaces.ScreenChangeHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;

/**
 * Graphical user interface for the sign in menu.
 * 
 * @author Nolan Flinchum (100%), Thomas Kay, Joseph Oladeji, Levi Sweat
 * @version 5/9/2023
 */
public class SignInGUI extends VBox{
    
	/** Handles the screen changing **/
    private ScreenChangeHandler screenChanger; 
	
	/** The button options of sign in menu **/
	private Button b1, b2;

	/** Designated username and password retrieved information */
	private String username, password;
	
	/**
	 * Constructor for creating the sign in menu.
	 */
    public SignInGUI() {
        this.getStyleClass().add("mainMenu");
		this.setAlignment(Pos.CENTER);
		this.setSpacing(50);

		Label label1 = new Label("Enter Username:");
		label1.getStyleClass().add("labelB");

		TextField tf1 = new TextField();

		Label label2 = new Label("Enter Password: ");
		label2.getStyleClass().add("labelB");

		TextField tf2 = new TextField();

		tf1.setMaxSize(450, 100);
		tf2.setMaxSize(450, 100);
		tf1.setPrefHeight(50);
		tf2.setPrefHeight(50);

		b1 = new Button("Exit");
		b1.getStyleClass().add("buttonStyleB");
		b1.getStyleClass().add("buttonSizeB");
		b1.setOnAction(btnHandle);

		b2 = new Button("Sign In");
		b2.getStyleClass().add("buttonStyleB");
		b2.getStyleClass().add("buttonSizeB");
		b2.setOnAction(btnHandle);

		this.getChildren().addAll(label1, tf1, label2, tf2, b2, b1);
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

				if(o == b1 || o == b2){
				   screenChanger.changeScreen(ScreenFactory.Screen.MAIN_SCREEN);
                }
			}
		}
	};	
 
}
