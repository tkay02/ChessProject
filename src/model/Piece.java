package src.model;
/**
 * Class that represents a piece object for ChessMeister.
 * 
 * @author Nolan Flinchum, Thomas Kay, Joseph Oladeji, Levi Sweat
 * @version 3/27/2023
 */
import src.interfaces.PieceIF;
import src.interfaces.MovementStrategy;
import src.enums.ChessPieceType;
import src.enums.GameColor;

public class Piece extends BlackAndWhite implements PieceIF {

    /* Represents the type of a chess piece */
    private ChessPieceType pieceType;

    /* Boolean for if a piece has moved or not */
    private boolean hasMoved;

    /* Strategy for the pieces movement */
    private MovementStrategy moveStrat;

    /**
     * Constructor for a piece object.
     * 
     * @param pieceType the type of chess piece
     */
    public Piece(ChessPieceType pieceType, GameColor color){
        super(color);
        this.pieceType = pieceType;
        this.hasMoved = false;
    }

    public void setMoveStrategy(MovementStrategy moveStrat){
        this.moveStrat = moveStrat;
    }
    /**
     * Getter for the type of a chess piece.
     * 
     * @return the type of chess piece
     */
    public ChessPieceType getChessPieceType(){
        return this.pieceType;
    }

    /**
     * Setter for the type of a chess piece.
     * 
     * @param pieceType the type of chess piece.
     */
    public void setChessPieceType(ChessPieceType pieceType){
        this.pieceType = pieceType;
    }

    /**
     * Determines if the move the player makes is valid.
     * 
     * @param from position of square to move from
     * @param to position of square to move to
     * @return true if the move is valid, false otherwise
     */
    public boolean validateMove(Position from, Position to){
        return moveStrat.validateMove(from, to);
    }

    /**
     * This method returns a boolean true if the piece has been moved, false 
     * otherwise.
     * @return hasMoved - A boolean that represents whether or not the
     * piece has been moved.
     */
    public boolean hasMoved(){
        return hasMoved;
    }
    
}