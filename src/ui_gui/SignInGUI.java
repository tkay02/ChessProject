package src.ui_gui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import src.interfaces.ScreenChangeHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;

public class SignInGUI extends VBox{
    
    private ScreenChangeHandler screenChanger; 
	
	private Button b1, b2;

	private String username, password;
	
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

    public void setScreenChangeHandler(ScreenChangeHandler screen){
		this.screenChanger = screen;
	}

	EventHandler<ActionEvent> btnHandle = new EventHandler<ActionEvent>(){
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
