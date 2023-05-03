package src.ui_gui;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import src.ui_gui.ChessPane;

public class BoardSetup {
	
	private VBox root;
	
	public BoardSetup() {
		super();
		
		this.root = new VBox();
		Label title = new Label("Chess Board Setup");
		this.root.getChildren().add(title);
		ChessPane board = new ChessPane();
		this.root.getChildren().add(board);
	
	}
	public Pane getRoot() {
		return this.root;
	}

}
