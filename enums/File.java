package enums;

/**
 * This class models 
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

    private String realFile; /** This represents the actual File (column) that 
    will be displayed of the board */
    private int arrayFile; /** This represents the array index (column) of the board */

   
    /**
     * This method
     * @param realFile
     * @param arrayFile
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
