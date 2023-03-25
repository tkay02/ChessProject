package src.model;
/**
 * A class that uses ranks and files to represent a position on a chess board.
 * 
 * @author Nolan Flinchum, Thomas Kay, Joseph Oladeji, Levi Sweat
 * @version 3/27/2023
 */
import src.enums.File;
import src.enums.Rank;

public class Position {

    /* Rank enum to represent the position's rank */
    private Rank rank;

    /* File enum to represent the position's file */
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

}
