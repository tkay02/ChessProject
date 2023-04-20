package src.model;

import src.enums.*;
import src.interfaces.PieceIF;
import src.interfaces.SquareIF;

/**
 * Class that represents a square object for ChessMeister.
 * 
 * @author Nolan Flinchum(80%), Thomas Kay (20%), Joseph Oladeji, Levi Sweat 
 * @version 3/27/2023
 */
public class Square extends BlackAndWhite implements SquareIF {
    
    /** Represents the current piece on the square **/
    private PieceIF piece;

    /** Position of the square **/
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
        setPiece(new Piece(ChessPieceType.EMPTY, GameColor.WHITE));
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
	 * Gets the the position object of the chess board square.
	 *
	 * @return A Position object representing the chosen chess board position.
	 */
    public Position getPosition(){
        return this.pos;
    }

}
