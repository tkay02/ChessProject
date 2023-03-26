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
import java.util.Scanner;

import src.enums.ChessPieceType;
import src.enums.File;
import src.enums.Rank;

public class Chess {

	/* The board to play chess on */
	private Board board;

	/**
	 * Constructor for the game of chess.
	 */
	public Chess(BoardStrategy drawStrategy) {
		this.newGame(drawStrategy);
	}
	
	/**
	 * Sets up a new game of chess.
	 */
	public void newGame(BoardStrategy drawStrategy) {
		this.board = new Board();
		this.board.setDrawStrategy(drawStrategy);
		boolean playing = true;
		String playerOne = "Player One's Turn";
		String playerTwo = "Player Two's Turn";
		boolean playerOneTurn = true;
		Scanner in = new Scanner(System.in);
		while(playing){
			System.out.println("Resign? (Y/N): ");
			if(in.nextLine().equals("Y")) playing = false;

			this.board.draw();
			if(playerOneTurn) System.out.println(playerOne + "\nRank of piece to move: ");
			else System.out.println(playerTwo + "\nRank of piece to move: ");
			Rank fromRank = Rank.getRankByIndex(in.nextInt());
			System.out.println("File of piece to move: ");
			File fromFile = File.getFileByChar(in.next().charAt(0));

			Position pos = new Position(fromRank, fromFile);
			Piece piece = (Piece) board.getPiece(fromRank, fromFile);
			ArrayList<Position> aL = piece.showMoves(pos);
			for(Position posn : aL){
				System.out.print("Valid Position: (" + posn.getRank().getArrayRank() + " " + posn.getFile().getRealFile() + ") ");
			}


			System.out.println("Rank of square to move to: ");
			Rank toRank = Rank.getRankByIndex(in.nextInt());
			System.out.println("Rank of square to move to: ");
			File toFile = File.getFileByChar(in.next().charAt(0));

			move(fromFile, fromRank, toFile, toRank);
			piece.setHasMoved();
			playerOneTurn = !playerOneTurn;
		}

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
	public void move(File fromF, Rank fromR, File toF, Rank toR) {
		Position toPos = new Position(toR, toF);
		System.out.print("What we enter: (" + toPos.getRank().getArrayRank() + " " + toPos.getFile().getRealFile() + ") ");

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
			System.out.println("Idiot\n");
		}
	}

	//Are the functions below necessary?

	public void startGame(){
		System.out.println("Let the game begin!\n");
	}

	public void printBoard(){

	}

	public Board getBoard(){
		return this.board;
	}

}