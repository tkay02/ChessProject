package src.model;

import src.enums.File;
import src.enums.Rank;

/**
 * A class that uses ranks and files to represent a position on a chess board.
 * 
 * @author Nolan Flinchum (70%), Thomas Kay, Joseph Oladeji (30%), Levi Sweat
 * @version 3/27/2023
 */

public class Position {

    /** Rank enum to represent the position's rank **/
    private Rank rank;

    /** File enum to represent the position's file **/
    private File file;

    /**
     * Constructor for the Position class.
     * 
     * @param rank number to represent the rank of the chess board
     * @param file number to represent the file of the chess board
     */
    public Position(Rank rank, File file) {
        this.rank = rank;
        this.file = file;
    }

    /**
     * Getter for the rank of the position.
     * 
     * @return the rank of the position
     */
    public Rank getRank(){
        return this.rank;
    }

    /**
     * Getter for the file of the position.
     * 
     * @return the file of the position
     */
    public File getFile(){
        return this.file;
    }

    /**
     * Compares two Position objects and checks for equality
     * 
     * @param pos other Position to compare
     * @return true if equal, false otherwise
     */
    public boolean equals(Position pos){
        return this.rank.equals(pos.getRank()) && this.file.equals(pos.getFile());
    }

    /**
     * Returns the string representation of the position. Places the file in front of the rank in regards to
     * the current position. Can be used to store positions within a file.
     */
    public String toString() {
        return "" + this.file + this.rank;
    }
    
}
