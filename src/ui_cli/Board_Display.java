package src.ui_cli;
import src.interfaces.BoardStrategy;
import src.interfaces.BoardIF;
import src.model.Piece;
import src.model.Square;

public abstract class Board_Display implements BoardStrategy{
    
    /** String built to display the board once completed. **/
	private String toDisplay;

	/** Piece representing the current piece when building the board. **/
	private Piece piece;

	/**
	 * Constructor for Board_Color_CLI class. Initilaizes toDisplay.
	 */
	public Board_Display(){
		this.toDisplay = "";
	}
    
    /**
	 * Draws the board with colors, with blue colored pieces representing white pieces and red 
	 * colored pieces representing black pieces. Also displays the rank and file to help user
	 * when making moves, and displays the pieces that have been taken thus far by each player.
	 * 
	 * @param board the chess board to draw
	 */
	public void drawWhite(BoardIF board) {
        toDisplay = "\n";
		for(int i = 0; i < board.getWidth(); i++){ //iterate through board
			toDisplay += displayRank(board.getWidth() - i); //rank to display on side of board
			for(int j = 0; j < board.getHeight(); j++){
				piece = (Piece)board.getPiece(i, j);
				//if the square is white
				if(((Square)board.getSquares()[i][j]).isWhite()){
                    toDisplay += whiteSquare(piece);
                }
				else{//square must be black
                    toDisplay += blackSquare(piece);
				}
			}
            toDisplay += "\n";
		}
        toDisplay += displayWhiteFile(); //display the file along the bottom of the board
        toDisplay += takenPieces(board); //display the pieces that have been taken
		System.out.println(toDisplay); //print out the string
	}

    public void drawBlack(BoardIF board) {
        toDisplay = "\n";
		for(int i = 7; i >= 0; i--){ //iterate through board
			toDisplay += displayRank(board.getWidth() - i); //rank to display on side of board
			for(int j = 7; j >= 0; j--){
				piece = (Piece)board.getPiece(i, j);
				//if the square is white
				if(((Square)board.getSquares()[i][j]).isWhite()){
                    toDisplay += whiteSquare(piece);
                }
				else{//square must be black
                    toDisplay += blackSquare(piece);
				}
			}
            toDisplay += "\n";
		}
        toDisplay += displayBlackFile(); //display the file along the bottom of the board
        toDisplay += takenPieces(board); //display the pieces that have been taken
		System.out.println(toDisplay); //print out the string
	}

    public abstract String whiteSquare(Piece piece);

    public abstract String blackSquare(Piece piece);

    public abstract String takenPieces(BoardIF Board);

    public abstract String displayRank(int rank);

    public abstract String displayWhiteFile();

    public abstract String displayBlackFile();

}
