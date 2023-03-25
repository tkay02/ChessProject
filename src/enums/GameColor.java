package src.enums;
/**
 * This class models the Game Color for the Chess Board and the each player's chess piece.
 * The color enumeraiton holds the corresponding ansi code for the color to be shown in the
 * terminal to change to that specified.
 * 
 * @author Nolan Flinchum, Thomas Kay, Joseph Oladeji, Levi Sweat
 * @version 3/27/2023
 */

public enum GameColor{
    
    /** Black color enumeration */ /** White color enumeration */
    BLACK("\u001b[30m"), WHITE("\u001b[37m");

    /** The ansi code representing the corresponding enumeration */
    private String ansiCode;

    /**
     * This constructor initializes the color of the enumeration to the
     * needed ansi code for the terminal.
     * 
     * @param ansiCode code that represents the color
     */
    private GameColor(String ansiCode){
        this.ansiCode = ansiCode;
    }

    /**
     * This toString returns the ansiCode of the specified enumeration.
     * 
     * @return the ansiCode to represent the color
     */
    public String toString(){
        return ansiCode;
    }

}