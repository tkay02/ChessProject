package src.model;

import src.interfaces.PieceIF;

/**
 * This class represents a move that a player makes, and holds all the necessary data to
 * undo and redo a move during the game.
 * @author Nolan Flinchum (100%), Thomas Kay, Joseph Oladeji, Levi Sweat
 * @version 3/27/2023
 */
public class Move {
    
    /** Position that a piece starts on **/
    private Position from;

    /** Position that a piece ends up on **/
    private Position to;

    /** Piece that the moving piece captures, if applicable **/
    private PieceIF piece;

    /**
     * Constructs a new Move object with the given from and to positions and chess piece.
     *
     * @param from The starting position of the chess move.
     * @param to The ending position of the chess move.
     * @param piece The chess piece being moved.
     */
    public Move(Position from, Position to, PieceIF piece){
        this.from = from;
        this.to = to;
        this.piece = piece;
    }

   /**
    * Returns the Position object representing the from position of a chess move.
    *
    * @return A Position object representing the from position of a chess move.
    */
    public Position getFromPos(){
        return this.from;
    }

   /**
    * Returns the Position object representing the to position of a chess move.
    *
    * @return A Position object representing the to position of a chess move.
    */
    public Position getToPos(){
        return this.to;
    }

    /**
     * Returns the PieceIF object representing a chess piece in a move.
     *
     * @return A PieceIF object representing a chess piece in a move.
     */
    public PieceIF getPiece(){
        return this.piece;
    }

    /**
     * Compares a Move object to another Move object for equality.
     *
     * @param other The Move object to compare to.
     * @return True if the two Move objects are equal (i.e. have the same 'from' and 'to' positions), false otherwise.
     */
    public boolean equals(Move other){
        boolean result = false;
        if(this.from.equals(other.getFromPos()) && this.to.equals(other.getToPos())) result = true;
        return result;
    }

}
