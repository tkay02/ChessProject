package src.controller;
/**
 * Represents the game of chess. In the future, this class will allow users to start games,
 * end games, save games, load games, and other operations that relate to the ChessMeister
 * service.
 * 
 * @author Nolan Flinchum, Thomas Kay, Joseph Oladeji, Levi Sweat
 * @version 3/27/2023
 */
import src.interfaces.BoardIF;
import src.interfaces.BoardStrategy;
import src.interfaces.PieceIF;
import src.model.Board;
import src.model.Piece;
import src.model.Position;
import src.model.Square;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import src.enums.ChessPieceType;
import src.enums.File;
import src.enums.Rank;

public class Chess {

	/* The board to play chess on */
	private Board board;
	private Scanner input;
	private String resignation;
	private String stringRank;
	private int intRank;
	private String stringFile;
	private char charFile;
	private ArrayList<String> resignationList =  new  ArrayList<>();
	private ArrayList<String> fileList = new ArrayList<>();
	private ArrayList<String> rankList = new  ArrayList<>();


	/**
	 * Constructor for the game of chess.
	 */
	public Chess(BoardStrategy drawStrategy) {
		this.input = new Scanner(System.in);
		this.resignationList.add("Y");
		this.fileList.add("A");
		this.fileList.add("B");
		this.fileList.add("C");
		this.fileList.add("D");
		this.fileList.add("E");
		this.fileList.add("F");
		this.fileList.add("G");
		this.fileList.add("H");
		this.rankList.add("1");
		this.rankList.add("2");
		this.rankList.add("3");
		this.rankList.add("4");
		this.rankList.add("5");
		this.rankList.add("6");
		this.rankList.add("7");
		this.rankList.add("8");
		this.newGame(drawStrategy);
	}

	public ArrayList<String> getRankList(){
		return this.rankList;
	}
	
	public String prompt(String question, ArrayList<String> list){
		boolean promptAgain = true;
		String result = "";
		while(promptAgain){
			System.out.println(question);
			result = input.nextLine();
			if(list.contains(result)) promptAgain = false;
		}
		return result;
	}

	public boolean playTurn(String player){
		boolean keepGoing = true;
		boolean resigned = false;
		boolean pieceChecker;
		String color = "";
		if(player.equals("One")) color = "White";
		else color = "Black";
		while(keepGoing){
			System.out.println("Player " + player + "'s Turn (" + color + " Pieces). Type 'Y' to resign, or anything else to continue.");
			resignation = input.nextLine();
			if(!resignation.equals("Y")){
				stringRank = prompt("Type the rank of the piece you'd like to move.", getRankList());
				intRank = Integer.parseInt(stringRank);						
				stringFile = prompt("Type the file of the piece you'd like to move.", fileList);
				charFile = stringFile.trim().charAt(0);
				Rank fromRank = Rank.getRankByReal(intRank);
				File fromFile = File.getFileByChar(charFile);
				Position pos = new Position(fromRank, fromFile);
				Piece piece = (Piece) board.getPiece(fromRank, fromFile);
				if(player.equals("One")) pieceChecker = piece.isWhite();
				else pieceChecker = piece.isBlack();
				if(pieceChecker){
					ArrayList<Position> aL = piece.showMoves(pos);

					System.out.print("\nValid Moves: ");
					for(Position posn : aL){
						System.out.print("(" + posn.getRank().getRealRank() + "," + posn.getFile().getRealFile() + ") ");
					}
					System.out.println("\n");

					stringRank = prompt("Type the rank of the square you'd like to move to.", rankList);
					intRank = Integer.parseInt(stringRank);
					stringFile = prompt("Type the file of the square you'd like to move to.", fileList);
					charFile = stringFile.trim().charAt(0);
					Rank toRank = Rank.getRankByReal(intRank);
					File toFile = File.getFileByChar(charFile);
					if(move(fromFile, fromRank, toFile, toRank)){
						keepGoing = false;
						piece.setHasMoved();
					}
					else{
						System.out.println("Try Again.");
						keepGoing = true;
					}
				}
			}
			else{
				keepGoing = false;
				resigned = true;
			}
			//ArrayList<Position> aL = piece.showMoves(pos);
			// for(Position posn : aL){
			// 	System.out.print("Valid Position: (" + posn.getRank().getRealRank() + " " + posn.getFile().getRealFile() + ") ");
			// }
		}
		return resigned;
	}
	/**
	 * Sets up a new game of chess.
	 */
	public void newGame(BoardStrategy drawStrategy) {
		this.board = new Board();
		this.board.setDrawStrategy(drawStrategy);
		boolean playing = true;
		int playerTurn = 0;
		while(playing){
			this.board.draw();
			if(playerTurn % 2 == 0){
				if(playTurn("One")) playing = false;
			}
			else{
				if(playTurn("Two")) playing = false;	
			}
			playerTurn++;
		}
		input.close();
	}
	
	/**
	 * Performs steps to end the game of chess.
	 */
	public void endGame() {
		
	}
	
	/**
	 * Setup for loading a game in.
	 * 
	 * @param file
	 * @return
	 */
	public BoardIF loadGame(String file) {
		return new Board();
	}
	
	/**
	 * Process of saving a game.
	 * 
	 * @param file Name of file to save game as
	 * @param game Interface of game to be saved
	 */
	public void saveGame(String file, BoardIF game) {
		//BoardIF in other package is not being recognized?
	}

	/**
	 * Moves piece and updates the board.
	 * 
	 * @param fromF File of piece to be moved
	 * @param fromR Rank of piece to be moved
	 * @param toF File of where piece is being moved to
	 * @param toR Rank of where piece is being moved to
	 */
	public boolean move(File fromF, Rank fromR, File toF, Rank toR) {
		Position toPos = new Position(toR, toF);

		boolean result = true;
		Piece piece = (Piece) board.getPiece(fromR, fromF);
		if(piece.validateMove(toPos)){
			int fromFileNum = fromF.getArrayFile();
			int fromRankNum = fromR.getArrayRank();
			int toFileNum = toF.getArrayFile();
			int toRankNum = toR.getArrayRank();

			Square fromSquare = (Square) board.getSquare(fromRankNum, fromFileNum);
			Square toSquare = (Square) board.getSquare(toRankNum, toFileNum);

			toSquare.setPiece(fromSquare.getPiece());
			fromSquare.clear();
		}
		else{
			result = false;
		}
		return result;
	}

}