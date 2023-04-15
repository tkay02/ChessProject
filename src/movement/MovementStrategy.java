package src.movement;
import src.enums.ChessPieceType;
import src.enums.File;
import src.enums.Rank;
import src.model.Board;
import src.model.Piece;
import src.model.Position;
import java.util.ArrayList;
/**
 * Interface for the way that the chess board is drawn in regards to color.
 *
 * @author Nolan Flinchum (100%), Thomas Kay, Joseph Oladeji, Levi Sweat
 * @version 3/27/2023
 */

public abstract class MovementStrategy {

     /** Array of valid moves for a selected piece **/
     public static ArrayList<Position> validMoves;

     /** Alias of the chess board used to generate valid moves **/
     public static Board board;
    
    /**
     * Constructor for any movement strategy.
     * 
     * @param board the chess board reference
     */
    public MovementStrategy(Board board){
        MovementStrategy.board = board;
        MovementStrategy.validMoves = new ArrayList<>();
    }

    /**
     * Generate all the valid moves of a piece from a current spot,
     * and use it to populate the valid moves array.
     * 
     * @param start the position we're moving from
     */
    public abstract void generateValidMoves(Position start);

    /**
     * Determines if the move the player makes is valid.
     * 
     * @param to - position of square to move to
     * @return true if the move is valid, false otherwise
     */
    public boolean validateMove(Position from, Position to){
        generateValidMoves(from);
        boolean isContained = false;
        for(Position pos : validMoves) if(pos.equals(to)) isContained = true;
        return isContained;
    }

    /**
     * Show all valid moves of a piece at a given position.
     * 
     * @param pos - The position of a piece that wants to move
     * @return an array of possible moves
     */
    public ArrayList<Position> showMoves(Position pos){
        generateValidMoves(pos);
        return validMoves;
    }
    
    /**
     * This method will take a current piece, row, and column and attempt to move the piece to the
     * specified row and column. If the piece is able to be moved it will be added to the list of
     * moves, then set the boolean value (valid) to true or false whether it's possible
     * to move again. It will later return the boolean value.
     * 
     * @param row - The row location that the currentPiece may be moved to
     * @param col - The column location that the currentPiece may be moved to
     * @param currentPiece - The currentPiece that we will be attempt to move
     */
    public boolean validPosition(Piece currentPiece, int row, int col, Position fromPos){
        boolean valid = false;
        if(row < board.getHeight() && row >= 0 && col >= 0 && col < board.getWidth()){
            Piece otherPiece = (Piece) board.getPiece(row, col);
            if(otherPiece.getChessPieceType() == ChessPieceType.EMPTY){
                if(tryMove(currentPiece, row, col, fromPos)){
                    validMoves.add(new Position(Rank.getRankByIndex(row), File.getFileByIndex(col)));
                    valid = true;
                }
            }else if(otherPiece.getColor() != currentPiece.getColor()){
                if(tryMove(currentPiece, row, col, fromPos))
                    validMoves.add(new Position(Rank.getRankByIndex(row), File.getFileByIndex(col)));
            }
        }
        return valid;
    } 

    /**
     * Will attempt to make the move by calling the tryMove method in Board.java
     * using the board reference in this class.
     * 
     * @param currentPiece the piece to be moved
     * @param row number representing the rank position in the 2D array
     * @param col number representing the file position in the 2d array
     * @param fromPos the position the moving piece starts on
     * @return true if the move attempted was valid, false otherwise
     */
    public boolean tryMove(Piece currentPiece, int row, int col, Position fromPos){
        return board.tryMove(currentPiece, row, col, fromPos);
    }
}
