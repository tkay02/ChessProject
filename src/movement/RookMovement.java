package src.movement;

import src.model.Board;
import src.model.Piece;
import src.model.Position;

/**
 * Class for the movement strategy of a rook.
 * 
 * @author Nolan Flinchum (50%), Thomas Kay, Joseph Oladeji (50%), Levi Sweat
 * @version 3/27/2023
 */

public class RookMovement extends MovementStrategy{
 


    /**
     * Constructor for the rook's movement strategy.
     * 
     * @param board the chess board reference
     */
    public RookMovement(Board board){
        super(board);
    }

    /**
     * Generate all the valid moves of a piece from a current spot,
     * and use it to populate the valid moves array.
     * 
     * @param from the position we're moving from
     */
    public void generateValidMoves(Position start) {
        validMoves.clear();
        int row = start.getRank().getArrayRank();
        int col = start.getFile().getArrayFile();
        Piece currentPiece = (Piece) board.getPiece(row, col);
        boolean up = true, down = true, left = true, right = true;
        for(int i = 1; i < board.getWidth(); i++){
            if(up) up = validPosition(currentPiece, row - i, col);
            if(down) down = validPosition(currentPiece, row + i, col);
            if(left) left = validPosition(currentPiece, row, col - i);
            if(right) right = validPosition(currentPiece, row, col + i);              
        }
    }

}
