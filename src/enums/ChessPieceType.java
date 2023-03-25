package src.enums;
/**
 * Enumeration to represent the type of chess piece.
 * 
 * @author Nolan Flinchum, Thomas Kay, Joseph Oladeji, Levi Sweat
 * @version 3/27/2023
 */

public enum ChessPieceType {

    KING("K", "King"), QUEEN("Q", "Queen"), ROOK("R", "Rook"),
    BISHOP("B", "Bishop"), KNIGHT("N", "Knight"), PAWN("P", "Pawn"), EMPTY(" ", "Empty");

    /* Letter representing a chess piece */
    private String chessPieceLetter;

    /* Word representing a chess piece */
    private String chessPieceWord;

    /**
     * Constructor for the chess piece type.
     * 
     * @param chessPieceLetter letter representing a chess piece
     * @param cheessPieceWord word representing a chess piece
     */
    private ChessPieceType(String chessPieceLetter, String cheessPieceWord){
        this.chessPieceLetter = chessPieceLetter;
        this.chessPieceWord = cheessPieceWord;
    }

    /**
     * Gets the letter representing a chess piece.
     * 
     * @return the letter representing a chess piece
     */
    public String getChessPieceLetter(){
        return this.chessPieceLetter;
    }
    
    /**
     * Gets the word representing a chess piece.
     * 
     * @return the word representing a chess piece
     */
    public String getChessPieceWord(){
        return this.chessPieceWord;
    }
    
}
