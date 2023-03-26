package src.model;
import src.interfaces.*;
import java.util.ArrayList;
import src.enums.*;

/**
 * @Author Thomas Kay ...
 * @Verison 3/27/2023
 * 
 * Movement Stragety for the Knight Piece
 */


public class KnightMovement implements MovementStrategy {
    
    /**The maximum length for each side of the chess board*/
    public static final int MAX = 8;

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
     */
    public KnightMovement(Board board, GameColor color) {
        
        this.board = board;
        this.color = color;
        this.validMoves = new ArrayList<Position>();
    
    }

    public void generateValidMoves(Position from) {

    }

    public boolean validateMove(Position from, Position to) {

        return true;
    }

    public Position[] showMoves(Position pos) {

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
            newPos = new Position(Rank.getRankByIndex(row-2), File.getFileByIndex(col-1));
        }
        return newPos;

    }

    private boolean notAlly(Position nextPos) {

        boolean isNotAlly = false;
        int[] nums = this.currentRowAndCol(nextPos);
        int row = nums[0];
        int col = nums[1];
        Square square = 

    }

}