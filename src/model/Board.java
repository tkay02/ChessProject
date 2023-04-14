package src.model;

import src.controller.Chess;
import src.enums.ChessPieceType;
import src.enums.GameColor;
import src.enums.File;
import src.enums.Rank;
import src.interfaces.PieceIF;
import src.interfaces.SquareIF;
import src.movement.*;
import src.ui_cli.BoardMonoCLI;
import src.interfaces.BoardIF;
import src.interfaces.BoardStrategy;

import java.util.ArrayList;

/**
 * Class to represent the board to play chess on.
 * 
 * @author Nolan Flinchum (25%), Thomas Kay (25%), Joseph Oladeji (25%), Levi Sweat (25%)
 * @version 3/27/2023
 */

public class Board implements BoardIF {

    /** A reference to the game of chess that is being played **/
    private Chess chess;
    
    /** Represents the chess board **/
    private SquareIF[][] chessBoard;

    /** The strategy for drawing the board **/
    private BoardStrategy drawStrategy;

    /** Width of the board **/
    private int width;

    /** Height of the board **/
    private int height;

    /** List of white pieces that have been taken **/
    private ArrayList<String> whiteTakenPieces = new ArrayList<>();

    /** List of white pieces that have been taken **/
	private ArrayList<String> blackTakenPieces = new ArrayList<>();

    /** Represents the position of the white king on the board **/
    private Position whiteKingPos = new Position(Rank.R1, File.E);

    /** Represents the position of the black king on the board **/
    private Position blackKingPos = new Position(Rank.R8, File.E);

    /**
     * Constructor for the chess board.
     */
    public Board(Chess chessGame){
        this.chess = chessGame;
        this.width = 8;
        this.height = 8;
        chessBoard = new Square[height][width];
        this.drawStrategy = new BoardMonoCLI();
        init_board();
        setup();
    }

    /**
     * Initialize the board with square objects
     */
    public void init_board(){
        boolean flipSquare = true;
        for(int row = 0; row < this.width; row++){
            for(int col = 0; col < this.height; col++){

                if(flipSquare) { 
                    setSquare(row, col, true);
                    chessBoard[row][col].setPiece(new Piece(ChessPieceType.EMPTY, GameColor.WHITE));
                }
                else {
                    setSquare(row, col, false);
                    chessBoard[row][col].setPiece(new Piece(ChessPieceType.EMPTY, GameColor.BLACK));
                }
                flipSquare = !flipSquare;
            }
            flipSquare = !flipSquare;
        }
    }

    /**
     * Sets up the pieces on the chess board.
     */
    public void setup(){
        // Pawns
        MovementStrategy pawnMoves = new PawnMovement(this);
        for(int col = 0; col < 8; col++){
            Piece blackPawn = new Piece(ChessPieceType.PAWN, GameColor.BLACK);
            initPiece(blackPawn, false, 1, col, pawnMoves);

            Piece whitePawn = new Piece(ChessPieceType.PAWN, GameColor.WHITE);
            initPiece(whitePawn, true, 6, col, pawnMoves);
        }

        // Rooks
        MovementStrategy rookMoves = new RookMovement(this);
        Piece blackRook1 = new Piece(ChessPieceType.ROOK, GameColor.BLACK);
        initPiece(blackRook1, false, 0, 0, rookMoves);

        Piece blackRook2 = new Piece(ChessPieceType.ROOK, GameColor.BLACK);
        initPiece(blackRook2, false, 0, 7, rookMoves);

        Piece whiteRook1 = new Piece(ChessPieceType.ROOK, GameColor.WHITE);
        initPiece(whiteRook1, true, 7, 0, rookMoves);

        Piece whiteRook2 = new Piece(ChessPieceType.ROOK, GameColor.WHITE);
        initPiece(whiteRook2, true, 7, 7, rookMoves);

        //Knights
        MovementStrategy knightMoves = new KnightMovement(this);
        Piece blackKnight1 = new Piece(ChessPieceType.KNIGHT, GameColor.BLACK);
        initPiece(blackKnight1, false, 0, 1, knightMoves);

        Piece blackKnight2 = new Piece(ChessPieceType.KNIGHT, GameColor.BLACK);
        initPiece(blackKnight2, false, 0, 6, knightMoves);

        Piece whiteKnight1 = new Piece(ChessPieceType.KNIGHT, GameColor.WHITE);
        initPiece(whiteKnight1, true, 7, 1, knightMoves);

        Piece whiteKnight2 = new Piece(ChessPieceType.KNIGHT, GameColor.WHITE);
        initPiece(whiteKnight2, true, 7, 6, knightMoves);

        // Bishops
        MovementStrategy bishopMoves = new BishopMovement(this);
        Piece blackBishop1 = new Piece(ChessPieceType.BISHOP, GameColor.BLACK);
        initPiece(blackBishop1, false, 0, 2, bishopMoves);

        Piece blackBishop2 = new Piece(ChessPieceType.BISHOP, GameColor.BLACK);
        initPiece(blackBishop2, false, 0, 5, bishopMoves);

        Piece whiteBishop1 = new Piece(ChessPieceType.BISHOP, GameColor.WHITE);
        initPiece(whiteBishop1, true, 7, 2, bishopMoves);

        Piece whiteBishop2 = new Piece(ChessPieceType.BISHOP, GameColor.WHITE);
        initPiece(whiteBishop2, true, 7, 5, bishopMoves);

        // Queens
        MovementStrategy queenMoves = new QueenMovement(this);
        Piece blackQueen = new Piece(ChessPieceType.QUEEN, GameColor.BLACK);
        initPiece(blackQueen, false, 0, 3, queenMoves);

        Piece whiteQueen = new Piece(ChessPieceType.QUEEN, GameColor.WHITE);
        initPiece(whiteQueen, true, 7, 3, queenMoves);

        // Kings
        MovementStrategy kingMoves = new KingMovement(this);
        Piece blackKing = new Piece(ChessPieceType.KING, GameColor.BLACK);
        initPiece(blackKing, false, 0, 4, kingMoves);

        Piece whiteKing = new Piece(ChessPieceType.KING, GameColor.WHITE);
        initPiece(whiteKing, true, 7, 4, kingMoves);
    }

