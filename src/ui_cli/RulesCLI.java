package src.ui_cli;
import java.util.Scanner;

import src.interfaces.RulesIF;
public class RulesCLI implements RulesIF {

    /** The input to scan the user's input **/
    private Scanner input;

    /** Constructor to the Rules Command Line Interface **/
    public RulesCLI(){
		this.input = new Scanner(System.in);
    }


    /**
     * Menu layout to navigate through the rules for playing chess. Goes on an infinite loop to accept input
     * from the user to determine which part of the rules that they want to read. The sections are divided into
     * board setup, the king's movement, the queen's movement, the bishop's movement, the knight's movement, the
     * rook's movement, the pawn's movement, and the overview of the game from 1-8. Input of 0 ends the loop.
     * 
     * @return The string of 0 when the user is finished reading over the rules.
     */
    public String displayRules(){
        String result = "";
        boolean promptAgain = true;
		while(promptAgain){
            System.out.println("\nView Rules:\n" + 
                               "============\n1: Board Setup\n2: King Moves\n3: Queen Moves\n" + 
                               "4: Bishop Moves\n5: Knight Moves\n6: Rook Moves\n7: Pawn Moves" +
                               "\n8: Overview\n0: Main Menu");			
            result = input.nextLine();
            if(result.equals("1")) boardSetup();
            if(result.equals("2")) kingMoves();
            if(result.equals("3")) queenMoves();
            if(result.equals("4")) bishopMoves();
            if(result.equals("5")) knightMoves();
            if(result.equals("6")) rookMoves();
            if(result.equals("7")) pawnMoves();
            if(result.equals("8")) overview();
			//If the player's input is part of the list of answers, ends loop
			//Repeats otherwise
			if(result.equals("0")) promptAgain = false;
		}
        return result;
    }

    /**
     * Displays the rules of how the board is prepared for a game of chess.
     */
    public void boardSetup() {
        System.out.println("\nThe game is played on an 8x8 black & white board with each player receiving 16 pieces each:");
        System.out.println(">8 pawns");
        System.out.println(">2 rooks");
        System.out.println(">2 knights");
        System.out.println(">2 bishops");
        System.out.println(">1 queen");
        System.out.println(">1 king");
        System.out.println("The white pieces are placed in ranks 1-2 while the black pieces are placed in ranks 7-8");
        System.out.println("The rooks are placed on the corners");
        System.out.println("The knights are placed next to the rooks, which the bishops are placed next to the knights");
        System.out.println("The queens are placed on the file D and the kings are placed on file E");
        System.out.println("The pawns are placed on the second ranks (the rank above/below the other pieces)\n");
        System.out.println("8\u001b[31m \u001b[47m R \u001b[40m N \u001b[47m B \u001b[40m Q \u001b[47m K \u001b[40m B \u001b[47m N \u001b[40m R \u001b[0m");
        System.out.println("7\u001b[31m \u001b[40m P \u001b[47m P \u001b[40m P \u001b[47m P \u001b[40m P \u001b[47m P \u001b[40m P \u001b[47m P \u001b[0m");
        System.out.println("6 \u001b[47m   \u001b[40m   \u001b[47m   \u001b[40m   \u001b[47m   \u001b[40m   \u001b[47m   \u001b[40m   \u001b[0m");
        System.out.println("5 \u001b[40m   \u001b[47m   \u001b[40m   \u001b[47m   \u001b[40m   \u001b[47m   \u001b[40m   \u001b[47m   \u001b[0m");
        System.out.println("4 \u001b[47m   \u001b[40m   \u001b[47m   \u001b[40m   \u001b[47m   \u001b[40m   \u001b[47m   \u001b[40m   \u001b[0m");
        System.out.println("3 \u001b[40m   \u001b[47m   \u001b[40m   \u001b[47m   \u001b[40m   \u001b[47m   \u001b[40m   \u001b[47m   \u001b[0m");
        System.out.println("2\u001b[34m \u001b[47m P \u001b[40m P \u001b[47m P \u001b[40m P \u001b[47m P \u001b[40m P \u001b[47m P \u001b[40m P \u001b[0m");
        System.out.println("1\u001b[34m \u001b[40m R \u001b[47m N \u001b[40m B \u001b[47m Q \u001b[40m K \u001b[47m B \u001b[40m N \u001b[47m R \u001b[0m");
        System.out.println("   A  B  C  D  E  F  G  H\n");
        System.out.println("\u001b[44m   \u001b[0m - White pieces");
        System.out.println("\u001b[41m   \u001b[0m - Black pieces");
        System.out.println("1-8 - Ranks");
        System.out.println("A-H - Files\n");
        System.out.println("Quick reminder: white always goes first\n");
        this.repeat();
    }
    
