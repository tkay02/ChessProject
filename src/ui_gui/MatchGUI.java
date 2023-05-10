package src.ui_gui;

import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import src.controller.Chess;
import src.interfaces.ScreenChangeHandler;
import src.ui_gui.ScreenFactory.Screen;
import java.util.ArrayList;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;

/**
 * Graphical user interface for the player vs. player screen.
 * 
 * @author Nolan Flinchum, Thomas Kay, Joseph Oladeji, Levi Sweat
 * @version 5/8/2023
 */
public class MatchGUI extends BorderPane {

    /** Handles the screen changing **/
    private ScreenChangeHandler screenChanger;

	/** Buttons for the match screen **/
    private Button b1, b2, b3, b4, b5, b6;

	/** Player names that were defined */
	private String playerOneName, playerTwoName;

	/** Describes what the players do */
	private static Label playerAction;

	/** FlowPanes that hold captured pieces */
	private static FlowPane whiteTakenPieces, blackTakenPieces;

	/** ChessBoardGUI instance to refrence the gui's chess game and other
	 * gui features.
	 */
	private ChessBoardGUI root;
	
	/** The pane for the center borderpane (Chess Game), which will
	 * be refreshed if a player chooses to load game.
	 */
	private VBox center;

	/**
	 * Constructor for MatchGUI.
	 */
    public MatchGUI(){
		HBox top = new HBox(50);
		HBox bottom = new HBox();
		VBox left = new VBox();
		VBox right = new VBox();
		center = new VBox();
		this.setTop(top);
		this.setBottom(bottom);
		this.setRight(right);
		this.setLeft(left);
		this.setCenter(center);
		this.getStyleClass().add("backgroundA");
		this.setPadding(new Insets(3));
				
		makeBottom(bottom); //BOTTOM
		makeTop(top); // TOP

		// CENTER
		center.getStyleClass().add("backgroundC"); 
		center.setAlignment(Pos.CENTER);
		BorderPane.setMargin(center, new Insets(3));
		root = new ChessBoardGUI(new Chess());
		center.getChildren().add(root);

		// LEFT
		makeSide(left);
		this.playerOneName = DefinePlayersGUI.getPlayerOneName();
		Label label1 = new Label("Player One:");
		label1.getStyleClass().add("labelB");
		Label label2 = new Label(playerOneName);
		label2.getStyleClass().add("labelB");

		Label label5 = new Label("Captured:");
		label5.getStyleClass().add("labelB");
		whiteTakenPieces = new FlowPane(Orientation.HORIZONTAL);

		left.getChildren().addAll(label1, label2, label5, whiteTakenPieces);

		// RIGHT
		makeSide(right);
		this.playerTwoName = DefinePlayersGUI.getPlayerTwoName();
		Label label3 = new Label("Player Two:");
		label3.getStyleClass().add("labelB");
		Label label4 = new Label(playerTwoName);
		label4.getStyleClass().add("labelB");

		Label label6 = new Label("Captured:");
		label6.getStyleClass().add("labelB");
		blackTakenPieces = new FlowPane();

		right.getChildren().addAll(label3, label4, label6, blackTakenPieces);
    }

	/**
	 * Helper method that sets up the top section of the BorderPane.
	 * 
	 * @param top the reference to the top section
	 */
	private void makeTop(HBox top){
		BorderPane.setMargin(top, new Insets(3));
		top.setAlignment(Pos.CENTER);
		top.setMinHeight(100.0);
		top.getStyleClass().add("backgroundB");
		
		b1 = buttonMaker("Load", "buttonStyleA", "buttonSizeA");
		b2 = buttonMaker("Save", "buttonStyleA", "buttonSizeA");
		b3 = buttonMaker("Undo", "buttonStyleA", "buttonSizeA");
		b4 = buttonMaker("Redo", "buttonStyleA", "buttonSizeA");
		b5 = buttonMaker("Settings", "buttonStyleA", "buttonSizeA");
		b6 = buttonMaker("Exit", "buttonStyleA", "buttonSizeA");
		top.getChildren().addAll(b1, b2, b3, b4, b5, b6);
	}

	/**
	 * Helper method that sets up the left and right section of the BorderPane.
	 * 
	 * @param side the reference to the left section or right section
	 */
	private void makeSide(VBox side){
		BorderPane.setMargin(side, new Insets(3));
		side.setPadding(new Insets(20, 0, 20, 20));
		side.getStyleClass().add("backgroundC");
		side.setPrefWidth(400.0);
	}

