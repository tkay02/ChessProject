package src.ui_gui;

import java.util.Arrays;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import src.interfaces.ScreenChangeHandler;
import src.ui_gui.screens.ColoramaGUI;

public class SettingsGUI extends BorderPane{

	/**The button options of the menu**/
	Button b1, b2, whiteButton, blackButton;

	private String whiteColor = "#ffffff"; //color of the white squares

	String blackColor = "#000000"; //color of the black squares
	
	private ScreenChangeHandler screenChanger;

	/**Construct the menu**/
	public SettingsGUI(){
		
		this.getStyleClass().add("mainMenu");

		VBox top = new VBox();
		VBox left = new VBox();
		AnchorPane right = new AnchorPane();

		this.setTop(top);
		this.setRight(right);
		this.setLeft(left);

		//TOP
		top.setAlignment(Pos.CENTER);
		Label lbl = new Label("Settings");
		lbl.getStyleClass().add("labelB");
		lbl.setPadding(new Insets(40));
		top.getChildren().add(lbl);

		//LEFT
		left.setPadding(new Insets(0, 0, 0, 50));
		left.setSpacing(50);
		Label lb2 = new Label("Colors:");
		lb2.getStyleClass().add("labelB");
		Label lb3 = new Label("White Squares:   ");
		lb3.getStyleClass().add("labelB");
		Label lb4 = new Label("Black Squares:    ");
		lb4.getStyleClass().add("labelB");

		whiteButton = new Button(); 
        whiteButton.getStyleClass().add("buttonWhiteSquares"); //color and style for white squares
		whiteButton.setStyle("-fx-background-color: " + whiteColor);
		blackButton = new Button(); 
        blackButton.getStyleClass().add("buttonBlackSquares"); // color and style for black squares
		blackButton.setStyle("-fx-background-color:" + blackColor);
		whiteButton.setOnAction(btnHandle);
		blackButton.setOnAction(btnHandle);

		//HBox for white colors
		HBox whiteHBox = new HBox();
		whiteHBox.setPadding(new Insets(0, 0, 0, 60));
		whiteHBox.getChildren().add(lb3);
		whiteHBox.getChildren().add(whiteButton);

		//HBox for black colors
		HBox blackHBox = new HBox();
		blackHBox.setPadding(new Insets(10, 0, 0, 60));
		blackHBox.getChildren().add(lb4);
		blackHBox.getChildren().add(blackButton);

		CheckBox cb1 = new CheckBox("Undo/Redo Moves");
		cb1.getStyleClass().add("labelB");
		cb1.setPadding(new Insets(100, 0, 40, 0));
		CheckBox cb2 = new CheckBox("Show Moves");
		cb2.getStyleClass().add("labelB");
		//cb2.setPadding(new Insets(40));

		left.getChildren().addAll(lb2, whiteHBox, blackHBox, cb1, cb2);

		//RIGHT
		b1 = new Button("Save");
		b1.getStyleClass().add("buttonStyleA");
		b1.getStyleClass().add("buttonSizeA");
		b1.setOnAction(btnHandle);

		b2 = new Button("Exit");
		b2.getStyleClass().add("buttonStyleA");
		b2.getStyleClass().add("buttonSizeA");
		b2.setOnAction(btnHandle);

		VBox buttons = new VBox();
		buttons.getChildren().addAll(b1, b2);
		buttons.setSpacing(30);

		AnchorPane.setRightAnchor(buttons, 50.0);
		AnchorPane.setBottomAnchor(buttons, 50.0);
		right.getChildren().add(buttons);
	}
	
	public void setScreenChangeHandler(ScreenChangeHandler screen){
		this.screenChanger = screen;
	}
	
	EventHandler<ActionEvent> btnHandle = new EventHandler<ActionEvent>(){
		public void handle(ActionEvent event){
			if(screenChanger != null){
				Object o = event.getSource();

				if(o == b2){
				   screenChanger.changeScreen(ScreenFactory.Screen.MAIN_SCREEN);
				}
				else if(o == whiteButton || o == blackButton){
					Dialog<String> colorama = new ColoramaGUI(whiteColor);
					colorama.showAndWait();
				}
			}
		}
	};	

}



