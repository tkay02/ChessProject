package src.controller;

import src.interfaces.*;
import src.model.Board;
import src.model.Move;
import src.model.Piece;
import src.model.Position;
import src.model.Square;
import src.ui_cli.BoardColorCLI;
import src.ui_cli.BoardMonoCLI;
import src.ui_cli.MainMenuCLI;
import src.ui_cli.PlayChessCLI;
import src.ui_cli.RulesCLI;
import src.ui_cli.SettingsCLI;
import src.ui_cli.ShowMovesCLI;

import java.util.ArrayList;
import java.util.LinkedList;

import src.enums.ChessPieceType;
import src.enums.File;
import src.enums.Rank;

/**
 * Represents the game of chess. In the future, this class will allow users to start games,
 * end games, save games, load games, and other operations that relate to the ChessMeister
 * service. Currently, it includes the majority of the logic allowing users to play the match,
 * including error handling if the user makes faulty inputs.
 * 
 * @author Nolan Flinchum (5%), Thomas Kay (5%), Joseph Oladeji (5%), Levi Sweat (85%)
 * @version 3/27/2023
 */

public class Chess {

	/** The board to play chess on **/
	private Board board;

	/** Strategy for drawing the board **/
	private BoardStrategy drawStrat;

	/** Used for main menu screen **/
	private MainMenuIF mainMenu;

	/** Used for Rules Screen **/
	private RulesIF rulesDisplay;

	/** Used for settings screen **/
	private SettingsIF settingsDisplay;

	/** Used for playing match screen **/
	private PlayChessIF playChess;

	/** Used to show moves of a piece **/
	private ShowMovesIF showMovesDisplay;

	/** True if players can undo, false if undo is off **/
	private boolean undo;

	/** True if possible moves are displayed, false if not **/
	private boolean showMoves;

	/** Arraylist of the positions of the board. **/
	private LinkedList<Move> moves;

	/** Index for the moves LinkedList. **/
	private int movesIndex;

	/**
	 * Constructor for the game of chess. Initializes scanner, ArrayList's of valid inputs, and
	 * calls newGame with the user's drawStrategy to run the bulk of the program.
	 *
	 * @param BoardStrategy drawStrategy user's input for the chessboard
	 */
	public Chess(){
		this.board = new Board();
		drawStrat = new BoardColorCLI(); //default BoardStrategy is color
		board.setDrawStrategy(drawStrat); 

		this.mainMenu = new MainMenuCLI();

		this.rulesDisplay = new RulesCLI();
		this.settingsDisplay = new SettingsCLI();
		this.showMovesDisplay = new ShowMovesCLI();

		this.undo = true; //can undo by default
		this.showMoves = true; //can showMoves by default
		this.moves = new LinkedList<Move>();
		this.movesIndex = -1;
	}
	

	/**
	 * Sets up and plays a new game of chess. Initializes the board strategy to the chess board.
	 * Determines which player is playing and ends the match if there is a resignation.
	 * 
	 * @param BoardStrategy drawStrategy Determines how the chess board is drawn.
	 */
	public void go() {
		boolean returnToMain = true;
		while(returnToMain){
			switch(mainMenu.userInteraction()){
				case "0":
					returnToMain = false;
					break;
				case "1":
					playGame();
					returnToMain = false;
					break;
				case "2":
					rulesDisplay.displayRules(); //NOT COMPLETED
					break;
				case "3":
					System.out.println("NOT CURRENTLY IMPLEMENTED.");
					break;
				case "4":
					settingsInteraction();
					break;
				case "5":
					System.out.println("NOT CURRENTLY IMPLEMENTED.");
			}
		}

	}
	
	public void settingsInteraction(){
		boolean returnToSettings = true;
		while(returnToSettings){
			String settingsInput = settingsDisplay.displaySettings(drawStrat, undo, showMoves);
			switch(settingsInput){
				case "0":
					returnToSettings = false;
					break;
				case "1":
					setDrawStrat("mono");
					break;
				case "2":
					setDrawStrat("color");
					break;
				case "3":
					setUndo(true);
					break;
				case "4":
					setUndo(false);
					break;
				case "5":
					setShowMoves(true);
					break;
				case "6":
					setShowMoves(false);
					break;
			}
		}

	}

	public void playGame(){
		this.playChess = new PlayChessCLI(undo, showMoves);
		this.board.setDrawStrategy(drawStrat);
		boolean playing = true;
		int playerTurn = 0;
		ArrayList<Position> empty = new ArrayList<>();
		while(playing){
			//Changes the current player turn
			if(playerTurn % 2 == 0) this.board.draw(true, empty);
			else this.board.draw(false, empty);

			if(playTurn()) playing = false;
			playerTurn++;
		}

	}

