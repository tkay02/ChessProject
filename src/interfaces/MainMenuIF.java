package src.interfaces;

import java.io.FileReader;
import java.io.FileWriter;

public interface MainMenuIF {
    public String userInteraction();

    public String promptSignUp(String question);

    public String promptSignIn(FileReader database);

    public boolean updateDatabase(FileReader readDb, FileWriter writeDb, String content, 
        String location);
}
