package src.model;
/**
 * Class for the movement strategy of a pawn.
 * 
 * @author Nolan Flinchum (100%), Thomas Kay, Joseph Oladeji, Levi Sweat
 * @version 3/27/2023
 */
import src.interfaces.MovementStrategy;
import java.util.ArrayList;
import src.enums.ChessPieceType;
import src.enums.Rank;
import src.enums.File;

public class PawnMovement implements MovementStrategy {
    
    /** Array of valid moves for a selected piece **/
    private ArrayList<Position> validMoves;

    /** Alias of the chess board used to generate valid moves **/
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
     * @param start the position we're moving from
     */
    public void generateValidMoves(Position start){
        validMoves.clear();
        int row = start.getRank().getArrayRank();
        int col = start.getFile().getArrayFile();
        Piece piece = (Piece) board.getPiece(row, col);

        if(piece.isWhite() && row - 1 >= 0) validPosition(piece, row - 1, col, -1, start);
        else if(row + 1 < board.getHeight()) validPosition(piece, row + 1, col, 1, start);
    }

    
    /**
     * This method will take a current piece, row, and column and attempt to move the piece to the
     * specified row and column. If the piece is able to be moved it will be added to the list of
     * moves, then set the boolean value (valid) to true or false whether it's possible
     * to move again. It will later return the boolean value.
     * 
     * @param currentPiece - The currentPiece that will be attempted to move.
     * from its original position to the specified row and column in the parameter.
     * @param row - The row location that the currentPiece may be moved to.
     * @param col - The column location that the currentPiece may be moved to.
     * @param dir - The direction in which the row will be moved, if it is 1 then down,
     * if it is -1 then it is up.
     * @param start - the position we're moving from.
     */
    private void validPosition(Piece currentPiece, int row, int col, int dir, Position start){
        boolean oneSpace = board.getPiece(row, col).getChessPieceType() == ChessPieceType.EMPTY;
        boolean twoSpace = board.getPiece(row + dir, col).getChessPieceType() == 
        ChessPieceType.EMPTY;

        if(oneSpace){
            validMoves.add(new Position(Rank.getRankByIndex(row), start.getFile()));
            if(!currentPiece.hasMoved() && twoSpace)
                validMoves.add(new Position(Rank.getRankByIndex(row + dir), start.getFile()));
        }
        if(col - 1 >= 0 && ((Piece) board.getPiece(row, col - 1)).getColor() != currentPiece.getColor() && 
        ((Piece) board.getPiece(row, col - 1)).getChessPieceType() != ChessPieceType.EMPTY)
            validMoves.add(new Position(Rank.getRankByIndex(row), File.getFileByIndex(col - 1)));
        if(col + 1 < board.getWidth() && ((Piece) board.getPiece(row, col + 1)).getColor() 
*        != currentPiece.getColor() && ((Piece) board.getPiece(row, col + 1)).getChessPieceType() 
        != ChessPieceType.EMPTY)
            validMoves.add(new Position(Rank.getRankByIndex(row), File.getFileByIndex(col + 1)));

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