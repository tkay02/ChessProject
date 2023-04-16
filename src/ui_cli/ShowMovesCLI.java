package src.ui_cli;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import src.enums.ChessPieceType;
import src.enums.File;
import src.enums.Rank;
import src.interfaces.BoardIF;
import src.interfaces.ShowMovesIF;
import src.model.Piece;
import src.model.Position;

public class ShowMovesCLI implements ShowMovesIF{

    /** The input to scan the user's input **/
    private Scanner input;    

	/** ArrayList representing valid inputs for the chess board's file **/
	private ArrayList<String> fileList = new ArrayList<>();
	
	/** ArrayList representing valid inputs for the chess board's rank **/
	private ArrayList<String> rankList = new ArrayList<>();

    public ShowMovesCLI(){
        this.input = new Scanner(System.in);
		this.fileList.addAll(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "a", "b", "c",
        "d", "e", "f", "g", "h"));
		this.rankList.addAll(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8"));
    }

    public String showMoves(BoardIF board, int turn){
        boolean keepGoing = true;
        String userInput = "";
        while(keepGoing){
            System.out.println("Show moves for what piece? ");
            userInput = input.nextLine();

            if(fileList.contains(String.valueOf(userInput.charAt(0)))) keepGoing = false;
            if(rankList.contains(String.valueOf(userInput.charAt(1)))) keepGoing = false;
        }
        File fromF = File.getFileByChar(userInput.charAt(0));
        Rank fromR = Rank.getRankByReal(Character.getNumericValue(userInput.charAt(1)));
        Piece piece = (Piece) board.getPiece(fromR, fromF);
        if(piece.getChessPieceType() != ChessPieceType.EMPTY){
 		    if(turn % 2 == 0) board.draw(true, piece.showMoves(new Position(fromR, fromF)));
            else board.draw(false, piece.showMoves(new Position(fromR, fromF)));
        }
        else{
            System.out.println("Error, no piece at " + userInput);
        }
        return "";
    }
    
}