	public boolean playTurn(){
		boolean quit = false;
		boolean turnNotOver = true;
        while(turnNotOver){
            String userInput = playChess.playChessDisplay();
            switch(userInput){
                case "0":
					turnNotOver = false;
					quit = true;
					break;
                case "1": //Move
					String move = playChess.makeMove();
					String[] parts = move.split(",");
					parts[0] = parts[0].trim();
					parts[1] = parts[1].trim();
					File fromF = File.getFileByChar(parts[0].charAt(0));
					File toF = File.getFileByChar(parts[1].charAt(0));
					Rank fromR = Rank.getRankByReal(Character.getNumericValue(parts[0].charAt(1)));
					Rank toR = Rank.getRankByReal(Character.getNumericValue(parts[1].charAt(1)));
					PieceIF piece = board.getPiece(toR, toF); //the piece that will be captured
					if(board.getPiece(fromR, fromF).getChessPieceType() == ChessPieceType.EMPTY){
						System.out.println("Error, no piece at " + parts[0]);
					}
					else if(move(fromF, fromR, toF, toR)){
						((Piece)board.getPiece(fromR, fromF)).setHasMoved();

						while(this.moves.size() - 1 > this.movesIndex) this.moves.pop();
	
						Position fromPos = new Position(fromR, fromF);
						Position toPos = new Position(toR, toF);
						this.moves.add(new Move(fromPos, toPos, piece));
						this.movesIndex++;
						turnNotOver = false;
					}
					else System.out.println("Invalid Move");
					break;
				case "2":
					if(this.movesIndex >= 0){
						turnNotOver = false;
						undo(true);
					}
					else{
						System.out.println("Undo is unavailable right now");
					}
					break;
				case "3":
					if(this.movesIndex < this.moves.size() - 1){
						turnNotOver = false;
						redo();
					}
					else{
						System.out.println("Redo is unavailable right now");
					}
					break;
				case "4":
					showMovesDisplay.showMoves(this.board);
					break;
				case "5":
					//SAVE GAMEEE
					break;
            }
        }
		return quit;
	}

	public void undo(boolean userUndo){
		Move lastMove = this.moves.get(this.movesIndex);
		Position toPos = lastMove.getFromPos();
		Position fromPos = lastMove.getToPos();
		Rank fromR = fromPos.getRank();
		File fromF = fromPos.getFile();
		Piece takenPiece = (Piece) lastMove.getPiece();
		String takenPieceLetter = takenPiece.getChessPieceType().getChessPieceLetter();
		System.out.println(takenPieceLetter);
		forceMove(fromF, fromR, toPos.getFile(), toPos.getRank());
		board.getSquare(fromR.getArrayRank(), fromF.getArrayFile()).setPiece(takenPiece);
		System.out.println(takenPiece.isBlack());
		if(takenPiece.isBlack()) board.getBlackTakenPieces().remove(takenPieceLetter);
		if(takenPiece.isWhite()) board.getWhiteTakenPieces().remove(takenPieceLetter);
		this.movesIndex--;
		if(!userUndo) this.moves.pop();
	}
	
	public void redo(){
		this.movesIndex++;
		Move move = this.moves.get(this.movesIndex);
		Position fromPos = move.getFromPos();
		Position toPos = move.getToPos();
		forceMove(fromPos.getFile(), fromPos.getRank(), toPos.getFile(), toPos.getRank());
	}


	/**
	 * Performs steps to end the game of chess. Not currently implemented, will be in the future.
	 */
	public void endGame() {

	}

	/**
	 * 
	 * Setup for loading a game in. Not currently implemented, will be in the future.
	 * 
	 * @param file
	 * @return
	 */
	public BoardIF loadGame(String file) {
		return new Board();
	}
	
	/**
	 * Process of saving a game. Not currently implemented, will be in the future.
	 * 
	 * @param file Name of file to save game as
	 * @param game Interface of game to be saved
	 */
	public void saveGame(String file, BoardIF game) {

	}