    /**
     * Shows the rules regarding the movement of the king piece in chess.
     */
    public void kingMoves() {
        System.out.println("\nThe king is the most important piece in the game\n");
        System.out.println("The king can move in any direction vertically, horizontally, and diagonally");
        System.out.println("However, the king could move only 1 space compared to the queen\n");
        System.out.println("\u001b[40m   \u001b[47m   \u001b[40m   \u001b[47m   \u001b[40m   \u001b[0m");
        System.out.println("\u001b[47m   \u001b[41m[ ]\u001b[41m[ ]\u001b[41m[ ]\u001b[47m   \u001b[0m");
        System.out.println("\u001b[40m   \u001b[41m[ ]\u001b[44m K \u001b[41m[ ]\u001b[40m   \u001b[0m");
        System.out.println("\u001b[47m   \u001b[41m[ ]\u001b[41m[ ]\u001b[41m[ ]\u001b[47m   \u001b[0m");
        System.out.println("\u001b[40m   \u001b[47m   \u001b[40m   \u001b[47m   \u001b[40m   \u001b[0m\n");
        System.out.println("\n\u001b[41m   \u001b[0m - Valid position");
        System.out.println("\u001b[44m   \u001b[0m - Initial position\n");
        System.out.println("Additional notes:");
        System.out.println(">The king cannot move that will makes itself into check");
        System.out.println(">The king cannot move if its path is blocked by a piece with the same color as the king or after capturing an opposing piece");
        System.out.println(">The king can capture opposing pieces\n");
        this.repeat();
    }

    /**
     * Shows the rules regarding the movement of the queen piece in chess.
     */
    public void queenMoves() {
        System.out.println("\nThe queen is the most powerful piece in the game\n");
        System.out.println("The queen can move in all possible directions vertically, horizontally, and diagonally");
        System.out.println("However, the queen cannot past through over pieces like the knight\n");
        System.out.println("\u001b[41m[ ]\u001b[47m   \u001b[41m[ ]\u001b[47m   \u001b[41m[ ]\u001b[0m");
        System.out.println("\u001b[47m   \u001b[41m[ ]\u001b[41m[ ]\u001b[41m[ ]\u001b[47m   \u001b[0m");
        System.out.println("\u001b[41m[ ]\u001b[41m[ ]\u001b[44m Q \u001b[41m[ ]\u001b[41m[ ]\u001b[0m");
        System.out.println("\u001b[47m   \u001b[41m[ ]\u001b[41m[ ]\u001b[41m[ ]\u001b[47m   \u001b[0m");
        System.out.println("\u001b[41m[ ]\u001b[47m   \u001b[41m[ ]\u001b[47m   \u001b[41m[ ]\u001b[0m\n");
        System.out.println("\n\u001b[41m   \u001b[0m - Valid position");
        System.out.println("\u001b[44m   \u001b[0m - Initial position\n");
        System.out.println("Additional notes:");
        System.out.println(">The queen cannot move if its path is blocked by a piece with the same color as the queen or after capturing an opposing piece");
        System.out.println(">Even though each side starts with one queen, another queen can be brought into the game if a pawn successfully promotes\n");
        this.repeat();
    }
    
    /**
     * Shows the rules regarding the movement of the bishop piece in chess.
     */
    public void bishopMoves() {
        System.out.println("\nThe bishop moves in an unique way to say the least\n");
        System.out.println("Like the king and queen, the bishop moves in diagonal directions");
        System.out.println("However, unlike the rook, the bishop cannot move horizontally or vertically\n");
        System.out.println("\u001b[41m[ ]\u001b[47m   \u001b[40m   \u001b[47m   \u001b[41m[ ]\u001b[0m");
        System.out.println("\u001b[47m   \u001b[41m[ ]\u001b[47m   \u001b[41m[ ]\u001b[47m   \u001b[0m");
        System.out.println("\u001b[40m   \u001b[47m   \u001b[44m B \u001b[47m   \u001b[40m   \u001b[0m");
        System.out.println("\u001b[47m   \u001b[41m[ ]\u001b[47m   \u001b[41m[ ]\u001b[47m   \u001b[0m");
        System.out.println("\u001b[41m[ ]\u001b[47m   \u001b[40m   \u001b[47m   \u001b[41m[ ]\u001b[0m\n");
        System.out.println("\n\u001b[41m   \u001b[0m - Valid position");
        System.out.println("\u001b[44m   \u001b[0m - Initial position\n");
        System.out.println("Additional notes:");
        System.out.println(">The bishop cannot move if its path is blocked by a piece with the same color as the bishop or after capturing an opposing piece");
        System.out.println(">A bishop can only move onto squares that match the color of its initial starting position\n");
        this.repeat();
    }

