package src.ui_cli;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import src.interfaces.MainMenuIF;

public class MainMenuCLI implements MainMenuIF{
        
    /** ArrayList representing valid inputs for the main menu **/
    private ArrayList<String> mainMenuInput = new ArrayList<>();

    /** The input to scan the user's input **/
    private Scanner input;

    public MainMenuCLI(){
		this.input = new Scanner(System.in);

        String[] menuInput = new String[]{"0", "1", "2", "3", "4", "5", "6", "7"};
        this.mainMenuInput.addAll(Arrays.asList(menuInput));
    }

    public String userInteraction(){
        String prompt = "Main Menu:\n===========\n1: Play Chess\n2: View Rules\n" + 
        "3: Sign In\n4: Sign Up\n5: Define Players\n6: Settings\n7: Load Game\n0: Quit";
        System.out.println(prompt);
        String userInput = input.nextLine();


		while(!mainMenuInput.contains(userInput)){
             System.out.println("\nIncorrect Input. Try Again.\n");
             System.out.println(prompt);
             userInput = input.nextLine();
		}
        return userInput;
    }

    public String promptSignUp(String question){
        System.out.print(question);
        String result = input.next();
        System.out.print("Are you sure (Y/N)? ");
        String affirm = input.next();
        if(!affirm.toUpperCase().equals("Y"))
            promptSignUp(question);

        return result;
    }

    public String promptSignIn(FileReader database){
        System.out.print("Enter your username: ");
        String user = input.next();
        System.out.print("Enter your password: ");
        String password = input.next();
        input.close();

        input = new Scanner(database);
        String userPass;
        while(input.hasNextLine()){
            if(input.nextLine().equals("#Player")){
                userPass = input.nextLine() + ":" + input.nextLine();
            }
        }
        return "";
    }

    
}
