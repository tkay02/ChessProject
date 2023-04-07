package src.movement;

import src.model.Board;
import src.model.Piece;
import src.model.Position;

/**
 * Class for the movement strategy of a king.
 * 
 * @author Nolan Flinchum (50%), Thomas Kay, Joseph Oladeji (50%), Levi Sweat
 * @version 3/27/2023
 */

public class KingMovement extends MovementStrategy {
  
    /**
     * Constructor for the king's movement strategy.
     * 
     * @param board the chess board reference
     */
    public KingMovement(Board board){
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
        boolean up = true, down = true, left = true, right = true, upRight = true,
                upLeft = true, downLeft = true, downRight = true;

        if(up) up = validPosition(currentPiece, row - 1, col);
        if(down) down = validPosition(currentPiece, row + 1, col);
        if(left) left = validPosition(currentPiece, row, col - 1);
        if(right) right = validPosition(currentPiece, row, col + 1);
        if(upRight) upRight = validPosition(currentPiece, row - 1, col + 1);
        if(upLeft) upLeft = validPosition(currentPiece, row - 1, col - 1);
        if(downLeft) downLeft = validPosition(currentPiece, row + 1, col - 1);
        if(downRight) downRight = validPosition(currentPiece, row + 1, col + 1);                
    }

}
