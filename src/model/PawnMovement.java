package src.model;
/**
 * Class for the movement strategy of a pawn.
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
     * Constructor for the pawn's movement strategy.
     * 
     * @param board the chess board reference
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
            if(row - 1 >= 0){
                if(board.getPiece(row - 1, col).getChessPieceType() == ChessPieceType.EMPTY){
                    validMoves.add(new Position(Rank.getRankByIndex(row - 1), start.getFile()));
                    if(!piece.hasMoved() && board.getPiece(row - 2, col).getChessPieceType() == ChessPieceType.EMPTY){
                        validMoves.add(new Position(Rank.getRankByIndex(row - 2), start.getFile()));
                    }
                }
                if(col - 1 >= 0){
                    if(((Piece) board.getPiece(row - 1, col - 1)).isBlack()){
                        validMoves.add(new Position(Rank.getRankByIndex(row - 1), File.getFileByIndex(col - 1)));
                    }
                }
                if(col + 1 < board.getWidth()){
                    if(((Piece) board.getPiece(row - 1, col + 1)).isBlack()){
                        validMoves.add(new Position(Rank.getRankByIndex(row - 1), File.getFileByIndex(col + 1)));
                    }
                }
            }
        }
        else{
            if(row + 1 < board.getHeight()){
                if(board.getPiece(row + 1, col).getChessPieceType() == ChessPieceType.EMPTY){
                    validMoves.add(new Position(Rank.getRankByIndex(row + 1), start.getFile()));
                    if(!piece.hasMoved() && board.getPiece(row + 2, col).getChessPieceType() == ChessPieceType.EMPTY){
                        validMoves.add(new Position(Rank.getRankByIndex(row + 2), start.getFile()));
                    }
                }
                if(col - 1 >= 0){
                    if(((Piece) board.getPiece(row + 1, col - 1)).isWhite()){
                        validMoves.add(new Position(Rank.getRankByIndex(row + 1), File.getFileByIndex(col - 1)));
                    }
                }
                if(col + 1 < board.getWidth()){
                    if(((Piece) board.getPiece(row + 1, col + 1)).isWhite()){
                        validMoves.add(new Position(Rank.getRankByIndex(row + 1), File.getFileByIndex(col + 1)));
                    }
                }
            }
        }
    }

    /**
     * Determines if the move the player makes is valid.
     * 
     * @param to position of square to move to
     * @return true if the move is valid, false otherwise
     */
    public boolean validateMove(Position to){
        boolean isContained = false;
        for(Position pos : validMoves){
            if(pos.equals(to)) isContained = true;
        }
        return isContained;
    }

    /**
     * Show all valid moves of a piece at a given position.
     * 
     * @param pos The position of a piece that wants to move
     * @return An array of possible moves
     */
    public ArrayList<Position> showMoves(Position pos){
        generateValidMoves(pos);
        return this.validMoves;
    }

}