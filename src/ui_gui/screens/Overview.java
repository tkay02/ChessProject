package src.ui_gui.screens;

import java.util.Arrays;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class Overview extends BorderPane implements EventHandler<ActionEvent> {

	Button check;
	Button checkMate;
	Button draw;
	Button notation;
	
	OverViewFactory fact;
	
	public Overview() {
		super();
		this.fact = new OverViewFactory();
		Label title = new Label("Overview");
		title.setId("Title");
		this.setTop(title);
		
		this.check = new Button("Rules for Check");
		check.setOnAction(this);
		this.checkMate = new Button("Rules for Checkmate");
		checkMate.setOnAction(this);
		this.draw = new Button("Draw Conditions");
		draw.setOnAction(this);
		this.notation = new Button("Chess Notation");
		notation.setOnAction(this);
		
		VBox buttonPanel = new VBox();
		buttonPanel.getChildren().addAll(Arrays.asList(check, checkMate, draw, notation));
		
		this.setLeft(buttonPanel);
	}
	
	@Override
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
