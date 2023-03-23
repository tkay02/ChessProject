package src.model;
/**
 * A class that uses ranks and files to represent a position on a chess board.
 * 
 * @author A-Team
 * @version 3/23/23
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
    public Position(int rank, int file){
        switch(rank){
            case 0:
                this.rank = Rank.R8;
                break;
            case 1:
                this.rank = Rank.R7;
                break;
            case 2:
                this.rank = Rank.R6;
                break;
            case 3:
                this.rank = Rank.R5;
                break;
            case 4:
                this.rank = Rank.R4;
                break;
            case 5:
                this.rank = Rank.R3;
                break;
            case 6:
                this.rank = Rank.R2;
                break;
            case 7:
                this.rank = Rank.R1;
        }

        switch(file){
            case 0:
                this.file = File.A;
                break;
            case 1:
                this.file = File.B;
                break;
            case 2:
                this.file = File.C;
                break;
            case 3:
                this.file = File.D;
                break;
            case 4:
                this.file = File.E;
                break;
            case 5:
                this.file = File.F;
                break;
            case 6:
                this.file = File.G;
                break;
            case 7:
                this.file = File.H;
        }
    }

}
