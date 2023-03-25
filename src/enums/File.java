package src.enums;

/**
 * This enumeration class models the File also known as the column for the
 * chess board. The letters representing the possible selections for the
 * columns and they have the realFile, which is the column letter that will
 * be displayed to the user and the array file, which is the corresponding
 * index within the array.
 * 
 * @author Joseph Oladeji
 * @author Thomas Kay
 * @author Levi Sweat
 * @author Nolan Flinchum
 * @version
 */

public enum File {

    A('A', 0), B('B', 1),
    C('C', 2), D('D', 3),
    E('E', 4), F('F', 5),
    G('G', 6), H('H', 7);

    private char realFile;
    private int arrayFile;

    /**
     * This constructor will intialize the realFile and arrayFile
     * of the enumeration.
     * 
     * @param realFile  - Column letter that will be displayed to the user
     * @param arrayFile - Column index to access the array.
     */
    private File(char realFile, int arrayFile) {
        this.realFile = realFile;
        this.arrayFile = arrayFile;
    }

    /**
     * 
     * @return
     */
    public char getRealFile() {
        return realFile;
    }

    /**
     * 
     * @return
     */
    public int getArrayFile() {
        return arrayFile;
    }

    /**
     * This method iterates through all the file enumerations
     * and gets the corresponding
     * 
     * @param index
     * @return
     */
    public File getFileByIndex(int index) {
        return File.values()[index];
    }

    /**
     * 
     * @return
     */
    public String toString() {
        return "";
    }
}
