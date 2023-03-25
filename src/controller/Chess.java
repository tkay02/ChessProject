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
import src.interfaces.PieceIF;
//import src.model.Position;
import src.model.Board;
import src.enums.File;
import src.enums.Rank;

public class Chess {

	/* The board to play chess on */
	private Board board;

	/**
	 * Constructor for the game of chess.
	 */
	public Chess() {
		this.newGame();
	}
	
	/**
	 * Sets up a new game of chess.
	 */
	public void newGame() {
		board = new Board();
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
		//Position fromPos = new Position(fromR, fromF);
		//Position toPos = new Position(toR, toF);
		PieceIF piece = board.getPiece(fromR, fromF);
		//incomplete
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