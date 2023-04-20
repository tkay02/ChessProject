package src.model;

import src.interfaces.BlackAndWhiteIF;
import src.enums.GameColor;

/**
 * Class that represnts the color black and white.
 * Acts as a superclass for squares and pieces in chess.
 * 
 * @author Nolan Flinchum (100%), Thomas Kay, Joseph Oladeji, Levi Sweat
 * @version 4/19/2023
 */
public class BlackAndWhite implements BlackAndWhiteIF {
    
    /* Represents the actual color of an object */
    private GameColor color;

    /* Boolean to determine if an object is "black" */
    private boolean isBlack;

    /* Boolean to determine if an object is "white" */
    private boolean isWhite;

    /**
     * Constructor for a BlackAndWhite object.
     * 
     * @param color color of an object
     */
    public BlackAndWhite(GameColor color){
        this.color = color;
        this.isBlack = false;
        this.isWhite = false;
    }

    /**
     * Getter for the color of an object.
     * 
     * @return the actual color of an object
     */
    public GameColor getColor(){
        return this.color;
    }

    /**
     * Setter for the color of an object.
     * 
     * @param color GameColor enum used for the actual color
     */
    public void setColor(GameColor color){
        this.color = color;
    }

    /**
     * Determines if a piece is "black"
     * 
     * @return boolean true if an object is "black", false otherwise
     */
    public boolean isBlack(){
        return this.isBlack;
    }

    /**
     * Determines if a piece is "white"
     * 
     * @return boolean true if an object is "white", false otherwise
     */
    public boolean isWhite(){
        return this.isWhite;
    }

    /**
     * Set an object to "black"
     * 
     * @param black boolean that determines if an object is "black"
     */
    public void setBlack(boolean black){
        if(black) this.isBlack = true;
    }

    /**
     * Set an object to "white"
     * 
     * @param white boolean that determines if an object is "white"
     */
    public void setWhite(boolean white){
        if(white) this.isWhite = true;
    }

}