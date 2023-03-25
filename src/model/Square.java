package src.model;
/**
 * Class that represents a square object for ChessMeister.
 * 
 * @author Nolan Flinchum, Thomas Kay, Joseph Oladeji, Levi Sweat
 * @version 3/27/2023
 */
import src.enums.*;
import src.interfaces.PieceIF;
import src.interfaces.SquareIF;

public class Square extends BlackAndWhite implements SquareIF {
    
    /* Represents the current piece on the square */
    private PieceIF piece;

    /* Position of the square */
    private Position pos;

    /**
     * Constructor for the square on a chess board.
     * 
     * @param rank rank of the square's position
     * @param file file of the square's position
     * @param color color of the square
     */
    public Square(int rank, int file, GameColor color){
        super(color);
        this.pos = new Position(Rank.values()[rank], File.values()[file]);
    }

	/**
	 * Clears any pieces off of the square.
	 */
    public void clear(){
        this.piece = null;
    }

    /**
     * Gets the current piece on the square object.
     * 
     * @return the current piece on the square
     */
    public PieceIF getPiece(){
        return this.piece;
    }

    /**
     * Sets a piece on the square object.
     * 
     * @param piece the piece to be placed on the square
     */
    public void setPiece(PieceIF piece){
        this.piece = piece;
    }

    /**
     * Getter for the position of the square.
     * May not need
     * 
     * @return the position of the square
     */
    public Position getPosition(){
        return this.pos;
    }

}
