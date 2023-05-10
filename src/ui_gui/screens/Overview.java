package src.ui_gui.screens;

import java.util.ArrayList;
import java.util.Arrays;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * Overview component that holds the buttons for check, checkmate, draw, and chess notation.
 * 
 * @author Nolan Flinchum, Thomas Kay (100%), Joseph Oladeji, Levi Sweat
 * @date 5/9/2023
 */
public class Overview extends BorderPane implements EventHandler<ActionEvent> {

	/**Button that displays check conditions**/
	Button check;

	/**Button that displays checkmate conditions**/
	Button checkMate;

	/**Button that displays draw conditions**/
	Button draw;

	/**Button that displays chess notation convention**/
	Button notation;
	
	/**To change the display to different scenes**/
	OverViewFactory fact;
	
	/**
	 * Constructor for the class.
	 */
	public Overview() {
		super();
		
		//Title
		this.fact = new OverViewFactory();
		Label title = new Label("Overview");
		title.setId("Title");
		this.setTop(title);
		
		//Buttons
		this.check = new Button("Check");
		this.checkMate = new Button("Checkmate");
		this.draw = new Button("Draw");
		this.notation = new Button("Notation");
		
		ArrayList<Button> b = new ArrayList<>(Arrays.asList(check, checkMate, draw, notation));
		b.forEach(n -> {
			n.setOnAction(this);
			n.getStyleClass().add("buttonStyleA");
			n.getStyleClass().add("buttonSizeA");
		});
		
		//To display the buttons
		VBox buttonPanel = new VBox(10);
		buttonPanel.getChildren().addAll(b);
		
		this.setLeft(buttonPanel);
	}
	
	@Override
	/**
	 * When a button is selected, displays information that the user has chosen. For instance,
	 * selecting chess notation displays how chess notation works.
	 */
	public void handle(ActionEvent event) {
		if(event.getSource() == check) {
			this.setCenter(this.fact.makeOverviewScreen(1));
		}
		else if(event.getSource() == checkMate) {
			this.setCenter(this.fact.makeOverviewScreen(2));
		}
		else if(event.getSource() == draw) {
			this.setCenter(this.fact.makeOverviewScreen(3));
		}
		else if(event.getSource() == notation) {
			this.setCenter(this.fact.makeOverviewScreen(4));
		}
	}
}