	/**
	 * Moves piece and updates the board. If necessary, adds any taken pieces to the correct
	 * ArrayList.
	 * 
	 * @param fromF File of piece to be moved
	 * @param fromR Rank of piece to be moved
	 * @param toF File of where piece is being moved to
	 * @param toR Rank of where piece is being moved to
	 * @return True if the selected move was validate; false otherwise
	 */
	public boolean move(File fromF, Rank fromR, File toF, Rank toR) {
		Position fromPos = new Position(fromR, fromF);
		Position toPos = new Position(toR, toF);

		//Retrieves piece from current position
		boolean result = true;
		Piece piece = (Piece) board.getPiece(fromR, fromF);
		//If move is valid
		if(piece.validateMove(fromPos, toPos)){
			forceMove(fromF, fromR, toF, toR);
		}
		else{
			result = false;
		}
		return result;
	}


	public void forceMove(File fromF, Rank fromR, File toF, Rank toR){
		//Retrieves the row and column numbers from original and new position
		int fromFileNum = fromF.getArrayFile();
		int fromRankNum = fromR.getArrayRank();
		int toFileNum = toF.getArrayFile();
		int toRankNum = toR.getArrayRank();

		//Retrieves square from current position
		Square fromSquare = (Square) board.getSquare(fromRankNum, fromFileNum);
		//Retrieves squre from new position
		Square toSquare = (Square) board.getSquare(toRankNum, toFileNum);

		Piece toPiece = (Piece) toSquare.getPiece();
		if(toPiece.isWhite()){ //if white, the piece needs to be "taken" and added to ArrayList
			board.getWhiteTakenPieces().add(toPiece.getChessPieceType().getChessPieceLetter());
		}
		if(toPiece.isBlack()){ //if black, the piece needs to be "taken" and added to ArrayList
			board.getBlackTakenPieces().add(toPiece.getChessPieceType().getChessPieceLetter());
		}
		toSquare.setPiece(fromSquare.getPiece()); //put piece at new location
		fromSquare.clear(); //remove piece from it's previous position on square
	}

	/**
	 * Used in the settings to determine if the undo is usable in match or not
	 * 
	 * @param status true if can undo, false if not
	 */
	public void setUndo(boolean status){
		this.undo = status;
	}

	/**
	 * Used in the settings to determine if the showMoves is usable in match or not
	 * 
	 * @param status true if can showMoves, false if not
	 */
	public void setShowMoves(boolean status){
		this.showMoves = status;
	}

	/**
	 * Used in settings to set the color of the board
	 * 
	 * @param strat string representing new drawStrat of board
	 * @return true if drawStrat is set, false if not
	 */
	public boolean setDrawStrat(String strat){
		boolean result = true;

		if(strat.equals("mono")) this.drawStrat = new BoardMonoCLI();
		else if(strat.equals("color")) this.drawStrat = new BoardColorCLI();
		else result = false;

		return result;
	}
}

// package src.controller;

// import src.interfaces.BoardIF;
// import src.interfaces.BoardStrategy;
// import src.model.Board;
// import src.model.Piece;
// import src.model.Position;
// import src.model.Square;

// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.Scanner;

// import src.enums.File;
// import src.enums.Rank;

// /**
//  * Represents the game of chess. In the future, this class will allow users to start games,
//  * end games, save games, load games, and other operations that relate to the ChessMeister
//  * service. Currently, it includes the majority of the logic allowing users to play the match,
//  * including error handling if the user makes faulty inputs.
//  * 
//  * @author Nolan Flinchum (5%), Thomas Kay (5%), Joseph Oladeji (5%), Levi Sweat (85%)
//  * @version 3/27/2023
//  */

// public class Chess {

// 	/** The board to play chess on **/
// 	private Board board;

// 	/** The input to scan the user's input **/
// 	private Scanner input;
	
// 	/** A string representing if the user resigned **/
// 	private String resignation;
	
// 	/** A string representing the user's input for the rank **/
// 	private String stringRank;
	
// 	/** An int representing the rank of the user that parses stringRank **/
// 	private int intRank;
	
// 	/** A string representing the user's input for the file **/
// 	private String stringFile;
	
// 	/** A string representing the user's char input for the file **/
// 	private char charFile;
	
// 	/** ArrayList representing valid inputs for the chess board's file **/
// 	private ArrayList<String> fileList = new ArrayList<>();
	
// 	/** ArrayList representing valid inputs for the chess board's rank **/
// 	private ArrayList<String> rankList = new  ArrayList<>();

// 	/**
// 	 * Constructor for the game of chess. Initializes scanner, ArrayList's of valid inputs, and
// 	 * calls newGame with the user's drawStrategy to run the bulk of the program.
// 	 *
// 	 * @param BoardStrategy drawStrategy user's input for the chessboard
// 	 */
// 	public Chess(BoardStrategy drawStrategy) {
// 		this.input = new Scanner(System.in);
// 		String[] fileArray = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "a", "b", "c",
// 										  "d", "e", "f", "g", "h"}; 
// 		this.fileList.addAll(Arrays.asList(fileArray));
// 		String[] rankArray = new String[]{"1", "2", "3", "4", "5", "6", "7", "8"};
// 		this.rankList.addAll(Arrays.asList(rankArray));
// 		this.newGame(drawStrategy);
// 	}
	
