package src.ui_gui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import src.interfaces.ScreenChangeHandler;
import src.ui_gui.ScreenFactory.Screen;
import java.util.Arrays;
import javafx.event.*;
import javafx.geometry.Pos;

public class RulesGUI implements EventHandler<ActionEvent> {
	
	/**Button that returns user back to main menu**/
	Button b0;

	/**Button that displays board overview**/
	Button b1;

	/**Button that displays king movement**/
	Button b2;

	/**Button that displays queen movement**/
	Button b3;

	/**Button that displays bishop movement**/
	Button b4;

	/**Button that displays knight movement**/
	Button b5;

	/**Button that displays rook movement**/
	Button b6;

	/**Button that displays pawn movement**/
	Button b7;

	/**Button that displays overview of conditions**/
	Button b8;
	
	/**Root of the layout**/
	private BorderPane root;

	/**To change the display to different scenes**/
	private RulesFactory fact;

	/** Handles the screen changing **/
	private ScreenChangeHandler screenChanger;
	
	/**Constructor for class**/
	public RulesGUI() {

		super();
		this.root = new BorderPane();
		this.fact = new RulesFactory();
		
		//Creates top panel for RulesGUI
		root.getStyleClass().add("mainMenu");
		HBox top = new HBox(300.0);
		Label rules = new Label("Rules");
		this.b0 = new Button("Back to Main Menu");
		top.getChildren().add(rules);
		top.getChildren().add(this.b0);
		
		root.setTop(top);
		
		//Creates left side menu
		VBox left = new VBox(10);
		left.setAlignment(Pos.BASELINE_LEFT);
		this.b1 = new Button("Board Setup");
		this.b2 = new Button("King Movement");
		this.b3 = new Button("Queen Movement");
		this.b4 = new Button("Bishop Movement");
		this.b5 = new Button("Knight Movement");
		this.b6 = new Button("Rook Movement");
		this.b7 = new Button("Pawn Movement");
		this.b8 = new Button("Overview");
		// Setting button actions to work with scene
		this.b0.setOnAction(this);
		this.b1.setOnAction(this);
		this.b2.setOnAction(this);
		this.b3.setOnAction(this);
		this.b4.setOnAction(this);
		this.b5.setOnAction(this);
		this.b6.setOnAction(this);
		this.b7.setOnAction(this);
		this.b8.setOnAction(this);
		left.getChildren().addAll(Arrays.asList(b1, b2, b3, b4, b5, b6, b7, b8));
		
		root.setLeft(left);
		
		//Sets screen to be used to display rule information
		TilePane display = new TilePane();
		display.setAlignment(Pos.BASELINE_CENTER);
		Label test = new Label("Select a button to view a rule");
		display.getChildren().add(test);
		
		root.setCenter(display);
	
	}
	
	public void setScreenChangeHandler(ScreenChangeHandler screen){
		this.screenChanger = screen;
	}

	public Pane getRoot() {
		return this.root;
	}

	@Override
	public void handle(ActionEvent event) {

		// Need to add code to buttons
		if(event.getSource() == this.b0) {
			screenChanger.changeScreen(Screen.MAIN_SCREEN);
		}
		if(event.getSource() == this.b1) {
			this.root.setCenter(this.fact.makeScreen(RuleScreens.BOARD));
		}
		else if(event.getSource() == this.b2) {
			this.root.setCenter(this.fact.makeScreen(RuleScreens.KING));
		}
		else if(event.getSource() == this.b3) {
		}
		else if(event.getSource() == this.b4) {
		}
		else if(event.getSource() == this.b5) {
		}
		else if(event.getSource() == this.b6) {
		}
		else if(event.getSource() == this.b7) {
		}
		else if(event.getSource() == this.b8) {
		}
		
	}

	

}
