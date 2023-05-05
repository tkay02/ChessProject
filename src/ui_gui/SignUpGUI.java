package src.ui_gui;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import src.interfaces.ScreenChangeHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SignUpGUI extends Pane {
    
    private ScreenChangeHandler screenChanger; 
	
	private Button b1;
	
    public SignUpGUI() {
        this.getStyleClass().add("mainMenu");
		b1 = new Button("Back to Main Menu");
		b1.getStyleClass().add("buttonStyleA");
		b1.getStyleClass().add("buttonSizeA");
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
