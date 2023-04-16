package src.ui_cli;

import java.io.FileReader;
import java.util.Scanner;

import src.interfaces.LoadGameIF;

public class LoadGameCLI implements LoadGameIF{
    
    private Scanner input;

    public LoadGameCLI(){
        input = new Scanner(System.in);
    }

    public String getFilePath(){
        System.out.println("\nEnter file path: ");
        return input.next();
    }

    public String loadGame(String filePath){
        String fileContent = "";
        try{
            FileReader gameFile = new FileReader(filePath);
            
            Scanner fileScanner = new Scanner(gameFile);
            fileContent = fileScanner.nextLine();
            fileScanner.close();
        }catch(Exception e){
            System.out.println("There was trouble loading/opening the game instance!");
        }
        return fileContent;
    }
    
}