// 	/**
// 	 * Asks the user a question in regards to a specific amount of answers. If the user's
// 	 * input is within the specific list of answers, then the user's input is returned to the
// 	 * program. If the user's input is not within the list, then the user is asked again until
// 	 * the method recieved the proper input.
// 	 * 
// 	 * @param String question The specific question that method is asking the user.
// 	 * @param ArrayList<String> list The list of answers that could only be accpeted in the method.
// 	 * @return The user's input if it's part of the specific amount list of answers provided to
// 	 * the method.
// 	 */
// 	public String prompt(String question, ArrayList<String> list){
// 		boolean promptAgain = true;
// 		String result = "";
// 		while(promptAgain){
// 			System.out.println(question);
// 			result = input.nextLine();
// 			//If the player's input is part of the list of answers, ends loop
// 			//Repeats otherwise
// 			if(list.contains(result)) promptAgain = false;
// 		}
// 		return result;
// 	}

// 	/**
// 	 * The turn of the current player. Checks if the player makes a move or resigns during their
// 	 * turn. Asks a prompt for the user to choose the current piece that they want to move. 
// 	 * Player one moves the white pieces while player two moves the black pieces. Checks to see 
// 	 * the player chosen a valid piece to move. If valid, asks the user to which valid position
// 	 * (which is displayed to the user) to move to. Checks if the player made a successful move and
// 	 * updates the board if a valid move is made. Player's turn ends if they successfully moved their
// 	 * piece or resigned.
// 	 * 
// 	 * @param String player The current player who's turn it is; will be changed later to
// 	 * Player object.
// 	 * @return True if the player has resigned during their turn; false otherwise.
// 	 */
// 	public boolean playTurn(String player){
// 		//Determines if the player's turn is still going on
// 		boolean keepGoing = true;
// 		//Determines if the player has resigned or not
// 		boolean resigned = false;
// 		//Checks if the piece chosen by player is one of theirs
// 		boolean pieceChecker;
// 		String color = ""; //White if Player one, black if Player two, to be printed
// 		if(player.equals("One")) color = "White";
// 		else color = "Black";
// 		while(keepGoing){
// 			System.out.println("Player " + player + "'s Turn (" + color + " Pieces)." +
// 							   " Type 'Y' to resign, or anything else to continue.");
// 			resignation = input.nextLine();
// 			//If the player doesn't resign
// 			if(!resignation.equals("Y")){
// 				//Asks player for rank for piece to move
// 				stringRank = prompt("Type the rank of the piece you'd like to move.", rankList);
// 				intRank = Integer.parseInt(stringRank);//Turn into int after correct input verified						

// 				stringFile = prompt("Type the file of the piece you'd like to move.", fileList);
// 				charFile = stringFile.trim().toUpperCase().charAt(0);//Turn into char after correct input verified		
				
// 				//Retrieves position of the chosen piece
// 				Rank fromRank = Rank.getRankByReal(intRank);
// 				File fromFile = File.getFileByChar(charFile);
// 				Position pos = new Position(fromRank, fromFile);
// 				Piece piece = (Piece) board.getPiece(fromRank, fromFile);
// 				//Sets the pieceChecker to black() or white() depending on if its player one or two
// 				if(player.equals("One")) pieceChecker = piece.isWhite();
// 				else pieceChecker = piece.isBlack();

// 				if(pieceChecker){//If piece chosen is valid
// 					//List of valid moves for the piece
// 					ArrayList<Position> aL = piece.showMoves(pos);
// 					//Displays the amount of valid moves that the player can choose from
// 					System.out.print("\nValid Moves: ");
// 					for(Position posn : aL){
// 						System.out.print("(" + posn.getRank().getRealRank() + "," + 
// 										 posn.getFile().getRealFile() + ") ");
// 					}
// 					System.out.println("\n");
// 					//Asks user for rank for new position to move to
// 					stringRank = prompt("Type the rank of the square you'd like to move to.", 
// 										rankList);
// 					intRank = Integer.parseInt(stringRank);
// 					//Asks user for file for new position to move to
// 					stringFile = prompt("Type the file of the square you'd like to move to.", fileList);
// 					charFile = stringFile.trim().toUpperCase().charAt(0);
// 					Rank toRank = Rank.getRankByReal(intRank);
// 					File toFile = File.getFileByChar(charFile);
// 					//Sets piece to be chosen position if valid
// 					if(move(fromFile, fromRank, toFile, toRank)){
// 						//Ends player's turn
// 						keepGoing = false;
// 						piece.setHasMoved(); //sets the piece to have being moved
// 					}
// 					else{
// 						//Repeats loop
// 						System.out.println("Try Again.");
// 						keepGoing = true;
// 					}
// 				}
// 			}
// 			else{
// 				//Player has resigned
// 				keepGoing = false;
// 				resigned = true;
// 			}
// 		}
// 		return resigned; //return whether or not the player has resigned
// 	}

