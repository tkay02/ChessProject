package src.ui_gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ChessView extends ImageView {

    public ChessSquare currentSquare;

    public final int MAX_SIZE = 64;
    
    public ChessView(){
        super();
    }

    public ChessView(Image view){
        super(view);
    }

    public void setChessSquare(ChessSquare square){
        this.currentSquare = square;
    }

    public ChessSquare getChessSquare(){
        return currentSquare;
    }

    public ChessView getCopy(){
        ChessView imgCopy = new ChessView(getImage());
        imgCopy.fitWidthProperty().bind(currentSquare.widthProperty());
        imgCopy.fitHeightProperty().bind(currentSquare.heightProperty());
        return imgCopy;
    }
}
