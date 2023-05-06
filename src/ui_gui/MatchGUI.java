package src.ui_gui;

import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import src.interfaces.ScreenChangeHandler;
import src.ui_gui.ScreenFactory.Screen;

import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

/**
 * Graphical user interface for the player vs. player screen.
 * 
 * @author Nolan Flinchum, Thomas Kay, Joseph Oladeji, Levi Sweat
 * @version 5/8/2023
 */
public class MatchGUI extends BorderPane {

    /** Handles the screen changing **/
    private ScreenChangeHandler screenChanger;

	/** Buttons for the match screen **/
    private Button b1, b2, b3, b4, b5, b6, b7;

	/**
	 * Constructor for MatchGUI.
	 */
    public MatchGUI(){
		HBox top = new HBox(50);
		HBox bottom = new HBox();
		VBox left = new VBox();
		VBox right = new VBox();
		Pane center = new Pane();
		this.setTop(top);
		this.setBottom(bottom);
		this.setRight(right);
		this.setLeft(left);
		this.setCenter(center);
		this.getStyleClass().add("backgroundA");
		this.setPadding(new Insets(3));
				
		makeBottom(bottom); //BOTTOM
		makeTop(top); // TOP

		// CENTER
		center.getStyleClass().add("backgroundC"); 
		BorderPane.setMargin(center, new Insets(3));

		// LEFT
		makeSide(left);
		b6 = buttonMaker("Show Moves", "buttonStyleA", "buttonSizeA");
		left.getChildren().add(b6);

		// RIGHT
		makeSide(right);
		b7 = buttonMaker("Exit", "buttonStyleA", "buttonSizeA");
		right.getChildren().add(b7);
    }

	/**
	 * Helper method that sets up the top section of the BorderPane.
	 * 
	 * @param top the reference to the top section
	 */
	private void makeTop(HBox top){
		BorderPane.setMargin(top, new Insets(3));
		top.setAlignment(Pos.CENTER);
		top.setMinHeight(100.0);
		top.getStyleClass().add("backgroundB");
		
		b1 = buttonMaker("Load", "buttonStyleA", "buttonSizeA");
		b2 = buttonMaker("Save", "buttonStyleA", "buttonSizeA");
		b3 = buttonMaker("Undo", "buttonStyleA", "buttonSizeA");
		b4 = buttonMaker("Redo", "buttonStyleA", "buttonSizeA");
		b5 = buttonMaker("Settings", "buttonStyleA", "buttonSizeA");
		top.getChildren().addAll(b1, b2, b3, b4, b5);
	}

	/**
	 * Helper method that sets up the left and right section of the BorderPane.
	 * 
	 * @param side the reference to the left section or right section
	 */
	private void makeSide(VBox side){
		BorderPane.setMargin(side, new Insets(3));
		side.setAlignment(Pos.CENTER);
		side.getStyleClass().add("backgroundC");
		side.setPrefWidth(400.0);
	}

	/**
	 * Helper method that sets up the bottom section of the BorderPane.
	 * 
	 * @param bottom the reference to the bottom section
	 */
	private void makeBottom(HBox bottom){
		BorderPane.setMargin(bottom, new Insets(3));
		bottom.setAlignment(Pos.CENTER);
		bottom.setMinHeight(100.0);
		bottom.getStyleClass().add("backgroundD");
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

				if(o == b7) screenChanger.changeScreen(Screen.MAIN_SCREEN);
				else if(o == b5) screenChanger.changeScreen(Screen.SETTINGS_SCREEN);
				else if(o == b1){
					Dialog<String> load = new LoadGameGUI("");
					Optional<String> result = load.showAndWait();
					if (result.isPresent()) {
						
					}
				}
			}
		}
	};	
}

