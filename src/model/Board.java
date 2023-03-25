package src.model;
/**
 * Class to represent the board to play chess on.
 * 
 * @author A-Team
 * @version 3/23/23
 */
import src.enums.ChessPieceType;
import src.enums.GameColor;
import src.enums.File;
import src.enums.Rank;
import src.interfaces.PieceIF;
import src.interfaces.SquareIF;
import src.interfaces.BoardIF;
import src.interfaces.BoardStrategy;

public class Board implements BoardIF {
    
    /* Represents the chess board */
    private SquareIF[][] chessBoard;

    /* The strategy for drawing the board */
    private BoardStrategy drawStrategy;

    /* Width of the board */
    private int width;

    /* Height of the board */
    private int height;

    /**
     * Constructor for the chess board.
     */
    public Board(){
        this.width = 8;
        this.height = 8;
        chessBoard = new Square[8][8];
        init_board();
        setup();
    }

    /**
     * Initialize the board with square objects
     */
    public void init_board(){
        boolean flip = true;
        for(int i = 0; i < this.width; i++){
            for(int j = 0; j < this.height; j++){
                if(flip){
                    Square whiteSquare = new Square(i, j, GameColor.WHITE);
                    whiteSquare.setWhite(true);
                    chessBoard[i][j] = whiteSquare;
                    // else chessBoard[i][j] = new Square(i, j, GameColor.BLACK);
                }
                else{
                    Square blackSquare = new Square(i, j, GameColor.BLACK);
                    blackSquare.setBlack(true);
                    chessBoard[i][j] = blackSquare;
                    // else chessBoard[i][j] = new Square(i, j, GameColor.WHITE);
                }
                chessBoard[i][j].setPiece(new Piece(ChessPieceType.EMPTY, GameColor.WHITE));
                flip = !flip;
            }
            flip = !flip;
        }
    }

    /**
     * This method is inefficicient, I will refactor later
     */
    public void setup(){
        //Pawns
        for(int col = 0; col < 8; col++){
            Piece blackPawn = new Piece(ChessPieceType.PAWN, GameColor.BLACK);
            blackPawn.setBlack(true);
            Piece whitePawn = new Piece(ChessPieceType.PAWN, GameColor.WHITE);
            whitePawn.setWhite(true);
            chessBoard[1][col].setPiece(blackPawn);
            chessBoard[6][col].setPiece(whitePawn);
        }

        //Rooks
        Piece blackRook1 = new Piece(ChessPieceType.ROOK, GameColor.BLACK);
        blackRook1.setBlack(true);
        chessBoard[0][0].setPiece(blackRook1);
        Piece blackRook2 = new Piece(ChessPieceType.ROOK, GameColor.BLACK);
        blackRook2.setBlack(true);
        chessBoard[0][7].setPiece(blackRook2);

        Piece whiteRook1 = new Piece(ChessPieceType.ROOK, GameColor.WHITE);
        whiteRook1.setWhite(true);
        chessBoard[7][0].setPiece(whiteRook1);
        Piece whiteRook2 = new Piece(ChessPieceType.ROOK, GameColor.WHITE);
        whiteRook2.setWhite(true);
        chessBoard[7][7].setPiece(whiteRook2);

        //Knights
        Piece blackKnight1 = new Piece(ChessPieceType.KNIGHT, GameColor.BLACK);
        blackKnight1.setBlack(true);
        chessBoard[0][1].setPiece(blackKnight1);
        Piece blackKnight2 = new Piece(ChessPieceType.KNIGHT, GameColor.BLACK);
        blackKnight2.setBlack(true);
        chessBoard[0][6].setPiece(blackKnight2);

        Piece whiteKnight1 = new Piece(ChessPieceType.KNIGHT, GameColor.WHITE);
        whiteKnight1.setWhite(true);
        chessBoard[7][1].setPiece(whiteKnight1);
        Piece whiteKnight2 = new Piece(ChessPieceType.KNIGHT, GameColor.WHITE);
        whiteKnight2.setWhite(true);
        chessBoard[7][6].setPiece(whiteKnight2);

        //Bishops
        Piece blackBishop1 = new Piece(ChessPieceType.BISHOP, GameColor.BLACK);
        blackBishop1.setBlack(true);
        chessBoard[0][2].setPiece(blackBishop1);
        Piece blackBishop2 = new Piece(ChessPieceType.BISHOP, GameColor.BLACK);
        blackBishop2.setBlack(true);
        chessBoard[0][5].setPiece(blackBishop2);

        Piece whiteBishop1 = new Piece(ChessPieceType.BISHOP, GameColor.WHITE);
        whiteBishop1.setWhite(true);
        chessBoard[7][2].setPiece(whiteBishop1);
        Piece whiteBishop2 = new Piece(ChessPieceType.BISHOP, GameColor.WHITE);
        whiteBishop2.setWhite(true);
        chessBoard[7][5].setPiece(whiteBishop2);

        //Queens
        Piece blackQueen = new Piece(ChessPieceType.QUEEN, GameColor.BLACK);
        blackQueen.setBlack(true);
        chessBoard[0][3].setPiece(blackQueen);

        Piece whiteQueen = new Piece(ChessPieceType.QUEEN, GameColor.WHITE);
        whiteQueen.setWhite(true);
        chessBoard[7][3].setPiece(whiteQueen);

        //Kings
        Piece blackKing = new Piece(ChessPieceType.KING, GameColor.BLACK);
        blackKing.setBlack(true);
        chessBoard[0][4].setPiece(blackKing);

        Piece whiteKing = new Piece(ChessPieceType.KING, GameColor.WHITE);
        whiteKing.setWhite(true);
        chessBoard[7][4].setPiece(whiteKing);
    }

    /**
     * Will call the drawStrategy's draw method to draw the board based on the strategy.
     */
    public void draw(){
        this.drawStrategy.draw(this);
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
        //not sure how to do this right now
        return chessBoard[rank.getArrayRank()][file.getArrayFile()].getPiece();
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
        // int numCol = 0;
        // for(File file : File.values())
        //     if (numCol == file.getRealFile()) numCol = file.getArrayFile();
        return chessBoard[row][col].getPiece();
        //okay idk how to do this either
        //???? why is row a character ????
    }

}
