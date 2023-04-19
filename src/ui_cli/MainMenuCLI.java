package src.ui_cli;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import src.interfaces.MainMenuIF;

public class MainMenuCLI implements MainMenuIF{
        
    /** ArrayList representing valid inputs for the main menu **/
    private ArrayList<String> mainMenuInput = new ArrayList<>();

    /** The input to scan the user's input **/
    private Scanner input;

    /**
     * Constructor for the main menu CLI. Initializes input as user input and sets all input
     * values to for all menu options.
     */
    public MainMenuCLI(){
		this.input = new Scanner(System.in);
        this.mainMenuInput.addAll(Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7"));
    }

    public String userInteraction(){
        String prompt = "\nMain Menu:\n===========\n1: Play Chess\n2: View Rules\n" + 
        "3: Sign In\n4: Sign Up\n5: Define Players\n6: Settings\n7: Load Game\n0: Quit";
        System.out.println(prompt);
        String userInput = input.next();

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

        // MAKE SURE USERS CANNOT SIGN UP WITH THE SAME USERNAME AS PLAYER IN THE DATABASE ALREADY
        // TODO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    public String promptSignIn(FileReader database){
        boolean valid = false;
        System.out.print("Enter your username: ");
        String user = input.next();
        System.out.print("Enter your password: ");
        String password = input.next();

        Scanner dbScan = new Scanner(database);
        String line = "";
        while(dbScan.hasNextLine() && !valid){
            line = dbScan.nextLine();
            String[] userContent = line.split(":");
            if(userContent[0].equals(user) && userContent[1].equals(password)) 
                valid = true;
            else dbScan.nextLine();
        }

        dbScan.close();

        if(valid) System.out.println("Succesfully logged in as " + user);
        else System.out.println("Invalid username or password");

        return line;
    }

    public boolean updateDatabase(FileReader readDb, FileWriter writeDb, String content, 
    String location){
        int lineIndex = 0;
        Path dbPath = Paths.get(location);
        Scanner dbScan = new Scanner(readDb);
        PrintWriter printWriter = new PrintWriter(writeDb);
        List<String> lines = null;
        try {
            lines = Files.readAllLines(dbPath, StandardCharsets.UTF_8);
        } catch (IOException e) { e.printStackTrace(); }

        String[] playerContent = content.split(":");
        boolean lineStop = false;
        while(dbScan.hasNextLine() && lineStop){
            String[] userContent = dbScan.nextLine().split(":");
            if(userContent[0].equals(playerContent[0])) lineStop = true;
            lineIndex++;
            dbScan.nextLine();
        }

        lines.set(lineIndex, content);
        try {
            Files.write(dbPath, lines, StandardCharsets.UTF_8);
        } catch (IOException e) { e.printStackTrace(); }

        return true;
    }

    
}
