package src.ui_cli;

import java.util.Scanner;
import src.interfaces.DefinePlayerIF;

public class DefinePlayerCLI implements DefinePlayerIF {

    private Scanner input;

    public DefinePlayerCLI() {
        input = new Scanner(System.in);
    }

    public String definePlayer(int playerNum){
        System.out.print("Enter Player name " + playerNum + ": ");
        return input.nextLine();
    }

}