    /**
     * Shows the rules regarding the movement of the knight piece in chess.
     */
    public void knightMoves() {
        System.out.println("\nThe knight is 'odd' compared to the other pieces\n");
        System.out.println("Instead of moving in a straight line, the knight moves in an 'L-shape' pattern");
        System.out.println("Which has two cases:\n");
        System.out.println("Case 1:");
        System.out.println(">The knight can move two squares vertically and one square horizontally\n");
        System.out.println("Case 2:");
        System.out.println(">The knight can move two squares horizontally and one square vertically\n");
        System.out.println("\u001b[40m   \u001b[41m[ ]\u001b[40m   \u001b[41m[ ]\u001b[40m   \u001b[0m");
        System.out.println("\u001b[41m[ ]\u001b[40m   \u001b[47m   \u001b[40m   \u001b[41m[ ]\u001b[0m");
        System.out.println("\u001b[40m   \u001b[47m   \u001b[44m N \u001b[47m   \u001b[40m   \u001b[0m");
        System.out.println("\u001b[41m[ ]\u001b[40m   \u001b[47m   \u001b[40m   \u001b[41m[ ]\u001b[0m");
        System.out.println("\u001b[40m   \u001b[41m[ ]\u001b[40m   \u001b[41m[ ]\u001b[40m   \u001b[0m");
        System.out.println("\n\u001b[41m   \u001b[0m - Valid position");
        System.out.println("\u001b[44m   \u001b[0m - Initial position");
        System.out.println("\nAdditional notes:");
        System.out.println(">The knight is the only piece that can 'jump over' pieces regardless of color");
        System.out.println(">A knight beginning on a black square will always end up on a white square and vice versa");
        System.out.println(">The only invalid spaces that a knight cannot move to is one occupied by a piece with its matching color\n");
        this.repeat();
    }

    /**
     * Helper function for the rules. Allows the user to read each section of the rules at their own pace and
     * without immediately jumping back to the main menu section.
     */
    public void repeat() {
        String result = "";
        String toReturn = "0";
        while(!result.equals(toReturn)) {
            System.out.print("Press 0 to return to rules: ");
            result = input.nextLine();
        }
    }

    /**
     * Shows the rules regarding the movement of the rook piece in chess.
     */
    public void rookMoves() {
        System.out.println("\nAmong chess players, the rook is the most vital chess piece after the queen\n");
        System.out.println("The rook can move onto all unoccupied squares vertically and horizontally");
        System.out.println("The only weakness that the rook has compared to the queen is its inability to move diagonally\n");
        System.out.println("\u001b[40m   \u001b[47m   \u001b[41m[ ]\u001b[47m   \u001b[40m   \u001b[0m");
        System.out.println("\u001b[47m   \u001b[40m   \u001b[41m[ ]\u001b[40m   \u001b[47m   \u001b[0m");
        System.out.println("\u001b[41m[ ]\u001b[41m[ ]\u001b[44m R \u001b[41m[ ]\u001b[41m[ ]\u001b[0m");
        System.out.println("\u001b[47m   \u001b[40m   \u001b[41m[ ]\u001b[40m   \u001b[47m   \u001b[0m");
        System.out.println("\u001b[40m   \u001b[47m   \u001b[41m[ ]\u001b[47m   \u001b[40m   \u001b[0m\n");
        System.out.println("\u001b[41m   \u001b[0m - Valid position");
        System.out.println("\u001b[44m   \u001b[0m - Initial position\n");
        System.out.println("Additional notes:");
        System.out.println(">The rook cannot move if its path is blocked by a piece with the same color as the rook or after capturing an opposing piece\n");
        this.repeat();
    }

    /**
     * Shows the rules regarding the movement of the pawn piece in chess.
     */
    public void pawnMoves() {
        System.out.println("\nThe pawn is the most diverse piece in regards to movement\n");
        System.out.println("The pawn is also the only piece in chess that moves forward, never backwards\n");
        System.out.println("The pawn can usually only move one space forward; however, it does have some exceptions:");
        System.out.println(">A pawn can move two spaces forward from its starting position");
        System.out.println(">A pawn can move forward diagonally by capturing an opposing piece");
        System.out.println(">However, a pawn cannot move if there is another piece in front of it, which includes other pawns\n");
        System.out.println("\u001b[40m   \u001b[47m   \u001b[42m[ ]\u001b[47m   \u001b[40m   \u001b[0m");
        System.out.println("\u001b[47m   \u001b[43m[ ]\u001b[41m[ ]\u001b[43m[ ]\u001b[47m   \u001b[0m");
        System.out.println("\u001b[40m   \u001b[47m   \u001b[44m P \u001b[47m   \u001b[40m   \u001b[0m");
        System.out.println("\n\u001b[41m   \u001b[0m - Valid position");
        System.out.println("\u001b[44m   \u001b[0m - Initial position");
        System.out.println("\u001b[42m   \u001b[0m - Valid position at starting position");
        System.out.println("\u001b[43m   \u001b[0m - Valid position if capturing\n");
        System.out.println("Additional Notes:");
        System.out.println(">Due to the unique way that pawns capture, pawns can be efficent blockers against other pawns");
        System.out.println(">If the pawn is able to reach the end of its respective side, then the pawn can promote to a different piece such as a queen,");
        System.out.println(" knight, rook, or bishop\n");
        this.repeat();
    }

