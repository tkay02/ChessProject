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
    BLACK("\u001b[40m", "\u001b[31m" ), WHITE("\u001b[47m", "\u001b[34m");

    /** The ansi code representing the corresponding enumeration */
    private String background;
    private String text;

    /**
     * This constructor initializes the color of the enumeration to the
     * needed ansi code for the terminal.
     * 
     * @param ansiCode code that represents the color
     */
    private GameColor(String background, String text){
        this.background = background;
        this.text = text;
    }

    /**
     * This toString returns the ansiCode of the specified enumeration.
     * 
     * @return the ansiCode to represent the color
     */
    public String getBackground(){
        return this.background;
    }

    public String getText(){
        return this.text;
    }

    public String reset_colorings(){
        return "\u001b[0m";
    }
}