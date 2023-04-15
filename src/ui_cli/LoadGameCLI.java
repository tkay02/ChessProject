package src.ui_cli;

import java.io.FileReader;
import java.util.Scanner;

import src.interfaces.LoadGameIF;

public class LoadGameCLI implements LoadGameIF{
    
    private Scanner input;

    public LoadGameCLI(){
        input = new Scanner(System.in);
    }

    public void loadGame(){
        System.out.println("Enter file path: ");
        String filePath = input.next();
        
        try{
            FileReader gameFile = new FileReader(filePath);
            Scanner fileScanner = new Scanner(gameFile);
            // Scanner 
        }catch(Exception e){
            System.out.println("There was trouble loading/opening the game instance!");
        }
    }
    
}
