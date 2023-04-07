package src.movement;

import src.model.Board;
import src.model.Piece;
import src.model.Position;

/**
 * Class for the movement strategy of a bishop.
 * 
 * @author Nolan Flinchum (50%), Thomas Kay, Joseph Oladeji (50%), Levi Sweat
 * @version 3/27/2023
 */

public class BishopMovement extends MovementStrategy {
    


    /**
     * Constructor for the bishop's movement strategy.
     * 
     * @param board the chess board reference
     */
    public BishopMovement(Board board){
        super(board);
    }

    /**
     * Generate all the valid moves of a piece from a current spot,
     * and use it to populate the valid moves array.
     * 
     * @param start the position we're moving from
     */
    public void generateValidMoves(Position start) {
        validMoves.clear();
        int row = start.getRank().getArrayRank();
        int col = start.getFile().getArrayFile();
        Piece currentPiece = (Piece) board.getPiece(row, col);
        boolean upRight = true, upLeft = true, downLeft = true, downRight = true;
        for(int i = 1; i < board.getWidth(); i++){
            if(upRight) upRight = validPosition(currentPiece, row - i, col + i);
            if(upLeft) upLeft = validPosition(currentPiece, row - i, col - i);
            if(downLeft) downLeft = validPosition(currentPiece, row + i, col - i);
            if(downRight) downRight = validPosition(currentPiece, row + i, col + i);                
        }
    }

}
