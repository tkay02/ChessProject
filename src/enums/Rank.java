package enums;

public enum Rank {
    R1(1, 0), R2(2, 1), 
    R3(3, 2), R4(4, 3),
    R5(5, 4), R6(6, 5), 
    R7(7, 6), R8(8, 7);

    private int realRank;
    private int arrayRank;

    private Rank(int realRank, int arrayRank){
        this.realRank = realRank;
        this.arrayRank = arrayRank;
    }

    public int getRealRank(){
        return realRank;
    }

    public int getArrayRaml(){
        return arrayRank;
    }

    public String toString(){
        return "";
    }

}
