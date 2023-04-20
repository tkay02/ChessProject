package src.enums;
/**
 * This class models the Game Color for the Chess Board and the each player's chess piece.
 * The color enumeraiton holds the corresponding ansi code for the color to be shown in the
 * terminal to change to that specified.
 * 
 * @author Nolan Flinchum, Thomas Kay, Joseph Oladeji (50%), Levi Sweat (50%)
 * @version 3/27/2023
 */

public enum GameColor{
    
    BLACK("\u001b[40m", "\u001b[31m" ), WHITE("\u001b[47m", "\u001b[34m");


    /** The ansi code representing the background color **/
    private String background;

    /** The ansi code representing the text color **/
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

    /**
     * This method will get the text ansi code of the current enumeration.
     * @return The ansi code representing the text colo
     */
    public String getText(){
        return this.text;
    }

    /**
     * This method will end and reset the color for the current color's ansi code.
     * @return The ansi code necessary to terminate a color.
     */
    public static String resetColor(){
        return "\u001b[0m";
    }
    
    /**
     * This method will return the ansi code for magenta.
     * @return The ansi code for magenta.
     */
    public static String showMoveColor(){
        return "\u001b[45m"; //background magenta
    }
}