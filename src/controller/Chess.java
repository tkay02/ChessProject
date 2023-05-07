package src.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import src.databases.DatabaseOps;
import src.enums.ChessPieceType;
import src.enums.File;
import src.enums.Rank;
import src.interfaces.*;
import src.model.*;
import src.ui_cli.*;

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

	/** Used to determine if the user's want to end the match as a draw */
	private DrawByAgreementIF drawMatch;
	
	/** True if players can undo, false if undo is off **/
	private boolean undo;
	
	/** True if possible moves are displayed, false if not **/
	private boolean showMoves;
	
	/** Arraylist of the positions of the board. **/
	private LinkedList<Move> moves = new LinkedList<Move>(); // MOVE TO BOARD???
	
	/**Used to define the players settings*/
	private DefinePlayerCLI definePlayers;
	
	/** Used to load previous games */
	private LoadGameCLI gameLoader;
	
	/** Used to save previous games */
	private SaveGameCLI gameSaver;
	
	/** Index for the moves LinkedList. **/
	private int movesIndex;
	
	/** Current player's turn as a number */
	private int turn; 
	
	/**Players for the actual chess game*/
	private Player playerOne, playerTwo;

	/** Database object to perform database operations */
	private DatabaseOps database;
	
	/** Field representing if a player is in check or not **/
	private boolean inCheck;

	/** Determines if we need to return to main. Set to false once game is completed. **/
	private boolean returnToMain;

	/** Keeps track of the number of moves made to determine if a draw has a occured due to 50
	 *  moves being made without a piece being captured and a pawn having moved. **/
	private int fiftyMoveDraw;
	
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
		this.mainMenu = new MainMenuCLI();
		this.rulesDisplay = new RulesCLI();
		this.settingsDisplay = new SettingsCLI();
		this.showMovesDisplay = new ShowMovesCLI();
		this.definePlayers = new DefinePlayerCLI();
		this.gameLoader = new LoadGameCLI();
		this.gameSaver = new SaveGameCLI();
		this.drawMatch = new DrawAgreementCLI();
		this.undo = true; //can undo by default
		this.showMoves = true; //can showMoves by default
		this.movesIndex = -1;
		this.database = new DatabaseOps();
		this.turn = 0;
		playerOne = new Player("Player 1");
		playerTwo = new Player("Player 2");
		this.inCheck = false;
		returnToMain = true;
	}
	
	/**
	* Sets up and plays a new game of chess. Initializes the board strategy to the chess board.
	* Determines which player is playing and ends the match if there is a resignation.
	* 
	* @param BoardStrategy drawStrategy Determines how the chess board is drawn.
	*/
	public void go() {
		returnToMain = true;
		while(returnToMain){
			switch(mainMenu.userInteraction()){
				case "0":
				returnToMain = false;
				break;
				case "1":
				playGame();
				break;
				case "2":
				rulesDisplay.displayRules();
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
				loadGame(gameLoader.getFilePath());
				break;
			}
		}
	}
	
	/**
	 * Retrieves the board that the chess game uses.
	 */
	public Board getBoard() {
		return this.board;
	}
	
	/** This method is responsible for authenticating a user by prompting them
	 * for their username and password and attempting to sign them in using a Database 
	 * object. If the sign-in operation is successful, the method initializes 
	 * a new Player object with the player's information. 
	 * */
	private void signIn(){
		String[] userPass = mainMenu.promptSignIn();
		String content = database.signInOperation(userPass[0], userPass[1]);
		if(!content.isEmpty()){
			String[] playerInfo = content.split(":");
			System.out.println(content);
			playerOne = new Player(playerInfo[0], playerInfo[1], Integer.parseInt(playerInfo[2]),
			Integer.parseInt(playerInfo[3]), Integer.parseInt(playerInfo[4]));
		}
	}
	
	/**
	 * This method is responsible for signing up a user by prompting them for their
	 * username and password and attempting to sign them in using a Database object. If the sign-in
	 * operation is successful, the method initializes a new Player object with the player's information.
	 */
	private void signUp(){
		if(playerOne.getPassword() == null){
			String username = mainMenu.promptSignUp("Enter the username you would like: ");
			String password = mainMenu.promptSignUp("Enter the password you would like: ");
			playerOne.setUsername(username);
			playerOne.setPassword(password);
			database.signUpOperation(playerOne.toString());
		}
		else System.out.println("\nUser already logged in as " + playerOne.getUsername());		
	}
	
	/**
	 * This private method is responsible for prompting the user to define two players by calling
	 * the definePlayer() method of a DefinePlayers object and setting the usernames to the playerOne
	 * and playerTwo objects.
	 */
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
	
	/**
	 * Initializes a new game of chess and runs the game loop until the game is finished.
	 * Uses a PlayChessCLI object to handle user input and displays the chessboard using
	 * the board's draw strategy.
	 *
	 */
	public void playGame(){
		this.playChess = new PlayChessCLI(undo, showMoves);
		this.board.setDrawStrategy(drawStrat);
		boolean playing = true;
		ArrayList<Position> empty = new ArrayList<>();
		while(playing){
			if(turn % 2 == 0) this.board.draw(true, empty, playerOne.getUsername(),
			playerTwo.getUsername());
			else this.board.draw(false, empty, playerOne.getUsername(), 
			playerTwo.getUsername());
			if(playTurn(turn)) playing = false;
			else turn++;
		}
	}
	
	/** This method allows a player to make a turn during the chess game. It takes in the turn number as a parameter and
	 * returns a boolean value indicating whether the player has quit the game.
	 * @param turn The turn number for the game.
	 * @return A boolean value indicating whether the player has quit the game.
	 */
	public boolean playTurn(int turn){
		for(Move move : moves){
			System.out.println(move); //testing
		}
		boolean quit = false;
		boolean turnNotOver = true;
		while(turnNotOver){
			String userInput = playChess.playChessDisplay();
			switch(userInput){
				case "0": //Resign
				if(turn % 2 == 0){
					endGame(false, playerOne);
					System.out.println(playerTwo.getUsername() + " wins by Resigation.");
				}
				else{ 
					endGame(false, playerTwo);
					System.out.println(playerOne.getUsername() + " wins by Resigation.");
				}
				turnNotOver = false;
				quit = true;
				break;
				case "1": //Move
				String[] parts = playChess.makeMove();
				File fromF = File.getFileByChar(parts[0].charAt(0));
				File toF = File.getFileByChar(parts[1].charAt(0));
				Rank fromR = Rank.getRankByReal(Character.getNumericValue(parts[0].charAt(1)));
				Rank toR = Rank.getRankByReal(Character.getNumericValue(parts[1].charAt(1)));
				Piece fromPiece1 = (Piece) board.getPiece(fromR, fromF);
				if(fromPiece1.getChessPieceType() == ChessPieceType.PAWN){
					fiftyMoveDraw = 0; //resets int keeping track of 50MoveDraw if pawn is moved
				}
				if(turn % 2 == 0){
					if(!fromPiece1.isWhite()){
						System.out.println("You cannot move a piece that is not yours.");
						break;
					}
				}
				else{
					if(!fromPiece1.isBlack()){
						System.out.println("You cannot move a piece that is not yours.");
						break;
					}
				}
				if(board.getPiece(fromR, fromF).getChessPieceType() == ChessPieceType.EMPTY){
					System.out.println("Error, no piece at " + parts[0]);
				}
				else if(move(fromF, fromR, toF, toR)){
					//Removes any extra moves if the user moves after undoing
					while(this.moves.size() - 1 > this.movesIndex) this.moves.removeLast();

					//Check if the white king has any valid moves when black moves
					if(turn % 2 == 1 && checkNoValidMoves(true)){
						if(check(board.getWhiteKingPos(), true)){
							System.out.println("\t " + playerTwo.getUsername() + " wins by Checkmate!");
							endGame(false, playerOne);
						}else{
							System.out.println("\t Draw by stalemate!");
							endGame(true, playerOne);
						}
						quit = true;
					}

					//Check if the black king has any valid moves when white moves
					if(turn % 2 == 0 && checkNoValidMoves(false)){
						if(check(board.getBlackKingPos(), false)){
							endGame(false, playerTwo);
							System.out.println("\t " + playerOne.getUsername() + " wins by Checkmate!");
						}else{
							System.out.println("\t Draw by stalemate!");
							endGame(true, playerTwo);
						}
						quit = true;
					}

					//Check if a threefold repetition has occured
					if(threeFoldRepetition()){
						System.out.println("\nDraw by threefold repetition.");
						quit = true;
						endGame(true, playerOne);
					}

					//Check if the Fifty Move rule has been met
					if(fiftyMoveDraw == 50){
						System.out.println("\nDraw by 50 move rule.");
						quit = true;
						endGame(true, playerOne);
					}

					//Check if one of the player's are in check
					if(check(board.getWhiteKingPos(), true) ||
					check(board.getBlackKingPos(), false) && !quit)
					System.out.println("\t### Check! ###");
					
					turnNotOver = false;
				}
				else System.out.println("Invalid Move");
				break;
				case "2":
				if(moves.size() > 0 && movesIndex > -1){
					turnNotOver = false;
					undo(true);
				}
				else{
					System.out.println("\nUndo is unavailable right now\n");
				}
				break;
				case "3":
				if(this.movesIndex < this.moves.size() - 1){
					turnNotOver = false;
					redo();
					turn++;
				}
				else{
					System.out.println("\nRedo is unavailable right now\n");
				}
				break;
				case "4":
				showMovesDisplay.showMoves(this.board, turn, playerOne.getUsername(), 
													   playerTwo.getUsername());
				break;
				case "5":
				if(moves.size() > 0){
					String fileLocation = gameSaver.promptSaveGame();
					saveGame(fileLocation, board);
					quit = true;
					turnNotOver = false;
				}else System.out.println("\nNot enough moves made to save game.\n");				
				break;
				
				case "6": //DRAW BY AGREEMENT
				String drawChoice = drawMatch.respondToDraw();
				if(drawChoice.equals("Y")){
					System.out.println("Game ended as a draw.");
					endGame(true, playerOne);
					turnNotOver = false;
					quit = true;
				}

				break;
			}
		}
		return quit;
	}
	
	/**
	 * Returns number of moves that indictate a fifty move draw has occurred.
	 */
	public int getFiftyMove() {
		return this.fiftyMoveDraw;
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
		if(userUndo){
			Piece movedPiece = (Piece) board.getPiece(fromR, fromF);
			movedPiece.decMoveCount();
			//if pawn was moved or if piece was captured
			System.out.println("LASTMOVE: " + lastMove.getPiece().getChessPieceType());
			System.out.println("MOVEDPIECE: " + movedPiece.getChessPieceType());

			if(takenPiece.getChessPieceType() == ChessPieceType.EMPTY || 
			   movedPiece.getChessPieceType() != ChessPieceType.PAWN) fiftyMoveDraw--;
		}
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

		Piece movingPiece = (Piece) board.getPiece(toPos.getRank(), toPos.getFile());
		Piece takenPiece = (Piece) board.getPiece(fromPos.getRank(), fromPos.getFile());
		if(takenPiece.getChessPieceType() == ChessPieceType.EMPTY || 
		   movingPiece.getChessPieceType() != ChessPieceType.PAWN) fiftyMoveDraw++;

		((Piece)board.getPiece(fromPos.getRank(), fromPos.getFile())).incMoveCount();
		forceMove(fromPos.getFile(), fromPos.getRank(), toPos.getFile(), toPos.getRank());
	}
	
	/**
	 * Checks if the same board state has occured three times in a row by checking the 12 previous
	 * moves.
	 * 
	 * @return true if draw by three fold repetition, false otherwise
	 */
	public boolean threeFoldRepetition(){
		boolean draw = true;
		if(moves.size() >= 12){
			int i = moves.size() - 1;
			while(i > (moves.size() - 5) && draw){
				Move move = moves.get(i);
				if(move.equals(moves.get(i - 4)) && move.equals(moves.get(i - 8))) i--;
				else draw = false;
			}
		}else draw = false;
		return draw;
	}

	/**
	* This function checks if a player has valid moves by looking through the board and
	* checking each piece's valid moves list. If a team has no valid moves while they are in
	* check, it's checkmate. If a team has no valid moves while they are not in check, it's
	* a draw by stalemate.
	* 
	* @param isWhite true if we're looking for white pieces, false if we're looking for
	*                   black pieces
	* @return true if the player has no valid moves, false otherwise
	*/
	public boolean checkNoValidMoves(boolean isWhite){
		boolean noValidMoves = true;
		for(int i = 0; i < board.getWidth(); i++){
			for(int j = 0; j < board.getHeight(); j++){
				Piece piece = (Piece) board.getPiece(i, j);
				ChessPieceType pieceType = piece.getChessPieceType();
				if(piece.isWhite() == isWhite && pieceType != ChessPieceType.EMPTY){
					Position pos = new Position(Rank.getRankByIndex(i), File.getFileByIndex(j));
					if(!piece.showMoves(pos).isEmpty()) noValidMoves = false;
				}
			}
		}
		return noValidMoves;
	}
	
	/**
	* This function checks if a king is in check by forming an arrayList of "wanted" pieces
	* and searching around the king for the "wanted" pieces that would be a danger to the
	* king.
	* 
	* @param kingPos the position of the king
	* @param isWhite true if the king is white, false if it's black
	* @return true of the king is in check, false otherwise
	*/
	public boolean check(Position kingPos, boolean isWhite){
		this.inCheck = false;
		int row = kingPos.getRank().getArrayRank();
		int col = kingPos.getFile().getArrayFile();
		ArrayList<ChessPieceType> wantedPieces = new ArrayList<>();
		
		//search for "checking" rooks and queens
		wantedPieces.add(ChessPieceType.QUEEN);
		wantedPieces.add(ChessPieceType.ROOK);
		boolean up = true, down = true, left = true, right = true;
		for(int i = 1; i < board.getWidth(); i++){
			if(up) up = search(isWhite, row - i, col, wantedPieces);
			if(down) down = search(isWhite, row + i, col, wantedPieces);
			if(left) left = search(isWhite, row, col - i, wantedPieces);
			if(right) right = search(isWhite, row, col + i, wantedPieces);
		}
		
		//search for "checking" bishops and queens
		wantedPieces.remove(ChessPieceType.ROOK);
		wantedPieces.add(ChessPieceType.BISHOP);
		boolean upRight = true, upLeft = true, downLeft = true, downRight = true;
		for(int i = 1; i < board.getWidth(); i++){
			if(upRight) upRight = search(isWhite, row - i, col + i, wantedPieces);
			if(upLeft) upLeft = search(isWhite, row - i, col - i, wantedPieces);
			if(downLeft) downLeft = search(isWhite, row + i, col - i, wantedPieces);
			if(downRight) downRight = search(isWhite, row + i, col + i, wantedPieces);     
		}
		
		//search for "checking" knights
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
		
		//search for "checking" pawns
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
	
	/**
	 * Returns current check status.
	 */
	public boolean getCheck() {
		return this.inCheck;
	}
	
	/**
	* This helper function searches the provided square for a piece in the "wanted" list
	* that could check a queen.
	* 
	* @param isWhite true if the king is white, false otherwise
	* @param row represents the rank of the provided square
	* @param col represents the file of the provided square
	* @param wantedP list of chessPieceTypes that pose a threat to the king provided
	* @return true if the square holds an opposite color piece contained in the "wantedP" list,
	* 		   false otherwise
	*/
	private boolean search(boolean isWhite, int row, int col, ArrayList<ChessPieceType> wantedP){
		boolean continueSearch = false;
		if(row < board.getHeight() && row >= 0 && col >= 0 && col < board.getWidth()){
			Piece otherPiece = (Piece) board.getPiece(row, col);
			ChessPieceType otherType = otherPiece.getChessPieceType();
			if(otherType == ChessPieceType.EMPTY) continueSearch = true;
			else if(wantedP.contains(otherType) && otherPiece.isWhite() != isWhite) inCheck = true;
		}
		return continueSearch;
	}
	
	/**
	* Moves piece and updates the board if the move is valid. If necessary, adds any taken pieces
	* to the correct ArrayList to display taken pieces.
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
			piece.incMoveCount();
			this.movesIndex++;
			PieceIF takenPiece = board.getPiece(toR, toF); //the piece that will be captured
			if(takenPiece.getChessPieceType() != ChessPieceType.EMPTY) fiftyMoveDraw = 0;
			else if(piece.getChessPieceType() != ChessPieceType.PAWN) fiftyMoveDraw++;
			this.moves.add(new Move(fromPos, toPos, takenPiece));
			forceMove(fromF, fromR, toF, toR);
		}
		else result = false; //return false if move was invalid

		return result;
	}
	
	/**
	* Will force a piece to move regardless of whether it's valid or not.
	* 
	* @param fromF file of the piece to be moved
	* @param fromR rank of the piece to be moved
	* @param toF file of where the piece is going to
	* @param toR rank of where the piece is going to
	*/
	public void forceMove(File fromF, Rank fromR, File toF, Rank toR){
		//Retrieves the row and column numbers from original and new position
		int fromFileNum = fromF.getArrayFile();
		int fromRankNum = fromR.getArrayRank();
		int toFileNum = toF.getArrayFile();
		int toRankNum = toR.getArrayRank();
		
		Square fromSquare = (Square) board.getSquare(fromRankNum, fromFileNum);
		Square toSquare = (Square) board.getSquare(toRankNum, toFileNum);
		Piece fromPiece = (Piece) fromSquare.getPiece();
		Piece toPiece = (Piece) toSquare.getPiece();
		if(toPiece.isWhite()) //if white, the piece needs to be "taken" and added to ArrayList
		board.getWhiteTakenPieces().add(toPiece.getChessPieceType().getChessPieceLetter());
		if(toPiece.isBlack()) //if black, the piece needs to be "taken" and added to ArrayList
		board.getBlackTakenPieces().add(toPiece.getChessPieceType().getChessPieceLetter());
		toSquare.setPiece(fromSquare.getPiece()); //put piece at new location
		fromSquare.clear(); //remove piece from it's previous position on square
		
		if(fromPiece.getChessPieceType() == ChessPieceType.KING){
			if(fromPiece.isWhite()) board.setWhiteKingPos(toR, toF);
			else board.setBlackKingPos(toR, toF);
		}
	}
	
	/**
	* Will attempt to see if a move is valid by forcing the move to occur, checking if their
	* team's king is in check, and then undoing the move. If their king was in check, the move
	* is invalid.
	* 
	* @param currentPiece the piece to be moved
	* @param row number representing the rank position in the 2D array
	* @param col number representing the file position in the 2d array
	* @param fromPos the position the moving piece starts on
	* @return true if the move attempted was valid, false otherwise
	*/
	public boolean tryMove(Piece currentPiece, int row, int col, Position fromPos){
		boolean wasInCheck = false;
		if(this.inCheck) wasInCheck = true; //store if the king was already in check
		//this.inCheck = false; //assume the king isn't in check before we check new positions
		boolean valid = true;
		boolean isWhite = true;
		Rank fromRank = fromPos.getRank();
		File fromFile = fromPos.getFile();
		Rank toRank = Rank.getRankByIndex(row);
		File toFile = File.getFileByIndex(col);
		
		this.movesIndex++;
		PieceIF takenPiece = board.getPiece(toRank, toFile); //the piece that will be captured
		Position toPos = new Position(toRank, toFile);
		this.moves.add(new Move(fromPos, toPos, takenPiece)); //add move to move list
		forceMove(fromFile, fromRank, toFile, toRank); //force the move to occur
		
		Position kingPos; //find king position and king color for determining if its in check
		if(currentPiece.isWhite()) kingPos = board.getWhiteKingPos();
		else{
			kingPos = board.getBlackKingPos();
			isWhite = false;
		}
		if(check(kingPos, isWhite)) valid = false;
		
		undo(false); //use the sytem's undo to undo the move we tried
		if(wasInCheck) this.inCheck = true; //put the king back in check if it was prior
		else this.inCheck = false; //leave the king out of check it wasn't prior
		return valid;
	}
	
	/**
	* Restarts the conditions of the chessboard and updates the statuses of the players.
	*
	* @param boolean draw Boolean condition that determines if the end game ended with a draw,
	* a checkmate, or a resign. If true, the game ended with a draw; checkmate or resign otherwise.
	* @param Player loser The player that lost the game.
	*/
	public void endGame(boolean draw, Player loser){
		//Restarts board
		//returnToMain = false;
		ArrayList<Position> empty = new ArrayList<>();
		//draw the board once more
		if(turn % 2 == 0) this.board.draw(true, empty, playerOne.getUsername(),
										  playerTwo.getUsername());
		else this.board.draw(false, empty, playerOne.getUsername(), 
							 playerTwo.getUsername());		
		resetGame();
		if(draw) { 
			playerOne.addDraw();
			playerTwo.addDraw();
		}else if(loser == playerOne){
			playerOne.addLoss();
			playerTwo.addWin();
		}else{
			playerOne.addWin();
			playerTwo.addLoss();
		}
		if(playerOne.getPassword() != null) updatePlayers();
	}
	
	public void updatePlayers(){
		database.updateOperation(playerOne.toString());
	}
	
	/**
	* Setup for loading a game in. Not currently implemented, will be in the future.
	* 
	* @param file name of the file that holds the saved game
	* @return
	*/
	public void loadGame(String file) {

		// If file name is not empty, load game state from the file
		System.out.println("File: " + file);
		if(!file.isEmpty()){
			// Reset the board, turn, moves and the current movesIndex.
			resetGame();
			String fileContent = gameLoader.loadGame(file);
			String[] fileData = fileContent.split(";");
			if(fileData.length > 2){
				int moveIndex = Integer.parseInt(fileData[fileData.length - 1]);

				// Iterate over the moves in the loaded game state and play them on the board
				for(int i = 0; i < fileData.length - 2; i++){

					// Get the source and destination squares for the move
					String[] pos = fileData[i].split(":");
					if(pos.length > 1){
						File fromFile = File.getFileByChar(pos[0].charAt(0));
						Rank fromRank = Rank.getRankByReal(Character.getNumericValue(pos[0].
						charAt(1)));
						File toFile = File.getFileByChar(pos[1].charAt(0));
						Rank toRank = Rank.getRankByReal(Character.getNumericValue(pos[1].
						charAt(1)));

						// Make the move on the board and update the turn counter
						move(fromFile, fromRank, toFile, toRank);
						turn++;
					}
				}
				// Undo moves that were made up until the moveIndex
				for(int i = moves.size(); i > moveIndex + 1; i--) undo(true);
			}
		}
	}

	/**
     * Resets the game state to its initial state.
     * Creates a new Board object, sets the turn to 0, clears the moves list, and
     * sets the moves index to -1.
     */
	private void resetGame(){
		this.board = new Board(this);
		turn = 0;
		moves.clear();
		movesIndex = -1;
	}
	
	/**
	* Process of saving a game.
	* 
	* @param file name of file to save game as
	* @param game interface of game to be saved
	*/
	public void saveGame(String file, BoardIF game) {
		String fileContent = "";
		for(int i = 0; i < moves.size(); i++){
			Move move = moves.get(i);
			fileContent += "" + String.valueOf(move.getFromPos().getFile().getRealFile()) + 
			move.getFromPos().getRank().getRealRank() + ":" + 
			String.valueOf(move.getToPos().getFile().getRealFile()) + 
			move.getToPos().getRank().getRealRank() + ";";
		}
		fileContent += playerOne.getUsername() + ":" + playerTwo.getUsername();
		fileContent += ";" + movesIndex;
		
		gameSaver.saveGame(file, fileContent);
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