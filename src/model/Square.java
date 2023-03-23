package src.model;
/**
 * Class that represents a square object for ChessMeister.
 * 
 * @author A-Team
 * @version 3/23/23
 */
import src.enums.GameColor;
import src.interfaces.PieceIF;
import src.interfaces.SquareIF;

public class Square extends BlackAndWhite implements SquareIF {
    
    /* Represents the current piece on the square */
    private PieceIF piece;

    /* Position of the square */
    private Position pos;

    public Square(int rank, int file, GameColor color){
        super(color);
        this.pos = new Position(rank, file);
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

}