// 	/**
// 	 * Sets up and plays a new game of chess. Initializes the board strategy to the chess board.
// 	 * Determines which player is playing and ends the match if there is a resignation.
// 	 * 
// 	 * @param BoardStrategy drawStrategy Determines how the chess board is drawn.
// 	 */
// 	public void newGame(BoardStrategy drawStrategy) {
// 		//Initializes new chess board
// 		this.board = new Board();
// 		//Initializes the board strategy in the way that
// 		this.board.setDrawStrategy(drawStrategy);
// 		boolean playing = true;
// 		int playerTurn = 0;
// 		while(playing){
// 			//Changes the current player turn
// 			if(playerTurn % 2 == 0){
// 				this.board.draw(true);
// 				if(playTurn("One")) playing = false;
// 			}
// 			else{
// 				this.board.draw(false);
// 				if(playTurn("Two")) playing = false;	
// 			}
// 			playerTurn++;
// 		}
// 		//Closes input
// 		input.close();
// 	}
	
// 	/**
// 	 * Performs steps to end the game of chess. Not currently implemented, will be in the future.
// 	 */
// 	public void endGame() {
		
// 	}
	
// 	/**
// 	 * Setup for loading a game in. Not currently implemented, will be in the future.
// 	 * 
// 	 * @param file
// 	 * @return
// 	 */
// 	public BoardIF loadGame(String file) {
// 		return new Board();
// 	}
	
// 	/**
// 	 * Process of saving a game. Not currently implemented, will be in the future.
// 	 * 
// 	 * @param file Name of file to save game as
// 	 * @param game Interface of game to be saved
// 	 */
// 	public void saveGame(String file, BoardIF game) {

// 	}

// 	/**
// 	 * Moves piece and updates the board. If necessary, adds any taken pieces to the correct
// 	 * ArrayList.
// 	 * 
// 	 * @param fromF File of piece to be moved
// 	 * @param fromR Rank of piece to be moved
// 	 * @param toF File of where piece is being moved to
// 	 * @param toR Rank of where piece is being moved to
// 	 * @return True if the selected move was validate; false otherwise
// 	 */
// 	public boolean move(File fromF, Rank fromR, File toF, Rank toR) {
// 		//Creates new position to move to
// 		Position toPos = new Position(toR, toF);

// 		//Retrieves piece from current position
// 		boolean result = true;
// 		Piece piece = (Piece) board.getPiece(fromR, fromF);
// 		//If move is valid
// 		if(piece.validateMove(toPos)){
// 			//Retrieves the row and column numbers from original and new position
// 			int fromFileNum = fromF.getArrayFile();
// 			int fromRankNum = fromR.getArrayRank();
// 			int toFileNum = toF.getArrayFile();
// 			int toRankNum = toR.getArrayRank();

// 			//Retrieves square from current position
// 			Square fromSquare = (Square) board.getSquare(fromRankNum, fromFileNum);
// 			//Retrieves squre from new position
// 			Square toSquare = (Square) board.getSquare(toRankNum, toFileNum);

// 			//Sets piece to new position and clears from original space
// 			Piece toPiece = (Piece) toSquare.getPiece();
// 			if(toPiece.isWhite()){ //if white, the piece needs to be "taken" and added to ArrayList
// 				board.getWhiteTakenPieces().add(toPiece.getChessPieceType().getChessPieceLetter());
// 			}
// 			if(toPiece.isBlack()){ //if black, the piece needs to be "taken" and added to ArrayList
// 				board.getBlackTakenPieces().add(toPiece.getChessPieceType().getChessPieceLetter());
// 			}
// 			toSquare.setPiece(fromSquare.getPiece()); //put piece at new location
// 			fromSquare.clear(); //remove piece from it's previous position on square
// 		}
// 		else{
// 			result = false;
// 		}
// 		return result;
// 	}

// }

