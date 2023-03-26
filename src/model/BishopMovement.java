package src.model;
/**
 * Class for the movement strategy of a bishop.
 * 
 * @author Nolan Flinchum, Thomas Kay, Joseph Oladeji, Levi Sweat
 * @version 3/27/2023
 */
import java.util.ArrayList;
import src.enums.ChessPieceType;
import src.enums.File;
import src.enums.Rank;
import src.interfaces.MovementStrategy;

public class BishopMovement implements MovementStrategy {
    
    /* Array of valid moves for a selected piece */
    private ArrayList<Position> validMoves;

    /* Alias of the chess board used to generate valid moves  */
    private Board board;

    /**
     * Constructor for the bishop's movement strategy.
     * 
     * @param board the chess board reference
     */
    public BishopMovement(Board board){
        this.board = board;
        this.validMoves = new ArrayList<>();
    }

    /**
     * Generate all the valid moves of a piece from a current spot,
     * and use it to populate the valid moves array.
     * 
     * @param start the position we're moving from
     */
    public void generateValidMoves(Position start) {
        this.validMoves.clear();
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
        if(row < board.getHeight() && row > 0 && col > 0 && col < board.getWidth()){
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
     * This method will generate a list of valid moves for the from position
     * and then return a boolean value whether or not the list of valid moves
     * contains the to position.
     * 
     * @param to - Desired ending position to be added
     */
    public boolean validateMove(Position to) {
        return contains(to);
    }

    /**
     * This method will generate a list of valid moves for the from position
     * and then return a boolean value whether or not the list of valid moves
     * contains the to position.
     * 
     * @param pos - List of valid position the current piece\e can move from.
     */
    public ArrayList<Position> showMoves(Position pos) {
        generateValidMoves(pos);
        return this.validMoves;
    }

    public boolean contains(Position otherPos){
        boolean isContained = false;
        for(Position pos : validMoves){
            if(pos.equals(otherPos)){
                isContained = true;
            }
        }
        return isContained;
    }

}