    /**
     * This method will take a piece, and set it's color depending on the
     * boolean value passed set its position coordinates for where it'll be placed.
     * 
     * @param piece - Piece that will be initialized.
     * @param whiteOrBlack - Boolean value for the piece whether it is black or white.
     * @param row - Integer value representing the row where the piece will be placed.
     * @param col - Integer value representing the column where the piece will be placed.
     */ 
    private void initPiece(Piece piece, boolean whiteOrBlack, int row, int col, 
        MovementStrategy move){
        if (whiteOrBlack) piece.setWhite(whiteOrBlack);
        else piece.setBlack(true);
        piece.setMoveStrategy(move);
        setBoardPos(piece, row, col);
    }

    /**
     * This method takes a piece and sets it's position to the desired
     * row and column within the board.
     * 
     * @param piece - Piece that will be set at a specified location.
     * @param row - Integer value representing the row where the piece will be placed.
     * @param col - Integer value representing the column where the piece will be placed.
     */
    private void setBoardPos(Piece piece, int row, int col){
        chessBoard[row][col].setPiece(piece);
    }

    /**
     * This method will go to the row and column within the chessBoard and retrieve the
     * specified Square.
     * @param row - Integer value representing the row where the piece will be retrieved from.
     * @param row - Integer value representing the column where the piece will be retrieved from.
     */
    public SquareIF getSquare(int row, int col){
        return chessBoard[row][col];
    }

    /**
     * This method will go to the row and column within the chessBoard and set it to the
     * specified Square.
     * @param square - Square object that will be set to the specified location
     * @param row - Integer value representing the row where the square will be set.
     * @param row - Integer value representing the column where the square will be set.
     * @param whiteOrBlack - Boolean value representing whether the square will be black or white.
     */
    private void setSquare(int row, int col, boolean whiteOrBlack){
        GameColor color = whiteOrBlack ? GameColor.WHITE : GameColor.BLACK;
        Square square = new Square(row, col, color);

        if(whiteOrBlack) square.setWhite(whiteOrBlack);
        else square.setBlack(whiteOrBlack);
        
        chessBoard[row][col] = square;
    }

    /**
     * Will call the drawStrategy's draw method to draw the board based on the strategy.
     */
    public void draw(boolean drawWhite, ArrayList<src.model.Position> validMoves){
        if(drawWhite) this.drawStrategy.drawWhite(this, validMoves);
        else this.drawStrategy.drawBlack(this, validMoves);
    }

    /**
     * Returns the current state of the squares on the chess board.
     * 
     * @return the squares that make up the chess board.
     */
    public SquareIF[][] getSquares(){
        return this.chessBoard;
    }

    /**
     * Sets the draw strategy of the chess board.
     * 
     * @param drawStrategy the strategy implemented for drawing the chess board
     */
    public void setDrawStrategy(BoardStrategy drawStrategy){
        this.drawStrategy = drawStrategy;
    }

    /**
     * Getter for the width of the chess board.
     * 
     * @return the width of the chess board
     */
    public int getWidth(){
        return this.width;
    }

    /**
     * Getter for the height of the chess board.
     * 
     * @return the height of the chess board
     */
    public int getHeight(){
        return this.height;
    }

    /**
     * Gets the piece at the location represented by the rank and file of a chess board.
     * 
     * @param rank rank of the square we want to get the piece of
     * @param file file of the square we want to get the piece of
     * @return the piece at the provided location
     */
    public PieceIF getPiece(Rank rank, File file){
        return getSquare(rank.getArrayRank(), file.getArrayFile()).getPiece();
    }

    /**
     * Gets the piece at the location represented by the row and column numbers
     * of the 2D array of squares.
     * 
     * @param col number that represents the column of the 2D square array
     * @param row character that represents the row of the 2D square array
     * @return the piece at the provided location
     */
    public PieceIF getPiece(int row, int col){
        return getSquare(row, col).getPiece();
    }

    /**
     * Getter for the ArrayList of white pieces that were taken.
     * 
     * @return ArrayList of white pieces that were taken
     */
    public ArrayList<String> getWhiteTakenPieces(){
        return this.whiteTakenPieces;
    }

    /**
     * Getter for the ArrayList of black pieces that were taken.
     * 
     * @return ArrayList of black pieces that were taken
     */
    public ArrayList<String> getBlackTakenPieces(){
        return this.blackTakenPieces;
    }

    public Position getWhiteKingPos(){
        return this.whiteKingPos;
    }

    public Position getBlackKingPos(){
        return this.blackKingPos;
    }

    public void setWhiteKingPos(Rank toRank, File toFile){
        this.whiteKingPos = new Position(toRank, toFile);
    }

    public void setBlackKingPos(Rank toRank, File toFile){
        this.blackKingPos = new Position(toRank, toFile);
    }

    public boolean tryMove(Piece currentPiece, int row, int col, Position fromPos){
        return chess.tryMove(currentPiece, row, col, fromPos);
    }

}