    /**
     * Shows an overview of the main objection of chess and the conditions that results with a game of chess
     * being finished.
     */
    public void overview() {
        System.out.println("\nThe main objection of the game is to checkmate the enemy king\n");
        System.out.println("Whenever an opposing piece is placed in the direction of the king, the king is placed in a state called check\n");
        System.out.println("An example of a situation that puts your king in check:\n");
        System.out.println("\u001b[34m\u001b[40m   \u001b[47m   \u001b[41m[K]\u001b[47m B \u001b[40m   \u001b[0m");
        System.out.println("\u001b[47m   \u001b[40m   \u001b[41m[ ]\u001b[40m   \u001b[47m   \u001b[0m");
        System.out.println("\u001b[34m\u001b[40m R \u001b[47m   \u001b[40m\u001b[31m Q \u001b[47m   \u001b[40m   \u001b[0m\n");
        System.out.println("\u001b[41m   \u001b[0m - Danger path toward King\n");
        System.out.println("When placed in check, the player only has three possible moves:");
        System.out.println(">Move the king away from incoming danger\n");
        System.out.println("\u001b[34m\u001b[40m   \u001b[47m K \u001b[40m   \u001b[47m B \u001b[40m   \u001b[0m");
        System.out.println("\u001b[47m   \u001b[40m   \u001b[47m   \u001b[40m   \u001b[47m   \u001b[0m");
        System.out.println("\u001b[34m\u001b[40m R \u001b[47m   \u001b[40m\u001b[31m Q \u001b[47m   \u001b[40m   \u001b[0m\n");
        System.out.println(">Capture the enemy piece that's putting the king in check\n");
        System.out.println("\u001b[34m\u001b[40m   \u001b[47m   \u001b[40m K \u001b[47m B \u001b[40m   \u001b[0m");
        System.out.println("\u001b[47m   \u001b[40m   \u001b[47m   \u001b[40m   \u001b[47m   \u001b[0m");
        System.out.println("\u001b[34m\u001b[40m   \u001b[47m   \u001b[40m R \u001b[47m   \u001b[40m   \u001b[0m\n");
        System.out.println(">Or have a chess piece block the incoming danger directed toward the king\n");
        System.out.println("\u001b[34m\u001b[40m   \u001b[47m   \u001b[40m K \u001b[47m   \u001b[40m   \u001b[0m");
        System.out.println("\u001b[34m\u001b[47m   \u001b[40m   \u001b[47m B \u001b[40m   \u001b[47m   \u001b[0m");
        System.out.println("\u001b[34m\u001b[40m R \u001b[47m   \u001b[40m\u001b[31m Q \u001b[47m   \u001b[40m   \u001b[0m\n");
        System.out.println("Any other moves are invalid if the king is in check");
        System.out.println("If the king's in check and there's no possible moves that could be made to get out of check, then that's checkmate\n");
        System.out.println("An example of a checkmate:\n");
        System.out.println("\u001b[31m\u001b[40m K \u001b[47m   \u001b[40m   \u001b[0m");
        System.out.println("\u001b[47m   \u001b[40m   \u001b[47m   \u001b[0m");
        System.out.println("\u001b[34m\u001b[40m R \u001b[47m Q \u001b[40m   \u001b[0m\n");
        System.out.println("In this example, checkmate is achieved by the rook makes the enemy king into check vertically and the queen makes the king");
        System.out.println("into check horizontally and diagonally with nothing that the enemy can do");
        System.out.println("\nHowever, checkmate is not the only condition that could end the game");
        System.out.println("A draw can occur if checkmate is not possible");
        System.out.println("\nOne condition that a draw can occur is a stalemate if there are no possible legal moves that could be made");
        System.out.println("By no legal moves, meaning that the king moves and make itself into check");
        System.out.println("An example of a stalemate:\n");
        System.out.println("\u001b[34m\u001b[40m K \u001b[47m   \u001b[40m   \u001b[0m");
        System.out.println("\u001b[31m\u001b[47m   \u001b[40m   \u001b[47m Q \u001b[0m\n");
        System.out.println("In this example, with the king being the only piece left, cannot move as all possible moves will bring itself ");
        System.out.println("to be checked by the enemy queen\n");
        System.out.println("Another condition that can cause a draw is if the game is over 50 turns\n");
        this.repeat();
    }
    
}
