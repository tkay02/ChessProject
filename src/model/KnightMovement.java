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

    /**To represent the dimensions of the chessboard*/
    public static final int ROWCOL = 2;

    /**Array of valid moves for a selected piece*/
    private ArrayList<Position> validMoves;

    /**The chess board to be used as referenced to generate valid moves*/
    private Board board;

    /**The color of the Knight*/
    private GameColor color;

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

        //Resets valid moves list when starting a new position
        this.validMoves.clear();
        Position newPos;
        //Checks if up left is a valid move
        newPos = this.checkUpLeft(from);
        if(newPos != null) {
            if(this.notAlly(newPos)) {
                this.validMoves.add(newPos);
            }
        }
        //Checks if up right is a valid move
        newPos = this.checkUpRight(from);
        if(newPos != null) {
            if(this.notAlly(newPos)) {
                this.validMoves.add(newPos);
            }
        }
        //Checks if left up is a valid move
        newPos = this.checkLeftUp(from);
        if(newPos != null) {
            if(this.notAlly(newPos)) {
                this.validMoves.add(newPos);
            }
        }
        //Checks if left down is a valid move
        newPos = this.checkLeftDown(from);
        if(newPos != null) {
            if(this.notAlly(newPos)) {
                this.validMoves.add(newPos);
            }
        }
        //Checks if right up is a valid move
        newPos = this.checkRightUp(from);
        if(newPos != null) {
            if(this.notAlly(newPos)) {
                this.validMoves.add(newPos);
            }
        }
        //Checks if right down is a valid move
        newPos = this.checkRightDown(from);
        if(newPos != null) {
            if(this.notAlly(newPos)) {
                this.validMoves.add(newPos);
            }
        }
        //Checks if down left is a valid move
        newPos = this.checkDownLeft(from);
        if(newPos != null) {
            if(this.notAlly(newPos)) {
                this.validMoves.add(newPos);
            }
        }
        //Checks if down right is a valid move
        newPos = this.checkDownRight(from);
        if(newPos != null) {
            if(this.notAlly(newPos)) {
                this.validMoves.add(newPos);
            }
        }

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

    /**
     * Checks if the move is valid from its current position to its new position.
     * 
     * @param Position from The original position of the Knight.
     * @param Position to The new possible position of the Knight.
     * @return True if the new position of the Knight is valid; false otherwise.
     */
    public boolean validateMove(Position to) {
        return contains(to);
    }

    /**
     * Creates an array that stores all of the possible valid positions from the
     * Knight's current position.
     * 
     * @param Position pos The current position of the Knight.
     * @return An array that stores all of the possible positions.
     */
    public ArrayList<Position> showMoves(Position pos) {
        Rank rank = pos.getRank();
        File file = pos.getFile();
        this.color = ((Piece) board.getPiece(rank, file)).getColor();

        this.generateValidMoves(pos);
        return this.validMoves;
    }

    /**
     * Returns an array that stores the current row and column numbers in a basic
     * integer array. The current row is stored as the first element while the current
     * column is stored as the second element.
     * 
     * @param Position pos The current position of the Knight piece.
     * @return An integer array that stores the number of the row and column.
     */
    private int[] currentRowAndCol(Position pos) {

        int[] rolAndCol = new int[ROWCOL];
        rolAndCol[0] = pos.getRank().getArrayRank();
        rolAndCol[1] = pos.getFile().getArrayFile();
        return rolAndCol;

    }

    /**
     * Determines if moving up left is a valid move.
     * 
     * @param Position pos The current position of the Knight piece.
     * @return The valid position of moving up left; null if the move is invalid.
     */
    private Position checkUpLeft(Position pos) {
        
        int[] nums = this.currentRowAndCol(pos);
        int row = nums[0];
        int col = nums[1];
        Position newPos;
        if(row + 2 >= MAX || col - 1 < 0) {
            newPos = null;
        }
        else {
            newPos = new Position(Rank.getRankByIndex(row+2), File.getFileByIndex(col-1));
        }
        return newPos;
    
    }

    /**
     * Determines if moving up right is a valid move. 
     * 
     * @param Position pos The current position of the piece.
     * @return The valid position of moving up right; null if move is invalid.
     */
    private Position checkUpRight(Position pos) {

        int[] nums = this.currentRowAndCol(pos);
        int row = nums[0];
        int col = nums[1];
        Position newPos;
        if(row + 2 >= MAX || col + 1 >= MAX) {
            newPos = null;
        }
        else {
            newPos = new Position(Rank.getRankByIndex(row+2), File.getFileByIndex(col+1));
        }
        return newPos;

    }

    /**
     * Determines if moving left up is a valid move. 
     * 
     * @param Position pos The current position of the piece.
     * @return The valid position of moving left up; null if move is invalid.
     */
    private Position checkLeftUp(Position pos) {

        int[] nums = this.currentRowAndCol(pos);
        int row = nums[0];
        int col = nums[1];
        Position newPos;
        if(row + 1 >= MAX || col - 2 < 0) {
            newPos = null;
        }
        else {
            newPos = new Position(Rank.getRankByIndex(row+1), File.getFileByIndex(col-2));
        }
        return newPos;
    }

    /**
     * Determines if moving left down is a valid move. 
     * 
     * @param Position pos The current position of the piece.
     * @return The valid position of moving left down; null if move is invalid.
     */
    private Position checkLeftDown(Position pos) {

        int[] nums = this.currentRowAndCol(pos);
        int row = nums[0];
        int col = nums[1];
        Position newPos;
        if(row - 1 < 0 || col - 2 < 0) {
            newPos = null;
        }
        else {
            newPos = new Position(Rank.getRankByIndex(row-1), File.getFileByIndex(col-2));
        }
        return newPos;
    
    }

    /**
     * Determines if moving right up is a valid move. 
     * 
     * @param Position pos The current position of the piece.
     * @return The valid position of moving right up; null if move is invalid.
     */
    private Position checkRightUp(Position pos) {
        
        int[] nums = this.currentRowAndCol(pos);
        int row = nums[0];
        int col = nums[1];
        Position newPos;
        if(row + 1 >= MAX || col + 2 >= MAX) {
            newPos = null;
        }
        else {
            newPos = new Position(Rank.getRankByIndex(row+1), File.getFileByIndex(col+2));
        }
        return newPos;

    }

    /**
     * Determines if moving right down is a valid move. 
     * 
     * @param Position pos The current position of the piece.
     * @return The valid position of moving right down; null if move is invalid.
     */
    private Position checkRightDown(Position pos) {
        
        int[] nums = this.currentRowAndCol(pos);
        int row = nums[0];
        int col = nums[1];
        Position newPos;
        if(row - 1 < 0 || col + 2 > MAX) {
            newPos = null;
        }
        else {
            newPos = new Position(Rank.getRankByIndex(row-1), File.getFileByIndex(col+2));
        }
        return newPos;

    }

    /**
     * Determines if moving down left is a valid move. 
     * 
     * @param Position pos The current position of the piece.
     * @return The valid position of moving down left; null if move is invalid.
     */
    private Position checkDownLeft(Position pos) {
        
        int[] nums = this.currentRowAndCol(pos);
        int row = nums[0];
        int col = nums[1];
        Position newPos;
        if(row - 2 < 0 || col - 1 < 0) {
            newPos = null;
        }
        else {
            newPos = new Position(Rank.getRankByIndex(row-2), File.getFileByIndex(col-1));
        }
        return newPos;

    }

    /**
     * Determines if moving down right is a valid move. 
     * 
     * @param Position pos The current position of the piece.
     * @return The valid position of moving down right; null if move is invalid.
     */
    private Position checkDownRight(Position pos) {
        
        int[] nums = this.currentRowAndCol(pos);
        int row = nums[0];
        int col = nums[1];
        Position newPos;
        if(row - 2 < 0 || col + 1 >= MAX) {
            newPos = null;
        }
        else {
            newPos = new Position(Rank.getRankByIndex(row-2), File.getFileByIndex(col+1));
        }
        return newPos;

    }

    /**
     * Checks if the next position doesn't have an ally piece.
     * 
     * @param Position nextPos The next position within the Knight's moves.
     * @return True if there's not an ally piece on the new position; false otherwise.
     */
    private boolean notAlly(Position nextPos) {

        boolean isNotAlly = true;
        int[] nums = this.currentRowAndCol(nextPos);
        int row = nums[0];
        int col = nums[1];
        Piece piece = (Piece)this.board.getPiece(row, col);
        if(piece.getChessPieceType() != ChessPieceType.EMPTY) {
            if(this.color == piece.getColor()) {
                isNotAlly = false;
            }
        }
        return isNotAlly; 

    }

}