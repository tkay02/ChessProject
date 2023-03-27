package src.model;
/**
 * Movement Stragety for the Knight Piece
 * 
 * @author Nolan Flinchum, Thomas Kay, Joseph Oladeji, Levi Sweat
 * @version 3/27/2023
 */
import src.interfaces.*;
import java.util.ArrayList;
import src.enums.*;

public class KnightMovement implements MovementStrategy {
    
    /**The maximum length for each side of the chess board*/
    public static final int MAX = 8;

    /**Array of valid moves for a selected piece*/
    private ArrayList<Position> validMoves;

    /**The chess board to be used as referenced to generate valid moves*/
    private Board board;

    /**
     * Constructor for KnightMovement.
     * 
     * @param Board board The board that KnightMovement is using for refrence.
     * @param GameColor color The current color of the Knight for reference.
     */
    public KnightMovement(Board board) {
        
        this.board = board;
        this.validMoves = new ArrayList<Position>();
    }

    /**
     * Determines the possible valid moves for the Knight in its current position. Checks if the new possible
     * position is neither out of bounds or is an ally space. If the move is valid, it's added to the list that
     * stores the valid moves.
     * 
     * @param Position from The current position of the Knight piece.
     */
    public void generateValidMoves(Position from) {

        // Resets valid moves list when starting a new position
        this.validMoves.clear();
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

     /**
     * This method will take a current piece, row, and column and attempt to move the piece to the
     * specified row and column. If the piece is able to be moved it will be added to the list of
     * moves, then set the boolean value (valid) to true or false whether it's possible
     * to move again. It will later return the boolean value.
     * 
     * @param row - The row location that the currentPiece may be moved to
     * @param col - The column location that the currentPiece may be moved to
     * @param currentPiece - The currentPiece that will be attempted to move
     * from its original position to the specified row and column in the parameter.
     */
    private boolean validPosition(Piece currentPiece, int row, int col){
        boolean valid = false;
        if(row < board.getHeight() && row >= 0 && col >= 0 && col < board.getWidth()){
            Piece otherPiece = (Piece) board.getPiece(row, col);
            if(otherPiece.getChessPieceType() == ChessPieceType.EMPTY){
                validMoves.add(new Position(Rank.getRankByIndex(row), File.getFileByIndex(col)));
                valid = true;
            }else if(otherPiece.getColor() != currentPiece.getColor()){
                validMoves.add(new Position(Rank.getRankByIndex(row), File.getFileByIndex(col)));
            }
        }
        return valid;
    }

    /**
     * Checks if the move is valid from its current position to its new position.
     * 
     * @param Position from The original position of the Knight.
     * @param Position to The new possible position of the Knight.
     * @return True if the new position of the Knight is valid; false otherwise.
     */
    public boolean validateMove(Position to) {
        boolean isContained = false;
        for(Position pos : validMoves){
            if(pos.equals(to)){
                isContained = true;
            }
        }
        return isContained;
    }

    /**
     * Creates an array that stores all of the possible valid positions from the
     * Knight's current position.
     * 
     * @param Position pos The current position of the Knight.
     * @return An array that stores all of the possible positions.
     */
    public ArrayList<Position> showMoves(Position pos) {
        this.generateValidMoves(pos);
        return this.validMoves;
    }


 

}
