package src.model;
/**
 * 
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
     * 
     * 
     * @param board
     */
    public BishopMovement(Board board){
        this.board = board;
        this.validMoves = new ArrayList<>();
    }

    /**
     * Generate all the valid moves of a piece from a current spot,
     * and use it to populate the valid moves array.
     * 
     * @param from the position we're moving from
     */
    public void generateValidMoves(Position start) {
        int row = start.getRank().getArrayRank();
        int col = start.getFile().getArrayFile();
        Piece currentPiece = (Piece) board.getPiece(row, col);
        boolean upRight = true, upLeft = true, downLeft = true, downRight = true;
        for(int i = 0; i < board.getWidth(); i++){
            if(upRight) upRight = validPosition(currentPiece, row - i, col + i);
            if(upLeft) upLeft = validPosition(currentPiece, row - i, col - i);
            if(downLeft) downLeft = validPosition(currentPiece, row + i, col - i);
            if(downRight) downRight = validPosition(currentPiece, row + i, col + i);                
        }
    }

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

    public boolean validateMove(Position from, Position to) {
        generateValidMoves(from);
        return this.validMoves.contains(to);
    }

    public ArrayList<Position> showMoves(Position pos) {
        return this.validMoves;
    }

}
