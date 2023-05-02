package src.ui_gui.screens;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

public class BoardSetup {
	
	private VBox root;
	
	public BoardSetup() {
		super();
		
		this.root = new VBox();
		this.root.setAlignment(Pos.BASELINE_CENTER);
		
		Label test = new Label("Test message");
		this.root.getChildren().add(test);
	}
	
	public VBox getRoot() {
		return this.root;
	}

}
