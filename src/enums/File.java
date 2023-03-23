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
    A("A", 0),  B("B", 1),  
    C("C", 2),  D("D", 3),  
    E("E", 4),  F("F", 5), 
    G("G", 6),  H("H", 7);

    private String realFile;
    private int arrayFile;

    /**
     * 
     * @param
     * @param
     */
    private File(String realFile, int arrayFile){
        this.realFile = realFile;
        this.arrayFile = arrayFile;
    }

    /**
     * 
     * @return
     */
    public String getRealFile(){
        return realFile;
    }

    /**
     * 
     * @return
     */
    public int getArrayFile(){
        return arrayFile;
    }

    /**
     * 
     * @return
     */
    public String toString(){
        return "";
    }
}
