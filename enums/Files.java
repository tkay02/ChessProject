package enums;

public enum Files {
    A("A", 0),  B("B", 1),  
    C("C", 2),  D("D", 3),  
    E("E", 4),  F("F", 5), 
    G("G", 6),  H("H", 7);

    private String realFile;
    private int arrayFile;

    private Files(String realFile, int arrayFile){
        this.realFile = realFile;
        this.arrayFile = arrayFile;
    }

    public String getRealFile(){
        return realFile;
    }

    public int arrayFile(){
        return arrayFile;
    }

    public String toString(){
        return "";
    }
}
