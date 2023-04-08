package src.movement;

import src.model.Board;
import src.model.Piece;
import src.model.Position;

/**
 * Movement Stragety for the Knight Piece
 * 
 * @author Nolan Flinchum, Thomas Kay (80%), Joseph Oladeji (20%), Levi Sweat
 * @version 3/27/2023
 */

public class KnightMovement extends MovementStrategy {

    /**
     * Constructor for KnightMovement.
     * 
     * @param Board board The board that KnightMovement is using for refrence.
     * @param GameColor color The current color of the Knight for reference.
     */
    public KnightMovement(Board board) {
        super(board);
    }

    /**
     * Determines the possible valid moves for the Knight in its current position. Checks if the
     * new possible position is neither out of bounds or is an ally space. If the move is valid,
     * it's added to the list that stores the valid moves.
     * 
     * @param Position from The current position of the Knight piece.
     */
    public void generateValidMoves(Position from) {
        validMoves.clear(); // Resets valid moves list
        int row = from.getRank().getArrayRank();
        int col = from.getFile().getArrayFile();
        Piece currentPiece = (Piece) board.getPiece(row, col);
        validPosition(currentPiece, row - 1, col - 2);
        validPosition(currentPiece, row - 1, col + 2);
        validPosition(currentPiece, row + 1, col - 2);
        validPosition(currentPiece, row + 1, col + 2);
        validPosition(currentPiece, row + 2, col + 1);
        validPosition(currentPiece, row + 2, col - 1);
        validPosition(currentPiece, row - 2, col + 1);
        validPosition(currentPiece, row - 2, col - 1);
    }

}
