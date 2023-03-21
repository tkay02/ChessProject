package model;

import enums.ChessPieceType;
import enums.GameColor;
import interfaces.PieceIF;
import interfaces.SquareIF;
import interfaces.BoardIF;

/**
 * Class that represents a board for chess.
 * 
 * @author A-Team
 * @version 3/20/23
 */

public class Board implements BoardIF {
    
    private SquareIF[][] chessBoard;
    private BoardStrategy drawStrategy;
    private int width;
    private int height;

    public Board(){
        this.width = 8;
        this.height = 8;
        init_board();
    }

    /**
     * Initialize the board with square objects
     */
    public void init_board(){
        for(int i = 0; i < this.width; i++){
            for(int j = 0; j < this.height; j++){
                if(j % 2 == 0){
                    if(i % 2 == 0) chessBoard[i][j] = new Square(i, j, GameColor.WHITE);
                    else chessBoard[i][j] = new Square(i, j, GameColor.BLACK);
                }
                else{
                    if(i % 2 == 0) chessBoard[i][j] = new Square(i, j, GameColor.BLACK);
                    else chessBoard[i][j] = new Square(i, j, GameColor.WHITE);
                }
            }
        }
    }

    /**
     * 
     */
    public void setup(){
        //setup pawns
        for(int col = 0; col < 8; col++){
            Piece blackPawn = new Piece(ChessPieceType.PAWN, GameColor.BLACK);
            blackPawn.setBlack(true);
            Piece whitePawn = new Piece(ChessPieceType.PAWN, GameColor.WHITE);
            whitePawn.setWhite(true);
            chessBoard[col][1].setPiece(blackPawn);
            chessBoard[col][7].setPiece(whitePawn);
        }

        Piece blackRook = new Piece(ChessPieceType.ROOK, GameColor.BLACK);
        blackRook.setBlack(true);
        chessBoard[0][0].setPiece(blackRook);
        chessBoard[0][7].setPiece(blackRook);

        Piece whiteRook = new Piece(ChessPieceType.ROOK, GameColor.WHITE);
        whiteRook.setWhite(true);
        chessBoard[7][0].setPiece(whiteRook);
        chessBoard[7][7].setPiece(whiteRook);

        Piece blackKnight = new Piece(ChessPieceType.KNIGHT, GameColor.BLACK);
        blackKnight.setBlack(true);
        chessBoard[0][1].setPiece(blackKnight);
        chessBoard[0][6].setPiece(blackKnight);

        Piece whiteKnight = new Piece(ChessPieceType.KNIGHT, GameColor.WHITE);
        whiteKnight.setWhite(true);
        chessBoard[7][1].setPiece(whiteKnight);
        chessBoard[7][6].setPiece(whiteKnight);

        Piece blackBishop = new Piece(ChessPieceType.BISHOP, GameColor.BLACK);
        blackBishop.setBlack(true);
        chessBoard[7][2].setPiece(blackBishop);
        chessBoard[7][5].setPiece(blackBishop);

        Piece whiteBishop = new Piece(ChessPieceType.BISHOP, GameColor.WHITE);
        whiteBishop.setWhite(true);
        chessBoard[7][2].setPiece(whiteBishop);
        chessBoard[7][5].setPiece(whiteBishop);


    }


    //might change depending on board strategy
    /**
     * 
     */
    public void draw(){

    }

    /**
     * 
     * @return
     */
    public SquareIf[][] getSquares(){

    }

    /**
     * 
     * @param drawStrategy
     */
    public void setDrawStrategy(BoardStrategy drawStrategy){
        this.drawStrategy = drawStrategy;
    }

    /**
     * 
     * @return
     */
    public int getWidth(){

    }

    /**
     * 
     * @return
     */
    public int getHeight(){

    }

    /**
     * 
     * @param rank
     * @param file
     * @return
     */
    public PieceIf getPiece(Rank rank, Files file){

    }

    /**
     * 
     * @param col
     * @param row
     * @return
     */
    public PieceIf getPiece(int col, int row){
        
    }

}
