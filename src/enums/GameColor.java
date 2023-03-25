package src.enums;

/**
 * This class models the Game Color for the Chess Board and the each player's
 * chess piece. The color enumeraiton holds the corresponding
 * ansi code for the color to be shown in the terminal.
 * to change to that specified
 * @author Joseph Oladeji
 * @author Thomas Kay
 * @author Levi Sweat
 * @author Nolan Flinchum
 * @version
 */
public enum GameColor{
    
    /** Black color enumeration */ /** Black color enumeration */
    BLACK("\u001b[30m"), WHITE("\u001b[37m");

    /** The ansi code representing the corresponding enumeration */
    private String ansiCode;

    /** This constructor initializes the color of the enumeration to the
     * needed ansi code for the terminal
     */
    private GameColor(String ansiCode){
        this.ansiCode = ansiCode;
    }

    /**
     * This toString returns the ansiCode of the specified enumeration.
     */
    public String toString(){
        return ansiCode;
    }

}