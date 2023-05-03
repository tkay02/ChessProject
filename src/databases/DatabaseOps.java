package src.databases;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


/**
 * A class that provides various database operations for managing user information.
 * @author Nolan Flinchum (90%), Thomas Kay (10%), Joseph Oladeji (0%), Levi Sweat (0%)
 * @version 4/19/2023
 */
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
                dbWriter = new FileWriter(dbLocation, true);
                dbWriter.append("\n" + content);
                dbWriter.close();
            } catch (IOException e) {
                System.out.println("Unable to write player to database");
            }
        }
    }

    /**
     * Performs a sign-in operation using the given username and password.
     *
     * @param user - The username to sign in with.
     * @param pass - The password to sign in with.
     * @return A String representing the user information if the sign-in operation is successful,
     *  otherwise an empty String.
     */
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

    /**
     * Validates the given content for creating a new player account.
     * 
     * @param content -  The content to validate, containing the username and password separated
     *  by a colon (example: "username:password").
     * @return true if the content is valid and doesn't already exist in the database, false otherwise.
     */
    public boolean validateContent(String content){
        boolean validated = true;
        String[] playerContent = content.split(":");
        parser = new Scanner(dbReader);

        // Check if the username or password already exists in the database
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

    /**
     * Updates the user information in the database file with the given content.
     * 
     * @param content - The new content to replace the existing user information in the database.
     */
    public void updateOperation(String content){
        int lineIndex = 0;
        Path dbPath = Paths.get(dbLocation);
        Scanner dbScan = new Scanner(dbReader);

        List<String> lines = new LinkedList<>();

        // Read all the lines from the database file
        try {
            lines = Files.readAllLines(dbPath, StandardCharsets.UTF_8);
        } catch (IOException e) { e.printStackTrace(); }

        // Split the new content into username and password
        String[] playerContent = content.split(":");
        boolean lineStop = false;

        // Find the line number that matches the given username
        while(dbScan.hasNextLine() && lineStop){
            String[] userContent = dbScan.nextLine().split(":");
            if(userContent[0].equals(playerContent[0])) lineStop = true;
            lineIndex++;
            dbScan.nextLine();
        }
        dbScan.close();

        // Replace the existing line with the new content
        lines.set(lineIndex, content);

        // Write the updated lines back to the database file
        try {
            Files.write(dbPath, lines, StandardCharsets.UTF_8);
        } catch (IOException e) { e.printStackTrace(); }
    }

}
