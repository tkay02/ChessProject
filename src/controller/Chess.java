package src.controller;

import src.enums.File;
import src.enums.Rank;
import src.interfaces.BoardIF;
import src.model.Position;
import src.model.Board;


public class Chess {

	BoardIF board;

	/**
	 * Constructor for game of Chess.
	 */
	public Chess() {
		this.board = new Board();
	}
	
	/**
	 * First steps when setting up a new game of Chess
	 */
	public void newGame() {
		
	}
	
	/**
	 * Final steps for recording information when ending a game and what to tell players.
	 */
	public void endGame() {
		
	}
	
	/**
	 * Setup for loading a game in.
	 * @param file
	 * @return
	 */
	public BoardIF loadGame(String file) {
		
	}
	
	/**
	 * Process of saving a game.
	 * @param file Name of file to save game as
	 * @param game Interface of game to be saved
	 */
	public void saveGame(String file, BoardIF game) {
		//BoardIf in other package is not being recognized?
	}

	/**
	 * Moves piece and updates board.
	 * @param fromF File of piece to be moved
	 * @param fromR Rank of piece to be moved
	 * @param toF File of where piece is being moved to
	 * @param toR Rank of where piece is being moved to
	 */
	public void move(File fromF, Rank fromR, File toF, Rank toR) {
		Position fromPos = new Position(fromR, fromF);
		Position toPos = new Position(toR, toF);
		PieceIF piece = chessBoard.getPiece(fromR, fromF);


	}

}



