package src.ui_gui;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import src.enums.GameColor;
import src.model.Piece;
import src.controller.GUIRunner;
import src.enums.ChessPieceType;

/**
 * GUI component that makes up a chess board GUI.
 * 
 * @author Nolan Flinchum, Thomas Kay, Joseph Oladeji, Levi Sweat
 * @version 5/5/2023
 */
public class ChessSquare extends Pane {
	
	/**Square is black or white**/
	boolean isWhite;
	
	/**Potential chess piece on square**/
	ChessView chessPiece;
	
	/** Actual Chess Piece **/
	Piece piece;

	private final double MAX_SIZE = 64.0;
	
	/**
	 * Constructor for ChessSquare.
	 * 
	 * @param boolean isWhite Boolean that determines if the square is white or black
	 * @param Piece piece The chess piece on the square.
	 */
	public ChessSquare(boolean isWhite, Piece piece) {
		super();
		//Size of the square
		this.setSize(MAX_SIZE, MAX_SIZE);
		//Sets square color
		this.isWhite = isWhite;
		this.setSquareColor(); //Sets square color
		this.chessPiece = new ChessView();
		chessPiece.setChessSquare(this);
		this.setPiece(piece);
		this.getChildren().add(this.chessPiece);
		
		this.setOnMouseClicked(new EventHandler<MouseEvent>() {
			/**
			 * When a square/chess piece is pressed on, retrieves the selected square and
			 * updates the game in regards to which type of square is selected.
			 */
			public void handle(MouseEvent e) {
				ChessSquare sq = null;
				//Retrieves the selected square when the square or its chess piece is pressed on.
				if(e.getTarget() instanceof ChessSquare) sq = (ChessSquare) e.getTarget();
				else{
					ChessView chessImg = (ChessView) e.getTarget();
					sq = chessImg.getChessSquare();
				}
				//If a white/black square is selected
				if(sq.getId().equals("WhiteSquare") || sq.getId().equals("BlackSquare")) {
					//If the square isn't empty
					if(sq.piece.getChessPieceType() != ChessPieceType.EMPTY) {
						sq.setSelectedColor();
						ChessBoardGUI.updateCurrentChessPiece(sq);
					}
					else {
						ChessBoardGUI.updateCurrentChessPiece(sq);
					}
				}
				//If a selected square is selected
				else if(sq.getId().equals("SelectedSquare")) {
					sq.setSquareColor();
					ChessBoardGUI.updateCurrentChessPiece(sq);
				}
				//If a valid square is selected
				else if(sq.getId().equals("ValidSquare")) {
					ChessBoardGUI.updateCurrentChessPiece(sq);
				}
				//If a checked square is selected
				else if(sq.getId().equals("CheckSquare")) {
					sq.setSelectedColor();
					ChessBoardGUI.updateCurrentChessPiece(sq);
				}
			}
			
		});
	}
	
	/**
	 * Sets the size of the chess square.
	 * 
	 * @param double width The width of the square.
	 * @param double height The height of the square.
	 */
	private void setSize(double width, double height) {
		this.setMaxSize(width, height);
		this.setMinSize(width, height);
		this.setPrefSize(width, height);
	}
	
	/**
	 * Sets the color of the square to a specific ID in the CSS application.
	 */
	public void setSquareColor() {
		if(this.isWhite) {
			this.setId("WhiteSquare");
			paintWhite();
		}
		else {
			this.setId("BlackSquare");
			paintBlack();
		}
	}
	
	/**
	 * Paints the background of the square as white.
	 */
	public void paintWhite() {
		this.setStyle("-fx-border-color: #000000; "
				+     "-fx-background-color: #" + SettingsGUI.whiteColor + ";");
	}
	
	/**
	 * Paints the background of the square as black.
	 */
	public void paintBlack() {
		this.setStyle("-fx-border-color: #000000; " +
	                  "-fx-background-color: #" + SettingsGUI.blackColor + ";");
	}
	
	/**
	 * Sets the color of the square when the user selects on it.
	 */
	public void setSelectedColor() {
		this.setId("SelectedSquare");
		this.setStyle("-fx-border-color: #000000; "
				    + "-fx-background-color: #0a0468;");
	}
	
	/**
	 * Sets the color of the square when part of valid moves.
	 */
	public void setValidColor() {
		this.setId("ValidSquare");
		this.setStyle("-fx-border-color: #000000; " +
					  "-fx-background-color: #5573d6;");
	}
	
	/**
	 * Sets the color of a king square when it's in check.
	 */
	public void setCheckColor() {
		this.setId("CheckSquare");
		this.setStyle("-fx-border-color: #000000; " +
					  "-fx-background-color: #d1f72c;");
	}
	
	/**
	 * Sets the piece on the square. If the piece entered as a chess piece type of "EMPTY", then
	 * clears the chess piece off of the square.
	 * 
	 * @param Piece piece The chess piece on the square with its unique color and chess piece
	 * type.
	 */
	public void setPiece(Piece piece) {
		this.piece = piece;
		String imageName = "";
		//Clears image if chess piece type of piece is "EMPTY"
		if(piece.getChessPieceType() == ChessPieceType.EMPTY) {
			this.chessPiece.setImage(null);
		}
		else {
			imageName += chessPieceColor(piece.getColor());
			imageName += chessPieceName(piece.getChessPieceType());
			//Uses Driver class
			Image image = new Image(ChessSquare.class.getResourceAsStream(imageName += ".png"));
			this.chessPiece.setImage(image);
			this.chessPiece.fitWidthProperty().bind(this.widthProperty());
			this.chessPiece.fitHeightProperty().bind(this.heightProperty());
		}
	}
	
	public Piece getPiece() {
		return piece;
	}

	/**
	 * Obtains the color of the chess piece.
	 * 
	 * @param GameColor color The color of the chess piece.
	 * @return "W" if the chesspiece is white; "B" otherwise.
	 */
	private String chessPieceColor(GameColor color) {
		String colorPiece = "";
		if(color == GameColor.WHITE) colorPiece = "W";
		else colorPiece = "B";
		return colorPiece;
	}
	
	/**
	 * Obtains the chess piece type of chess piece.
	 * 
	 * @param ChessPieceType type The type of chess piece.
	 * @return A letter that represents the type of the chess piece (for instance, "B" for
	 * bishop).
	 */
	private String chessPieceName(ChessPieceType type) {
		String playerPiece = "";
		if(type == ChessPieceType.KING) playerPiece = "K";
		else if(type == ChessPieceType.QUEEN) playerPiece = "Q";
		else if(type == ChessPieceType.BISHOP) playerPiece = "B";
		else if(type == ChessPieceType.KNIGHT) playerPiece = "N";
		else if(type == ChessPieceType.ROOK) playerPiece = "R";
		else if(type == ChessPieceType.PAWN) playerPiece = "P";
		return playerPiece;
	}
	
	/**
	 * Disables clicking on a chess square (used for display or when a game of chess is over).
	 */
	public void disable() {
		this.setDisable(true);
	}

	/**
	 * Returns the chess square that's currently on the square.
	 */
	public ChessView getChessView(){
		return chessPiece;
	}

	/**
	 * Sets a chess piece on the square.
	 * 
	 * @param ChessView view The chess piece that's being placed on the square.
	 */
	public void setChessView(ChessView view){
		this.chessPiece = view;
	}
	
}