	/**
	 * Helper method that sets up the bottom section of the BorderPane.
	 * 
	 * @param bottom the reference to the bottom section
	 */
	private void makeBottom(HBox bottom){
		BorderPane.setMargin(bottom, new Insets(3));
		bottom.setAlignment(Pos.CENTER);
		bottom.setMinHeight(100.0);
		bottom.getStyleClass().add("backgroundD");
		playerAction = new Label();
		playerAction.getStyleClass().add("labelB");
		bottom.getChildren().add(playerAction);
	}

	/**
	 * Helper method that creates and returns a button.
	 * 
	 * @param text words that appear on the button
	 * @param style the CSS style of the button
	 * @param size designated size of the button
	 * @return fully created button
	 */
	private Button buttonMaker(String text, String style, String size){
		Button button = new Button(text);
		button.getStyleClass().add(style);
		button.getStyleClass().add(size);
		button.setOnAction(btnHandle);
		return button;
	}

	/**
	 * Setter method for the screen change handler.
	 * 
	 * @param screen screen change handler to use
	 */
    public void setScreenChangeHandler(ScreenChangeHandler screen){
		this.screenChanger = screen;
	}


	// Variable that holds how a button press is handled
	EventHandler<ActionEvent> btnHandle = new EventHandler<ActionEvent>(){

		/**
		 * Defines what action to do based on what button is pressed.
		 * 
		 * @param event the action event that occured
		 */
		public void handle(ActionEvent event){
			if(screenChanger != null){
				Object o = event.getSource();

				if(o == b6) {
					ScreenFactory.prevScreen = null;
					screenChanger.changeScreen(Screen.MAIN_SCREEN);
				}
				else if(o == b5){
					ScreenFactory.prevScreen = Screen.MATCH_SCREEN;
					screenChanger.changeScreen(Screen.SETTINGS_SCREEN);
				}
				else if(o == b1){
					Dialog<String> load = new LoadGameGUI();
					Optional<String> result = load.showAndWait();
					if (result.isPresent()) {
						center.getChildren().clear();
						setCenter(null);
						Chess game = new Chess();
						game.loadGame(LoadGameGUI.getFilePath());
						root = new ChessBoardGUI(game);
						checkTakenPieces(game, game.getBoard().getBlackTakenPieces(), true);
						checkTakenPieces(game, game.getBoard().getWhiteTakenPieces(), false);

						center.getChildren().add(root);
						setCenter(center);
					}
				}
				else if(o == b2){
					Dialog<String> save = new SaveGameGUI();
					Optional<String> result = save.showAndWait();
					if (result.isPresent()) {
						String location = System.getProperty("os.name").startsWith("Windows") ? 
						"src\\databases\\" :  "src/databases/";
						Chess game = ChessBoardGUI.getChessGame();
						game.saveGame(location + SaveGameGUI.getFileName(),
						 game.getBoard());
					}
				}
				else if(o == b3){
					ChessBoardGUI.undo();
				}
				else if(o == b4) {
					ChessBoardGUI.redo();
				}
			}
		}
	};	

	/**
	 * This method will add the list of taken pieces to either player 1's or 2's
	 * side depending if they have taken pieces from the previous game.
	 * @param game - Chess game instance
	 * @param list - List of taken pieces
	 * @param isWhite - Boolean value whether or not the list of taken pieces is 
	 * black or white
	 */
	public void checkTakenPieces(Chess game, ArrayList<String> list, boolean isWhite){
		for(int idx = 0; idx < list.size(); idx++){
			String image = isWhite ? "W" : "B";
			image = image += list.get(idx);
			ChessView addImage = new ChessView 
			(new Image(ChessSquare.class.getResourceAsStream(image += ".png")));
			if(isWhite)
				addBlackTakenPiece(addImage);
			else
				addWhiteTakenPiece(addImage);
		}

	}

	/**
	 * Sets the text of the player action label.
	 * @param text describes the players' actions
	 */
	public static void setPlayerAction(String text){
		playerAction.setText(text);
	}

	/**
	 * Adds to the list of white's captured pieces.
	 * @param image visualization of captured piece
	 */
	public static void addWhiteTakenPiece(ChessView image){
		whiteTakenPieces.getChildren().add(image);
	}

	/**
	 * Adds to the list of black's captured pieces.
	 * @param image visualization of captured piece
	 */
	public static void addBlackTakenPiece(ChessView image){
		blackTakenPieces.getChildren().add(image);
	}
}

