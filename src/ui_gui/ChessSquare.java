package src.ui_gui;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import src.enums.GameColor;
import src.model.Piece;
import src.enums.ChessPieceType;

public class ChessSquare extends Pane {
	
	/**Square is black or white**/
	boolean isWhite;
	/**Potential chess piece on square**/
	ImageView chessPiece;
	/**Name of the piece on the square**/
	String name;
	
	/**
	 * Constructor for ChessSquare.
	 * 
	 * @param boolean isWhite Boolean that determines if the square is a white square or a black
	 * square.
	 * @param Piece piece The chess piece on the square.
	 */
	public ChessSquare(boolean isWhite, Piece piece) {
		super();
		//Size of the square
		this.setSize(64.0, 64.0);
		//Sets square color
		this.isWhite = isWhite;
		this.setSquareColor();
		this.name = "";
		this.chessPiece = new ImageView();
		this.setPiece(piece);
		this.getChildren().add(this.chessPiece);
		
		//Move this chess grid?
		this.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				ChessSquare sq = (ChessSquare)e.getTarget();
				if(sq.getId().equals("WhiteSquare") || sq.getId().equals("BlackSquare")) {
					if(sq.chessPiece.getImage() != null) {
						sq.setId("SelectedSquare");
						ChessBoardGUI.updateCurrentChessPiece(sq);
					}
					else {
						ChessBoardGUI.updateCurrentChessPiece(sq);
					}
				}
				else if(sq.getId().equals("SelectedSquare")) {
					sq.setSquareColor();
					ChessBoardGUI.updateCurrentChessPiece(sq);
				}
				else if(sq.getId().equals("ValidSquare")) {
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
		if(this.isWhite) this.setId("WhiteSquare");
		else this.setId("BlackSquare");
	}
	
	/*t	 */
	public void setSelectedColor() {
		this.setId("SelectedSquare");
	}
	
	/**
	 * Sets the color of the square when part of valid moves.
	 */
	public void setValidColor() {
		this.setId("ValidSquare");
	}
	
	/**
	 * Sets the piece on the square. If the piece entered as a chess piece type of "EMPTY", then
	 * clears the chess piece off of the square.
	 * 
	 * @param Piece piece The chess piece on the square with its unique color and chess piece
	 * type.
	 */
	public void setPiece(Piece piece) {
		String imageName = "";
		//Clears image if chess piece type of piece is "EMPTY"
		if(piece.getChessPieceType() == ChessPieceType.EMPTY) {
			this.chessPiece.setImage(null);
			this.name = "";
		}
		else {
			imageName += chessPieceColor(piece.getColor());
			imageName += chessPieceName(piece.getChessPieceType());
			//Uses Driver class
			Image image = new Image(SmallTester.class.getResourceAsStream(imageName += ".png"));
			this.chessPiece.setImage(image);
			this.chessPiece.fitWidthProperty().bind(this.widthProperty());
			this.chessPiece.fitHeightProperty().bind(this.heightProperty());
		}
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
		if(type == ChessPieceType.KING) {
			playerPiece = "K";
			this.name = "King";
		}
		else if(type == ChessPieceType.QUEEN) {
			playerPiece = "Q";
			this.name = "Queen";
		}
		else if(type == ChessPieceType.BISHOP) {
			playerPiece = "B";
			this.name = "Bishop";
		}
		else if(type == ChessPieceType.KNIGHT) {
			playerPiece = "N";
			this.name = "Knight";
		}
		else if(type == ChessPieceType.ROOK) {
			playerPiece = "R";
			this.name = "Rook";
		}
		else if(type == ChessPieceType.PAWN) {
			playerPiece = "P";
			this.name = "Pawn";
		}
		return playerPiece;
	}
	
	/**
	 * Returns the name of the current piece.
	 */
	public String getName() {
		return this.name;
	}
}
