package src.ui_cli;

import java.io.FileWriter;
import java.util.Scanner;

import src.interfaces.SaveGameIF;

public class SaveGameCLI implements SaveGameIF {
    
    private Scanner input;

    public SaveGameCLI(){
        input = new Scanner(System.in);
    }

    public String promptSaveGame(){
        System.out.print("Enter file path: ");
        return input.next();
    }
    public void saveGame(String filePath, String fileContent){
        try{
            FileWriter gameFile = new FileWriter(filePath, false);
            gameFile.write(fileContent);
            gameFile.close();
        }catch(Exception e){
            System.out.println("There was trouble saving the game instance!");
        }
    }

}
