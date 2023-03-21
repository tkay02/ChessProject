package model;
/**
 * DESCRIPTION
 * 
 * @author A-Team
 * @version 3/20/23
 */
import enums.GameColor;
import interfaces.PieceIF;
import interfaces.SquareIF;

public class Square extends BlackAndWhite implements SquareIF {
    
    private PieceIF piece;
    private Position pos;

    public Square(int rank, int file, GameColor color){
        super(color);
        this.pos = new Position(rank, file);
    }

    public PieceIF getPiece(){
        return this.piece;
    }

    public void setPiece(Piece piece){
        this.piece = piece;
    }

    //Maybe add a getPosition for the future?

}
