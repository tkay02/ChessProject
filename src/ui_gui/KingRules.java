package src.ui_gui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class KingRules {

	private GridPane root;
	
	public KingRules() {
		super();
		
		this.root = new GridPane();
		this.root.setVgap(5);
		Label title = new Label("The King");
		title.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		TextField sent1 = new TextField(">The king is the most important piece in chess");
		sent1.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		TextField sent2 = new TextField(">The king can move in any direction vertically, "+
										"horizontally, and diagonally");
		sent2.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		TextField sent3 = new TextField(">However, the king can only move 1 space compared to the "+
										"queen");
		sent3.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		this.root.add(title, 3, 0);
		this.root.add(sent1, 0, 1);
		this.root.add(sent2, 0, 2);
		this.root.add(sent3, 0, 3);
		
	}
	
	public Pane getRoot() {
		return this.root;
	}
	
}
