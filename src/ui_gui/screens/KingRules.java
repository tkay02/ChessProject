package src.ui_gui.screens;

import javafx.geometry.Pos;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class KingRules {

	private TilePane root;
	
	public KingRules() {
		super();
		
		this.root = new TilePane();
		root.setAlignment(Pos.BASELINE_CENTER);
		root.setPrefColumns(5);
		Rectangle tile1 = new Rectangle(64,64);
		tile1.setFill(Color.valueOf("#3F301D"));
		root.getChildren().add(tile1);
		Rectangle tile2 = new Rectangle(64,64);
		tile2.setFill(Color.valueOf("#FEF9F3"));
		root.getChildren().add(tile2);
		
	}
	
	public TilePane getRoot() {
		return this.root;
	}
	
}
