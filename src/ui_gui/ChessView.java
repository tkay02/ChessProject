package src.ui_gui;

import javafx.scene.image.ImageView;

public class ChessView extends ImageView {

    public ChessSquare currentSquare;

    public ChessView(){
        super();
    }

    public void setChessSquare(ChessSquare square){
        this.currentSquare = square;
    }

    public ChessSquare getChessSquare(){
        return currentSquare;
    }
}
