package src.model;
/**
 * 
 * 
 * @author Nolan Flinchum, Thomas Kay, Joseph Oladeji, Levi Sweat
 * @version 3/27/2023
 */
import src.interfaces.MovementStrategy;
import java.util.ArrayList;
import src.enums.ChessPieceType;
import src.enums.Rank;
import src.enums.File;


public class PawnMovement implements MovementStrategy {
    
    /* Array of valid moves for a selected piece */
    private ArrayList<Position> validMoves;

    /* Alias of the chess board used to generate valid moves  */
    private Board board;

    /**
     * Constructor for the PawnMovement class.
     */
    public PawnMovement(Board board){
        this.board = board;
        this.validMoves = new ArrayList<>();
    }

    /**
     * Generate all the valid moves of a piece from a current spot,
     * and use it to populate the valid moves array.
     * 
     * @param from the position we're moving from
     */
    public void generateValidMoves(Position start){
        validMoves.clear();
        int row = start.getRank().getArrayRank();
        int col = start.getFile().getArrayFile();
        Piece piece = (Piece) board.getPiece(row, col);
        if(piece.isWhite()){
            if(!piece.hasMoved() && board.getPiece(row - 2, col).getChessPieceType() == ChessPieceType.EMPTY){
                validMoves.add(new Position(Rank.getRankByIndex(row - 2), start.getFile()));
            }
            if(board.getPiece(row - 1, col).getChessPieceType() == ChessPieceType.EMPTY){
                validMoves.add(new Position(Rank.getRankByIndex(row - 1), start.getFile()));
            }
            if(((Piece) board.getPiece(row - 1, col - 1)).isBlack()){
                validMoves.add(new Position(Rank.getRankByIndex(row - 1), File.getFileByIndex(col - 1)));
            }
            if(((Piece) board.getPiece(row - 1, col + 1)).isBlack()){
                validMoves.add(new Position(Rank.getRankByIndex(row - 1), File.getFileByIndex(col + 1)));
            }
        }
        else{
            if(!piece.hasMoved() && board.getPiece(row + 2, col).getChessPieceType() == ChessPieceType.EMPTY){
                validMoves.add(new Position(Rank.getRankByIndex(row + 2), start.getFile()));
            }
            if(board.getPiece(row + 1, col).getChessPieceType() == ChessPieceType.EMPTY){
                validMoves.add(new Position(Rank.getRankByIndex(row + 1), start.getFile()));
            }
            if(((Piece) board.getPiece(row + 1, col - 1)).isBlack()){
                validMoves.add(new Position(Rank.getRankByIndex(row + 1), File.getFileByIndex(col - 1)));
            }
            if(((Piece) board.getPiece(row + 1, col + 1)).isBlack()){
                validMoves.add(new Position(Rank.getRankByIndex(row + 1), File.getFileByIndex(col + 1)));
            }
        }
    }

    public boolean validateMove(Position from, Position to){
        generateValidMoves(from);
        return this.validMoves.contains(to);
    }

    public ArrayList<Position> showMoves(Position pos){
        return this.validMoves;
    }

}