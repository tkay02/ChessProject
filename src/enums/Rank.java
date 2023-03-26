package src.enums;
/**
 * This enumeration class models the Rank also known as the row for the
 * chess board.
 * 
 * @author Nolan Flinchum, Thomas Kay, Joseph Oladeji, Levi Sweat
 * @version 3/27/2023
 */

public enum Rank {

    R1(1, 7), R2(2, 6), R3(3, 5), R4(4, 4),
    R5(5, 3), R6(6, 2), R7(7, 1), R8(8, 0);

    /* The number to be displayed for a rank */
    private int realRank;

    /* The number representing the rank's position in the 2d array */
    private int arrayRank;

    /**
     * Constructor for the rank enum. Initializes the display rank number and the
     * array rank number.
     * 
     * @param realRank display rank number
     * @param arrayRank rank number representing position in 2d array
     */
    private Rank(int realRank, int arrayRank){
        this.realRank = realRank;
        this.arrayRank = arrayRank;
    }

    /**
     * Getter for the display rank number.
     * 
     * @return the rank number to be displayed on the board
     */
    public int getRealRank(){
        return realRank;
    }    

    /**
     * Get the rank number representing the position on the 2d array.
     * 
     * @return the rank number representing the position on the 2d array
     */
    public int getArrayRank(){
        return arrayRank;
    }

    /**
     * Gets the rank when provided the index.
     * 
     * @param index the index representing the rank in the 2d array
     * @return the actual Rank object
     */
    public static Rank getRankByIndex(int index){
        return Rank.values()[7 - index];
    }

    public static Rank getRankByReal(int index){
        return Rank.values()[index - 1];
    }

    /**
     * Is this necessary?
     */
    public String toString(){
        return "";
    }

}
