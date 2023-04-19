package src.ui_cli;

import java.util.Scanner;

import src.interfaces.DrawByAgreementIF;

public class DrawAgreementCLI implements DrawByAgreementIF{
    public String respondToDraw(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Type Y to end the match as a draw. " +
                           "Enter anything else to continue playing");
        String input = sc.nextLine();
        sc.close();
        return input;
    }
    
}
