package src.model;
/**
 * Class that represents a player object.
 * 
 * @author Nolan Flinchum (100%), Thomas Kay, Joseph Oladeji, Levi Sweat
 * @version 3/27/2023
 */

public class Player {
    
    /** User's first name **/
    private String first;

    /** User's last name **/
    private String last;
    
    /** User's email **/
    private String email;
    
    /** User's username **/
    private String username;
    
    /** User's password **/
    private String password;
    
    /** User's number of wins **/
    private int wins;
    
    /** User's number of losses **/
    private int losses;
    
    /** User's number of draws **/
    private int draws;

     /**
     * Constructor for a player object that initializes the username and game stats.
     * This constructor doesn't initialize the player's name and email, because this could be
     * optional information to fill out in an account settings page possibly implemented in the
     * future.
     * 
     * @param userID String that holds the user's username
     */
    public Player(String username){
        this.username = username;
        this.wins = 0;
        this.losses = 0;
        this.draws = 0;
    }

    /**
     * Constructor for a player object that initializes the username, password, and game stats.
     * This constructor doesn't initialize the player's name and email, because this could be
     * optional information to fill out in an account settings page possibly implemented in the
     * future.
     * 
     * @param userID String that holds the user's username
     * @param password String that holds the user's password
     */
    public Player(String username, String password){
        this.username = username;
        this.password = password;
        this.wins = 0;
        this.losses = 0;
        this.draws = 0;
    }

     /**
     * Constructor for a player object that initializes the username, password, and game stats.
     * This constructor doesn't initialize the player's name and email, because this could be
     * optional information to fill out in an account settings page possibly implemented in the
     * future.
     * 
     * @param userID String that holds the user's username
     * @param password String that holds the user's password
     * @param wins Integer that holds the user's wins
     * @param draws Integer that holds the user's password
     * @param losses Integer that holds the user's password
     */
    public Player(String username, String password, int wins, int draws, int losses){
        this.username = username;
        this.password = password;
        this.wins = wins;
        this.losses = losses;
        this.draws = draws;
    }


    /**
     * Setter for the user's first name.
     * 
     * @param first String that holds the user's firt name
     */
    public void setFirstName(String first){
        this.first = first;
    }

    /**
     * Setter for the user's last name.
     * 
     * @param last String that holds the user's last name
     */    
    public void setLastName(String last){
        this.last = last;
    }

    /**
     * Setter for the user's email address.
     * 
     * @param email String that holds the user's email address
     */
    public void setEmail(String email){
        this.email = email;   
    }
    

     /**
     * Get the user's username
     * @return username - String that holds the user's username
     */
    public void setUsername(String username){
        this.username = username;
    }

    /**
    * Get the user's username
    * @return username - String that holds the user's username
    */
    public void setPassword(String pass){
        this.password = pass;
    }

    /**
     * Get the user's username
     * @return username - String that holds the user's username
     */
    public String getUsername(){
        return username;
    }

     /**
     * Get the user's password
     * @return password - String that holds the user's password
     */
    public String getPassword(){
        return password;
    }

    /**
     * Get the user's wins
     * @return wins - Integer that holds the user's wins
     */
    public Integer getWins(){
        return wins;
    }
    
    /**
     * Get the user's losses
     * @return losses - Integer that holds the user's losses
     */
    public Integer getLosses(){
        return losses;
    }

    /**
     * Get the user's draws
     * @return draws - Integer that holds the user's draws
     */
    public Integer getDraws(){
        return draws;
    }

    /**
     * This method will increment the designated players wins
     */
    public void addWin(){
        this.wins += 1;
    }

    /**
     * This method will increment the designated players wins
     */
    public void addLoss(){
        this.losses += 1;
    }

    /**
     * This method will increment the designated players wins
     */
    public void addDraw(){
        this.draws += 1;
    }

    /** This method will return out all the attributes of the player object as
     * a String.
     */
    public String toString(){
        return username +  ":" + password + ":" + wins + ":" + draws + ":" + losses;
    }

}
