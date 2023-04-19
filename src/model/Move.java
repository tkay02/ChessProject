package src.model;

import src.interfaces.PieceIF;

/**
 * This class represents a move that a player makes, and holds all the necessary data to
 * undo and redo a move during the game.
 */
public class Move {
    
    /** Position that a piece starts on **/
    private Position from;

    /** Position that a piece ends up on **/
    private Position to;

    /** Piece that the moving piece captures, if applicable **/
    private PieceIF piece;

    public Move(Position from, Position to, PieceIF piece){
        this.from = from;
        this.to = to;
        this.piece = piece;
    }

    public Position getFromPos(){
        return this.from;
    }

    public Position getToPos(){
        return this.to;
    }

    public PieceIF getPiece(){
        return this.piece;
    }

    /**
     * TESTING
     */
    public String toString(){
        String toReturn = "from: " + from.getFile() + from.getRank() + " to: " +
         to.getFile() + to.getRank() + " piece: " + piece.getChessPieceType();
        return toReturn;
    }

}
