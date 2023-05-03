package src.model;


import src.interfaces.PieceIF;
import src.model.movement.MovementStrategy;

import java.util.ArrayList;

import src.enums.ChessPieceType;
import src.enums.GameColor;

/**
 * Class that represents a piece object for ChessMeister.
 * 
 * @author Nolan Flinchum (100%), Thomas Kay, Joseph Oladeji, Levi Sweat
 * @version 3/27/2023
 */

public class Piece extends BlackAndWhite implements PieceIF {

    /** Represents the type of a chess piece **/
    private ChessPieceType pieceType;

    /** Boolean for if a piece has moved or not **/
    private int moveCount;

    /** Strategy for the pieces movement **/
    private MovementStrategy moveStrat;

    /**
     * Constructor for a piece object.
     * 
     * @param pieceType the type of chess piece
     */
    public Piece(ChessPieceType pieceType, GameColor color){
        super(color);
        this.pieceType = pieceType;
        this.moveCount = 0;
    }

    /**
     * Sets the moveStrategy based on the user's input.
     * 
     * @param moveStrat user's input moveStrategy
     */
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
     * Show all valid moves of a piece at a given position.
     * 
     * @param pos The position of a piece that wants to move
     * @return An array of possible moves
     */
    public ArrayList<Position> showMoves(Position pos){
        return moveStrat.showMoves(pos);
    }

    /**
     * This method returns a boolean true if the piece has been moved, false 
     * otherwise.
     * @return hasMoved - A boolean that represents whether or not the
     * piece has been moved.
     */
    public boolean hasNotMoved(){
        return this.moveCount == 0;
    }

    /**
     * Increases the move count of a piece by one.
     */
    public void incMoveCount(){
        this.moveCount++;
    }

    /**
     * Decreases the move count of a piece by one.
     */
    public void decMoveCount(){
        this.moveCount--;
    }
}