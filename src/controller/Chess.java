package src.controller;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import src.interfaces.*;
import src.model.Board;
import src.model.Move;
import src.model.Piece;
import src.model.Player;
import src.model.Position;
import src.model.Square;
import src.ui_cli.BoardColorCLI;
import src.ui_cli.BoardMonoCLI;
import src.ui_cli.DefinePlayerCLI;
import src.ui_cli.MainMenuCLI;
import src.ui_cli.PlayChessCLI;
import src.ui_cli.RulesCLI;
import src.ui_cli.SettingsCLI;
import src.ui_cli.ShowMovesCLI;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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

	/**Used to define the players settings*/
	private DefinePlayerCLI definePlayers;

	/** Index for the moves LinkedList. **/
	private int movesIndex;

	/**Players for the actual chess game*/
	Player playerOne, playerTwo;

	/** Field for player database location*/
	private String PLAYER_DB_LOCATION = "src/databases/PlayerDatabase.txt";

	/** Field representing if a player is in check or not **/
	private boolean inCheck;

	/**
	 * Constructor for the game of chess. Initializes scanner, ArrayList's of valid inputs, and
	 * calls newGame with the user's drawStrategy to run the bulk of the program.
	 *
	 * @param BoardStrategy drawStrategy user's input for the chessboard
	 */
	public Chess(){
		this.board = new Board(this);
		drawStrat = new BoardColorCLI(); // Default BoardStrategy is color
		board.setDrawStrategy(drawStrat); 
		if(System.getProperty("os.name").startsWith("Windows"))
			PLAYER_DB_LOCATION = "src\\databases\\PlayerDatabase.txt";
		this.mainMenu = new MainMenuCLI();
		this.rulesDisplay = new RulesCLI();
		this.settingsDisplay = new SettingsCLI();
		this.showMovesDisplay = new ShowMovesCLI();
		this.definePlayers = new DefinePlayerCLI();
		this.undo = true; //can undo by default
		this.showMoves = true; //can showMoves by default
		this.moves = new LinkedList<Move>();
		this.movesIndex = -1;
		playerOne = new Player("Player 1");
		playerTwo = new Player("Player 2");
		this.inCheck = false;
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
					signIn();
					break;
				case "4":
					signUp();
					break;
				case "5":
					displayPlayerOptions();
					break;
				case "6":
					settingsInteraction();
					break;
				case "7":
					System.out.println("NOT CURRENTLY IMPLEMENTED.");
			}
		}
	}

	private void signIn(){
		FileReader playerDatabase = readerFile(PLAYER_DB_LOCATION);
		mainMenu.promptSignIn(playerDatabase);
	}

	private void signUp(){
		FileWriter playerDatabase = writerFile(PLAYER_DB_LOCATION);
		String username = mainMenu.promptSignUp("Enter the username you would like: ");
		String password = mainMenu.promptSignUp("Enter the password you would like: ");
		try {
			playerDatabase.append("#Player\n" + username + "\n" + password + "\n");
			playerDatabase.close();
		} catch (IOException e) {
			System.out.println("Unable to write player" + username + " to database");
		}

	}

	private FileWriter writerFile(String file){
		FileWriter newFile = null;

		try { newFile = new FileWriter(file, true); } 
		catch (IOException exception) { System.out.println("Unable to read " + file); }

		return newFile;
	}

	private FileReader readerFile(String file){
		FileReader newFile = null;

		try { newFile = new FileReader(file); } 
		catch (IOException exception) { System.out.println("Unable to read " + file); }

		return newFile;
	}

	private void displayPlayerOptions(){
		playerOne.setUsername(definePlayers.definePlayer(1));
		playerTwo.setUsername(definePlayers.definePlayer(2));
	}
	
	/**
	 * Calls an external UI class so that users can interact with the settings menu.
	 * Their response to the settings menu will be handled here.
	 */
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
			if(playerTurn % 2 == 0) this.board.draw(true, empty);
			else this.board.draw(false, empty);

			if(playTurn(playerTurn)) playing = false;
			playerTurn++;
		}
	}

	/**
	 * Calls an external UI class to prompt the user with the Play Chess menu. The option that
	 * the user selects while playing a game of chess are evaluated and carried out here.
	 * 
	 * @return boolean value to determine if the game is over through resignation
	 */
	public boolean playTurn(int playerTurn){
		for(Move move : moves){
			System.out.println(move); //testing
		}
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
					if(board.getPiece(fromR, fromF).getChessPieceType() == ChessPieceType.EMPTY){
						System.out.println("Error, no piece at " + parts[0]);
					}
					else if(move(fromF, fromR, toF, toR)){
						while(this.moves.size() - 1 > this.movesIndex) this.moves.pop();
						Piece movedPiece = (Piece)board.getPiece(toR, toF);
						movedPiece.setHasMoved();

						if(check(board.getWhiteKingPos(), true) || check(board.getBlackKingPos(), false)){
							System.out.println("\t### Check! ###");
						}
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
					showMovesDisplay.showMoves(this.board, playerTurn);
					break;
				case "5":
					//SAVE GAMEEE
					break;
            }
        }
		return quit;
	}

	/**
	 * This method will undo a move during the game if the user selects the option. If the user
	 * is performing the undo, the boolean in the parameter will be true, and the undone move
	 * will not be popped off of the list of moves. If the system is performing the undo, the
	 * move is popped off of the list so the user can't redo the system's move.
	 * 
	 * @param userUndo true if the user is performing undo, false otherwise
	 */
	public void undo(boolean userUndo){
		Move lastMove;
		if(userUndo) lastMove = this.moves.get(this.movesIndex);
		else lastMove = this.moves.getLast();
		Position toPos = lastMove.getFromPos();
		Position fromPos = lastMove.getToPos();
		Rank fromR = fromPos.getRank();
		File fromF = fromPos.getFile();
		Piece takenPiece = (Piece) lastMove.getPiece();
		String takenPieceLetter = takenPiece.getChessPieceType().getChessPieceLetter();
		System.out.println(takenPiece.getChessPieceType().getChessPieceLetter());
		forceMove(fromF, fromR, toPos.getFile(), toPos.getRank());
		board.getSquare(fromR.getArrayRank(), fromF.getArrayFile()).setPiece(takenPiece);
		if(takenPiece.isBlack()) board.getBlackTakenPieces().remove(takenPieceLetter);
		if(takenPiece.isWhite()) board.getWhiteTakenPieces().remove(takenPieceLetter);
		this.movesIndex--;
		if(!userUndo) this.moves.removeLast();
	}
	
	/**
	 * This method will redo a move if a move has been undone.
	 */
	public void redo(){
		this.movesIndex++;
		Move move = this.moves.get(this.movesIndex);
		Position fromPos = move.getFromPos();
		Position toPos = move.getToPos();
		forceMove(fromPos.getFile(), fromPos.getRank(), toPos.getFile(), toPos.getRank());
	}

    public boolean check(Position kingPos, boolean isWhite){
		int row = kingPos.getRank().getArrayRank();
        int col = kingPos.getFile().getArrayFile(); //how do i update king's position?
		ArrayList<ChessPieceType> wantedPieces = new ArrayList<>();

		//search for checking rooks and queens
		wantedPieces.add(ChessPieceType.QUEEN);
		wantedPieces.add(ChessPieceType.ROOK);
		boolean up = true, down = true, left = true, right = true;
		for(int i = 1; i < board.getWidth(); i++){
            if(up) up = search(isWhite, row - i, col, wantedPieces);
            if(down) down = search(isWhite, row + i, col, wantedPieces);
            if(left) left = search(isWhite, row, col - i, wantedPieces);
            if(right) right = search(isWhite, row, col + i, wantedPieces);
		}

		//search for checking bishops and queens
		wantedPieces.remove(ChessPieceType.ROOK);
		wantedPieces.add(ChessPieceType.BISHOP);
		boolean upRight = true, upLeft = true, downLeft = true, downRight = true;
        for(int i = 1; i < board.getWidth(); i++){
            if(upRight) upRight = search(isWhite, row - i, col + i, wantedPieces);
            if(upLeft) upLeft = search(isWhite, row - i, col - i, wantedPieces);
            if(downLeft) downLeft = search(isWhite, row + i, col - i, wantedPieces);
            if(downRight) downRight = search(isWhite, row + i, col + i, wantedPieces);     
        }

		//search for checking knights
		wantedPieces.clear();
		wantedPieces.add(ChessPieceType.KNIGHT);
		search(isWhite, row - 1, col - 2, wantedPieces);
        search(isWhite, row - 1, col + 2, wantedPieces);
        search(isWhite, row + 1, col - 2, wantedPieces);
        search(isWhite, row + 1, col + 2, wantedPieces);
        search(isWhite, row + 2, col + 1, wantedPieces);
        search(isWhite, row + 2, col - 1, wantedPieces);
        search(isWhite, row - 2, col + 1, wantedPieces);
        search(isWhite, row - 2, col - 1, wantedPieces);

		//search for checking pawns
		wantedPieces.clear();
		wantedPieces.add(ChessPieceType.PAWN);
		if(isWhite){
			search(isWhite, row - 1, col - 1, wantedPieces);
			search(isWhite, row - 1, col + 1, wantedPieces);
		}
		else{
			search(isWhite, row + 1, col - 1, wantedPieces);
			search(isWhite, row + 1, col + 1, wantedPieces);
		}

        return this.inCheck;
    }

	private boolean search(boolean isWhite, int row, int col, ArrayList<ChessPieceType> wantedP){
		boolean continueSearch = false;
        if(row < board.getHeight() && row >= 0 && col >= 0 && col < board.getWidth()){
			Piece otherPiece = (Piece) board.getPiece(row, col);
			if(otherPiece.getChessPieceType() == ChessPieceType.EMPTY)
				continueSearch = true;
			else if(wantedP.contains(otherPiece.getChessPieceType()) && otherPiece.isWhite() != isWhite)
				this.inCheck = true;
		}
		return continueSearch;
	}

	/**
	 * Moves piece and updates the board. If necessary, adds any taken pieces to the correct
	 * ArrayList to display taken pieces.
	 * 
	 * @param fromF File of piece to be moved
	 * @param fromR Rank of piece to be moved
	 * @param toF File of where piece is being moved to
	 * @param toR Rank of where piece is being moved to
	 * @return True if the selected move was valid, false otherwise
	 */
	public boolean move(File fromF, Rank fromR, File toF, Rank toR) {
		Position fromPos = new Position(fromR, fromF);
		Position toPos = new Position(toR, toF);
		boolean result = true;
		Piece piece = (Piece) board.getPiece(fromR, fromF); //piece from current position

		if(piece.validateMove(fromPos, toPos)){
			this.movesIndex++;
			PieceIF takenPiece = board.getPiece(toR, toF); //the piece that will be captured
			this.moves.add(new Move(fromPos, toPos, takenPiece));
			forceMove(fromF, fromR, toF, toR);
		}
		else result = false; //return false if move was invalid
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
		Piece fromPiece = (Piece) fromSquare.getPiece();
		Piece toPiece = (Piece) toSquare.getPiece();
		if(toPiece.isWhite()){ //if white, the piece needs to be "taken" and added to ArrayList
			board.getWhiteTakenPieces().add(toPiece.getChessPieceType().getChessPieceLetter());
		}
		if(toPiece.isBlack()){ //if black, the piece needs to be "taken" and added to ArrayList
			board.getBlackTakenPieces().add(toPiece.getChessPieceType().getChessPieceLetter());
		}
		toSquare.setPiece(fromSquare.getPiece()); //put piece at new location
		fromSquare.clear(); //remove piece from it's previous position on square

		if(fromPiece.getChessPieceType() == ChessPieceType.KING){
			if(fromPiece.isWhite()) board.setWhiteKingPos(toR, toF);
			else board.setBlackKingPos(toR, toF);
		}
	}

	public boolean tryMove(Piece currentPiece, int row, int col, Position fromPos){
		boolean valid = true;
        boolean isWhite = true;
        Rank fromRank = fromPos.getRank();
        File fromFile = fromPos.getFile();
        Rank toRank = Rank.getRankByIndex(row);
        File toFile = File.getFileByIndex(col);

        this.movesIndex++;
		PieceIF takenPiece = board.getPiece(toRank, toFile); //the piece that will be captured
		Position toPos = new Position(toRank, toFile);
		this.moves.add(new Move(fromPos, toPos, takenPiece));
		forceMove(fromFile, fromRank, toFile, toRank);

		//System.out.println("during tryMove, after adding it: ");
		//for(Move move : moves){
		//	System.out.println(move);
		//}

        Position kingPos;
        if(currentPiece.isWhite()) kingPos = board.getWhiteKingPos();
        else{
            kingPos = board.getBlackKingPos();
            isWhite = false;
        }
        if(check(kingPos, isWhite)){
			System.out.println("\n\nYou are in check! You can't move this piece!\n\n");
			valid = false;
		}
        undo(false);
		this.inCheck = false;
        return valid;
	}

	/**
	 * Performs steps to end the game of chess. Not currently implemented, will be in the future.
	 */
	public void endGame() {

	}

	/**
	 * Setup for loading a game in. Not currently implemented, will be in the future.
	 * 
	 * @param file name of the file that holds the saved game
	 * @return
	 */
	public BoardIF loadGame(String file) {
		return new Board(this);
	}
	
	/**
	 * Process of saving a game. Not currently implemented, will be in the future.
	 * 
	 * @param file name of file to save game as
	 * @param game interface of game to be saved
	 */
	public void saveGame(String file, BoardIF game) {

	}

	/**
	 * Used in the settings to determine if the undo is usable in match or not
	 * 
	 * @param status true if players can undo, false if not
	 */
	public void setUndo(boolean status){
		this.undo = status;
	}

	/**
	 * Used in the settings to determine if the showMoves is usable in match or not.
	 * 
	 * @param status true if players can showMoves, false if not
	 */
	public void setShowMoves(boolean status){
		this.showMoves = status;
	}

	/**
	 * Used in settings to set the color of the board.
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