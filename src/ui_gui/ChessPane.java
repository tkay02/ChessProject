package src.ui_gui;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ChessPane extends TilePane {
	
	private static final int MAX_SQUARE_WIDTH = 64;
	private static final int MAX_SQUARE_HEIGHT = 64;
	private static final int MAX_WIDTH = 640;
	private static final int MAX_HEIGHT = 640;
	
	ArrayList<Rectangle> tiles;
	String[] rank = {"1","2","3","4","5","6","7","8"};
	String[] files = {"","A","B","C","D","E","F","G","H"};
	String blackSquare;
	String whiteSquare;
	
	public ChessPane() {
		super();
		this.setAlignment(Pos.BASELINE_CENTER);
		this.setMaxWidth(MAX_WIDTH);
		this.setMaxHeight(MAX_HEIGHT);
		//Change later
		this.tiles = new ArrayList<>();
		blackSquare = "#3F301D";
		whiteSquare = "#FEF9F3";
		
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				Rectangle tile = new Rectangle(MAX_SQUARE_WIDTH,MAX_SQUARE_HEIGHT);
				tile.setStroke(Color.BLACK);
				if((i % 2 == 0 && j % 2 == 0) || (i % 2 != 0 && j % 2 != 0)) {
					tile.setFill(Color.valueOf(whiteSquare));
				}
				else {
					tile.setFill(Color.valueOf(blackSquare));
				}
				this.tiles.add(tile);
			}
		}
		int rankNum = 0;
		for(int i = 0; i < this.tiles.size(); i++) {
			if(i % 8 == 0) {
				this.getChildren().add(new Label(this.rank[rankNum]));
				rankNum++;
			}
			this.getChildren().add(this.tiles.get(i));
		}
		for(int i = 0; i < this.files.length; i++) {
			this.getChildren().add(new Label(this.files[i]));
		}
		
	}
}
