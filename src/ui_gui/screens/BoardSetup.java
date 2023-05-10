package src.ui_gui.screens;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import src.controller.Chess;
import src.model.Board;
import src.model.Piece;
import src.model.Square;
import src.ui_gui.ChessSquare;

/**
 * BorderPane to explain different aspects of board setup in chess.
 * 
 * @author Nolan Flinchum, Thomas Kay (100%), Joseph Oladeji, Levi Sweat
 * @date 5/9/2023
 */
public class BoardSetup extends BorderPane implements EventHandler<ActionEvent> {
	
	/** Board from the white player's perspective **/
	GridPane board1;
	
	/** Board from the black player's perspective **/
	GridPane board2;
	
	/** Button that switches between the screens **/
	Button swap;
	
	/**
	 * Displays board information that the player can understand, such as the board layout,
	 * the amount/type of pieces, and what the rows/cols are called.
	 */
	public BoardSetup() {
		super();
		
		//Title
		Label title = new Label("Board Setup");
		title.setId("Title");
		title.setAlignment(Pos.TOP_CENTER);
		this.setTop(title);
		
		//Pane that stores the subtitles
		VBox rules = new VBox();
		rules.setAlignment(Pos.TOP_LEFT);
		
		//Subtitles
		ArrayList<Label> subtitles = new ArrayList<>();
		Label s0 = new Label("The game is played on an 8x8 black and white board with \neach player " +
						     "receiving 16 pieces each");
		subtitles.add(s0);
		Label s1 = new Label(">8 pawns \n>2 rooks, knights, and bishops \n>1 queen and king");
		subtitles.add(s1);
		Label s2 = new Label("The white pieces are placed in ranks 1-2 while the black \npieces are "+
				             "placed in ranks 7-8");
		subtitles.add(s2);
		Label s3 = new Label("The rooks are placed on the corners");
		subtitles.add(s3);
		Label s4 = new Label("The knights are placed next to the rooks, which the \nbishops are "+
							 "placed next to the knights");
		subtitles.add(s4);
		Label s5 = new Label("The queens are placed on the file D and the \nkings are placed on "+
                			 "file E");
		subtitles.add(s5);
		Label s6 = new Label("The pawns are placed on the second ranks (the \nrank above/below the "+
                           "other pieces)");
		subtitles.add(s6);
		Label s7 = new Label("The ranks, which are the rows of the board, are represented " +
                             "\nwith numbers 1-8");
		subtitles.add(s7);
		Label s8 = new Label("The files, which are the columns of the board, are represented " +
							"\nwith letters A-F");
		subtitles.add(s8);
		Label s9 = new Label("Quick reminder: white always goes first");
		subtitles.add(s9);
		subtitles.forEach(n -> {
			n.getStyleClass().add("subtitle");
			rules.getChildren().add(n);
		});
		
		//Creates button to switch scenes
		this.swap = new Button("Switch sides");
		this.swap.setOnAction(this);
		this.swap.getStyleClass().add("buttonStyleA");
		this.swap.getStyleClass().add("buttonSizeA");
		rules.getChildren().add(this.swap);
		
		this.setLeft(rules);
		
		//Board information such as the tile order and name of the ranks/files
		Board toDisplay = new Board(new Chess());
		String[] ranks = {"8", "7", "6", "5", "4", "3", "2", "1"};
		String[] files = {"A", "B", "C", "D", "E", "F", "G", "H"};
		Square[][] tiles = (Square[][])toDisplay.getSquares();
		
		//Board for white player's perspective
		this.board1 = new GridPane();
		board1.setAlignment(Pos.CENTER);
		for(int i = 0; i < tiles.length; i++) {
			Label rank = new Label(ranks[i]);
			rank.getStyleClass().add("rank_fileLabel");
			board1.add(rank, 0, i);
			GridPane.setConstraints(rank, 0, i);
			for(int j = 0; j < tiles[i].length; j++) {
				ChessSquare sq = new ChessSquare(tiles[i][j].isWhite(),(Piece)tiles[i][j].getPiece());
				sq.disable();
				board1.add(sq, j + 1, i);
			}
		}
		for(int k = 0; k < files.length; k++) {
			Label file = new Label(files[k]);
			file.getStyleClass().add("rank_fileLabel");
			board1.add(file, k+1, 8);
			GridPane.setConstraints(file, k+1, 8);
		}
		
		//Board for black player's perspective
		this.board2 = new GridPane();
		board2.setAlignment(Pos.CENTER);
		for(int i = tiles.length - 1; i >= 0; i--) {
			Label rank = new Label(ranks[i]);
			rank.getStyleClass().add("rank_fileLabel");
			board2.add(rank, 0, ranks.length - (i+1));
			GridPane.setConstraints(rank, 0, ranks.length - (i+1));
			for(int j = tiles[i].length - 1; j >= 0; j--) {
				ChessSquare sq = new ChessSquare(tiles[i][j].isWhite(),(Piece)tiles[i][j].getPiece());
				sq.disable();
				board2.add(sq, tiles[i].length - j, tiles.length - (i+1));
			}
		}
		for(int i = 0; i < files.length; i++) {
			Label file = new Label(files[files.length - (i+1)]);
			file.getStyleClass().add("rank_fileLabel");
			board2.add(file, i+1, 8);
			GridPane.setConstraints(file, i+1, 8);
		}
		
		//White player's perspective is set as default
		this.setCenter(board1);
		
		
	}

	@Override
	/**
	 * When swap is pressed, switches the board setup to show the black player's perspective
	 * if it originally showed the whote player's perspective and vice versa.
	 */
	public void handle(ActionEvent event) {
		if(event.getSource() == this.swap) {
			if(this.getCenter() == this.board1) this.setCenter(board2);
			else if(this.getCenter() == this.board2) this.setCenter(board1);
		}
		
	}

}
