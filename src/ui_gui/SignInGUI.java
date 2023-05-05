package src.ui_gui;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import src.interfaces.ScreenChangeHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SignInGUI extends Pane {
    
    private ScreenChangeHandler screenChanger; 
	
	private Button b1;
	
    public SignInGUI() {
        this.getStyleClass().add("mainMenu");
		b1 = new Button("Back to Main Menu");
		getChildren().add(b1);
		b1.setOnAction(btnHandle);
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
