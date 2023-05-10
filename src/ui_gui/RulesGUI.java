package src.ui_gui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import src.interfaces.ScreenChangeHandler;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.event.*;
import javafx.geometry.Pos;

/**
 * Graphical user interface for the rules page.
 * 
 * @author Nolan Flinchum, Thomas Kay (100%), Joseph Oladeji, Levi Sweat
 * @date 5/9/2023
 */
public class RulesGUI extends BorderPane implements EventHandler<ActionEvent> {
	
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
	
	/**To change the display to different scenes**/
	private RulesFactory fact;

	/** Handles the screen changing **/
	ScreenChangeHandler screenChanger;
	
	/**
	 * Constructor for RulesGUI.
	 */
	public RulesGUI() {
		super();
		this.fact = new RulesFactory();
		
		//Creates top panel for RulesGUI
		this.getStyleClass().add("mainMenu");
		HBox top = new HBox(300.0);
		Label rules = new Label("Rules");
		rules.setId("Title");
		this.b0 = new Button("Main Menu");
		this.b0.getStyleClass().add("buttonStyleA");
		this.b0.getStyleClass().add("buttonSizeA");
		this.b0.setOnAction(this);
		top.getChildren().add(rules);
		top.getChildren().add(this.b0);
		
		this.setTop(top);
		
		//Creates left side menu
		VBox left = new VBox(10);
		
		left.setAlignment(Pos.BASELINE_LEFT);
		this.b1 = new Button("Board Setup");
		this.b2 = new Button("King Movement");
		this.b3 = new Button("Queen Mov");
		this.b4 = new Button("Bishop Mov");
		this.b5 = new Button("Knight Mov");
		this.b6 = new Button("Rook Movement");
		this.b7 = new Button("Pawn Movement");
		this.b8 = new Button("Overview");
		
		ArrayList<Button> b = new ArrayList<>(Arrays.asList(b1,b2,b3,b4,b5,b6,b7,b8));
		b.forEach(n -> {
			// Setting button actions to work with scene
			n.setOnAction(this);
			n.getStyleClass().add("buttonStyleA");
			n.getStyleClass().add("buttonSizeA");
			left.getChildren().add(n);
		});
		this.setLeft(left);
	
	}
	
	/**
	 * Setter for screen change handler.
	 * 
	 * @param screen screen change handler
	 */
	public void setScreenChangeHandler(ScreenChangeHandler screen){
		this.screenChanger = screen;
	}

	/**
	 * Returns the root of the RulesGUI.
	 */
	public Pane getRoot() {
		return this;
	}

	/**
	 * When a button is pressed, created a scene in the center of the GUI to display the rules
	 * in regards to which button was pressed. For instance, pressing the king movement button
	 * will present rules about the kings movement. Uses factory field to create screens.
	 */
	@Override
	public void handle(ActionEvent event) {
		if(screenChanger != null) {
			if(event.getSource() == this.b0) {
				screenChanger.changeScreen(ScreenFactory.Screen.MAIN_SCREEN);
			}
			if(event.getSource() == this.b1) {
				this.setCenter(this.fact.makeScreen(RuleScreens.BOARD));
			}
			else if(event.getSource() == this.b2) {
				this.setCenter(this.fact.makeScreen(RuleScreens.KING));
			}
			else if(event.getSource() == this.b3) {
				this.setCenter(this.fact.makeScreen(RuleScreens.QUEEN));
			}
			else if(event.getSource() == this.b4) {
				this.setCenter(this.fact.makeScreen(RuleScreens.BISHOP));
			}
			else if(event.getSource() == this.b5) {
				this.setCenter(this.fact.makeScreen(RuleScreens.KNIGHT));
			}
			else if(event.getSource() == this.b6) {
				this.setCenter(this.fact.makeScreen(RuleScreens.ROOK));
			}
			else if(event.getSource() == this.b7) {
				this.setCenter(this.fact.makeScreen(RuleScreens.PAWN));
			}
			else if(event.getSource() == this.b8) {
				this.setCenter(this.fact.makeScreen(RuleScreens.OVERVIEW));
			}	
		}
	}

}
