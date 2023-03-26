package src.interfaces;
/**
 * Interface for the way that the chess board is drawn in regards to color.
 *
 * @author Nolan Flinchum, Thomas Kay, Joseph Oladeji, Levi Sweat
 * @version 3/27/2023
 */
import src.model.Position;
import java.util.ArrayList;

public interface MovementStrategy {
    
    /**
     * Generate all the valid moves of a piece from a current spot,
     * and use it to populate the valid moves array.
     * 
     * @param from the position we're moving from
     */
    public void generateValidMoves(Position start);

    /**
     * Determines if the move the player makes is valid.
     * 
     * @param from position of square to move from
     * @param to position of square to move to
     * @return true if the move is valid, false otherwise
     */
    public boolean validateMove(Position from, Position to);

    /**
     * Show all valid moves of a piece at a given position.
     * 
     * @param pos The position of a piece that wants to move
     * @return An array of possible moves
     */
    public ArrayList<Position> showMoves(Position pos);
    

}
