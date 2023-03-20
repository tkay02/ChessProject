package enums;

/**
 * @author Joseph Oladeji
 * @author Thomas Kay
 * @author Levi Sweat
 * @author Nolan Flinchum
 * @version
 */
public enum ChessPieceType {

    KING("K", "King"), QUEEN("Q", "Queen"), ROOK("R", "Rook"),
    BISHOP("B", "Bishop"), KNIGHT("K", "Knight"), PAWN("P", "PAWN");

    private String chessPieceLetter;
    private String chessPieceWord;

    /**
     * Sets the chessPieceLetter and chessPieceWord of the ChessPieceType Enumerationn
     * @param chessPieceLetter
     * @param cheessPieceWord
     */
    private ChessPieceType(String chessPieceLetter, String cheessPieceWord){
        this.chessPieceLetter = chessPieceLetter;
        this.chessPieceWord = cheessPieceWord;
    }

    /**
     * 
     * @return
     */
    public String getChessPieceLetter(){
        return this.chessPieceLetter;
    }
    
    /**
     * 
     * @return
     */
    public String getChessPieceWord(){
        return this.chessPieceWord;
    }
}
