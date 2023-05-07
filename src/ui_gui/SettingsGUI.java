package src.ui_gui;

import java.util.Arrays;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import src.interfaces.ScreenChangeHandler;
import src.ui_gui.screens.ColoramaGUI;

public class SettingsGUI extends VBox{

	/**The button options of the menu**/
	Button b1, b2, b3, b4, b5, b6, b7, b8, whiteButton, blackButton;

	private String whiteColor; //color of the white squares

	String blackColor; //color of the black squares
	
	private ScreenChangeHandler screenChanger;
	/**Construct the menu**/
	public SettingsGUI(){
		
		whiteColor = "#ffffff";
		
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
		Label lb3 = new Label("White Squares:   ");//Squares labels
		Label lb4 = new Label("Black Squares:    ");//will need to set styleclass

		whiteButton = new Button(); 
        whiteButton.getStyleClass().add("buttonWhiteSquares"); //color and style for white squares
		whiteButton.setStyle("-fx-background-color: " + whiteColor);

		blackButton = new Button(); 
        blackButton.getStyleClass().add("buttonBlackSquares"); // color and style for black squares
		//blackButton.setStyle("-fx-background-color: rgb(125, 43, 43)");
		
		whiteButton.setOnAction(btnHandle);
		blackButton.setOnAction(btnHandle);
		
		//VBOX FOR CHANGING COLORS
		VBox v1 = new VBox(); 
		this.getChildren().add(v1);

		//HBox for white colors
		HBox whiteHBox = new HBox();
		v1.getChildren().add(whiteHBox);
		whiteHBox.getChildren().add(lb3);
		whiteHBox.getChildren().add(whiteButton);

		//HBox for black colors
		HBox blackHBox = new HBox();
		v1.getChildren().add(blackHBox);
		blackHBox.getChildren().add(lb4);
		blackHBox.getChildren().add(blackButton);

		v1.getChildren().add(lb2); //colors header



		
		
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
				else if(o == whiteButton || o == blackButton){
					Dialog<String> colorama = new ColoramaGUI(whiteColor);
					colorama.showAndWait();
				}

			}
		}
	};	

}



