package src.movement;

import src.model.Board;
import src.model.Piece;
import src.model.Position;

import java.util.ArrayList;
import src.enums.ChessPieceType;
import src.enums.Rank;
import src.enums.File;

/**
 * This class models the movement of a pawn and examines all the possible 
 * movements a pawn can make given the row and column of the pawn.
 * 
 * @author Nolan Flinchum (100%), Thomas Kay, Joseph Oladeji, Levi Sweat
 * @version 3/27/2023
 */

public class PawnMovement extends MovementStrategy {
    
    /**
     * Constructor for the pawn's movement strategy.
     * 
     * @param board - the chess board reference
     */
    public PawnMovement(Board board){
        super(board);
    }

    /**
     * Generate all the valid moves of a piece from a current spot,
     * and use it to populate the valid moves array.
     * 
     * @param start - the position we're moving from
     */
    public void generateValidMoves(Position start){
        validMoves.clear();
        // Get the row of the starting position
        int row = start.getRank().getArrayRank();
        // Get the column of the starting position
        int col = start.getFile().getArrayFile();
        // Grab the piece at the specified row and column on the board
        Piece piece = (Piece) board.getPiece(row, col);
        // If the piece is White decrement the row, so the piece moves downwards
        // Otherwise increment the row so the piece goes upwards if it's black
        if(piece.isWhite() && row - 1 >= 0) validPosition(piece, row - 1, col, -1, start);
        else if(row + 1 < board.getHeight()) validPosition(piece, row + 1, col, 1, start);
    }

    
    /**
     * This method will take a current piece, row, and column and attempt to move the piece to the
     * specified row and column. If the piece is able to be moved it will be added to the list of
     * moves, then set the boolean value (valid) to true or false whether it's possible
     * to move again. It will later return the boolean value.
     * 
     * @param currentPiece - The currentPiece that will be attempted to move.
     * from its original position to the specified row and column in the parameter.
     * @param row - The row location that the currentPiece may be moved to.
     * @param col - The column location that the currentPiece may be moved to.
     * @param dir - The direction in which the row will be moved, if it is 1 then down,
     * if it is -1 then it is up.
     * @param start - the position we're moving from.
     */
    private void validPosition(Piece currentPiece, int row, int col, int dir, Position start){

        // If the square at the specified row and column is empty then add it to the
        // list of possible moves
        if(board.getPiece(row, col).getChessPieceType() == ChessPieceType.EMPTY){
            validMoves.add(new Position(Rank.getRankByIndex(row), start.getFile()));
            // If the square infront of the previous checked square is empty, then add it
            // to the list of possible moves.
            if(!currentPiece.hasMoved() && board.getPiece(row + dir, col).getChessPieceType() == 
            ChessPieceType.EMPTY)
                validMoves.add(new Position(Rank.getRankByIndex(row + dir), start.getFile()));
        }

        // If the piece diagnol to the current pawn is a piece of the opposite color, 
        // and the current column minus 1 is greater than or equal to the first column
        // and not an empty square, then add it to the list of possible moves
        if(col - 1 >= 0 && ((Piece) board.getPiece(row, col - 1)).getColor() != currentPiece.getColor() && 
        ((Piece) board.getPiece(row, col - 1)).getChessPieceType() != ChessPieceType.EMPTY)
            validMoves.add(new Position(Rank.getRankByIndex(row), File.getFileByIndex(col - 1)));

        // If the piece diag7nol to the current pawn is a piece of the opposite color
        // and the current column plus 1 is less than the last column
        // and not an empty square, then add it to the list of possible moves
        if(col + 1 < board.getWidth() && ((Piece) board.getPiece(row, col + 1)).getColor() 
        != currentPiece.getColor() && ((Piece) board.getPiece(row, col + 1)).getChessPieceType() 
        != ChessPieceType.EMPTY)
            validMoves.add(new Position(Rank.getRankByIndex(row), File.getFileByIndex(col + 1)));

    }

}