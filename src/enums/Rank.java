package src.enums;

public enum Rank {
    R1(1, 7), R2(2, 6), 
    R3(3, 5), R4(4, 4),
    R5(5, 3), R6(6, 2), 
    R7(7, 1), R8(8, 0);

    private int realRank;
    private int arrayRank;

    private Rank(int realRank, int arrayRank){
        this.realRank = realRank;
        this.arrayRank = arrayRank;
    }

    public int getRealRank(){
        return realRank;
    }

    public int getArrayRank(){
        return arrayRank;
    }

    public Rank getRankByIndex(int index){
        return Rank.values()[index];
    }

    public String toString(){
        return "";
    }

}
