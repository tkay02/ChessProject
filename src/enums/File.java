package src.enums;
/**
 * This enumeration class models the File also known as the column for the
 * chess board. The letters representing the possible selections for the
 * columns and they have the realFile, which is the column letter that will
 * be displayed to the user and the array file, which is the corresponding
 * index within the array.
 * 
 * @author Nolan Flinchum, Thomas Kay, Joseph Oladeji, Levi Sweat
 * @version 3/27/2023
 */

public enum File {

    A('A', 0), B('B', 1), C('C', 2), D('D', 3),
    E('E', 4), F('F', 5), G('G', 6), H('H', 7);

    /* The letter to represent a file on a chess board */
    private char realFile;

    /* The number to represent a file on a chess board */
    private int arrayFile;

    /**
     * This constructor will intialize the realFile and arrayFile
     * of the enumeration.
     * 
     * @param realFile column letter that will be displayed to the user
     * @param arrayFile column index to access the array.
     */
    private File(char realFile, int arrayFile) {
        this.realFile = realFile;
        this.arrayFile = arrayFile;
    }

    /**
     * Gets the letter to represent a file.
     * 
     * @return the letter to represent a file
     */
    public char getRealFile() {
        return realFile;
    }

    /**
     * Gets the number to represent a file.
     * 
     * @return the number to represent a file
     */
    public int getArrayFile() {
        return arrayFile;
    }

    /**
     * This method gets the file when given an index to represent the file.
     * 
     * @param index number to represent the file
     * @return the file represented by the index
     */
    public File getFileByIndex(int index) {
        return File.values()[index];
    }

    /**
     * Do we really need this?
     * 
     * @return
     */
    public String toString() {
        return "";
    }
    
}
