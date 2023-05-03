package src.enums;
/**
 * This enumeration class models the Rank also known as the row for the
 * chess board.
 * 
 * @author Nolan Flinchum (20%), Thomas Kay, Joseph Oladeji (60%), Levi Sweat (20%)
 * @version 4/19/2023
 */

public enum Rank {

    R1(1, 7), R2(2, 6), R3(3, 5), R4(4, 4),
    R5(5, 3), R6(6, 2), R7(7, 1), R8(8, 0);

    /** The number to be displayed for a rank **/
    private int realRank;

    /** The number representing the rank's position in the 2d array **/
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
     * Returns the visual representation of the rank.
     */
    public String toString() {
        return "" + this.realRank;
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

    /**
     * This method will get the rank of the enumeration
     * by the index.
     * @param index - Integer value of the rank enumeration.
     * @return The rank enumeration corresponding to the index value passed.
     */
    public static Rank getRankByReal(int index){
        return Rank.values()[index - 1];
    }
}
