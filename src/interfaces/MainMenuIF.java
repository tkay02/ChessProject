package src.interfaces;

import java.io.FileReader;

public interface MainMenuIF {
    public String userInteraction();

    public String promptSignUp(String question);

    public boolean promptSignIn(FileReader database);
}
