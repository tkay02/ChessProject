package src.ui_gui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import src.interfaces.ScreenChangeHandler;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;


/**
 * 
 * This class creates the Define Players scene and allows the user
 * to define the player names.
 * @author Nolan Flinchum , Thomas Kay, Joseph Oladeji, Levi Sweat
 * @version 5/5/2023
 */
public class DefinePlayersGUI extends VBox implements EventHandler<ActionEvent>{
        
    private ScreenChangeHandler screenChanger;

    private Button mainMenuBtn, playNameBtn;

	private static String playerOneName = "";
	
	private static String playerTwoName = "";

	public TextField playerOneDefine, playerTwoDefine;
	
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

    public void setScreenChangeHandler(ScreenChangeHandler screen){
		this.screenChanger = screen;
	}

	public void handle(ActionEvent event){
		if(screenChanger != null){
			Object o = event.getSource();
			if(o == playNameBtn){
				playerOneName = playerOneDefine.getText();
				playerTwoName = playerTwoDefine.getText();
			}
			screenChanger.changeScreen(ScreenFactory.Screen.MAIN_SCREEN);
			
		}
	}

	public static String getPlayerOneName(){
		return playerOneName;
	}

	public static String getPlayerTwoName(){
		return playerTwoName;
	}

}
