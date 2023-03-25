package src.model;
/**
 * 
 */
import src.interfaces.MovementStrategy;
import src.interfaces.SquareIF;
import src.enums.ChessPieceType;
import src.enums.Rank;

public class PawnMovement implements MovementStrategy {
    
    /* Array of valid moves for a selected piece */
    private Position[] validMoves;

    /* Alias of the chess board used to generate valid moves  */
    private Board board;

    /**
     * Constructor for the PawnMovement class.
     */
    public PawnMovement(Board board){
        this.board = board;
        this.validMoves = new Position[4];
    }

    /**
     * Generate all the valid moves of a piece from a current spot,
     * and use it to populate the valid moves array.
     * 
     * @param from the position we're moving from
     */
    public void generateValidMoves(Position start){
        int i = 0;
        int row = start.getRank().getArrayRank();
        int col = start.getFile().getArrayFile();
        Piece piece = (Piece) board.getPiece(row, col);
        if(piece.isWhite()){
            if(!piece.hasMoved() && board.getPiece(row - 2, col).getChessPieceType() == ChessPieceType.EMPTY){
                validMoves[i] = new Position(getRankByIndex(row - 2), start.getFile());
                i++;
            }
            if(board.getPiece(row - 1, col).getChessPieceType() == ChessPieceType.EMPTY){
                validMoves[i] = new Position(getRankByIndex(row - 1), start.getFile());
                i++;
            }
        }
        else{
            if(!piece.hasMoved() && board.getPiece(row + 2, col).getChessPieceType() == ChessPieceType.EMPTY){
                validMoves[i] = new Position(getRankByIndex(row + 2), start.getFile());
                i++;
            }
            if(board.getPiece(row + 1, col).getChessPieceType() == ChessPieceType.EMPTY){
                validMoves[i] = new Position(getRankByIndex(row + 1), start.getFile());
                i++;
            }
        }
    }

    public boolean validateMove(Position from, Position to){
        generateValidMoves(from);
        for(Position pos : validMoves){
            if(to == pos) return true; //psuedocode for future equals method
        }
        return false;
    }

    public Position[] showMoves(Position pos){
        return this.validMoves;
    }

}
