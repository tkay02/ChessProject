package src.ui_cli;
import java.util.Scanner;

import src.interfaces.RulesIF;
public class RulesCLI implements RulesIF {

    /** The input to scan the user's input **/
    private Scanner input;

    public RulesCLI(){
		this.input = new Scanner(System.in);
    }


    public String displayRules(){
        String result = "";
        boolean promptAgain = true;
		while(promptAgain){
            System.out.println("****PAGE IN DEVELOPMENT. PRESS 0 TO RETURN.****\nView Rules:\n" + 
                               "============\n1: Board Setup\n2: King Moves\n3: Queen Moves\n" + 
                               "4: Bishop Moves\n5: Knight Moves\n6: Rook Moves\n7: Pawn Moves" +
                               "\n8: Overview\n0: Main Menu");			
            result = input.nextLine();
			//If the player's input is part of the list of answers, ends loop
			//Repeats otherwise
			if(result.equals("0")) promptAgain = false;
		}
        return result;
    }
    
}
