package src.databases;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;



public class DatabaseOps {
    /** FileWriter Object used to write data to the player database. */
    private FileWriter dbWriter;

    /** FileReader Object used to read data from the player database. */
    private FileReader dbReader; 
    
    /** Scanner object used to parse the data in the player database. */
    private Scanner parser;    

    /**a String that represents the file path of the player database */
    private String dbLocation = "src/databases/PlayerDatabase.txt";

    /** Constructor that initializes the dbReader field by creating a FileReader
     * Object with the file path specified in the dbLocation. */
    public DatabaseOps(){
        if(System.getProperty("os.name").startsWith("Windows"))
            dbLocation = "src\\databases\\PlayerDatabase.txt";
        try{
            dbReader = new FileReader(dbLocation);
        }catch(IOException e){
            System.out.println("Unable to initialize database.");
        }
    }

    /**
     * Method that takes in a String representing the content to be added to 
     * the player database. It first calls the validateContent method to ensure 
     * that the content is valid, then uses the dbWriter object to write the content
     * to the player database.
     */
    public void signUpOperation(String content){
        if(validateContent(content)){
            try {
                dbWriter = new FileWriter(dbLocation);
                dbWriter.append(content);
                dbWriter.close();
            } catch (IOException e) {
                System.out.println("Unable to write player to database");
            }
        }
    }

    public String signInOperation(String user, String pass){
        String line = "";
        parser = new Scanner(dbReader);
        boolean valid = false;
        while(parser.hasNextLine() && !valid){
            line = parser.nextLine();
            String[] userContent = line.split(":");
            if(userContent[0].equals(user) && userContent[1].equals(pass)) 
                valid = true;
        }

        parser.close();

        if(valid) System.out.println("Succesfully logged in as " + user);
        else System.out.println("Invalid username or password");

        return valid ? line : "";

    }

    public boolean validateContent(String content){
        boolean validated = true;
        String[] playerContent = content.split(":");
        parser = new Scanner(dbReader);
        while(parser.hasNext()){
            String[] otherPlayer = parser.nextLine().split(":");
            if(otherPlayer[0].equals(playerContent[0]) || otherPlayer[1].equals(playerContent[1]))
                validated = false;
        }
        if(validated) System.out.println("Succesfully logged in as " + playerContent[0]);
        else{
            System.out.println("Unable to create a player account for " + playerContent[0] + 
                " please try a different username/password.");
        }
        parser.close();
        return validated;
    }

    public void updateOperation(String content){
        int lineIndex = 0;
        Path dbPath = Paths.get(dbLocation);
        Scanner dbScan = new Scanner(dbReader);

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
        dbScan.close();
        lines.set(lineIndex, content);
        try {
            Files.write(dbPath, lines, StandardCharsets.UTF_8);
        } catch (IOException e) { e.printStackTrace(); }
    }

}
