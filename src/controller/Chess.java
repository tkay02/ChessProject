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
		boolean correctPiece = false;
		int playerTurn = 0;
		Scanner input = new Scanner(System.in);
		String stringRank = "";
		int intRank = 0;
		String stringFile = "";
		char charFile = 'q';
		String resignation = "";
		boolean keepGoing = true;
		while(playing){
			correctPiece = false;
			this.board.draw();
			if(playerTurn % 2 == 0){
				while(!correctPiece && playing){
					System.out.println("Player One's Turn (White Pieces). Type 'Y' to resign, or anything else to play.");
					try{
						resignation = input.nextLine();
						keepGoing = true;
					}
					catch(Exception e){
						System.out.println("Input Error: e");
						keepGoing = false;
						input.nextLine();
					}
						if(!resignation.equals("Y") && keepGoing){
							System.out.println("Type the rank of the piece you'd like to "
											+  "move.\nExample Input: 2");
							try{
								stringRank = input.nextLine();
								keepGoing = true;
							}catch(Exception e){
								keepGoing = false;
								System.out.println("Error: " + e);
								input.nextLine();
							}
							if(keepGoing){
								try{
									intRank = Integer.parseInt(stringRank);
									keepGoing = true;
								} catch(Exception e){
									System.out.println("Error: " + e);
									keepGoing = false;
								}
							}
							if(keepGoing){
								System.out.println("Type the file of the piece you'd like to move."
												+  "\nExample Input: B");							
								try{
									stringFile = input.nextLine();
									keepGoing = true;
									charFile = stringFile.trim().charAt(0);
								} catch(Exception e){
									keepGoing = false;
									System.out.println("Error: " + e);
									input.nextLine();
								}
							}
							if((Integer.compare(intRank, 10) <= 0 && Integer.compare(intRank, 0) >= 0) &&
							(charFile == 'A' || charFile == 'B' || charFile == 'C' || 
								charFile == 'D' || charFile == 'E' || charFile == 'F' || 
								charFile == 'G' || charFile == 'G') && keepGoing){
									Rank fromRank = Rank.getRankByReal(intRank);
									File fromFile = File.getFileByChar(charFile);
									Position pos = new Position(fromRank, fromFile);
									Piece piece = (Piece) board.getPiece(fromRank, fromFile);
									while(piece.isWhite() && keepGoing){
										System.out.println("Type the rank of the square you'd"
														+ " like to move to.\nExample Input: 4");
										try{
											stringRank = input.nextLine();
											keepGoing = true;
										}catch(Exception e){
											keepGoing = false;
											System.out.println("Error1: " + e);
											input.nextLine();
										}
										if(keepGoing){				
											try{
												intRank = Integer.parseInt(stringRank);
												keepGoing = true;
											} catch(Exception e){
												System.out.println("Error2: " + e);
												keepGoing = false;
											}
										}
										if(keepGoing){
											System.out.println("Type the file of the square you'd like to move to.\nExample: B");
											try{
												stringFile = input.nextLine();
												charFile = stringFile.trim().charAt(0);
												keepGoing = true;
											} catch(Exception e){
												keepGoing = false;
												System.out.println("Error: " + e);
												input.nextLine();
											}
										}
										if((Integer.compare(intRank, 10) <= 0 && Integer.compare(intRank, 0) >= 0) &&
										(charFile == 'A' || charFile == 'B' || charFile == 'C' || 
											charFile == 'D' || charFile == 'E' || charFile == 'F' || 
											charFile == 'G' || charFile == 'G') && keepGoing){
											correctPiece = true;
											ArrayList<Position> aL = piece.showMoves(pos);
											// for(Position posn : aL){
											// 	System.out.print("Valid Position: (" + posn.getRank().getRealRank() + " " + posn.getFile().getRealFile() + ") ");
											// }
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
										else System.out.println("Try again.");
									}
									if(!piece.isWhite()){
										System.out.println("Try again.");
									}
							}
							else System.out.println("Try again.");
					}
					else playing = false;
				}
			}
			else{
				while(!correctPiece && playing){
					System.out.println("Player Two's Turn (Black Pieces). Type 'Y' to resign, or anything else to play.");
					try{
						resignation = input.nextLine();
						keepGoing = true;
					}
					catch(Exception e){
						System.out.println("Input Error: e");
						keepGoing = false;
						input.nextLine();
					}
						if(!resignation.equals("Y") && keepGoing){
							System.out.println("Type the rank of the piece you'd like to "
											+  "move.\nExample Input: 7");
							try{
								stringRank = input.nextLine();
								keepGoing = true;
							}catch(Exception e){
								keepGoing = false;
								System.out.println("Error: " + e);
								input.nextLine();
							}
							if(keepGoing){
								try{
									intRank = Integer.parseInt(stringRank);
									keepGoing = true;
								} catch(Exception e){
									System.out.println("Error: " + e);
									keepGoing = false;
								}
							}
							if(keepGoing){
								System.out.println("Type the file of the piece you'd like to move."
												+  "\nExample Input: B");							
								try{
									stringFile = input.nextLine();
									keepGoing = true;
									charFile = stringFile.trim().charAt(0);
								} catch(Exception e){
									keepGoing = false;
									System.out.println("Error: " + e);
									input.nextLine();
								}
							}
							if((Integer.compare(intRank, 10) <= 0 && Integer.compare(intRank, 0) >= 0) &&
							(charFile == 'A' || charFile == 'B' || charFile == 'C' || 
								charFile == 'D' || charFile == 'E' || charFile == 'F' || 
								charFile == 'G' || charFile == 'G') && keepGoing){
									Rank fromRank = Rank.getRankByReal(intRank);
									File fromFile = File.getFileByChar(charFile);
									Position pos = new Position(fromRank, fromFile);
									Piece piece = (Piece) board.getPiece(fromRank, fromFile);
									while(piece.isBlack() && keepGoing){
										System.out.println("Type the rank of the square you'd"
														+ " like to move to.\nExample Input: 5");
										try{
											stringRank = input.nextLine();
											keepGoing = true;
										}catch(Exception e){
											keepGoing = false;
											System.out.println("Error1: " + e);
											input.nextLine();
										}
										if(keepGoing){				
											try{
												intRank = Integer.parseInt(stringRank);
												keepGoing = true;
											} catch(Exception e){
												System.out.println("Error2: " + e);
												keepGoing = false;
											}
										}
										if(keepGoing){
											System.out.println("Type the file of the square you'd like to move to.\nExample: B");
											try{
												stringFile = input.nextLine();
												charFile = stringFile.trim().charAt(0);
												keepGoing = true;
											} catch(Exception e){
												keepGoing = false;
												System.out.println("Error: " + e);
												input.nextLine();
											}
										}
										if((Integer.compare(intRank, 10) <= 0 && Integer.compare(intRank, 0) >= 0) &&
										(charFile == 'A' || charFile == 'B' || charFile == 'C' || 
											charFile == 'D' || charFile == 'E' || charFile == 'F' || 
											charFile == 'G' || charFile == 'G') && keepGoing){
											correctPiece = true;
											ArrayList<Position> aL = piece.showMoves(pos);
											// for(Position posn : aL){
											// 	System.out.print("Valid Position: (" + posn.getRank().getRealRank() + " " + posn.getFile().getRealFile() + ") ");
											// }
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
										else System.out.println("Try again.");
									}
									if(!piece.isBlack()){
										System.out.println("Try again.");
									}
							}
							else System.out.println("Try again.");
					}
					else playing = false;
				}
			}

			//Position pos = new Position(fromRank, fromFile);
			//Piece piece = (Piece) board.getPiece(fromRank, fromFile);



			// System.out.println("Rank of square to move to: ");
			// Rank toRank = Rank.getRankByReal(in.nextInt());
			// System.out.println("Rank of square to move to: ");
			// File toFile = File.getFileByChar(in.next().charAt(0));

			// move(fromFile, fromRank, toFile, toRank);
			// piece.setHasMoved();
			// playerOneTurn = !playerOneTurn;
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
		System.out.print("What we enter: (" + toPos.getRank().getArrayRank() + " " + toPos.getFile().getRealFile() + ") ");

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