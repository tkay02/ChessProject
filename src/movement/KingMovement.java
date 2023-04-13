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
     * @param start the position we're moving from
     */
    public void generateValidMoves(Position start) {
        validMoves.clear();
        int row = start.getRank().getArrayRank();
        int col = start.getFile().getArrayFile();
        Piece currentPiece = (Piece) board.getPiece(row, col);
        validPosition(currentPiece, row - 1, col, start);
        validPosition(currentPiece, row + 1, col, start);
        validPosition(currentPiece, row, col - 1, start);
        validPosition(currentPiece, row, col + 1, start);
        validPosition(currentPiece, row - 1, col + 1, start);
        validPosition(currentPiece, row - 1, col - 1, start);
        validPosition(currentPiece, row + 1, col - 1, start);
        validPosition(currentPiece, row + 1, col + 1, start);                
    }
}