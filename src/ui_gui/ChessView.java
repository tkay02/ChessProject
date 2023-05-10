package src.ui_gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * ChessView (Chess Piece Image) used for the boardGUI.
 * 
 * @author Nolan Flinchum, Thomas Kay, Joseph Oladeji, Levi Sweat
 * @version 5/9/2023
 */
public class ChessView extends ImageView {

    /**Points to the current square that the chess piece is on**/
	public ChessSquare currentSquare;

    /**Max size of the chess image**/
	public final int MAX_SIZE = 64;
    
    /**
     * Constructor for the chess view.
     */
	public ChessView(){
        super();
    }

    /**
     * Constructor for the chess view.
     * 
     * @param Image view The image that displays the chess piece.
     */
	public ChessView(Image view){
        super(view);
        this.setFitHeight(MAX_SIZE);
        this.setFitWidth(MAX_SIZE);
    }

    /**
     * Sets the current square of the chess view.
     * 
     * @param square The square that the chess piece is on.
     */
	public void setChessSquare(ChessSquare square){
        this.currentSquare = square;
    }

    /**
     * Returns the chess square that the chess piece is currently on.
     */
	public ChessSquare getChessSquare(){
        return currentSquare;
    }

    /**
     * Creates a copy of the current chess view.
     * 
     * @return A clone of the chess view.
     */
	public ChessView getCopy(){
        ChessView imgCopy = new ChessView(getImage());
        imgCopy.fitWidthProperty().bind(currentSquare.widthProperty());
        imgCopy.fitHeightProperty().bind(currentSquare.heightProperty());
        return imgCopy;
    }
}
