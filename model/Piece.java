package model;
import interfaces.PieceIF;
/**
 * Class that represents a piece object for ChessMeister.
 * 
 * @author A-Team
 * @version 3/20/23
 */
import interfaces.PieceIF;
import enums.ChessPieceType;
import enums.GameColor;

public class Piece extends BlackAndWhite implements PieceIF {

    /* Represents the type of a chess piece */
    private ChessPieceType pieceType;

    /* Boolean for if a piece has moved or not */
    private boolean hasMoved;

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
}
