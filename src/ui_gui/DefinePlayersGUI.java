package src.ui_gui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import src.interfaces.ScreenChangeHandler;
import src.ui_gui.ScreenFactory.Screen;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;

/**
 * This class creates the Define Players scene and allows the user
 * to define the player names.
 * 
 * @author Nolan Flinchum, Thomas Kay, Joseph Oladeji (100%), Levi Sweat
 * @version 5/9/2023
 */
public class DefinePlayersGUI extends VBox implements EventHandler<ActionEvent>{
        
	/**
	 * Setter method for the screen change handler.
	 * 
	 * @param screen screen change handler to use
	 */
    private ScreenChangeHandler screenChanger;

	/** Buttons for going back to main menu and applying the name to the game */
    private Button mainMenuBtn, playNameBtn;

	/** The first player names for the Chess Game */
	private static String playerOneName = "";
	
	/** The second player names for the Chess Game */
	private static String playerTwoName = "";

	/** Text field for the player names */
	public TextField playerOneDefine, playerTwoDefine;
	
	/** Constructor for creating the define player GUI */
    public DefinePlayersGUI() {

		this.getStyleClass().add("mainMenu");

		setAlignment(Pos.CENTER);

		HBox btns = new HBox();

		playNameBtn = new Button("Play");
		mainMenuBtn = new Button("Exit");

		mainMenuBtn.getStyleClass().add("buttonStyleA");
		mainMenuBtn.getStyleClass().add("buttonSizeA");

		playNameBtn.getStyleClass().add("buttonStyleA");
		playNameBtn.getStyleClass().add("buttonSizeA");
		
		Label title = new Label("Define Players");

		Label playerOneBox = new Label("Define Player One's Name");
		Label playerTwoBox = new Label("Define Player Two's Name");
		
		playerOneBox.getStyleClass().add("labelB");
		playerTwoBox.getStyleClass().add("labelB");

		playerOneBox.setMinSize(50, 50);
		playerTwoBox.setMinSize(50, 50);
		
		playerOneDefine = new TextField();
		playerOneDefine.setMaxWidth(400);
		playerOneDefine.setMinHeight(75);

		playerTwoDefine = new TextField();
		playerTwoDefine.setMaxWidth(400);
		playerTwoDefine.setMinHeight(75);

		title.getStyleClass().add("labelA");

		this.getChildren().add(title);
		this.getChildren().addAll(playerOneBox, playerOneDefine);
		this.getChildren().addAll(playerTwoBox, playerTwoDefine);

		mainMenuBtn.setOnAction(this);
		playNameBtn.setOnAction(this);

		VBox verticalSep = new VBox();
		verticalSep.setPrefHeight(150);
		getChildren().add(verticalSep);

		HBox horizontalSep = new HBox();
		btns.setAlignment(Pos.CENTER);
		horizontalSep.setPrefWidth(125);

		btns.getChildren().add(playNameBtn);
		btns.getChildren().add(horizontalSep);
		btns.getChildren().add(mainMenuBtn);
		getChildren().add(btns);

    }

	/**
	 * Setter method for the screen change handler.
	 * 
	 * @param screen screen change handler to use
	 */
    public void setScreenChangeHandler(ScreenChangeHandler screen){
		this.screenChanger = screen;
	}

	/**
	 * Defines what action to do based on what button is pressed.
	 * 
	 * @param event the action event that occured
	 */
	public void handle(ActionEvent event){
		if(screenChanger != null){
			Object o = event.getSource();
			if(o == playNameBtn){
				playerOneName = playerOneDefine.getText();
				playerTwoName = playerTwoDefine.getText();
				screenChanger.changeScreen(ScreenFactory.Screen.MATCH_SCREEN);
			}
			else if(o == mainMenuBtn){
				screenChanger.changeScreen(Screen.MAIN_SCREEN);
			}
		}
	}

	/** Retrieves the player one's name */
	public static String getPlayerOneName(){
		return playerOneName;
	}

	/** Retrieves the player two's name */
	public static String getPlayerTwoName(){
		return playerTwoName;
	}

}
