package src.ui_cli;

import java.io.FileReader;
import java.util.Scanner;

import src.interfaces.SaveGameIF;

public class SaveGameCLI implements SaveGameIF {
    
    private Scanner input;

    public SaveGameCLI(){
        input = new Scanner(System.in);
    }

    public void saveGame(){
        System.out.println("Enter file path: ");
        String filePath = input.next();
        FileReader gameFile = new FileReader(filePath);
        Scanner fileScanner = new Scanner(gameFile);
        try{
            // FileHandler gameFile = new FileHandler(filePath, false);
            // Scanner 
        }catch(Exception e){

        }
    }

}
